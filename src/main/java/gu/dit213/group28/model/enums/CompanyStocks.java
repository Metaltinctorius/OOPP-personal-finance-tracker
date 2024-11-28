package gu.dit213.group28.model.enums;


import static gu.dit213.group28.model.enums.StockCategory.LIFESCIENCE;
import static gu.dit213.group28.model.enums.StockCategory.PHARMACEUTICALS;

import java.util.List;

public enum CompanyStocks
{

  PFIZER(List.of(LIFESCIENCE, PHARMACEUTICALS)),
  ASTRA_ZENECA(List.of(LIFESCIENCE, PHARMACEUTICALS));

  private final List<StockCategory> categories;


  CompanyStocks(List<StockCategory> categories) {
    this.categories = categories;
  }


  public List<StockCategory> getCategories() {
    return categories;
  }
}
