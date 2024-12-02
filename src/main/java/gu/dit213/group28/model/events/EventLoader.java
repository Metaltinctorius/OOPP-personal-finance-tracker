package gu.dit213.group28.model.events;

import gu.dit213.group28.model.Event;
import gu.dit213.group28.model.enums.EventType;
import gu.dit213.group28.model.enums.PlayerAction;
import gu.dit213.group28.model.enums.Sector;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class EventLoader {

  /** This is where the events from the json file are read and stored. */
  private final List<Event> predefinedEvents;

  /** This list is where the ids predefined in the json file are stored */
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
  public List<Event> getPredefinedEvents() {
    return predefinedEvents;
  }

  public List<Integer> getReservedIds() {
    return reservedIds;
  }

  /**
   * Call this function to load the events from the predefined events from the JSON file
   *
   * @return Returns a list of type Event with all predefined events.
   */
  private void readFromJsonFile() {
    JSONParser parser = new JSONParser();

    try (FileReader reader =
        new FileReader("src/main/java/gu/dit213/group28/model/events/events.json")) {

      JSONArray jsonArray = (JSONArray) parser.parse(reader);

      for (Object obj : jsonArray) {
        JSONObject jsonObject = (JSONObject) obj;

        /* Reads the int id */
        int id = Integer.parseInt(String.valueOf(jsonObject.get("id")));

        /* Reads the description */
        String description = jsonObject.get("description").toString();

        /* Reads the type of event */
        EventType type = EventType.valueOf(jsonObject.get("type").toString());

        int iterations = Integer.parseInt(jsonObject.get("iterations").toString());

        /* List of categories the event holds */
        JSONArray categoriesJson = ((JSONArray) jsonObject.get("categories"));
        List<String> categories = new ArrayList<>();
        for (Object category : categoriesJson) {
          categories.add(category.toString());
        }

        PlayerAction action = null;
        if (jsonObject.containsKey("action")) {
          action = PlayerAction.valueOf(jsonObject.get("action").toString());
        }

        buildFromFile(id, description, type, iterations, categories, action);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void buildFromFile(
      int id,
      String description,
      EventType type,
      int iterations,
      List<String> categories,
      PlayerAction action) {

    /*
     * Extract a list of Sector categories from the list of String arguments.
     */
    List<Sector> stockCategories = parseCategories(categories);

    /* Build the event */
    Event.EventBuilder builder = new Event.EventBuilder(id, description, stockCategories, type);
    builder.setIterations(iterations);

    if (action != null) {
      builder.setPlayerAction(action);
    }

    Event event = builder.build().copy();

    /* Add the event to current (active) game events */
    predefinedEvents.add(event);

    reservedIds.add(event.getId());
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

  public void viewParsedInputs(){
    for(Event event : predefinedEvents){
      System.out.println("**************************");
      System.out.println("* "+ event.getId());
      System.out.println("* "+ event.getDescription());
      System.out.println("* "+ event.getType());
      System.out.println("* "+ event.getIterationsLeft());
      System.out.println("* "+ event.getAction());
    }
  }
}
