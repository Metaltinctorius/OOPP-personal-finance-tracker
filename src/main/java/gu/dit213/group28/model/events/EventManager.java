package gu.dit213.group28.model.events;

import gu.dit213.group28.model.IdManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class EventManager {

  /** A queue for scheduled events */
  private final Queue<OldEvent> eventQueue = new LinkedList<>();

  /** A list of all events that have been initiated during the game's lifecycle. */
  private final List<OldEvent> eventLog = new ArrayList<>();

  /** The List of predefined events from the JSON file. */
  private final List<OldEvent> predefinedEvents;

  private final IdManager idManager;

  public EventManager(List<OldEvent> predefinedEvents, List<Integer> reservedIdsFromLoader) {
    this.predefinedEvents = predefinedEvents;
    this.idManager = new IdManager(reservedIdsFromLoader);
  }

  public OldEvent getNextEvent() {
    if (eventQueue.isEmpty()) {
      throw new IllegalStateException("No events in the queue.");
    }
    return eventQueue.poll();
  }

  public void addToEventLog(OldEvent event) {
    eventLog.add(event);
  }

  public void addToEventQueue(OldEvent event) {
    eventQueue.add(event);
  }

  public static int generateRandomIndex(List<OldEvent> events) {
    if (events == null || events.isEmpty()) {
      throw new IllegalArgumentException("List must not be null or empty");
    }
    return new Random().nextInt(events.size());
  }

  public int generateId() {
    return idManager.getNextId();
  }

  public List<OldEvent> getPredefinedEvents() {
    return predefinedEvents;
  }

  public List<OldEvent> getEventLog() {
    return eventLog;
  }

  public Queue<OldEvent> getEventQueue() {
    return eventQueue;
  }

  public OldEvent getEventFromId(int id) {
    for (OldEvent e : predefinedEvents) {
      if (e.getId() == id) {
        return e;
      }
    }
    throw new IllegalArgumentException("Input id not matching any ids in predefined events: " + id);
  }
}
