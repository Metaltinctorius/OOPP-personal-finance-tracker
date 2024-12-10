package gu.dit213.group28.model;

import gu.dit213.group28.model.events.EventBuy;
import gu.dit213.group28.model.events.EventPredef;
import gu.dit213.group28.model.events.EventSell;
import gu.dit213.group28.model.events.EventTick;
import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.IeventExtractor;
import gu.dit213.group28.model.interfaces.Iobserver;
import gu.dit213.group28.model.records.MarketOutput;
import java.util.ArrayList;
import java.util.List;

/** Class for extracting output from events and updating the view. */
public class EventExtractor extends Observable implements IeventExtractor {

  /**
   * Getter for the observable. Always returns itself,
   *
   * @return this event extractor
   */
  @Override
  public Observable getObservable() {
    return this;
  }

  /**
   * Extracts an event based on its ID and updates the view accordingly.
   *
   * @param e The Ievent to be extracted
   */
  @Override
  public void extractEvent(Ievent e) {
    if (e.getID() == 0) {
      EventTick te = (EventTick) e;
      for (Iobserver o : observers) {

        o.updateGraphs(te.tick, te.mOutput, te.uOutput);
        o.updateCurrency(te.getCurrency());
        o.updateProgress(te.tick, te.getIndexValue(), te.getPlayerValue());

        //o.updateGraphs(te.tick, te.mOutput, te.uOutput);
        // o.updateOnEvent("Test");
        // o.updateEventHistory("Test event history");

      }
    }
    if (e.getID() == 1) {
      assert e instanceof EventBuy;
      EventBuy be = (EventBuy) e;
      for (Iobserver o : observers) {
        o.updateOwned(be.getSector(), be.getOwned(), be.getValue());
      }
    }

    if (e.getID() == 2) {
      // send error?
    }
    if (e.getID() == 3) {
      EventSell se = (EventSell) e;
      for (Iobserver o : observers) {
        o.updateOwned(se.getSector(), se.getOwned(), se.getValue());
      }
    }
    if (e.getID() == 4) {
      // send error?
    }
    if (e.getID()>4) {
      EventPredef pre = (EventPredef) e;
      for (Iobserver o : observers) {
        o.updateOnEvent(pre.getDescription());
      }
    }
  }
}
