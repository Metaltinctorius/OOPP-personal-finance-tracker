package gu.dit213.group28.model.events;

import gu.dit213.group28.model.UserOutput;
import gu.dit213.group28.model.interfaces.ImarketEx;
import gu.dit213.group28.model.interfaces.IuserEx;
import gu.dit213.group28.model.market.Asset;
import java.util.ArrayList;
import java.util.List;

public class EventTick extends Event {
  public final List<UserOutput> output;
  public final int tick;

  public EventTick(int id, int tick) {
    super(id);
    output = new ArrayList<>();
    this.tick = tick;
  }

  @Override
  public void execute(ImarketEx m) {
    for (Asset a : m.getAssets()) {
      a.updatePrice();
      output.add(new UserOutput(a.getSector(), a.getPrice()));
    }
  }

  @Override
  public void execute(IuserEx u) {}
}
