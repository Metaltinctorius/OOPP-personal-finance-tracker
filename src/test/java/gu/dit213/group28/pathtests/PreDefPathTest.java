package gu.dit213.group28.pathtests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gu.dit213.group28.model.GameCore;
import gu.dit213.group28.model.Model;
import gu.dit213.group28.model.events.EventFacade;
import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.IeventFacade;
import gu.dit213.group28.model.interfaces.Imodel;
import gu.dit213.group28.model.interfaces.Ipath;
import gu.dit213.group28.model.interfaces.Ipathable;
import gu.dit213.group28.model.path.PreDefPath;
import org.junit.jupiter.api.Test;

/**
 * Tests for PreDefPath.
 */
public class PreDefPathTest {
  /**
   * Tests that a pending PreDefPath gives the right values.
   */
  @Test
  public void pendingTest() {
    Imodel m = new Model();
    Ipathable p = new GameCore(m);
    IeventFacade ef = new EventFacade();
    Ievent e = ef.getPredefinedEvent();
    Ipath s = new PreDefPath(p, e);

    assertFalse(s.start());
    assertTrue(s.start());
  }
}
