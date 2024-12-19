package gu.dit213.group28.model.user;

import gu.dit213.group28.model.enums.Sector;

/** Represents an entry in a portfolio, containing information about a specific investment. */
public class PortfolioEntry {

  /** The sector associated with this portfolio entry. */
  private final Sector sector;

  /** The number of units held in this portfolio entry. */
  private final int quantity;

  /** The price paid per unit for this portfolio entry. */
  private final double purchasePrice;

  /**
   * Constructs a new PortfolioEntry with the specified sector, quantity, and purchase price.
   *
   * @param sector The sector associated with this entry.
   * @param quantity The number of units held.
   * @param purchasePrice The price paid per unit.
   */
  public PortfolioEntry(Sector sector, int quantity, double purchasePrice) {
    this.sector = sector;
    this.quantity = quantity;
    this.purchasePrice = purchasePrice;
  }

  /**
   * Retrieves the sector associated with this portfolio entry.
   *
   * @return The sector.
   */
  public Sector getSector() {
    return sector;
  }

  /**
   * Retrieves the number of units or shares held in this portfolio entry.
   *
   * @return The quantity of units or shares.
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Retrieves the price paid per unit for this portfolio entry.
   *
   * @return The purchase price per unit or share.
   */
  public double getPurchasePrice() {
    return purchasePrice;
  }

  /**
   * Calculates the total cost of this portfolio entry.
   *
   * @return The total cost, calculated as quantity * purchasePrice.
   */
  public double getTotalCost() {
    return quantity * purchasePrice;
  }
}
