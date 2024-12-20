package gu.dit213.group28.model.path;

import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Ipathable;

/** Abstract class for all paths. */
public class AbstractPath {
  /** The path variable. */
  protected final Ipathable path;

  /** The event variable. */
  protected final Ievent event;

  /**
   * Abstract class for all paths.
   *
   * @param path The path to be executed.
   * @param event The event to be executed
   */
  public AbstractPath(Ipathable path, Ievent event) {
    this.path = path;
    this.event = event;
  }
}
