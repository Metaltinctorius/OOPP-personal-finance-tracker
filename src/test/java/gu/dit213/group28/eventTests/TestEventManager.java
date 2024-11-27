package gu.dit213.group28.eventTests;

import gu.dit213.group28.model.Event;
import gu.dit213.group28.model.events.EventLoader;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEventManager
{


  EventLoader manager;
  @BeforeEach
  public void setUp(){
    EventLoader manager = new EventLoader();
  }

  @Test
  public void testGetEventFromJson(){
    EventLoader manager = new EventLoader();
    List<Event> events = manager.loadEventsFromDocument();
    Assertions.assertEquals(events.getFirst().getDescription(), "Event 1");
  }
}
