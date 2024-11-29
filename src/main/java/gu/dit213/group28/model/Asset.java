package gu.dit213.group28.model;

import gu.dit213.group28.model.enums.AssetType;
import gu.dit213.group28.model.enums.Sector;
import java.util.List;
import java.time.LocalDate;

public class Asset
{
  private final String ticker;
  private final String name;
  private final AssetType type;
  private final Sector sector;
  private double trend;
  //private final List<TrendModifiers> trendModifiers;
  private double price;
  private final List<PriceRecord> historicalPrices;

  public Asset(String ticker, String name, AssetType type, Sector sector, double trend,
               double price, List<PriceRecord> historicalPrices)
  {
    this.ticker = ticker;
    this.name = name;
    this.type = type;
    this.sector = sector;
    this.trend = trend;
    this.price = price;
    this.historicalPrices = historicalPrices;
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

  public double getTrend()
  {
    return trend;
  }

  public double getPrice()
  {
    return price;
  }

  public List<PriceRecord> getHistoricalPrices()
  {
    return historicalPrices;
  }

  public void updateTrend(double multiplier)
  {
    trend *= multiplier;
  }

  /*
   public List<Double> getTrendModifiers()

   public void addTrendModifier(double modifier)

   public void removeTrendModifier(int id)
   */




  public void updatePrice()
  {
    price *= trend;
    historicalPrices.add(new PriceRecord(price, LocalDate.now()));
  }


}
