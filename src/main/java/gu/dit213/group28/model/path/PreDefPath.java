package gu.dit213.group28.model.path;

import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Ipath;
import gu.dit213.group28.model.interfaces.Ipathable;

/** Path for regular predefined events. */
public class PreDefPath extends AbstractPath implements Ipath {
  private int trigger;

  /** Path for regular predefined events. */
  public PreDefPath(Ipathable p, Ievent e) {
    super(p, e);
    trigger = 1;
  }

  /**
   * Starts a path for the predefined event held by this path.
   *
   * @return true if path is finished and can be discarded, false otherwise.
   */
  @Override
  public boolean start() {
    if (trigger > 0) {
      path.extract(event);
      trigger--;
      return false;
    }
    path.executeOnMarket(event);
    return true;
  }

  /**
   * A pending event is an event that needs to be stored for future calls to start().
   *
   * @return true if event is pending, false otherwise.
   */
  @Override
  public boolean isPending() {
    return true;
  }
}
