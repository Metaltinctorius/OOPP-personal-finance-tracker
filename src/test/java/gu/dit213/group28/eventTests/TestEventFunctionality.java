package gu.dit213.group28.eventTests;

import gu.dit213.group28.model.Event;
import gu.dit213.group28.model.EventFacade;
import gu.dit213.group28.model.events.EventLoader;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEventFunctionality {

  static EventFacade facade;
  private EventLoader loader;
  List<Event> predefinedEvents;

  @Test
  public void test_decrement_iterations(){
    Event event = predefinedEvents.get(3);
    event.decrementIterations();
    int actual = event.getIterations();
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
