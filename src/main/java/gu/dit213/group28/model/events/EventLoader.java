package gu.dit213.group28.model.events;

import gu.dit213.group28.model.enums.EventType;
import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.IeventLoader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class EventLoader implements IeventLoader {

  /** This is where the events from the json file are read and stored. */
  private final List<EventPredef> predefinedEvents;

  /**
   * This list is where the ids predefined in the json file are stored, this is important for the
   * idManager for in-game created events
   */
  private final List<Integer> reservedIds;

  public EventLoader() {
    predefinedEvents = new ArrayList<>();
    reservedIds = new ArrayList<>();
  }

  /** This is the method to load the events, reachable with an instance of the loader. */
  @Override
  public void loadEvents(String path) {
    readFromJsonFile(path);
  }

  /**
   * This is the method that is open for the EventLoader.
   *
   * @return returns the list of all predefined events.
   */
  @Override
  public List<EventPredef> getPredefinedEvents() {
    return predefinedEvents;
  }

  /*
   * Returns the list of reservedIds (important for in-game created events).
   */
  @Override
  public List<Integer> getReservedIds() {
    return reservedIds;
  }

  /** This method reads and parses the json file */
  private void readFromJsonFile(String file) {
    JSONParser parser = new JSONParser();

    try (FileReader reader = new FileReader(file)) {

      JSONArray jsonArray = (JSONArray) parser.parse(reader);

      for (Object obj : jsonArray) {
        JSONObject jsonObject = (JSONObject) obj;

        int id = Integer.parseInt(String.valueOf(jsonObject.get("id")));
        String title = jsonObject.get("title").toString();
        String description = jsonObject.get("description").toString();
        EventType type = EventType.valueOf(jsonObject.get("type").toString());
        int iterations = Integer.parseInt(jsonObject.get("iterations").toString());

        /* List of stocks the event holds */
        JSONArray sectorsJson = ((JSONArray) jsonObject.get("sectors"));
        List<String> sectorStrings = new ArrayList<>();
        for (Object sector : sectorsJson) {
          sectorStrings.add(sector.toString());
        }
        double modifier = Double.parseDouble(jsonObject.get("modifier").toString());

        checkArguments(id, type, iterations, sectorStrings);
        buildFromFile(id, title, description, type, iterations, sectorStrings, modifier);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Wrapper method for validating arguments. Used in the reader method before returning the
   * arguments to the builder method
   *
   * @param id int (predefined in the json file)
   * @param type eventType
   * @param iterations int iterations
   * @param sectorStrings sectors as strings
   */
  private void checkArguments(int id, EventType type, int iterations, List<String> sectorStrings) {
    validateId(id);
    validateIterations(type, iterations);
    validateSectors(sectorStrings);
  }

  /**
   * Method for checking if the id given to the event in the json file already exists.
   *
   * @param id int
   */
  private void validateId(int id) {
    if (reservedIds.contains(id)) {
      throw new IllegalArgumentException("Duplicate ID in event JSON file: " + id);
    }
  }

  /**
   * Method for checking if the event type matches the iterations. A repeating event should not have
   * iterations = 1 or 0
   *
   * @param type eventType (Once, Repeating)
   * @param iterations the amount of iterations given.
   */
  private void validateIterations(EventType type, int iterations) {
    if (type == EventType.REPEATING && iterations <= 0) {
      throw new IllegalArgumentException("Repeating events must have iterations > 0.");
    }
    if (type == EventType.ONCE && iterations != 1) {
      throw new IllegalArgumentException("One-time events must have iterations = 1.");
    }
  }

  /**
   * Method for validating the list of sectors an event has. Makes sure the sector matches actual
   * sectors in the enum "Sector".
   *
   * @param sectorStrings strings of sectors
   */
  private void validateSectors(List<String> sectorStrings) {
    for (String sector : sectorStrings) {
      try {
        Sector.valueOf(sector.toUpperCase());
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Invalid sector: " + sector, e);
      }
    }
  }

  /**
   * Receives the parameters from the reader and creates new Predefined Events, and adds them to the
   * List of PredefinedEvents
   *
   * @param id int
   * @param title string
   * @param description string
   * @param type eventType
   * @param iterations int
   * @param sectorStrings List of strings
   * @param modifier double
   */
  private void buildFromFile(
      int id,
      String title,
      String description,
      EventType type,
      int iterations,
      List<String> sectorStrings,
      double modifier) {

    List<Sector> sectors = parseSectors(sectorStrings);

    EventPredef event =
        new EventPredef(id, title, description, type, iterations, sectors, modifier);
    predefinedEvents.add(event);
    reservedIds.add(event.getID());
  }

  /**
   * Used to parse the categories array of the event.
   *
   * @param stringSectors Takes in an array of type String
   * @return Returns an array with categories of type StockCategory
   */
  private List<Sector> parseSectors(List<String> stringSectors) {
    List<Sector> sectors = new ArrayList<>();
    for (String sector : stringSectors) {
      sectors.add(Sector.valueOf(sector));
    }
    return sectors;
  }

  /** Method for viewing the parsed inputs. */
  public void viewParsedInputs() {
    for (EventPredef event : predefinedEvents) {
      System.out.println("**************************");
      System.out.println("* id:          " + event.getID());
      System.out.println("* title:       " + event.getTitle());
      System.out.println("* description: " + event.getDescription());
      System.out.println("* type:        " + event.getType());
      System.out.println("* categories:  " + event.getSectors());
      System.out.println("* modifier:    " + event.getModifier());
      System.out.println("* iterations:  " + event.getIterations());
    }
  }
}
