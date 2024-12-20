package gu.dit213.group28.model.events;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.ImarketEx;
import gu.dit213.group28.model.interfaces.IuserEx;
import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.user.PortfolioRecord;

/** Basic event for selling assets. */
public class EventSell extends Event {
  private final Sector sector;
  private final int quantity;
  private int owned;

  private double value;

  /**
   * Basic event for selling assets.
   *
   * @param sector The sector of the assets.
   * @param quantity The quantity of assets sold
   */
  public EventSell(Sector sector, int quantity) {
    super(3);
    this.sector = sector;
    this.quantity = quantity;
    if (quantity <= 0) {
      setId(4);
    }
    owned = -1;
  }

  /**
   * Getter for sector.
   *
   * @return The sector of the assets.
   */
  public Sector getSector() {
    return sector;
  }

  /**
   * Getter for quantity.
   *
   * @return The quantity of the assets.
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Getter for value.
   *
   * @return The value of the assets.
   */
  public double getValue() {
    return value;
  }

  /**
   * Gets the total number of assets owned by the user in the sector.
   *
   * @return number of assets owned or -1 if user has not yet been visited by this event.
   */
  public int getOwned() {
    return owned;
  }

  /**
   * Executes event on given ImarketEx.
   *
   * @param m ImarketEx to be executed upon.
   */
  @Override
  public void execute(ImarketEx m) {
    for (Asset a : m.getAssets()) {
      if (a.getSector() == sector) {
        value = a.getPrice();
        break;
      }
    }
  }

  /**
   * Executes event on given IuserEx.
   *
   * @param u IuserEx to be executed upon.
   */
  @Override
  public void execute(IuserEx u) {
    owned = u.getRecordQuantity(sector);
    PortfolioRecord sell = null;
    for (PortfolioRecord record : u.getRecords()) {
      if (record.getSector() == sector) {
        sell = record;
      }
    }
    if (sell != null && sell.getQuantity() >= quantity) {
      u.addCurrency(value * quantity);
      sell.addQuantity(-quantity);
      if (sell.getQuantity() <= 0) {
        u.getRecords().remove(sell);
      }
      owned = sell.getQuantity();
    } else {
      setId(4);
    }
  }
}
