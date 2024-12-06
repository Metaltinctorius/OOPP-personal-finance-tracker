package gu.dit213.group28.model.market;

import gu.dit213.group28.model.user.PriceRecord;
import gu.dit213.group28.model.enums.AssetType;
import gu.dit213.group28.model.enums.Sector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.time.LocalDate;
import java.util.Random;

public class Asset {
  private final String ticker;
  private final String name;
  private final Sector sector;
  private final double trend;
  private final List<TrendModifier> trendModifiers;
  private double price;
  private final List<PriceRecord> historicalPrices;
  private final Random rng;
  private double indexValue;

  public Asset(String ticker, String name, Sector sector, double price) {
    this.ticker = ticker;
    this.name = name;
    this.sector = sector;
    this.trend = 0;
    this.price = price;
    this.historicalPrices = new ArrayList<>();
    trendModifiers = new ArrayList<>();
    rng = new Random();
  }

  // Getters
  public String getTicker() {
    return ticker;
  }

  public String getName() {
    return name;
  }

  public Sector getSector() {
    return sector;
  }

  public double getPrice() {
    return price;
  }

  public double getTrend() {
    double sumMods = 0;
    for (TrendModifier mod : trendModifiers) {
      sumMods += mod.getModifier();
    }
    return trend + sumMods;
  }
  public void setIndexValue(double value){
    indexValue = value;
  }
  public double getIndexValue(){
    return indexValue;
  }

  public List<PriceRecord> getHistoricalPrices() {
    return historicalPrices;
  }

  public List<TrendModifier> getTrendModifiers() {
    return trendModifiers;
  }

  public void addTrendModifier(TrendModifier mod) {
    trendModifiers.add(mod);
  }


  public void decrementAssetModifiers() {
    Iterator<TrendModifier> iterator = trendModifiers.iterator();
    while (iterator.hasNext()) {
      TrendModifier mod = iterator.next();
      mod.decrementIterations();
      if (mod.getIterationsLeft() < 1) {
        iterator.remove(); // Safely remove using the iterator
      }
    }
  }

  public void updatePrice() {
    price *= this.getTrend() + Market.getInstance().getTrend();
<<<<<<< HEAD
    price *= 1 + (rng.nextDouble() - 0.5) / 5;

    indexValue *= this.getTrend() + Market.getInstance().getTrend();

=======


    //price *= 1 + (rng.nextDouble() - 0.5) / 5;
>>>>>>> implement-predefined
    historicalPrices.add(new PriceRecord(price, LocalDate.now()));
  }
}
