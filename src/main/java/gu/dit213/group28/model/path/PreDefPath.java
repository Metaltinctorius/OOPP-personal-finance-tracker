package gu.dit213.group28.model.path;

import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Ipath;
import gu.dit213.group28.model.interfaces.Ipathable;

public class PreDefPath extends AbstractPath implements Ipath {
  private int trigger;

  public PreDefPath(Ipathable p, Ievent e) {
    super(p, e);
    trigger = 1;
  }

  @Override
  public boolean start() {
    if (trigger > 0) {
      System.out.println("extract");
      path.extract(event);
      trigger--;
      return false;
    }
    path.executeOnMarket(event);
    return true;
  }

  @Override
  public boolean isPending() {
    return true;
  }
}
