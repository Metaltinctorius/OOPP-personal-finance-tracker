package gu.dit213.group28.model.path;

import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Ipath;
import gu.dit213.group28.model.interfaces.Ipathable;

public class SellPath extends AbstractPath implements Ipath {

  public SellPath(Ipathable p, Ievent e) {
    super(p, e);
  }

  @Override
  public boolean start() {
    if (e.getID() == 4) {
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
