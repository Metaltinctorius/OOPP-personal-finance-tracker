package gu.dit213.group28.model.path;

import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Ipathable;

/** Abstract class for all paths. */
public class AbstractPath {
  protected final Ipathable path;
  protected final Ievent event;

  /** Abstract class for all paths. */
  public AbstractPath(Ipathable path, Ievent event) {
    this.path = path;
    this.event = event;
  }
}
