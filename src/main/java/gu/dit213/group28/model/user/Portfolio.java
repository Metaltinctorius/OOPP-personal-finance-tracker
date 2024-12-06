package gu.dit213.group28.model.user;

import gu.dit213.group28.model.MarketOutput;
import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Iuser;

import gu.dit213.group28.model.interfaces.IuserEx;
import java.util.ArrayList;
import java.util.List;

public class Portfolio implements Iuser, IuserEx {
  private final List<PortfolioEntry> entries;
  private final List<PortfolioRecord> records;
  private double money;

  public Portfolio(int startMoney) {
    entries = new ArrayList<>();
    records = new ArrayList<>();
    money = startMoney;
  }

  public double getMoney() {
    return money;
  }

  public void addMoney(double amount) {
    money += amount;
  }

  public void addEntry(PortfolioEntry entry) {
    entries.add(entry);
  }

  public void removeEntry(PortfolioEntry entry) {
    entries.remove(entry);
  }

  public List<PortfolioEntry> getEntries() {
    return entries;
  }

  public void addRecord(Sector s, int quantity) {
    boolean exists = false;
    for (PortfolioRecord record : records) {
      if (record.getSector() == s) {
        record.addQuantity(quantity);
        exists = true;
      }
    }
    if (!exists) {
      PortfolioRecord p = new PortfolioRecord(s);
      p.addQuantity(quantity);
      records.add(p);
    }
  }

  public List<PortfolioRecord> getRecords() {
    return records;
  }

  public int getRecordQuantity(Sector s) {
    for (PortfolioRecord record : records) {
      if (record.getSector() == s) {
        return record.getQuantity();
      }
    }
    return 0;
  }

  public double getTotalValue(List<MarketOutput> values){
    double res = money;
    for (MarketOutput m : values) {
      for (PortfolioRecord p : records) {
        if (p.getSector() == m.sector()){
          res += p.getQuantity() * m.value();
        }
      }
    }
    return res;
  }
  public double getTotalCost() {
    return entries.stream().mapToDouble(PortfolioEntry::getTotalCost).sum();
  }

  public List<PortfolioRecord> getState() {
    return records.stream()
        .map(
            (p) -> { // deep copies
              PortfolioRecord newP = new PortfolioRecord(p.getSector());
              newP.addQuantity(p.getQuantity());
              return newP;
            })
        .toList();
  }

  @Override
  public void accept(Ievent e) {
    e.execute(this);
  }
}
