package gu.dit213.group28.eventTests;


import gu.dit213.group28.model.Event;
import gu.dit213.group28.model.EventFacade;
import gu.dit213.group28.model.enums.EventType;
import gu.dit213.group28.model.enums.PlayerAction;
import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.events.EventLoader;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;


public class TestEventLoader {

  static EventFacade facade;
  private EventLoader loader;
  List <Event> predefinedEvents;


  /**
   * Just a confirmation that the eventLoader can load several events.
   */
  @Test
  public void test_loading_several(){
    int expected = 4;
    int actual = predefinedEvents.size();
    loader.viewParsedInputs();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void test_id(){
    int actualId = predefinedEvents.get(0).getId();
    int expectedId = 1;
    Assertions.assertEquals(expectedId, actualId);
  }

  @Test
  public void test_description(){
    String description = predefinedEvents.get(0).getDescription();
    String expected = "Event 1";
    Assertions.assertEquals(description, expected);
  }

  @Test
  public void test_type_once(){
    EventType type = predefinedEvents.get(0).getType();
    EventType expected = EventType.ONCE;
    Assertions.assertEquals(type, expected);
  }
  @Test
  public void test_type_repeating(){
    EventType type = predefinedEvents.get(2).getType();
    EventType expected = EventType.REPEATING;
    Assertions.assertEquals(type, expected);
  }

  @Test
  public void test_type_once_iterations_zero(){
    Event event = predefinedEvents.get(1);
    Assertions.assertEquals(event.getIterations(), 0);
  }


  @Test
  public void test_iterations(){
    Event event = predefinedEvents.get(0);
    int actual = event.getIterations();
    int expected = 0;
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void test_categories_successful_list(){
    Event event = predefinedEvents.get(0);
    List <Sector> sectors = event.getCategories();
    int expected = 2;
    Assertions.assertEquals(expected, sectors.size());
  }

  @Test
  public void test_categories(){
    Event event = predefinedEvents.get(0);
    List <Sector> sectors = event.getCategories();
    Assertions.assertEquals(sectors.getFirst(), Sector.HEALTHCARE);
    Assertions.assertEquals(sectors.get(1), Sector.HEALTHCARE);
  }

  @Test
  public void test_categories_empty(){
    Event event = predefinedEvents.get(2);
    List <Sector> sectors = event.getCategories();
    Assertions.assertTrue(sectors.isEmpty());
  }

  @Test
  public void test_no_action(){
    Event event = predefinedEvents.get(3);
    Assertions.assertNull(event.getAction());
  }

  @BeforeEach
  public void setup(){
    facade = new EventFacade();
    loader = new EventLoader();
    loader.loadEvents();
    predefinedEvents = loader.getPredefinedEvents(); //Loads the events.
  }


}
