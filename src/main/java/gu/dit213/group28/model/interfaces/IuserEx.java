package gu.dit213.group28.model.interfaces;

import gu.dit213.group28.model.records.MarketOutput;
import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.user.PortfolioRecord;
import java.util.List;

/** Interface used by events for executing on a user. */
public interface IuserEx {
  /**
   * Gets the users current currency.
   *
   * @return amount of currency user has
   */
  double getCurrency();

  /**
   * Adds the amount to the users currency, the amount can be negative.
   *
   * @param amount Amount added to users currency
   */
  void addCurrency(double amount);

  /**
   * Adds a new portfolio record in a given sector and quantity.
   *
   * @param sector The sector of the new record
   * @param quantity The quantity of the new record
   */
  void addRecord(Sector sector, int quantity);

  /**
   * Gets a list of all portfolio records from the user.
   *
   * @return List of portfolio records.
   */
  List<PortfolioRecord> getRecords();

  /**
   * Gets the total quantity owned by a user within the given sector across all records.
   *
   * @param sector The sector to count
   * @return Total quantity owned by user
   */
  int getRecordQuantity(Sector sector);

  /**
   * Gets the total value of a user, including both records and currency
   *
   * @param m List of market outputs that include the current value of sectors.
   * @return Total value of a user.
   */
  double getTotalValue(List<MarketOutput> m);
}
