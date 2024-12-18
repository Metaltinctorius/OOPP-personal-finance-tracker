package gu.dit213.group28.model.path;

import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Ipath;
import gu.dit213.group28.model.interfaces.IpathCreator;
import gu.dit213.group28.model.interfaces.Ipathable;

public class PathCreator implements IpathCreator {

  @Override
  public Ipath getEventPath(Ipathable p, Ievent e) {
    if (e.getID() == 0) {
      return new TickPath(p, e);
    }
    if (e.getID() == 1 || e.getID() == 2) {
      return new BuyPath(p, e);
    }
    if (e.getID() == 3 || e.getID() == 4) {
      return new SellPath(p, e);
    }
    if (e.getID() > 4) {
      return new PreDefPath(p, e);
    }
    return null;
  }
}
