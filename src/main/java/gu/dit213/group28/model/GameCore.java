package gu.dit213.group28.model;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.enums.Speed;
import gu.dit213.group28.model.events.EventFacade;
import gu.dit213.group28.model.interfaces.*;
import gu.dit213.group28.model.market.Market;
import gu.dit213.group28.model.user.Portfolio;
import gu.dit213.group28.model.wrappers.wEventFacade;
import gu.dit213.group28.model.wrappers.wModel;
import gu.dit213.group28.model.wrappers.wMarket;
import gu.dit213.group28.model.wrappers.wUser;

import java.util.ArrayList;
import java.util.List;

/** Class that takes input, creates events, delivers events and keeps track of game time. */
public class GameCore {

  private final Itimer timer;
  private final IeventFacade eventFacade;
  private final Imodel logic;
  private final Imarket market;
  private final Iuser user;
  private int tick;
  private boolean isPaused;

  private final List<ScheduleEvent> pendingEvents = new ArrayList<>();

  /** Class that takes input, creates events, delivers events and keeps track of game time. */
  public GameCore(Model model) {
    timer = new Time();
    timer.initTime();
    eventFacade = new wEventFacade(new EventFacade());
    this.logic = new wModel(model);
    market = new wMarket(Market.getInstance());
    user = new wUser(new Portfolio(100000));
    tick = 0;
    isPaused = false;
  }

  /** Initializes the GameCore, starting the timer. */
  public void init() {
    timer.start();
    new Thread(
            () -> {
              while (true) {

                try {
                  timer.next();
                  market.decrementAllModifiers();
                  processPendingEvents();
                  if (eventFacade.isRandomEventReady()) {
                    makePredefEvent();
                  }
                  Ievent e = eventFacade.getTickEvent(tick);
                  market.accept(e);
                  user.accept(e);
                  logic.extractEvent(e);
                  tick++;
                } catch (InterruptedException ex) {
                  throw new RuntimeException(ex);
                }
              }
            })
        .start();
  }

  /**
   * Creates a basic buy event and, if successful, delivers it to the market first then to the user.
   * Finally, it delivers the event for extraction.
   *
   * @param sector The sector of the assets.
   * @param quantity The quantity of assets bought
   */
  public void makePurchase(Sector sector, int quantity) {
    Ievent e = eventFacade.getBuyEvent(sector, quantity);
    if (e.getID() == 2) {
      logic.extractEvent(e);
      return;
    }
    market.accept(e);
    user.accept(e);
    logic.extractEvent(e);
  }

  /**
   * Creates a basic sell event and, if successful, delivers it to the market first then to the
   * user. Finally, it delivers the event for extraction.
   *
   * @param sector The sector of the assets.
   * @param quantity The quantity of assets sold
   */
  public void makeSell(Sector sector, int quantity) {
    Ievent e = eventFacade.getSellEvent(sector, quantity);

    if (e.getID() == 4) {
      logic.extractEvent(e);
      return;
    }
    market.accept(e);
    user.accept(e);
    logic.extractEvent(e);
  }

  /** Creates a Predefined event and delivers it to the market. */

  public void makePredefEvent() throws InterruptedException {
    Ievent e = eventFacade.getPredefinedEvent();
    timer.pause();
    int trigger = 3;
    pendingEvents.add(new ScheduleEvent(e, trigger));
    logic.extractEvent(e);
    //market.accept(e);
  }

  void processPendingEvents(){
    List<ScheduleEvent> toTrigger = new ArrayList<>();
    for(ScheduleEvent se : pendingEvents){
      if(se.trigger <= 0){
        toTrigger.add(se);
      } else {
        se.trigger =- 1;
      }
    }

    for(ScheduleEvent trigg : toTrigger){
      market.accept(trigg.event);
      pendingEvents.remove(trigg);
    }
  }

  /** Sets the game speed to Normal. */
  public void setSpeedNormal() {
    timer.setThreshold(Speed.NORMAL);
  }

  /** Sets the game speed to Slow. */
  public void setSpeedSlow() {
    timer.setThreshold(Speed.SLOW);
  }

  /** Sets the game speed to Fast. */
  public void setSpeedFast() {
    timer.setThreshold(Speed.FAST);
  }

  /** Pauses the timer if currently active, resumes the timer if currently paused. */
  public void pauseAndResume() {
    timer.pauseAndResume();
  }
}
