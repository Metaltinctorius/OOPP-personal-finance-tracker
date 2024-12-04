package gu.dit213.group28.model.user;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.market.Asset;

import java.time.LocalDate;

public class PortfolioEntry {
  private final Sector sector;
  private final int quantity; // Number of units/shares held
  private final double purchasePrice; // Price paid per unit

  public PortfolioEntry(Sector sector, int quantity, double purchasePrice) {
    this.sector = sector;
    this.quantity = quantity;
    this.purchasePrice = purchasePrice;
  }

  // Getters
  public Sector getSector() {
    return sector;
  }

  public int getQuantity() {
    return quantity;
  }

  public double getPurchasePrice() {
    return purchasePrice;
  }

  // Calculate the total cost for this entry
  public double getTotalCost() {
    return quantity * purchasePrice;
  }
}
