package gu.dit213.group28.model.market;

import gu.dit213.group28.model.enums.Sector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Represents a financial asset in the market, characterized by its ticker, name, sector, price, and
 * trend. The class also maintains historical price records and allows for trend modifications.
 */
public class Asset {

  /** The sector to which the asset belongs. */
  private final Sector sector;

  /** The trend value of the asset, representing its general direction of price movement. */
  private final double trend;

  /** List of modifiers that affect the trend of the asset. */
  private final List<TrendModifier> trendModifiers;

  /** The current price of the asset. */
  private double price;

  /** A random number generator for simulating random behavior. */
  private final Random rng;

  private double indexValue;

  /**
   * Constructs a new Asset instance.
   *
   * @param sector The sector to which the asset belongs.
   * @param price The initial price of the asset.
   */
  public Asset(Sector sector, double price) {
    this.sector = sector;
    this.trend = 0;
    this.price = price;
    this.trendModifiers = new ArrayList<>();
    this.rng = new Random();
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

  /** Decrements the iterations left on all trend modifiers and removes any that have expired. */
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

    indexValue = price;
  }
}
