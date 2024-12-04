package gu.dit213.group28.model;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.enums.Speed;
import gu.dit213.group28.model.events.EventFacade;
import gu.dit213.group28.model.interfaces.*;
import gu.dit213.group28.model.market.Market;
import gu.dit213.group28.model.user.Portfolio;
import gu.dit213.group28.model.wrappers.wEventFacade;
import gu.dit213.group28.model.wrappers.wLogic;
import gu.dit213.group28.model.wrappers.wUser;

public class GameCore {

  private final Itimer timer;
  private final Ieventfacade eventFacade;
  private final Ilogic logic;
  private final Imarket market;
  private final Iuser user;
  private int tick;

  public GameCore(Logic logic) {
    timer = new Time();
    timer.initTime();
    eventFacade = new wEventFacade(new EventFacade());
    this.logic = new wLogic(logic);
    market = Market.getInstance();
    user = new wUser(new Portfolio(10000));
    tick = 0;
  }

  public void init() {
    timer.start();
    new Thread(
            () -> {
              while (true) {
                Ievent e = eventFacade.getTickEvent(tick);
                market.accept(e);
                logic.extractEvent(e);
                try {
                  timer.next();
                  tick++;
                } catch (InterruptedException ex) {
                  throw new RuntimeException(ex);
                }
              }
            })
        .start();
  }

  public void makePurchase(Sector s, int quantity) {
    Ievent e = eventFacade.getBuyEvent(s, quantity);
    market.accept(e);
    user.accept(e);
    logic.extractEvent(e);
  }

  public void makeSell(Sector s, int quantity) {
    Ievent e = eventFacade.getSellEvent(s, quantity);
    market.accept(e);
    user.accept(e);
    logic.extractEvent(e);
  }

  public void setSpeedNormal() {
    timer.setThreshold(Speed.NORMAL);
  }

  public void setSpeedSlow() {
    timer.setThreshold(Speed.SLOW);
  }

  public void setSpeedFast() {
    timer.setThreshold(Speed.FAST);
  }
}
