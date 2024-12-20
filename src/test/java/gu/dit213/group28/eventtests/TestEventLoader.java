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

/**
 * Test suite for the event loader.
 */
public class TestEventLoader {

  private EventLoader loader;
  List<EventPredef> predefinedEvents;

  /**
   * Test that the id of the loaded event is the same as the json file specified id.
   */
  @Test
  public void test_id() {
    int actualId = predefinedEvents.get(0).getId();
    int expectedId = 1;
    Assertions.assertEquals(expectedId, actualId);
  }

  /**
   * Test that the description of the loaded event is the same as the json file specified.
   */
  @Test
  public void test_description() {
    String description = predefinedEvents.get(0).getDescription();
    String expected = "Test repeating event with 6 iterations, healthcare";
    Assertions.assertEquals(description, expected);
  }

  /**
   * Test that the title of the loaded event is the same as the json file specified.
   */
  @Test
  public void test_title() {
    String description = predefinedEvents.get(0).getTitle();
    String expected = "TEST REPEATING HEALTHCARE";
    Assertions.assertEquals(description, expected);
  }

  /**
   * Test that the type of the loaded event is the same as the json file specified.
   */
  @Test
  public void test_type_once() {
    EventType type = predefinedEvents.get(1).getType();
    EventType expected = EventType.ONCE;
    Assertions.assertEquals(type, expected);
  }

  /**
   * Test that an event with empty list of sectors is actually empty.
   */
  @Test
  public void test_type_once_empty_sectors() {
    Assertions.assertTrue(predefinedEvents.get(2).getSectors().isEmpty());
  }

  /**
   * Test that the type is correctly read by the loader.
   */
  @Test
  public void test_type_repeating() {
    EventType type = predefinedEvents.get(0).getType();
    EventType expected = EventType.REPEATING;
    Assertions.assertEquals(type, expected);
  }

  /**
   * Test that the iterations are correctly read.
   */
  @Test
  public void test_iterations() {
    EventPredef event = predefinedEvents.get(0);
    int actual = event.getIterations();
    int expected = 6;
    Assertions.assertEquals(expected, actual);
  }

  /**
   * Setup for the tests.
   */
  @BeforeEach
  public void setup() {
    loader = new EventLoader();
    EventManager manager = new EventManager();
    loader.loadEvents(manager.getTestFile());
    predefinedEvents = loader.getPredefinedEvents();
  }
}
