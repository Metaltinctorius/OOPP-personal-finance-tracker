package gu.dit213.group28.model;

import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.IeventExtractor;

public class EventExtractor extends Observable implements IeventExtractor {

  @Override
  public void extractEvent(Ievent e) {
    if (e.getID() == 0) {
      // update view
    }
    if (e.getID() == 1) {}

    if (e.getID() == 2) {}
  }
}
