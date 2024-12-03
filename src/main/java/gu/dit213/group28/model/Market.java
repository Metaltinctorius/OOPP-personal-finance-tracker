package gu.dit213.group28.model;

import java.util.*;

public class Market implements Imarket
{
  private final String name;
  private final double trend;
  private List<TrendModifier> trendModifiers;
  private final List<Asset> assets;

  // The single instance of the Market
  private static Market instance;

  // Private constructor to prevent direct instantiation
  private Market(String name, double trend)
  {
    this.name = name;
    this.trend = trend;
    this.assets = new ArrayList<>();
    this.trendModifiers = new ArrayList<>();
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
    double sumMods = 0;
    for (TrendModifier mod : trendModifiers)
    {
      sumMods += mod.getModifier();
    }
    return trend + sumMods;
  }

  public List<Asset> getAssets()
  {
    return assets;
  }

  public void addAsset(Asset asset)
  {
    assets.add(asset);
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
//TODO: visitor pattern with Event
/*
  public void visit(Ievent e)
  {
    e.execute(this);
  }

  public String getState()
  {
    return "state"
  }
*/
}

