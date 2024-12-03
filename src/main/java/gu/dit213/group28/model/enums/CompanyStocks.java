package gu.dit213.group28.model.enums;


import static gu.dit213.group28.model.enums.Sector.*;

import java.util.List;

public enum CompanyStocks
{

  PFIZER(List.of(HEALTHCARE, INFORMATION_TECHNOLOGY)),
  ASTRA_ZENECA(List.of(HEALTHCARE));

  private final List<Sector> categories;


  CompanyStocks(List<Sector> categories) {
    this.categories = categories;
  }


  public List<Sector> getCategories() {
    return categories;
  }
}
