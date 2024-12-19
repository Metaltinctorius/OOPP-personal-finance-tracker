package gu.dit213.group28.model.wrappers;

import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Iuser;
import java.util.concurrent.locks.ReentrantLock;

/** Wrapper class for a user that ensures thread safety. */
public class WrapUser implements Iuser {
  private final ReentrantLock lock = new ReentrantLock();
  private final Iuser user;

  /** Wrapper class for a user that ensures thread safety. */
  public WrapUser(Iuser user) {
    this.user = user;
  }

  /**
   * Accepts incoming events.
   *
   * @param e Event that will execute on this market
   */
  @Override
  public void accept(Ievent e) {
    lock.lock();
    user.accept(e);
    lock.unlock();
  }
}
