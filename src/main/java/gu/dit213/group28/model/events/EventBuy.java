package gu.dit213.group28.model.events;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.ImarketEx;
import gu.dit213.group28.model.interfaces.IuserEx;
import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.user.PortfolioEntry;

/** Basic event for buying assets. */
public class EventBuy extends Event {

  private final Sector sector;
  private final int quantity;
  private int owned;
  private double value;

  /**
   * Basic event for buying assets.
   *
   * @param sector The sector of the assets.
   * @param quantity The quantity of assets bought
   */
  public EventBuy(Sector sector, int quantity) {
    super(1);
    this.sector = sector;
    this.quantity = quantity;
    if (quantity <= 0) {
      setID(2);
    }
    owned = -1;
  }

  /**
   * Getter for sector
   *
   * @return The sector of the assets.
   */
  public Sector getSector() {
    return sector;
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
   * Executes event on given ImarketEx
   *
   * @param m ImarketEx to be executed upon
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
   * Executes event on given IuserEx
   *
   * @param u IuserEx to be executed upon
   */
  @Override
  public void execute(IuserEx u) {
    owned = u.getRecordQuantity(sector);
    if (u.getCurrency() >= value * quantity) {
      u.addCurrency(-value * quantity);
      u.addEntry(new PortfolioEntry(sector, quantity, value));
      u.addRecord(sector, quantity);
      owned = u.getRecordQuantity(sector);
    } else {
      setID(2);
    }
  }
}
