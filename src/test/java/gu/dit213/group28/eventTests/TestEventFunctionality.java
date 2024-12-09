package gu.dit213.group28.eventTests;

import gu.dit213.group28.model.events.EventFacade;
import gu.dit213.group28.model.events.EventLoader;
import gu.dit213.group28.model.events.EventPredef;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEventFunctionality {

  static EventFacade facade;
  private EventLoader loader;
  List<EventPredef> predefinedEvents;



  @BeforeEach
  public void setup(){
    facade = new EventFacade();
    loader = new EventLoader();
    loader.loadEvents();
    predefinedEvents = loader.getPredefinedEvents(); //Loads the events.
  }
}
