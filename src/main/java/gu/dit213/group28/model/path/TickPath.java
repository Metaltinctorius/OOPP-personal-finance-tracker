package gu.dit213.group28.model.path;

import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Ipath;
import gu.dit213.group28.model.interfaces.Ipathable;

/** Path for regular tick events. */
public class TickPath extends AbstractPath implements Ipath {

  /**
   * Path for regular sell events.
   *
   * @param p The path to be executed.
   * @param e The event to be executed
   */
  public TickPath(Ipathable p, Ievent e) {
    super(p, e);
  }

  /**
   * Starts a path for the tick event held by this path.
   *
   * @return true if path is finished and can be discarded, false otherwise.
   */
  @Override
  public boolean start() {
    path.executeOnMarket(event);
    path.executeOnUser(event);
    path.extract(event);
    return true;
  }

  /**
   * A pending event is an event that needs to be stored for future calls to start().
   *
   * @return true if event is pending, false otherwise.
   */
  @Override
  public boolean isPending() {
    return false;
  }
}
