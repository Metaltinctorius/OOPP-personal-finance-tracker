package gu.dit213.group28.model;

import gu.dit213.group28.model.enums.EventType;
import gu.dit213.group28.model.enums.StockCategory;
import gu.dit213.group28.model.enums.TransactionType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import jdk.jfr.Category;
import netscape.javascript.JSObject;
import org.bson.Document;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class EventManager {

  /** A queue to hold the events, not sure if this should stay in GameState or here. */
  private final Queue<Event> eventQueue = new LinkedList<>();

  /** This is where the events from the json file are read and stored. */
  private final List<Event> gameEvents;

  public EventManager() {
    gameEvents = new ArrayList<>();
  }

  public void createEvent() {

    int index = getRandomIndex(gameEvents);
    Event event = gameEvents.get(index).copy();
    Event.EventBuilder builder =
        new Event.EventBuilder(event.getDescription(), event.getCategories(), event.getType());
    builder.build();
  }

  public List<Event> loadEventsFromDocument() {
    JSONParser parser = new JSONParser();
    List<Event> gameEvents = new ArrayList<>();
    try (FileReader reader = new FileReader("src/main/java/gu/dit213/group28/model/events/events.json")) {

      JSONArray jsonArray = (JSONArray) parser.parse(reader);

      for (Object obj : jsonArray) {
        JSONObject jsonObject = (JSONObject) obj;
        String description = jsonObject.get("description").toString();
        EventType type = parseType(jsonObject.get("type").toString());

        EventType type2 = EventType.valueOf(jsonObject.get("type").toString());

        List<StockCategory> categories = parseCategories((JSONArray) jsonObject.get("categories"));

        Event.EventBuilder builder = new Event.EventBuilder(description, categories, type);
        Event event = builder.build();
        gameEvents.add(event);
      }
      return gameEvents;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return gameEvents;
  }

  public List<StockCategory> parseCategories(JSONArray categoriesArray) {
    List<StockCategory> categories = new ArrayList<>();
    for (Object category : categoriesArray) {
      categories.add(StockCategory.valueOf((String) category));
    }
    return categories;
  }

  public EventType parseType(String type) {
    String field = type.toLowerCase();
    if (field.equals("once")) {
      return EventType.ONCE;
    }
    if (field.equals("repeating")) {
      return EventType.REPEATING;
    }
    if (field.equals("sequential")) {
      return EventType.SEQUENTIAL;
    } else {
      throw new IllegalArgumentException(("Not a legit event type: " + type));
    }
  }

  public static int getRandomIndex(List<Event> events) {
    if (events == null || events.isEmpty()) {
      throw new IllegalArgumentException("List must not be null or empty");
    }
    return new Random().nextInt(events.size());
  }

  public void addEvent(Event event) {
    eventQueue.add(event);
  }

  public void initiateEvent() {}

  public Event getNextEvent() {
    if (eventQueue.isEmpty()) {
      throw new IllegalStateException("No events in the event queue");
    }
    return eventQueue.poll().copy();
  }
}
