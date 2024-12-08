package gu.dit213.group28.model.wrappers;

import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Imarket;

import java.util.concurrent.locks.ReentrantLock;

/** Wrapper class for Market that ensures thread safety. */
public class wMarket implements Imarket {
  private final ReentrantLock lock = new ReentrantLock();
  private final Imarket market;

  /** Wrapper class for Market that ensures thread safety. */
  public wMarket(Imarket market) {
    this.market = market;
  }

  /**
   * Accepts incoming events
   *
   * @param e Event that will execute on this market
   */
  @Override
  public void accept(Ievent e) {
    lock.lock();
    market.accept(e);
    lock.unlock();
  }

  /** Decrements the timer on all current modifiers inside the market */
  @Override
  public void decrementAllModifiers() {
    lock.lock();
    market.decrementAllModifiers();
    lock.unlock();
  }
}
