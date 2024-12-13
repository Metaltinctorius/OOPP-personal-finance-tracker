package gu.dit213.group28.model.user;

import java.time.LocalDate;

/** Represents a record of a price for an asset, associated with a specific date. */
public class PriceRecord {
  /** The price of the asset. */
  private final double price;

  /** The date associated with the price. */
  private final LocalDate date;

  /**
   * Constructs a PriceRecord with the specified price and date.
   *
   * @param price the price of the asset
   * @param date the date associated with the price
   */
  public PriceRecord(double price, LocalDate date) {
    this.price = price;
    this.date = date;
  }

  /**
   * Gets the price of the asset.
   *
   * @return the price of the asset
   */
  public double getPrice() {
    return price;
  }

  /**
   * Gets the date associated with the price.
   *
   * @return the date associated with the price
   */
  public LocalDate getDate() {
    return date;
  }
}
