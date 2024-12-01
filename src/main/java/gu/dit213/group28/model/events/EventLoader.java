package gu.dit213.group28.model.events;

import gu.dit213.group28.model.Event;
import gu.dit213.group28.model.enums.EventType;
import gu.dit213.group28.model.enums.PlayerAction;
import gu.dit213.group28.model.enums.StockCategory;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class EventLoader {

  /** This is where the events from the json file are read and stored. */
  private final List<Event> predefinedEvents;

  private final List<Integer> reservedIds;

  public EventLoader() {
    predefinedEvents = new ArrayList<>();
    reservedIds = new ArrayList<>();
    loadEventsFromDocument();
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
  private void loadEventsFromDocument() {
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

        /* List of categories the event holds */
        List<StockCategory> categories = parseCategories((JSONArray) jsonObject.get("categories"));

        PlayerAction action = PlayerAction.valueOf(jsonObject.get("action").toString());

        /* Build the event */
        Event.EventBuilder builder =
            new Event.EventBuilder(id, description, categories, type, action);

        switch (type) {
          case REPEATING:
            int iterationsLeft = Integer.parseInt(String.valueOf(jsonObject.get("iterationsLeft")));
            builder.setIterations(iterationsLeft);
            break;

          case SEQUENTIAL:
            int stage = Integer.parseInt(String.valueOf(jsonObject.get("stage")));
            int totalStages = Integer.parseInt(String.valueOf(jsonObject.get("totalStages")));
            builder.setStage(stage, totalStages);
            break;

          case ONCE:
            break;

          default:
            throw new IllegalArgumentException("Unknown EventType: " + type);
        }

        Event event = builder.build().copy();

        /* Add the event to current (active) game events */
        predefinedEvents.add(event);

        reservedIds.add(event.getId());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Used to parse the categories array of the event.
   *
   * @param categoriesArray Takes in an array of type JSONArray
   * @return Returns an array with categories of type StockCategory
   */
  private List<StockCategory> parseCategories(JSONArray categoriesArray) {
    List<StockCategory> categories = new ArrayList<>();
    for (Object category : categoriesArray) {
      categories.add(StockCategory.valueOf((String) category));
    }
    return categories;
  }

  private int generateRandomIndex(List<Event> events) {
    if (events == null || events.isEmpty()) {
      throw new IllegalArgumentException("List must not be null or empty");
    }
    return new Random().nextInt(events.size());
  }
}
