package gu.dit213.group28.model;

import gu.dit213.group28.model.enums.CompanyStocks;
import gu.dit213.group28.model.enums.StockCategory;
import java.util.List;

public class Stock {
  private final CompanyStocks companyStock;
  private double multiplier;
  private double value;

  private int quantity;

  public Stock (CompanyStocks companyStock, double multiplier, double value, int quantity) {
    this.companyStock = companyStock;
    this.multiplier = multiplier;
    this.value = value;
    this.quantity = quantity;
  }

  public double getMultiplier() {
    return multiplier;
  }

  public double getValue() {
    return value;
  }

  public int getQuantity() {return quantity; }

  public CompanyStocks getCompanyStock(){
    return companyStock;
  }

  public void setMultiplier(double multiplier) {
    this.multiplier = multiplier;
  }

  public void setQuantity (int quantity) { this.quantity = quantity; }
  public void setValue(double value) {
    this.value = value;
  }

}
