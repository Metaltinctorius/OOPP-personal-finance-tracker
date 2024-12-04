package gu.dit213.group28.eventTests;


import gu.dit213.group28.model.events.EventPredef;
import gu.dit213.group28.model.events.EventFacade;
import gu.dit213.group28.model.enums.EventType;
import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.events.EventLoader;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;


public class TestEventLoader {

  static EventFacade facade;
  private EventLoader loader;
  List <EventPredef> predefinedEvents;


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
    EventPredef event = predefinedEvents.get(1);
    Assertions.assertEquals(event.getIterations(), 0);
  }


  @Test
  public void test_iterations(){
    EventPredef event = predefinedEvents.get(0);
    int actual = event.getIterations();
    int expected = 0;
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void test_categories_successful_list(){
    EventPredef event = predefinedEvents.get(0);
    List <Sector> sectors = event.getSectorList();
    int expected = 1;
    Assertions.assertEquals(expected, sectors.size());
  }

  @Test
  public void test_categories(){
    EventPredef event = predefinedEvents.get(1);
    List <Sector> sectors = event.getSectorList();
    Assertions.assertEquals(sectors.getFirst(), Sector.HEALTHCARE);
    Assertions.assertEquals(sectors.get(1), Sector.UTILITIES);
  }

  @Test
  public void test_categories_empty(){
    EventPredef event = predefinedEvents.get(2);
    List <Sector> sectors = event.getSectorList();
    Assertions.assertTrue(sectors.isEmpty());
  }

  @BeforeEach
  public void setup(){
    facade = new EventFacade();
    loader = new EventLoader();
    loader.loadEvents();
    predefinedEvents = loader.getPredefinedEvents(); //Loads the events.
  }


}
