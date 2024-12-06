package gu.dit213.group28.model.interfaces;

import gu.dit213.group28.model.MarketOutput;
import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.user.PortfolioEntry;
import gu.dit213.group28.model.user.PortfolioRecord;
import java.util.List;

public interface IuserEx {
  double getMoney();

  void addMoney(double amount);

  void addEntry(PortfolioEntry entry);

  void removeEntry(PortfolioEntry entry);

  List<PortfolioEntry> getEntries();

  double getTotalCost();

  void addRecord(Sector s, int quantity);

  List<PortfolioRecord> getRecords();

  int getRecordQuantity(Sector sector);
  double getTotalValue(List<MarketOutput> m);
}
