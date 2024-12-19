package gu.dit213.group28.model.path;

import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Ipath;
import gu.dit213.group28.model.interfaces.IpathCreator;
import gu.dit213.group28.model.interfaces.Ipathable;

public class PathCreator implements IpathCreator {

  @Override
  public Ipath getEventPath(Ipathable p, Ievent e) {
    if (e.getId() == 0) {
      return new TickPath(p, e);
    }
    if (e.getId() == 1 || e.getId() == 2) {
      return new BuyPath(p, e);
    }
    if (e.getId() == 3 || e.getId() == 4) {
      return new SellPath(p, e);
    }
    if (e.getId() > 4) {
      return new PreDefPath(p, e);
    }
    return null;
  }
}
