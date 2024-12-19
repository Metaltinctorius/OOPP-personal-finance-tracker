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
import org.junit.jupiter.api.Test;

public class PathCreatorTest {

  @Test
  public void testPathCreator() {
    IpathCreator pathCreator = new PathCreator();
    Imodel model = new Model();
    Ipathable pathable = new GameCore(model);
    IeventFacade eventFacade = new EventFacade();

    Ievent buy = eventFacade.getBuyEvent(Sector.UTILITIES, 10);
    Ipath buyPath = pathCreator.getEventPath(pathable, buy);
    assertInstanceOf(BuyPath.class, buyPath);

    Ievent sell = eventFacade.getSellEvent(Sector.UTILITIES, 10);
    Ipath sellPath = pathCreator.getEventPath(pathable, sell);
    assertInstanceOf(SellPath.class, sellPath);

    Ievent preDef = eventFacade.getPredefinedEvent();
    Ipath preDefPath = pathCreator.getEventPath(pathable, preDef);
    assertInstanceOf(PreDefPath.class, preDefPath);

    Ievent tick = eventFacade.getTickEvent(10);
    Ipath tickPath = pathCreator.getEventPath(pathable, tick);
    assertInstanceOf(TickPath.class, tickPath);
  }
}
