package gu.dit213.group28.model.wrappers;

import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Imodel;

import java.util.concurrent.locks.ReentrantLock;

/** Wrapper class for Model that ensures thread safety. */
public class wModel implements Imodel {
  private final ReentrantLock lock = new ReentrantLock();
  private final Imodel model;

  /** Wrapper class for Model that ensures thread safety. */
  public wModel(Imodel model) {
    this.model = model;
  }

  /**
   * Extracts an event and updates the view.
   *
   * @param e Event to be extracted.
   */
  @Override
  public void extractEvent(Ievent e) {
    lock.lock();
    model.extractEvent(e);
    lock.unlock();
  }

  @Override
  public void updatePause(boolean pause) {
    lock.lock();
    model.updatePause(pause);
    lock.unlock();
  }
}
