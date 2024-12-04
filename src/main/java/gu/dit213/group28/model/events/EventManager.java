package gu.dit213.group28.model.events;

import gu.dit213.group28.model.IdManager;

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
  private final List<EventPredef> predefinedEvents;

  private final IdManager idManager;

  public EventManager(List<EventPredef> predefinedEvents, List<Integer> reservedIdsFromLoader) {
    this.predefinedEvents = predefinedEvents;
    this.idManager = new IdManager(reservedIdsFromLoader);
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
    eventQueue.add(event);
  }

  public static int generateRandomIndex(List<EventPredef> events) {
    if (events == null || events.isEmpty()) {
      throw new IllegalArgumentException("List must not be null or empty");
    }
    return new Random().nextInt(events.size());
  }

  public int generateId() {
    return idManager.getNextId();
  }

  public List<EventPredef> getPredefinedEvents() {
    return predefinedEvents;
  }

  public List<Event> getEventLog() {
    return eventLog;
  }

  public Queue<Event> getEventQueue() {
    return eventQueue;
  }

  /**
   * Returns a random event from the list of PredefinedEvents
   *
   * @return
   */
  public EventPredef getRandomEvent() {
    if (predefinedEvents.isEmpty()) {
      throw new IllegalStateException("List of predefined events is empty.");
    }
    int index = generateRandomIndex(predefinedEvents);
    return predefinedEvents.get(index);
  }

  /**
   * Used to get an event from the predefinedEvents with an id.
   *
   * @param id
   * @return
   */
  public EventPredef getEventFromId(int id) {
    for (EventPredef e : predefinedEvents) {
      if (e.getID() == id) {
        return e;
      }
    }
    throw new IllegalArgumentException("Input id not matching any ids in predefined events: " + id);
  }
}
