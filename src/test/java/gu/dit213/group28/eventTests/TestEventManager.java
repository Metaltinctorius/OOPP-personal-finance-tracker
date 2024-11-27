package gu.dit213.group28.eventTests;

import gu.dit213.group28.model.Event;
import gu.dit213.group28.model.EventManager;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEventManager
{


  EventManager manager;
  @BeforeEach
  public void setUp(){
    EventManager manager = new EventManager();
  }

  @Test
  public void testGetEventFromJson(){
    EventManager manager = new EventManager();
    List<Event> events = manager.loadEventsFromDocument();
    Assertions.assertEquals(events.getFirst().getDescription(), "Event 1");
  }
}
