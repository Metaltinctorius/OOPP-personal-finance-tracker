package gu.dit213.group28.model.user;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Iuser;
import gu.dit213.group28.model.interfaces.IuserEx;
import gu.dit213.group28.model.records.MarketOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a portfolio that manages assets, records, and financial transactions. Implements the
 * Iuser and IuserEx interfaces.
 */
public class Portfolio implements Iuser, IuserEx {

  /** A list of records tracking the portfolio's investments. */
  private final List<PortfolioRecord> records;

  /** The amount of money available in the portfolio. */
  private double money;

  /**
   * Constructs a new Portfolio with a specified starting amount of money.
   *
   * @param startMoney The initial amount of money in the portfolio.
   */
  public Portfolio(int startMoney) {
    records = new ArrayList<>();
    money = startMoney;
  }

  /**
   * Retrieves the amount of money available in the portfolio.
   *
   * @return The amount of money.
   */
  public double getCurrency() {
    return money;
  }

  /**
   * Adds a specified amount of money to the portfolio.
   *
   * @param amount The amount of money to add.
   */
  public void addCurrency(double amount) {
    money += amount;
  }

  /**
   * Adds a quantity to a sector's record or creates a new record if none exists.
   *
   * @param sector The sector to add the quantity to.
   * @param quantity The quantity to add.
   */
  public void addRecord(Sector sector, int quantity) {
    boolean exists = false;
    for (PortfolioRecord record : records) {
      if (record.getSector() == sector) {
        record.addQuantity(quantity);
        exists = true;
      }
    }
    if (!exists) {
      PortfolioRecord p = new PortfolioRecord(sector);
      p.addQuantity(quantity);
      records.add(p);
    }
  }

  /**
   * Retrieves the list of portfolio records.
   *
   * @return A list of PortfolioRecord objects.
   */
  public List<PortfolioRecord> getRecords() {
    return records;
  }

  /**
   * Retrieves the total quantity for a specified sector.
   *
   * @param sector The sector to retrieve the quantity for.
   * @return The total quantity for the sector.
   */
  public int getRecordQuantity(Sector sector) {
    for (PortfolioRecord record : records) {
      if (record.getSector() == sector) {
        return record.getQuantity();
      }
    }
    return 0;
  }

  /**
   * Calculates the total value of the portfolio based on market outputs.
   *
   * @param values A list of MarketOutput objects representing current market values.
   * @return The total value of the portfolio.
   */
  public double getTotalValue(List<MarketOutput> values) {
    double res = money;
    for (MarketOutput m : values) {
      for (PortfolioRecord p : records) {
        if (p.getSector() == m.sector()) {
          res += p.getQuantity() * m.value();
        }
      }
    }
    return res;
  }

  /**
   * Accepts an event and applies it to the portfolio using the visitor pattern.
   *
   * @param e The event to execute on the portfolio.
   */
  @Override
  public void accept(Ievent e) {
    e.execute(this);
  }
}
