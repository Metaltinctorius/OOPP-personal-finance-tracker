package gu.dit213.group28.eventTests;


import gu.dit213.group28.model.Event;
import gu.dit213.group28.model.EventFacade;
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



  @Test
  public void testLoadFromJson(){
    List <Event> predefinedEvents = loader.getPredefinedEvents(); //Loads the events.
    int actualId = predefinedEvents.get(0).getId();
    int expectedId = 0001;
    Assertions.assertEquals(expectedId, actualId);
  }

  @BeforeEach
  public void setup(){
    facade = new EventFacade();
    loader = new EventLoader();
  }


}
