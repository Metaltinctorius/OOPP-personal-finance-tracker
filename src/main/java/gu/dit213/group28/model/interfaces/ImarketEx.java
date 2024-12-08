package gu.dit213.group28.model.interfaces;

import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.market.TrendModifier;
import java.util.List;

/** Interface used by events for executing on a market. */
public interface ImarketEx {
  /** Gets the list of assets listed on the market */
  List<Asset> getAssets();

  /** Gets the current value of the index fund of the market. */
  double getIndexValue();

  /** Adds a new modifier to the markets growth rate. */
  void addTrendModifier(TrendModifier mod);
}
