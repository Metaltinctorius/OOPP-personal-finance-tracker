package gu.dit213.group28.model.market;

import gu.dit213.group28.model.user.PriceRecord;
import gu.dit213.group28.model.enums.AssetType;
import gu.dit213.group28.model.enums.Sector;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Asset
{
  private final String ticker;
  private final String name;
  private final AssetType type;
  private final Sector sector;
  private final double trend;
  private final List<TrendModifier> trendModifiers;
  private double price;
  private final List<PriceRecord> historicalPrices;

  public Asset(String ticker, String name, AssetType type, Sector sector,
               double price, List<PriceRecord> historicalPrices)
  {
    this.ticker = ticker;
    this.name = name;
    this.type = type;
    this.sector = sector;
    this.trend = 1;
    this.price = price;
    this.historicalPrices = historicalPrices;
    trendModifiers = new ArrayList<>();
  }

  //Getters
  public String getTicker()
  {
    return ticker;
  }

  public String getName()
  {
    return name;
  }

  public AssetType getType()
  {
    return type;
  }

  public Sector getSector()
  {
    return sector;
  }

  public double getPrice()
  {
    return price;
  }

  public double getTrend()
  {
    double sumMods = 0;
    for (TrendModifier mod : trendModifiers)
    {
      sumMods += mod.getModifier();
    }
    return trend + sumMods;
  }

  public List<PriceRecord> getHistoricalPrices()
  {
    return historicalPrices;
  }

  public List<TrendModifier> getTrendModifiers()
  {
    return trendModifiers;
  }

  public void addTrendModifier(TrendModifier mod)
  {
    trendModifiers.add(mod);
  }

  public void removeTrendModifier(int id)
  {
    for (int i = 0; i < trendModifiers.size(); i++)
    {
      if (trendModifiers.get(i).getId() == id)
      {
        trendModifiers.remove(i);
        break;
      }
    }
  }

  public void updatePrice()
  {
    price *= this.getTrend() + Market.getInstance("",.07).getTrend();
    System.out.println(price);
    historicalPrices.add(new PriceRecord(price, LocalDate.now()));
  }


}
