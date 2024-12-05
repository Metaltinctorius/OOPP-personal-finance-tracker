package gu.dit213.group28.model.events;

import gu.dit213.group28.model.enums.EventType;
import gu.dit213.group28.model.enums.Sector;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class EventLoader {

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
  public void loadEvents() {
    readFromJsonFile();
  }

  /**
   * This is the function that is open for the EventLoader.
   *
   * @return returns the list of all predefined events.
   */
  public List<EventPredef> getPredefinedEvents() {
    return predefinedEvents;
  }

  /*
   * Returns the list of reservedIds (important for in-game created events).
   */
  public List<Integer> getReservedIds() {
    return reservedIds;
  }

  String testFile = "src/main/java/gu/dit213/group28/model/events/testFile.json";

  String mvpEvents = "src/main/java/gu/dit213/group28/model/events/mvpEvents.json";
  /** Call this function to load the events from the predefined events from the JSON file */
  private void readFromJsonFile() {
    JSONParser parser = new JSONParser();

    try (FileReader reader = new FileReader(mvpEvents)) {

      JSONArray jsonArray = (JSONArray) parser.parse(reader);

      for (Object obj : jsonArray) {
        JSONObject jsonObject = (JSONObject) obj;

        int id = Integer.parseInt(String.valueOf(jsonObject.get("id")));
        String description = jsonObject.get("description").toString();
        EventType type = EventType.valueOf(jsonObject.get("type").toString());
        int iterations = Integer.parseInt(jsonObject.get("iterations").toString());

        /* List of categories the event holds */
        JSONArray categoriesJson = ((JSONArray) jsonObject.get("categories"));
        List<String> categories = new ArrayList<>();
        for (Object category : categoriesJson) {
          categories.add(category.toString());
        }
        double modifier = Double.parseDouble(jsonObject.get("modifier").toString());

        checkArguments(id, type, iterations, categories);
        buildFromFile(id, description, type, iterations, categories, modifier);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void validateId(int id) {
    if (reservedIds.contains(id)) {
      throw new IllegalArgumentException("Duplicate ID in event JSON file: " + id);
    }
  }

  private void validateIterations(EventType type, int iterations) {
    if (type == EventType.REPEATING && iterations <= 0) {
      throw new IllegalArgumentException("Repeating events must have iterations > 0.");
    }
    if (type == EventType.ONCE && iterations != 0) {
      throw new IllegalArgumentException("One-time events must have iterations = 0.");
    }
  }

  private void validateCategories(List<String> categories) {
    for (String category : categories) {
      try {
        Sector.valueOf(category);
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Invalid category: " + category, e);
      }
    }
  }

  private void checkArguments(int id, EventType type, int iterations, List<String> categories) {
    validateId(id);
    validateIterations(type, iterations);
    validateCategories(categories);
  }

  private void buildFromFile(
      int id,
      String description,
      EventType type,
      int iterations,
      List<String> categories,
      double modifier) {

    List<Sector> stockCategories = parseCategories(categories);

    EventPredef event =
        new EventPredef(id, description, type, iterations, stockCategories, modifier);
    predefinedEvents.add(event);
    reservedIds.add(event.getID());
  }

  /**
   * Used to parse the categories array of the event.
   *
   * @param stringCategories Takes in an array of type String
   * @return Returns an array with categories of type StockCategory
   */
  private List<Sector> parseCategories(List<String> stringCategories) {
    List<Sector> categories = new ArrayList<>();
    for (String category : stringCategories) {
      categories.add(Sector.valueOf(category));
    }
    return categories;
  }

  public void viewParsedInputs() {
    for (EventPredef event : predefinedEvents) {
      System.out.println("**************************");
      System.out.println("* id:          " + event.getID());
      System.out.println("* description: " + event.getDescription());
      System.out.println("* type:        " + event.getType());
      System.out.println("* categories:  " + event.getSectors());
      System.out.println("* modifier:    " + event.getModifier());
      System.out.println("* iterations:  " + event.getIterations());
    }
  }
}
