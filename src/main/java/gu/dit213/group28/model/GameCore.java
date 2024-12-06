package gu.dit213.group28.model;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.enums.Speed;
import gu.dit213.group28.model.events.EventFacade;
import gu.dit213.group28.model.events.EventPredef;
import gu.dit213.group28.model.interfaces.*;
import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.market.Market;
import gu.dit213.group28.model.user.Portfolio;
import gu.dit213.group28.model.wrappers.wEventFacade;
import gu.dit213.group28.model.wrappers.wLogic;
import gu.dit213.group28.model.wrappers.wMarket;
import gu.dit213.group28.model.wrappers.wUser;
import java.util.List;
import java.util.Random;

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
    market = new wMarket(Market.getInstance());
    user = new wUser(new Portfolio(100000));
    tick = 0;
  }

  public void init() {
    timer.start();
    new Thread(
            () -> {
              while (true) {
                Ievent e = eventFacade.getTickEvent(tick);
                market.accept(e);
                user.accept(e);
                logic.extractEvent(e);
                try {
                  market.decrementAllModifiers();
                  timer.next();
                  makePredefEvent();
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
    if (e.getID() == 2) {
      logic.extractEvent(e);
      return;
    }
    market.accept(e);
    user.accept(e);
    logic.extractEvent(e);
  }

  public void makeSell(Sector s, int quantity) {
    Ievent e = eventFacade.getSellEvent(s, quantity);

    if (e.getID() == 4) {
      logic.extractEvent(e);
      return;
    }
    market.accept(e);
    user.accept(e);
    logic.extractEvent(e);
  }

  public void makePredefEvent() {
    Random rng = new Random();
    int percentage = 10;

    if (rng.nextInt(100) < percentage) {
      EventPredef e = (EventPredef) eventFacade.getPredefinedEvent();
      eventFacade.addEventToLog(e);
      market.accept(e);
      System.out.println(e.getDescription());

    }
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

  public void pause() {
    timer.pause();
  }
}
