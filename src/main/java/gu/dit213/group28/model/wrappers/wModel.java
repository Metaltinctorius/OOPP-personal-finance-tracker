package gu.dit213.group28.model.wrappers;

import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Imodel;

import java.util.concurrent.locks.ReentrantLock;

/** Wrapper class for Model that ensures thread safety. */
public class wModel implements Imodel {
  private final ReentrantLock lock = new ReentrantLock();
  private final Imodel logic;

  /** Wrapper class for Model that ensures thread safety. */
  public wModel(Imodel logic) {
    this.logic = logic;
  }

  /**
   * Extracts an event and updates the view.
   *
   * @param e Event to be extracted.
   */
  @Override
  public void extractEvent(Ievent e) {
    lock.lock();
    logic.extractEvent(e);
    lock.unlock();
  }
}
