package gu.dit213.group28.model;

import gu.dit213.group28.model.events.EventBuy;
import gu.dit213.group28.model.events.EventSell;
import gu.dit213.group28.model.events.EventTick;
import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.IeventExtractor;
import gu.dit213.group28.model.interfaces.Iobserver;

public class EventExtractor extends Observable implements IeventExtractor {

  @Override
  public Observable getObservable() {
    return this;
  }

  @Override
  public void extractEvent(Ievent e) {
    if (e.getID() == 0) {
      EventTick te = (EventTick) e;
      for (Iobserver o : observers) {
        o.updateGraphs(te.tick, te.output);
        o.updateOnEvent("Test");
        o.updateEventHistory("Test event history");
      }
    }
    if (e.getID() == 1) {
      assert e instanceof EventBuy;
      EventBuy be = (EventBuy) e;
      for (Iobserver o : observers) {
        o.updateOwned(be.getSector(), be.getOwned());
      }
    }

    if (e.getID() == 2) {
      // send error?
    }
    if (e.getID() == 3) {
      EventSell se = (EventSell) e;
      for (Iobserver o : observers) {
        o.updateOwned(se.getSector(), se.getOwned());
      }
    }
    if (e.getID() == 4) {
      // send error?
    }
  }
}
