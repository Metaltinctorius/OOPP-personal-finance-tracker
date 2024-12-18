package gu.dit213.group28.model.market;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Imarket;

import gu.dit213.group28.model.interfaces.ImarketEx;
import java.util.*;

/**
 * Represents a financial market that maintains a list of assets and overall trend modifiers.
 * It implements a singleton pattern to ensure only one instance of the Market exists.
 */
public class Market implements Imarket, ImarketEx {
  /** The name of the market. */
  private final String name;

  /** The base trend of the market. */
  private final double trend;

  /** List of modifiers that affect the market's trend. */
  private final List<TrendModifier> trendModifiers;

  /** The list of assets in the market. */
  private final List<Asset> assets;

  /** The starting value for market index calculations. */
  private final double startValue;

  /** The single instance of the Market, following the singleton pattern. */
  private static Market instance;

  /**
   * Private constructor to initialize a Market instance.
   *
   * @param name  The name of the market.
   * @param trend The initial trend value of the market.
   */
  private Market(String name, double trend) {
    this.name = name;
    this.trend = trend;
    this.assets = new ArrayList<>();
    this.trendModifiers = new ArrayList<>();
    createAssets();
    startValue = 1000;
    initIndex();
  }

  /**
   * Retrieves the single instance of the Market, creating it if necessary.
   *
   * @return The singleton Market instance.
   */
  public static Market getInstance() {
    if (instance == null) {
      synchronized (Market.class) { // Ensure thread safety
        if (instance == null) {
          instance = new Market("NYSE", 1.00565);
        }
      }
    }
    return instance;
  }

  /**
   * Retrieves the name of the market.
   *
   * @return The market's name.
   */
  public String getName() {
    return name;
  }

  /**
   * Computes and retrieves the overall trend of the market, including modifiers.
   *
   * @return The overall market trend.
   */
  public double getTrend() {
    double sumMods = 0;
    for (TrendModifier mod : trendModifiers) {
      sumMods += mod.getModifier();
    }
    return trend + sumMods;
  }

  /**
   * Retrieves the list of assets in the market.
   *
   * @return A list of assets.
   */
  public List<Asset> getAssets() {
    return assets;
  }

  /**
   * Adds a new asset to the market.
   *
   * @param asset The asset to add.
   */
  public void addAsset(Asset asset) {
    assets.add(asset);
  }

  /**
   * Retrieves the list of trend modifiers affecting the market.
   *
   * @return A list of trend modifiers.
   */
  public List<TrendModifier> getTrendModifiers() {
    return trendModifiers;
  }

  /**
   * Adds a new trend modifier to the market.
   *
   * @param mod The trend modifier to add.
   */
  public void addTrendModifier(TrendModifier mod) {
    trendModifiers.add(mod);
  }

  /*    public void removeTrendModifier() {
    for (int i = 0; i < trendModifiers.size(); i++) {
      if (trendModifiers.get(i).getIterationsLeft() == id) {
        trendModifiers.remove(i);
        break;
      }
    }
  }*/

  /**
   * Decrements the iterations left on all market trend modifiers and removes any that have expired.
   */
  public void decrementMarketModifiers() {
    Iterator<TrendModifier> iterator = trendModifiers.iterator();
    while (iterator.hasNext()) {
      TrendModifier mod = iterator.next();
      mod.decrementIterations();
      if (mod.getIterationsLeft() < 1) {
        iterator.remove();
      }
    }
  }

  /**
   * Decrements the iterations left on all trend modifiers across the market and its assets.
   */
  public void decrementAllModifiers() {
    decrementMarketModifiers();
    for (Asset asset : assets) {
      asset.decrementAssetModifiers();
    }
  }

  /**
   * Initializes the index values for all assets in the market.
   */
  private void initIndex() {
    for (Asset a : assets) {
      a.setIndexValue(startValue / assets.size());
    }
  }


  /**
   * Computes and retrieves the current value of the market index.
   *
   * @return The market index value.
   */
  public double getIndexValue(){
    double res = 0;
    for (Asset a : assets) {
      res += 0.5 * a.getPrice() * a.getIndexValue();
    }
    return res;
  }

  /**
   * Accepts a market event and executes it using the visitor pattern.
   *
   * @param e The event to execute.
   */
  public void accept(Ievent e) {
    e.execute(this);
  }

  /**
   * Creates and initializes assets for the market across all sectors.
   */
  private void createAssets() {
    Random rng = new Random();
    Sector[] sectors = Sector.values();
    for (Sector sector : sectors) {
      if (sector == Sector.INDEX) {
        continue;
      }
      double price = 500 + 500 * rng.nextDouble();
      Asset a = new Asset("", "", sector, price);
      assets.add(a);
    }
  }
}

