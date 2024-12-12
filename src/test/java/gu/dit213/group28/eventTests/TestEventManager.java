package gu.dit213.group28.eventTests;

import gu.dit213.group28.model.enums.EventType;
import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.events.Event;
import gu.dit213.group28.model.events.EventManager;
import gu.dit213.group28.model.events.EventLoader;
import gu.dit213.group28.model.events.EventPredef;
import gu.dit213.group28.model.interfaces.Ievent;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEventManager {

  EventManager manager;
  Ievent testEvent;
  List<EventPredef> events;

  @Test
  public void test_add_event_to_log() {
    manager.addToEventLog(testEvent);
    Assertions.assertEquals(manager.getEventLog().getFirst().getID(), 99);
  }

  @Test
  public void test_get_id_from_predefined_events() {
    Assertions.assertFalse(manager.getPredefinedEvents().isEmpty());
  }

  @Test
  public void get_event_log() {
    manager.addToEventLog(testEvent);
    Assertions.assertTrue(manager.getEventLog().contains(testEvent));
  }

  @Test
  public void get_random_event() {
    EventPredef event = manager.getRandomEvent();
    Assertions.assertTrue(manager.getPredefinedEvents().contains(event));
  }

  @Test
  public void get_event_from_id() {
    EventPredef event = manager.getEventFromId(1);
    Assertions.assertEquals(event, events.getFirst());
  }

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
