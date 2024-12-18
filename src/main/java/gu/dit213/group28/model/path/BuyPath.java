package gu.dit213.group28.model.path;

import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Ipath;
import gu.dit213.group28.model.interfaces.Ipathable;

public class BuyPath extends AbstractPath implements Ipath {

  public BuyPath(Ipathable p, Ievent e) {
    super(p, e);
  }

  @Override
  public boolean start() {
    if (e.getID() == 2) {
      p.extract(e);
      return true;
    }
    p.executeOnMarket(e);
    p.executeOnUser(e);
    p.extract(e);
    return true;
  }

  @Override
  public boolean isPending() {
    return false;
  }
}
