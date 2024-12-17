package gu.dit213.group28.model.path;

import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Ipathable;

public class AbstractPath {
  protected final Ipathable p;
  protected final Ievent e;

  public AbstractPath(Ipathable p, Ievent e) {
    this.p = p;
    this.e = e;
  }
}
