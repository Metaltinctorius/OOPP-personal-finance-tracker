package gu.dit213.group28.model.user;

import gu.dit213.group28.model.enums.Sector;

/**
 * Represents a record in a portfolio, tracking a specific sector and the associated quantity.
 * The quantity starts at 0 and can be updated with new additions.
 */
public class PortfolioRecord {
  /** The sector associated with this portfolio record. */
  private final Sector sector;

  /** The quantity associated with the portfolio record. */
  private int quantity;

  /**
   * Constructs a PortfolioRecord with a specified sector.
   * The quantity is initialized to 0.
   *
   * @param sector the sector to associate with this portfolio record
   */
  public PortfolioRecord(Sector sector) {
    this.sector = sector;
    quantity = 0;
  }

  /**
   * Gets the sector associated with this portfolio record.
   *
   * @return the sector of this portfolio record
   */
  public Sector getSector() {
    return sector;
  }

  /**
   * Gets the current quantity associated with this portfolio record.
   *
   * @return the quantity of this portfolio record
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Adds a specified quantity to the current quantity of this portfolio record.
   *
   * @param quantity the quantity to add to the current quantity
   */
  public void addQuantity(int quantity) {
    this.quantity += quantity;
  }
}
