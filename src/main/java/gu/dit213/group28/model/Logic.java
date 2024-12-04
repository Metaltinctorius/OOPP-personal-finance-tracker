package gu.dit213.group28.model;

import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.IeventExtractor;
import gu.dit213.group28.model.interfaces.Ilogic;
import gu.dit213.group28.model.interfaces.Icontrollable;
import gu.dit213.group28.model.interfaces.Iobserver;
import java.util.List;

public class Logic implements Icontrollable, Ilogic {
  private final GameCore gameCore;
  private final IeventExtractor eventExtractor;

  public Logic() {
    gameCore = new GameCore(this);
    eventExtractor = new EventExtractor();
  }

  public void init() {
    gameCore.init();
  }

  @Override
  public void setSpeedFast() {
    gameCore.setSpeedFast();
  }

  @Override
  public void setSpeedNormal() {
    gameCore.setSpeedNormal();
  }

  @Override
  public void setSpeedSlow() {
    gameCore.setSpeedSlow();
  }

  public Observable getObservable() {
    return eventExtractor.getObservable();
  }

  @Override
  public void extractEvent(Ievent e) {
    eventExtractor.extractEvent(e);
  }
}
