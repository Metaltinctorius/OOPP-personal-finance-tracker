package gu.dit213.group28.model.wrappers;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Ieventfacade;

import java.util.concurrent.locks.ReentrantLock;

public class wEventFacade implements Ieventfacade {
  private final ReentrantLock lock = new ReentrantLock();
  private final Ieventfacade facade;

  public wEventFacade(Ieventfacade facade) {
    this.facade = facade;
  }

  @Override
  public Ievent getTickEvent(int tick) {
    lock.lock();
    Ievent e = facade.getTickEvent(tick);
    lock.unlock();
    return e;
  }

  @Override
  public Ievent getBuyEvent(Sector s, int quantity) {
    lock.lock();
    Ievent e = facade.getBuyEvent(s, quantity);
    lock.unlock();
    return e;
  }

  @Override
  public Ievent getSellEvent(Sector s, int quantity) {
    lock.lock();
    Ievent e = facade.getSellEvent(s, quantity);
    lock.unlock();
    return e;
  }

  @Override
  public Ievent getPredefinedEvent()
  {
    lock.lock();
    Ievent e = facade.getPredefinedEvent();
    lock.unlock();
    return  e;
  }

  @Override
  public void addEventToLog(Ievent event)
  {

  }
}
