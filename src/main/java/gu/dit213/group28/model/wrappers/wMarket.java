package gu.dit213.group28.model.wrappers;

import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Imarket;

import java.util.concurrent.locks.ReentrantLock;

public class wMarket implements Imarket {
  private final ReentrantLock lock = new ReentrantLock();
  private final Imarket market;

  public wMarket(Imarket market) {
    this.market = market;
  }

  @Override
  public void accept(Ievent e) {
    lock.lock();
    market.accept(e);
    lock.unlock();
  }

  @Override
  public void decrementAllModifiers() {
    lock.lock();
    market.decrementAllModifiers();
    lock.unlock();
  }

  @Override
  public String getState() {
    lock.lock();
    String m = market.getState();
    lock.unlock();
    return m;
  }
}
