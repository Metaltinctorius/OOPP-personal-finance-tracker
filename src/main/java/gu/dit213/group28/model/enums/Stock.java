package gu.dit213.group28.model.enums;


import static gu.dit213.group28.model.enums.StockCategory.LIFESCIENCE;
import static gu.dit213.group28.model.enums.StockCategory.PHARMACEUTICALS;

import java.util.List;

public enum Stock {

  PFIZER(0.0, 1000, List.of(LIFESCIENCE, PHARMACEUTICALS));

  private final List<StockCategory> categories;
  private double multiplier;
  private double value;

  Stock(double multiplier, double value, List<StockCategory> categories) {
    this.multiplier = multiplier;
    this.value = value;
    this.categories = categories;
  }

  public double getMultiplier() {
    return multiplier;
  }

  public double getValue() {
    return value;
  }

  public void setMultiplier(double multiplier) {
    this.multiplier = multiplier;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public List<StockCategory> getCategories() {
    return categories;
  }
}
