package gu.dit213.group28.model.events;

import gu.dit213.group28.model.interfaces.ImarketEx;
import gu.dit213.group28.model.interfaces.IuserEx;
import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.records.MarketOutput;
import gu.dit213.group28.model.records.UserOutput;
import gu.dit213.group28.model.user.PortfolioRecord;
import java.util.ArrayList;
import java.util.List;

/** Basic tick event. */
public class EventTick extends Event {

  /** List of market outputs. */
  public final List<MarketOutput> marketOutput;

  /** Current game tick. */
  public final int tick;

  /** List of user outputs. */
  public final List<UserOutput> userOutput;

  private double currency;
  private double indexValue;
  private double playerValue;

  /**
   * Basic tick event.
   *
   * @param tick Current game tick.
   */
  public EventTick(int tick) {
    super(0);
    marketOutput = new ArrayList<>();
    this.tick = tick;
    userOutput = new ArrayList<>();
  }

  /**
   * Gets the current currency of the user.
   *
   * @return currency
   */
  public double getCurrency() {
    return currency;
  }

  /**
   * Gets the total value of all index fund assets.
   *
   * @return indexValue
   */
  public double getIndexValue() {
    return indexValue;
  }

  /**
   * Gets the total value of all player assets and currency.
   *
   * @return playerValue
   */
  public double getPlayerValue() {
    return playerValue;
  }

  /**
   * Executes event on given ImarketEx.
   *
   * @param m ImarketEx to be executed upon.
   */
  @Override
  public void execute(ImarketEx m) {
    m.decrementAllModifiers();
    for (Asset a : m.getAssets()) {
      a.updatePrice();
      marketOutput.add(new MarketOutput(a.getSector(), a.getPrice()));
    }

    indexValue = m.getIndexValue();
  }

  /**
   * Executes event on given IuserEx.
   *
   * @param u IuserEx to be executed upon.
   */
  @Override
  public void execute(IuserEx u) {
    for (PortfolioRecord p : u.getRecords()) {
      for (MarketOutput m : marketOutput) {
        if (p.getSector() == m.sector()) {
          userOutput.add(new UserOutput(p.getSector(), p.getQuantity(), m.value()));
        }
      }
    }
    currency = u.getCurrency();
    playerValue = u.getTotalValue(marketOutput);
  }
}
