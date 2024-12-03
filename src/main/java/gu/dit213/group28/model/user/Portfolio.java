package gu.dit213.group28.model.user;

import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Iuser;

import java.util.ArrayList;
import java.util.List;

public class Portfolio implements Iuser
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


  @Override
  public void accept(Ievent e) {
    e.execute(this);
  }
}
