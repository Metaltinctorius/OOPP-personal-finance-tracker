package gu.dit213.group28.model.events;

import gu.dit213.group28.model.records.MarketOutput;
import gu.dit213.group28.model.records.UserOutput;
import gu.dit213.group28.model.interfaces.ImarketEx;
import gu.dit213.group28.model.interfaces.IuserEx;
import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.user.PortfolioRecord;

import java.util.ArrayList;
import java.util.List;

/** Basic tick event. */
public class EventTick extends Event {
  public final List<MarketOutput> mOutput;
  public final int tick;
  private double currency;
  private double indexValue;
  private double playerValue;
  public final List<UserOutput> uOutput;

  /**
   * Basic tick event.
   *
   * @param tick Current game tick.
   */
  public EventTick(int tick) {
    super(0);
    mOutput = new ArrayList<>();
    this.tick = tick;
    uOutput = new ArrayList<>();
  }

  /** Gets the current currency of the user */
  public double getCurrency() {
    return currency;
  }

  /** Gets the total value of all index fund assets. */
  public double getIndexValue() {
    return indexValue;
  }

  /** Gets the total value of all player assets and currency. */
  public double getPlayerValue() {
    return playerValue;
  }

  /**
   * Executes event on given ImarketEx
   *
   * @param m ImarketEx to be executed upon
   */
  @Override
  public void execute(ImarketEx m) {
    for (Asset a : m.getAssets()) {
      a.updatePrice();
      mOutput.add(new MarketOutput(a.getSector(), a.getPrice()));
    }
    indexValue = m.getIndexValue();
  }

  /**
   * Executes event on given IuserEx
   *
   * @param u IuserEx to be executed upon
   */
  @Override
  public void execute(IuserEx u) {
    for (PortfolioRecord p : u.getRecords()) {
      for (MarketOutput m : mOutput) {
        if (p.getSector() == m.sector()) {
          uOutput.add(new UserOutput(p.getSector(), p.getQuantity(), m.value()));
        }
      }
    }
    currency = u.getCurrency();
    playerValue = u.getTotalValue(mOutput);
  }
}
