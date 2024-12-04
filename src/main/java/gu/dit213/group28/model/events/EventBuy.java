package gu.dit213.group28.model.events;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.ImarketEx;
import gu.dit213.group28.model.interfaces.IuserEx;
import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.user.PortfolioEntry;

public class EventBuy extends Event {

  private final Sector sector;
  private final int amount;
  private boolean success;

  private double value;

  public EventBuy(int id, Sector sector, int amount) {
    super(id);
    this.sector = sector;
    this.amount = amount;
    success = false;
  }

  public Sector getSector() {
    return sector;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public int getAmount() {
    return amount;
  }

  public void execute() {}

  public void unpackage() {
    // return something
  }

  @Override
  public void execute(ImarketEx m) {
    for (Asset a : m.getAssets()) {
      if (a.getSector() == sector) {
        value = a.getPrice();
        break;
      }
    }
  }

  @Override
  public void execute(IuserEx u) {
    if (u.getMoney() >= value * amount) {
      u.addMoney(-value * amount);
      u.addEntry(new PortfolioEntry(sector, amount, value));
      u.addRecord(sector, amount);
      success = true;
    }
  }
}
