package gu.dit213.group28.eventTests;


import gu.dit213.group28.model.Event;
import gu.dit213.group28.model.EventFacade;
import gu.dit213.group28.model.enums.PlayerAction;
import gu.dit213.group28.model.events.EventLoader;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.*;
public class TestEventLoader {

  static EventFacade facade;
  private EventLoader loader;
  List <Event> predefinedEvents;



  @Test
  public void test_loading_several(){
    int expected = 2;
    int actual = predefinedEvents.size();
    loader.viewParsedInputs();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void test_id(){
    int actualId = predefinedEvents.get(0).getId();
    int expectedId = 0001;
    Assertions.assertEquals(expectedId, actualId);
  }

  @Test
  public void test_iterations(){
    Event event = predefinedEvents.get(0);
    int actual = event.getIterationsLeft();
    int expected = 9;
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void test_decrement_iterations(){
    Event event = predefinedEvents.get(0);
    event.decrementIterations();
    int actual = event.getIterationsLeft();
    int expected = 8;
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void test_action(){
    Event event = predefinedEvents.get(0);
    PlayerAction actual = event.getAction();
    PlayerAction expected = PlayerAction.NONE;
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void test_no_action(){
    Event event = predefinedEvents.get(0);
    event.decrementIterations();
    int actual = event.getIterationsLeft();
    int expected = 8;
    Assertions.assertEquals(expected, actual);
  }

  @BeforeEach
  public void setup(){
    facade = new EventFacade();
    loader = new EventLoader();
    loader.loadEvents();
    predefinedEvents = loader.getPredefinedEvents(); //Loads the events.
  }


}
