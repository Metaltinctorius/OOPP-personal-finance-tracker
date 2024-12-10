package gu.dit213.group28.model.events;

import gu.dit213.group28.model.IdManager;

import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.IeventLoader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class EventManager {

  /** A queue for scheduled events */
  private final Queue<Ievent> eventQueue = new LinkedList<>();

  /** A list of all events that have been initiated during the game's lifecycle. */
  private final List<Ievent> eventLog = new ArrayList<>();

  /** The List of predefined events from the JSON file. */
  private final List<EventPredef> predefinedEvents;

  /** Manager for managing the identification of events */
  private final IdManager idManager;

  /** Loader to load the events from the Json file */
  private final IeventLoader loader;

  public EventManager() {
    this.loader = new EventLoader();
    this.predefinedEvents = loader.getPredefinedEvents();
    this.idManager = new IdManager(loader.getReservedIds());
    loader.loadEvents();
  }

  /// Top-layer methods exposed from outside the manager class ///

  /** Adds an event to the log. Exposed to the facade. */
  public void addToEventLog(Ievent event) {
    eventLog.add(event);
  }

  /** Adds an event to the queue. Exposed to the facade. */
  public void addToEventQueue(Ievent event) {
    eventQueue.add(event);
  }

  /** Returns the loaded events. Exposed to the facade. */
  public List<EventPredef> getPredefinedEvents() {
    return predefinedEvents;
  }

  /** Returns the history (log) of events. Exposed to the facade. */
  public List<Ievent> getEventLog() {
    return eventLog;
  }

  /** Returns the queue of scheduled events. Exposed to the facade. */
  public Queue<Ievent> getEventQueue() {
    return eventQueue;
  }

  /** Returns a random event picked from the predefined events. Exposed to the facade. */
  public EventPredef getRandomEvent() {
    return returnRandomEvent();
  }

  /** Returns an event from identification. Exposed to the facade. */
  public EventPredef getEventFromId(int id) {
    return returnEventFromId(id);
  }

  /** Returns the next event in the queue. Exposed to the facade. */
  public Ievent getNextEvent() {
    return returnNextEvent();
  }

  /// Private methods intended for in-class logic and mediation ///

  /**
   * When the queue holds scheduled events, this method picks the next one.
   *
   * @return Event
   */
  private Ievent returnNextEvent() {
    if (eventQueue.isEmpty()) {
      throw new IllegalStateException("No events in the queue.");
    }
    return eventQueue.poll();
  }

  /**
   * Returns a random event from the list of PredefinedEvents
   *
   * @return EventPredef
   */
  private EventPredef returnRandomEvent() {
    if (predefinedEvents.isEmpty()) {
      throw new IllegalStateException("List of predefined events is empty.");
    }
    int index = generateRandomIndex();
    return predefinedEvents.get(index);
  }

  /**
   * Used to get an event from the predefinedEvents with an id.
   *
   * @param id The id of the event to search for in the predefinedEvents list.
   * @return returns an event with the id if found.
   */
  private EventPredef returnEventFromId(int id) {
    for (EventPredef e : predefinedEvents) {
      if (e.getID() == id) {
        return e;
      }
    }
    throw new IllegalArgumentException("Input id not matching any ids in predefined events: " + id);
  }

  /**
   * Generates a random id, this is used to take a random event (random index) from the event list.
   *
   * @return int
   */
  private int generateRandomIndex() {
    if (predefinedEvents == null || predefinedEvents.isEmpty()) {
      throw new IllegalArgumentException("List must not be null or empty");
    }
    return new Random().nextInt(predefinedEvents.size());
  }

  private int generateId() {
    return idManager.getNextId();
  }
}
