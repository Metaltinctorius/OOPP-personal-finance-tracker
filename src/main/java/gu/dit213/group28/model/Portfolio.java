package gu.dit213.group28.model;

import java.util.ArrayList;
import java.util.List;

public class Portfolio
{
  private final List<PortfolioEntry> entries;

  public Portfolio()
  {
    this.entries = new ArrayList<>();
  }

  public void addEntry(PortfolioEntry entry)
  {
    entries.add(entry);
  }

  public void removeEntry(PortfolioEntry entry)
  {
    entries.remove(entry);
  }

  public List<PortfolioEntry> getEntries()
  {
    return entries;
  }

  public double getTotalValue()
  {
    return entries.stream()
        .mapToDouble(entry -> entry.getQuantity() * entry.getAsset().getPrice())
        .sum();
  }

  public double getTotalCost()
  {
    return entries.stream()
        .mapToDouble(PortfolioEntry::getTotalCost)
        .sum();
  }

  //TODO: Implement buy & sell methods

}
