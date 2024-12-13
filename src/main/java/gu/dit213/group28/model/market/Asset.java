package gu.dit213.group28.model.market;

import gu.dit213.group28.model.user.PriceRecord;
import gu.dit213.group28.model.enums.Sector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.time.LocalDate;
import java.util.Random;

/**
 * Represents a financial asset in the market, characterized by its ticker, name, sector, price, and trend.
 * The class also maintains historical price records and allows for trend modifications.
 */
public class Asset {
  /** The unique identifier for the asset. */
  private final String ticker;

  /** The name of the asset. */
  private final String name;

  /** The sector to which the asset belongs. */
  private final Sector sector;

  /** The trend value of the asset, representing its general direction of price movement. */
  private final double trend;

  /** List of modifiers that affect the trend of the asset. */
  private final List<TrendModifier> trendModifiers;

  /** The current price of the asset. */
  private double price;

  /** A list of historical price records for the asset. */
  private final List<PriceRecord> historicalPrices;

  /** A random number generator for simulating random behavior. */
  private final Random rng;

  /** The index value of the asset, used for market indices. */
  private double indexValue;

  /**
   * Constructs a new Asset instance.
   *
   * @param ticker The unique identifier for the asset.
   * @param name   The name of the asset.
   * @param sector The sector to which the asset belongs.
   * @param price  The initial price of the asset.
   */
  public Asset(String ticker, String name, Sector sector, double price) {
    this.ticker = ticker;
    this.name = name;
    this.sector = sector;
    this.trend = 0;
    this.price = price;
    this.historicalPrices = new ArrayList<>();
    this.trendModifiers = new ArrayList<>();
    this.rng = new Random();
  }

  /**
   * Retrieves the ticker of the asset.
   *
   * @return The asset's ticker.
   */
  public String getTicker() {
    return ticker;
  }

  /**
   * Retrieves the name of the asset.
   *
   * @return The asset's name.
   */
  public String getName() {
    return name;
  }

  /**
   * Retrieves the sector of the asset.
   *
   * @return The asset's sector.
   */
  public Sector getSector() {
    return sector;
  }

  /**
   * Retrieves the current price of the asset.
   *
   * @return The asset's price.
   */
  public double getPrice() {
    return price;
  }

  /**
   * Computes and retrieves the overall trend of the asset, including modifiers.
   *
   * @return The overall trend of the asset.
   */
  public double getTrend() {
    double sumMods = 0;
    for (TrendModifier mod : trendModifiers) {
      sumMods += mod.getModifier();
    }
    return trend + sumMods;
  }

  /**
   * Sets the index value of the asset.
   *
   * @param value The new index value.
   */
  public void setIndexValue(double value) {
    indexValue = value;
  }


  /**
   * Retrieves the index value of the asset.
   *
   * @return The index value.
   */
  public double getIndexValue() {
    return indexValue;
  }

  /**
   * Retrieves the historical price records of the asset.
   *
   * @return A list of historical price records.
   */
  public List<PriceRecord> getHistoricalPrices() {
    return historicalPrices;
  }

  /**
   * Retrieves the list of trend modifiers affecting the asset.
   *
   * @return A list of trend modifiers.
   */
  public List<TrendModifier> getTrendModifiers() {
    return trendModifiers;
  }

  /**
   * Adds a trend modifier to the asset.
   *
   * @param mod The trend modifier to add.
   */
  public void addTrendModifier(TrendModifier mod) {
    trendModifiers.add(mod);
  }


  /**
   * Decrements the iterations left on all trend modifiers and removes any that have expired.
   */
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

  /**
   * Updates the price of the asset based on its trend and market trends, and records the new price.
   */
  public void updatePrice() {
    price *= this.getTrend() + Market.getInstance().getTrend();

    price *= 1 + (rng.nextDouble() - 0.5) / 20;

    indexValue *= this.getTrend() + Market.getInstance().getTrend();
    historicalPrices.add(new PriceRecord(price, LocalDate.now()));
  }
}
