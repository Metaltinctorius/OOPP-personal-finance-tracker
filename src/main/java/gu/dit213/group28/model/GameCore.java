package gu.dit213.group28.model;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.enums.Speed;
import gu.dit213.group28.model.events.EventFacade;
import gu.dit213.group28.model.interfaces.*;
import gu.dit213.group28.model.market.Market;
import gu.dit213.group28.model.path.PathCreator;
import gu.dit213.group28.model.user.Portfolio;
import gu.dit213.group28.model.wrappers.wEventFacade;
import gu.dit213.group28.model.wrappers.wModel;
import gu.dit213.group28.model.wrappers.wMarket;
import gu.dit213.group28.model.wrappers.wUser;

import java.util.ArrayList;
import java.util.List;

/** Class that takes input, creates events, delivers events and keeps track of game time. */
public class GameCore implements Ipathable, Icore {

  private final Itimer timer;
  private final IeventFacade eventFacade;
  private final Imodel model;
  private final Imarket market;
  private final Iuser user;
  private int tick;
  private final IpathCreator pathCreator;

  private final List<Ipath> pendingEvents = new ArrayList<>();

  /** Class that takes input, creates events, delivers events and keeps track of game time. */
  public GameCore(Model model) {
    timer = new Time();
    timer.initTime();
    eventFacade = new wEventFacade(new EventFacade());
    this.model = new wModel(model);
    market = new wMarket(Market.getInstance());
    user = new wUser(new Portfolio(100000));
    tick = 0;
    pathCreator = new PathCreator();
  }

  /** Initializes the GameCore, starting the timer. */
  @Override
  public void init() {
    timer.start();
    Thread t =
        new Thread(
            () -> {
              pauseAndResume();
              while (true) {
                try {
                  timer.next();
                  processPendingEvents();
                  if (eventFacade.isRandomEventReady()) {
                    makePredefEvent();
                  }
                  makeTick();
                  tick++;
                } catch (InterruptedException ex) {
                  throw new RuntimeException(ex);
                }
              }
            });
    t.setDaemon(true);
    t.start();
  }

  private void makeEvent(Ievent e) {
    Ipath path = pathCreator.getEventPath(this, e);
    if (path.isPending()) {
      pendingEvents.add(path);
    }
    path.start();
  }

  /**
   * Creates a basic buy event and, if successful, delivers it to the market first then to the user.
   * Finally, it delivers the event for extraction.
   *
   * @param sector The sector of the assets.
   * @param quantity The quantity of assets bought
   */
  @Override
  public void makePurchase(Sector sector, int quantity) {
    makeEvent(eventFacade.getBuyEvent(sector, quantity));
  }

  /**
   * Creates a basic sell event and, if successful, delivers it to the market first then to the
   * user. Finally, it delivers the event for extraction.
   *
   * @param sector The sector of the assets.
   * @param quantity The quantity of assets sold
   */
  @Override
  public void makeSell(Sector sector, int quantity) {
    makeEvent(eventFacade.getSellEvent(sector, quantity));
  }

  private void makeTick() {
    makeEvent(eventFacade.getTickEvent(tick));
  }

  /** Creates a Predefined event and delivers it to the market. */
  private void makePredefEvent() {
    makeEvent(eventFacade.getPredefinedEvent());
    timer.pause();
    model.updatePause(true);
  }

  private void processPendingEvents() {
    List<Ipath> done = new ArrayList<>();
    for (Ipath p : pendingEvents) {
      if (p.start()) {
        done.add(p);
      }
    }
    for (Ipath p : done) {
      pendingEvents.remove(p);
    }
  }

  /** Sets the game speed to Normal. */
  @Override
  public void setSpeedNormal() {
    // System.out.println("Before Normal: " + timer.getCurrentTick());
    timer.setThreshold(Speed.NORMAL);
    // System.out.println("After Normal: " + timer.getCurrentTick());
  }

  /** Sets the game speed to Slow. */
  @Override
  public void setSpeedSlow() {
    // System.out.println("Before slow: " + timer.getCurrentTick());
    timer.setThreshold(Speed.SLOW);
    // System.out.println("After slow: " + timer.getCurrentTick());
  }

  /** Sets the game speed to Fast. */
  @Override
  public void setSpeedFast() {
    // System.out.println("Before fast: " + timer.getCurrentTick());
    timer.setThreshold(Speed.FAST);
    // System.out.println("After fast: " + timer.getCurrentTick());
  }

  /** Pauses the timer if currently active, resumes the timer if currently paused. */
  @Override
  public void pauseAndResume() {
    boolean running = timer.pauseAndResume();
    model.updatePause(running);
  }

  @Override
  public void executeOnMarket(Ievent e) {
    market.accept(e);
  }

  @Override
  public void executeOnUser(Ievent e) {
    user.accept(e);
  }

  @Override
  public void extract(Ievent e) {
    model.extractEvent(e);
  }
}
