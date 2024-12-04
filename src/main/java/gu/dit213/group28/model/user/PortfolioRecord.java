package gu.dit213.group28.model.user;

import gu.dit213.group28.model.enums.Sector;

public class PortfolioRecord {
  private final Sector sector;
  private int quantity;

  public PortfolioRecord(Sector sector) {
    this.sector = sector;
    quantity = 0;
  }

  public Sector getSector() {
    return sector;
  }

  public int getQuantity() {
    return quantity;
  }

  public void addQuantity(int quantity) {
    this.quantity += quantity;
  }
}
