package gu.dit213.group28.model.market;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Imarket;

import gu.dit213.group28.model.interfaces.ImarketEx;
import java.util.*;

public class Market implements Imarket, ImarketEx {
  private final String name;
  private final double trend;
  private List<TrendModifier> trendModifiers;
  private final List<Asset> assets;
  private final double startValue;

  // The single instance of the Market
  private static Market instance;

  // Private constructor to prevent direct instantiation
  private Market(String name, double trend) {
    this.name = name;
    this.trend = trend;
    this.assets = new ArrayList<>();
    this.trendModifiers = new ArrayList<>();
    createAssets();
    startValue = 1000;
    initIndex();
  }

  // Public static method to get the single instance
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

  // Getters
  public String getName() {
    return name;
  }

  public double getTrend() {
    double sumMods = 0;
    for (TrendModifier mod : trendModifiers) {
      sumMods += mod.getModifier();
    }
    return trend + sumMods;
  }

  public List<Asset> getAssets() {
    return assets;
  }

  public void addAsset(Asset asset) {
    assets.add(asset);
  }

  public List<TrendModifier> getTrendModifiers() {
    return trendModifiers;
  }

  public void addTrendModifier(TrendModifier mod) {
    trendModifiers.add(mod);
  }

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

  public void decrementAllModifiers() {
    decrementMarketModifiers();
    for (Asset asset : assets) {
      asset.decrementAssetModifiers();
    }
  }

  private void initIndex() {
    for (Asset a : assets) {
      a.setIndexValue(startValue / assets.size());
    }
  }

  public double getIndexValue() {
    double res = 0;
    for (Asset a : assets) {
      res += 0.5 * a.getPrice() * a.getIndexValue();
    }
    return res;
  }

  // TODO: visitor pattern with Event

  public void accept(Ievent e) {
    e.execute(this);
  }

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
