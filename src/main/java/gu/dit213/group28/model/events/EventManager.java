package gu.dit213.group28.model.events;

import gu.dit213.group28.model.IdManager;
import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.IeventLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Manages the events in the game. This class uses composition to incorporate an IdManager for
 * managing event IDs and an EventLoader for loading events from JSON files. It maintains an event
 * log, provides access to predefined events, and supports retrieving events by ID.
 */
public class EventManager {

  /** A list of all events that have been initiated during the game's lifecycle. */
  private final List<Ievent> eventLog = new ArrayList<>();

  /** Manager for managing the identification of events. */
  private final IdManager idManager;

  /** Loader to load the events from the Json file. */
  private final IeventLoader loader;

  String testFile = "src/main/java/gu/dit213/group28/model/events/testFile.json";
  String mvpEvents = "src/main/java/gu/dit213/group28/model/events/mvpEvents.json";

  /** The List of predefined events from the JSON file. */
  private List<EventPredef> predefinedEvents;

  /**
   * Constructor for the EventManager. Initializes the predefinedEvents list and the loader. Also
   * initializes the IdManager.
   */
  public EventManager() {
    this.predefinedEvents = new ArrayList<>();
    this.loader = new EventLoader();
    this.idManager = new IdManager();
  }

  /**
   * Loads the events from the JSON file. The loader is responsible for reading the file and storing
   * the events in a list. The IdManager is also updated with the reserved IDs from the JSON file.
   *
   * @param path The path to the JSON file containing the events.
   */
  public void loadEvents(String path) {
    loader.loadEvents(path);
    predefinedEvents = loader.getPredefinedEvents();
    idManager.setReservedIds(loader.getReservedIds());
  }

  /**
   * For applying the file location outside the event manager, the loader is now more decoupled and
   * only requires a json file as a string path, instead of having the file paths locally in its
   * implementation.
   *
   * @return filepath as a string
   */
  public String getTestFile() {
    return testFile;
  }

  /**
   * Returns the event file path and saves it as a String. Used by the event facade.
   *
   * @return The event file source path as a string.
   */
  public String getEventFile() {
    return mvpEvents;
  }

  /**
   * Adds an event to the log. Exposed to the facade.
   *
   * @param event The event to add to the log.
   */
  public void addToEventLog(Ievent event) {
    eventLog.add(event);
  }

  /**
   * Returns the loaded events. Exposed to the facade.
   *
   * @return The list of predefined events.
   */
  public List<EventPredef> getPredefinedEvents() {
    return predefinedEvents;
  }

  /**
   * Returns the history (log) of events. Exposed to the facade.
   *
   * @return The list of events.
   */
  public List<Ievent> getEventLog() {
    return eventLog;
  }

  /**
   * Returns a random event picked from the predefined events. Exposed to the facade.
   *
   * @return return a predefined random event.
   */
  public EventPredef getRandomEvent() {
    return returnRandomEvent();
  }

  /**
   * Returns an event from identification. Exposed to the facade.
   *
   * @param id The id of the event to search for in the predefinedEvents list.
   * @return returns an event with the id if found.
   */
  public EventPredef getEventFromId(int id) {
    return returnEventFromId(id);
  }

  /// Private methods intended for in-class logic and mediation ///

  /**
   * Returns a random event from the list of PredefinedEvents.
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
      if (e.getId() == id) {
        return e;
      }
    }
    throw new IllegalArgumentException("Input id not matching any ids in predefined events: " + id);
  }

  /**
   * Generates a random index within the bounds of the predefined events list. This index can be
   * used to select a random event from the list.
   *
   * @return A random index between 0 (inclusive) and the size of the predefined events list
   *     (exclusive).
   * @throws IllegalArgumentException If the predefined events list is null or empty.
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
