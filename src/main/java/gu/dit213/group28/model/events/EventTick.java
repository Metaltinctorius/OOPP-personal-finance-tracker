package gu.dit213.group28.model.events;

import gu.dit213.group28.model.interfaces.ImarketEx;
import gu.dit213.group28.model.interfaces.IuserEx;
import gu.dit213.group28.model.market.Asset;

public class EventTick extends Event {

  public EventTick(int id) {
    super(id);
  }

  @Override
  public void execute(ImarketEx m) {
    for (Asset a : m.getAssets()) a.updatePrice();
  }

  @Override
  public void execute(IuserEx u) {}
}
