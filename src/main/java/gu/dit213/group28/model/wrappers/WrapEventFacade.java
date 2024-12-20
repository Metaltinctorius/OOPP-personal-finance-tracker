package gu.dit213.group28.model.wrappers;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.IeventFacade;
import java.util.concurrent.locks.ReentrantLock;

/** Wrapper class for EventFacade that ensures thread safety. */
public class WrapEventFacade implements IeventFacade {
  private final ReentrantLock lock = new ReentrantLock();
  private final IeventFacade facade;

  /**
   * Wrapper class for EventFacade that ensures thread safety.
   * @param facade the event facade used by the game core.
   */
  public WrapEventFacade(IeventFacade facade) {
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
  public Ievent getBuyEvent(Sector sector, int quantity) {
    lock.lock();
    Ievent e = facade.getBuyEvent(sector, quantity);
    lock.unlock();
    return e;
  }

  @Override
  public Ievent getSellEvent(Sector sector, int quantity) {
    lock.lock();
    Ievent e = facade.getSellEvent(sector, quantity);
    lock.unlock();
    return e;
  }

  @Override
  public Ievent getPredefinedEvent() {
    lock.lock();
    Ievent e = facade.getPredefinedEvent();
    lock.unlock();
    return e;
  }

  @Override
  public boolean isRandomEventReady() {
    return facade.isRandomEventReady();
  }
}
