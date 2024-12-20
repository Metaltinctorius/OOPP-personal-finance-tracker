package gu.dit213.group28.eventtests;

import gu.dit213.group28.model.enums.EventType;
import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.events.EventManager;
import gu.dit213.group28.model.events.EventPredef;
import gu.dit213.group28.model.interfaces.Ievent;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test suite for the event manager. */
public class TestEventManager {

  EventManager manager;
  Ievent testEvent;
  List<EventPredef> events;

  /** Test to add an event to the log file of passed events, maintained by the manager. */
  @Test
  public void test_add_event_to_log() {
    manager.addToEventLog(testEvent);
    Assertions.assertEquals(manager.getEventLog().getFirst().getId(), 99);
  }

  /** Test to return the event log with the added event. */
  @Test
  public void get_event_log() {
    manager.addToEventLog(testEvent);
    Assertions.assertTrue(manager.getEventLog().contains(testEvent));
  }

  /** Test to get and return a random event. */
  @Test
  public void get_random_event() {
    EventPredef event = manager.getRandomEvent();
    Assertions.assertTrue(manager.getPredefinedEvents().contains(event));
  }

  /** Test to get an event by using ID. */
  @Test
  public void get_event_from_id() {
    EventPredef event = manager.getEventFromId(1);
    Assertions.assertEquals(event, events.getFirst());
  }

  /** Setup to be run before the tests. */
  @BeforeEach
  public void setup() {
    manager = new EventManager();

    // Tests are based on the "testFile.json" file.
    manager.loadEvents(manager.getTestFile());
    events = manager.getPredefinedEvents();

    List<Sector> sectors = new ArrayList<>();
    testEvent = new EventPredef(99, "test event", "for testing", EventType.ONCE, 1, sectors, 0.2);
  }
}
