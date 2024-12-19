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
    if (event.getId() == 4) {
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
