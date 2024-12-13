package gu.dit213.group28;

import gu.dit213.group28.model.GameCore;
import gu.dit213.group28.model.Model;
import gu.dit213.group28.model.interfaces.Imodel;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

public class TestGameCore {


  Model model;
  GameCore gc;

  @Test
  public void testTriggerDecrement() throws NoSuchMethodException, NoSuchFieldException {
    gc.init();

    Field pendingEventsField = GameCore.class.getDeclaredField("pendingEvents");
    pendingEventsField.setAccessible(true);


    // Access the private method
    Method processPendingEvents = GameCore.class.getDeclaredMethod("processPendingEvents");
    processPendingEvents.setAccessible(true); // Make it accessible
  }

  @BeforeEach
  public void setup(){
    Model model  = new Model();
    GameCore gc = new GameCore(model);
  }


}
