package gu.dit213.group28.model;

import java.time.LocalDate;

public class PriceRecord
{
  private final double price;      // Price of the asset
  private final LocalDate date;   // Date associated with the price

  public PriceRecord(double price, LocalDate date)
  {
    this.price = price;
    this.date = date;
  }

  public double getPrice()
  {
    return price;
  }

  public LocalDate getDate()
  {
    return date;
  }
}
