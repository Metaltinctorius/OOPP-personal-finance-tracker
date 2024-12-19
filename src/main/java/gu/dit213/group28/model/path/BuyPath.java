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
    if (event.getId() == 2) {
      path.extract(event);
      return true;
    }
    path.executeOnMarket(event);
    path.executeOnUser(event);
    path.extract(event);
    return true;
  }

  @Override
  public boolean isPending() {
    return false;
  }
}
