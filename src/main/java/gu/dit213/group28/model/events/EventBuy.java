package gu.dit213.group28.model.events;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.ImarketEx;
import gu.dit213.group28.model.interfaces.IuserEx;
import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.user.PortfolioEntry;
import gu.dit213.group28.model.user.PortfolioRecord;

public class EventBuy extends Event {

  private final Sector sector;
  private final int amount;
  private int owned;
  private double value;

  public EventBuy(int id, Sector sector, int amount) {
    super(id);
    this.sector = sector;
    this.amount = amount;
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

  public int getOwned() {
    return owned;
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
    owned = u.getRecordQuantity(sector);
    if (u.getMoney() >= value * amount) {
      u.addMoney(-value * amount);
      u.addEntry(new PortfolioEntry(sector, amount, value));
      u.addRecord(sector, amount);
      owned = u.getRecordQuantity(sector);
    }
  }
}
