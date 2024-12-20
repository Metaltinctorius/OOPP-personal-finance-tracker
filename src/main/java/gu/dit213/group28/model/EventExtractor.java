package gu.dit213.group28.model;

import gu.dit213.group28.model.events.EventBuy;
import gu.dit213.group28.model.events.EventPredef;
import gu.dit213.group28.model.events.EventSell;
import gu.dit213.group28.model.events.EventTick;
import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.IeventExtractor;
import gu.dit213.group28.model.interfaces.Iobserver;

/** Class for extracting output from events and updating the view. */
public class EventExtractor extends Observable implements IeventExtractor {

  /** Constructor for the EventExtractor. Initializes the observer list. */
  public EventExtractor() {
    super();
  }

  /**
   * Getter for the observable. Always returns itself.
   *
   * @return this event extractor.
   */
  @Override
  public Observable getObservable() {
    return this;
  }

  /**
   * Extracts an event based on its ID and updates the view accordingly.
   *
   * @param e The Ievent to be extracted.
   */
  @Override
  public void extractEvent(Ievent e) {
    if (e.getId() == 0) {
      EventTick te = (EventTick) e;
      for (Iobserver o : observers) {

        o.updateGraphs(te.tick, te.marketOutput, te.userOutput);
        o.updateCurrency(te.getCurrency());
        o.updateProgress(te.tick, te.getIndexValue(), te.getPlayerValue());
      }
    }
    if (e.getId() == 1) {
      assert e instanceof EventBuy;
      EventBuy be = (EventBuy) e;
      for (Iobserver o : observers) {
        o.updateOwned(be.getSector(), be.getOwned(), be.getValue());
        o.updateBuyHistory(be.getSector(), be.getQuantity(), be.getValue());
      }
    }

    if (e.getId() == 2) {
      for (Iobserver o : observers) {
        o.updateOnEvent("YOU ARE BROKE, SIR");
      }
    }
    if (e.getId() == 3) {
      EventSell se = (EventSell) e;
      for (Iobserver o : observers) {
        o.updateOwned(se.getSector(), se.getOwned(), se.getValue());
        o.updateSellHistory(se.getSector(), se.getQuantity(), se.getValue());
      }
    }
    if (e.getId() == 4) {
      for (Iobserver o : observers) {
        o.updateOnEvent("YOU DON'T HAVE THOSE KINDA STOCKS BUD");
      }
    }
    if (e.getId() > 4) {
      EventPredef pre = (EventPredef) e;
      for (Iobserver o : observers) {
        o.updateOnEvent(pre.getDescription());
        o.updateEventHistory(pre.getTitle(), pre.getDescription());
      }
    }
  }

  @Override
  public void updatePause(boolean pause) {
    for (Iobserver o : observers) {
      o.updatePause(pause);
    }
  }
}
