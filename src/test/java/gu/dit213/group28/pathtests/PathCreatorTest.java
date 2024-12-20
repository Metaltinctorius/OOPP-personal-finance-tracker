package gu.dit213.group28.pathtests;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import gu.dit213.group28.model.GameCore;
import gu.dit213.group28.model.Model;
import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.events.EventFacade;
import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.IeventFacade;
import gu.dit213.group28.model.interfaces.Imodel;
import gu.dit213.group28.model.interfaces.Ipath;
import gu.dit213.group28.model.interfaces.IpathCreator;
import gu.dit213.group28.model.interfaces.Ipathable;
import gu.dit213.group28.model.path.BuyPath;
import gu.dit213.group28.model.path.PathCreator;
import gu.dit213.group28.model.path.PreDefPath;
import gu.dit213.group28.model.path.SellPath;
import gu.dit213.group28.model.path.TickPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Tests for IpathCreator/PatchCreator. */
public class PathCreatorTest {
  private PathCreator pathCreator;
  private Imodel model;
  private Ipathable pathable;
  private IeventFacade eventFacade;

  @BeforeEach
  public void setUp() {
    pathCreator = new PathCreator();
    model = new Model();
    pathable = new GameCore(model);
    eventFacade = new EventFacade();
  }

  /** Tests creating a BuyPath. */
  @Test
  public void testBuyPath() {
    Ievent buy = eventFacade.getBuyEvent(Sector.UTILITIES, 10);
    Ipath buyPath = pathCreator.getEventPath(pathable, buy);
    assertInstanceOf(BuyPath.class, buyPath);
  }

  /** Tests creating a SellPath. */
  @Test
  public void testSellPath() {
    Ievent sell = eventFacade.getSellEvent(Sector.UTILITIES, 10);
    Ipath sellPath = pathCreator.getEventPath(pathable, sell);
    assertInstanceOf(SellPath.class, sellPath);
  }

  /** Tests creating a TickPath. */
  @Test
  public void testTickPath() {
    Ievent tick = eventFacade.getTickEvent(10);
    Ipath tickPath = pathCreator.getEventPath(pathable, tick);
    assertInstanceOf(TickPath.class, tickPath);
  }

  /** Tests creating a PreDefPath. */
  @Test
  public void testPreDefPath() {
    Ievent preDef = eventFacade.getPredefinedEvent();
    Ipath preDefPath = pathCreator.getEventPath(pathable, preDef);
    assertInstanceOf(PreDefPath.class, preDefPath);
  }
}
