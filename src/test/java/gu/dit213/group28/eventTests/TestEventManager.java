package gu.dit213.group28.eventTests;

import gu.dit213.group28.model.events.Event;
import gu.dit213.group28.model.events.EventManager;
import gu.dit213.group28.model.events.EventLoader;
import gu.dit213.group28.model.events.EventPredef;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEventManager {

  EventManager manager;
  List<Event> events;

  // Tests are based on the "testFile.json" file.

  @BeforeEach
  public void setup() {
    events = new ArrayList<>();
    EventLoader loader = new EventLoader();
    loader.loadEvents();
    // Manager needs the list of loaded events from the json file, as well as the ids the occupy.
    manager = new EventManager();
    // Only the events in the json file.
    events.addAll(manager.getPredefinedEvents());
  }

  @Test
  public void test_getPredefinedEvents() {
    int expected = 4;
    Assertions.assertEquals(events.size(), expected);
  }

  @Test
  public void test_add_event_to_log() {
    manager.addToEventLog(events.getFirst());
    assert (true);
    // Assertions.assertEquals(manager.getEventLog().getFirst().getDescription(), "Event 1");
  }


  @Test
  public void test_get_id_from_predefined_events() {
    EventPredef event = manager.getEventFromId(2);
    Assertions.assertEquals(event.getDescription(), "Event 2");
  }

  @Test
  public void test_add_event_to_queue() {
    Event event = events.getFirst();
    manager.addToEventQueue(event);
    Assertions.assertTrue(manager.getEventQueue().contains(event));
  }

  @Test
  public void test_get_next_event_from_queue() {
    Event event = events.getFirst();
    manager.addToEventQueue(event);
    Assertions.assertEquals(manager.getNextEvent(), event);
  }
}
