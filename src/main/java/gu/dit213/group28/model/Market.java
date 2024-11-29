package gu.dit213.group28.model;

import java.util.*;

public class Market implements Imarket
{
  private final String name;
  private double trend;
  private final List<Asset> assets;

  // The single instance of the Market
  private static Market instance;

  // Private constructor to prevent direct instantiation
  private Market(String name, double trend)
  {
    this.name = name;
    this.trend = trend;
    this.assets = new ArrayList<>();
  }

  // Public static method to get the single instance
  public static Market getInstance(String name, double trend)
  {
    if (instance == null)
    {
      synchronized (Market.class)
      { // Ensure thread safety
        if (instance == null)
        {
          instance = new Market(name, trend);
        }
      }
    }
    return instance;
  }

  // Getters
  public String getName()
  {
    return name;
  }

  public double getTrend()
  {
    return trend;
  }

  public List<Asset> getAssets()
  {
    return assets;
  }

  public void addAsset(Asset asset)
  {
    assets.add(asset);
  }

  public void updateTrend(double multiplier)
  {
    trend *= multiplier;
  }

  /*
  public void visit(Ievent e)
  {
    e.execute(this);
  }
*/
}

