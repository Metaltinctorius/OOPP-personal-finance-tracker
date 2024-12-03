package gu.dit213.group28.model.user;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.market.Asset;

import java.time.LocalDate;

public class PortfolioEntry
{
  private final Sector sector;
  private final int quantity;  // Number of units/shares held
  private final double purchasePrice;  // Price paid per unit
  private final LocalDate purchaseDate;  // Date of purchase


  public PortfolioEntry(Sector sector, int quantity, double purchasePrice, LocalDate purchaseDate)
  {
    this.sector = sector;
    this.quantity = quantity;
    this.purchasePrice = purchasePrice;
    this.purchaseDate = purchaseDate;
  }

  // Getters
  public Sector getSector()
  {
    return sector;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public double getPurchasePrice()
  {
    return purchasePrice;
  }

  public LocalDate getPurchaseDate()
  {
    return purchaseDate;
  }

  // Calculate the total cost for this entry
  public double getTotalCost()
  {
    return quantity * purchasePrice;
  }

}
