package gu.dit213.group28.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class EventManager {

  /** A queue for scheduled events */
  private final Queue<Event> eventQueue = new LinkedList<>();

  /** A list of all events that have been initiated during the game's lifecycle. */
  private final List<Event> eventLog = new ArrayList<>();

  /** The List of predefined events from the JSON file. */
  private List<Event> predefinedEvents = new ArrayList<>();

  private final IdManager idManager;

  public EventManager(List<Event> predefinedEvents, List<Integer> reservedIdsFromLoader) {
    this.predefinedEvents = predefinedEvents;
    this.idManager = new IdManager(reservedIdsFromLoader);
  }

  public void scheduleEvent(Event event) {
    eventQueue.add(event);
    eventLog.add(event);
  }

  public Event getNextEvent() {
    if (eventQueue.isEmpty()) {
      throw new IllegalStateException("No events in the queue.");
    }
    return eventQueue.poll();
  }

  public void addToEventLog(Event event) {
    eventLog.add(event);
  }

  public void addToEventQueue(Event event) {
    eventLog.add(event);
  }

  public static int generateRandomIndex(List<Event> events) {
    if (events == null || events.isEmpty()) {
      throw new IllegalArgumentException("List must not be null or empty");
    }
    return new Random().nextInt(events.size());
  }

  public int generateId() {
    return idManager.getNextId();
  }

  public List<Event> getPredefinedEvents() {
    return predefinedEvents;
  }

  public List<Event> getEventLog() {
    return eventLog;
  }

  public Queue<Event> getEventQueue() {
    return eventQueue;
  }
}
