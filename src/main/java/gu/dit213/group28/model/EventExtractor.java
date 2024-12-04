package gu.dit213.group28.model;

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
      }
    }
    if (e.getID() == 1) {}

    if (e.getID() == 2) {}
  }
}
