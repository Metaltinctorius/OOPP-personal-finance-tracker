package gu.dit213.group28.model.user;

import gu.dit213.group28.model.records.MarketOutput;
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

  public double getCurrency() {
    return money;
  }

  public void addCurrency(double amount) {
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

  public void addRecord(Sector sector, int quantity) {
    boolean exists = false;
    for (PortfolioRecord record : records) {
      if (record.getSector() == sector) {
        record.addQuantity(quantity);
        exists = true;
      }
    }
    if (!exists) {
      PortfolioRecord p = new PortfolioRecord(sector);
      p.addQuantity(quantity);
      records.add(p);
    }
  }

  public List<PortfolioRecord> getRecords() {
    return records;
  }

  public int getRecordQuantity(Sector sector) {
    for (PortfolioRecord record : records) {
      if (record.getSector() == sector) {
        return record.getQuantity();
      }
    }
    return 0;
  }

  public double getTotalValue(List<MarketOutput> values) {
    double res = money;
    for (MarketOutput m : values) {
      for (PortfolioRecord p : records) {
        if (p.getSector() == m.sector()) {
          res += p.getQuantity() * m.value();
        }
      }
    }
    return res;
  }

  public double getTotalCost() {
    return entries.stream().mapToDouble(PortfolioEntry::getTotalCost).sum();
  }

  @Override
  public void accept(Ievent e) {
    e.execute(this);
  }
}
