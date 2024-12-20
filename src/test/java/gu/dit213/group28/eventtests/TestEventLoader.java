package gu.dit213.group28.eventtests;

import gu.dit213.group28.model.enums.EventType;
import gu.dit213.group28.model.events.EventFacade;
import gu.dit213.group28.model.events.EventLoader;
import gu.dit213.group28.model.events.EventManager;
import gu.dit213.group28.model.events.EventPredef;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEventLoader {

  private EventLoader loader;
  List<EventPredef> predefinedEvents;

  @Test
  public void test_id() {
    int actualId = predefinedEvents.get(0).getId();
    int expectedId = 1;
    Assertions.assertEquals(expectedId, actualId);
  }

  @Test
  public void test_description() {
    String description = predefinedEvents.get(0).getDescription();
    String expected = "Test repeating event with 6 iterations, healthcare";
    Assertions.assertEquals(description, expected);
  }

  @Test
  public void test_title() {
    String description = predefinedEvents.get(0).getTitle();
    String expected = "TEST REPEATING HEALTHCARE";
    Assertions.assertEquals(description, expected);
  }

  @Test
  public void test_type_once() {
    EventType type = predefinedEvents.get(1).getType();
    EventType expected = EventType.ONCE;
    Assertions.assertEquals(type, expected);
  }

  @Test
  public void test_type_once_empty_sectors() {
    Assertions.assertTrue(predefinedEvents.get(2).getSectors().isEmpty());
  }

  @Test
  public void test_type_repeating() {
    EventType type = predefinedEvents.get(0).getType();
    EventType expected = EventType.REPEATING;
    Assertions.assertEquals(type, expected);
  }

  @Test
  public void test_type_once_iterations_zero() {
    EventPredef event = predefinedEvents.get(1);
    Assertions.assertEquals(event.getIterations(), 1);
  }

  @Test
  public void test_iterations() {
    EventPredef event = predefinedEvents.get(0);
    int actual = event.getIterations();
    int expected = 6;
    Assertions.assertEquals(expected, actual);
  }

  @BeforeEach
  public void setup() {
    loader = new EventLoader();
    EventManager manager = new EventManager();
    loader.loadEvents(manager.getTestFile());
    predefinedEvents = loader.getPredefinedEvents(); // Loads the events.
  }
}
