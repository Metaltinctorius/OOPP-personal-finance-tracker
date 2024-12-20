package gu.dit213.group28.model.interfaces;

import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.market.TrendModifier;
import java.util.List;

/** Interface used by events for executing on a market. */
public interface ImarketEx {

  /**
   * Gets the list of assets listed on the market.
   *
   * @return The list of assets.
   */
  List<Asset> getAssets();

  /**
   * Gets the current value of the index fund of the market.
   *
   * @return The value of the index.
   */
  double getIndexValue();

  /**
   * Adds a new modifier to the markets growth rate.
   *
   * @param mod The modifier to add.
   */
  void addTrendModifier(TrendModifier mod);

  /** Decrements the timer on all current modifiers inside the market. */
  void decrementAllModifiers();
}
