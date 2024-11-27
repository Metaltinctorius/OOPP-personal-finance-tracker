package gu.dit213.group28.model;

import gu.dit213.group28.model.enums.Stock;

public class EventCalculator {

  public EventCalculator() {}

  /**
   * CHATGPT: Price Goes Up: In most market scenarios, if a player buys more stocks, the demand
   * increases, which could drive the price up. The increase can be proportional to the number of
   * stocks purchased relative to the market supply.
   *
   * @param currentPrice
   * @param purchasedStocks
   * @param totalAvailableStocks
   * @return Return the new price for the stock after player buys it.
   */
  public double calculateNewPrice(
      double currentPrice, int purchasedStocks, int totalAvailableStocks) {
    double demandFactor = (double) purchasedStocks / totalAvailableStocks;
    return currentPrice * (1 + demandFactor);
  }

  /**
   * In some scenarios, the multiplier might decrease if owning too much of the same stock reduces
   * returns, simulating "diminishing returns" due to market saturation.
   *
   * @param baseMultiplier
   * @param ownedStocks
   * @param diminishingFactor
   * @return
   */
  public double calculateMultiplier(
      double baseMultiplier, int ownedStocks, double diminishingFactor) {
    return baseMultiplier + ownedStocks * diminishingFactor;
  }


  public void adjustSectorValues(Stock purchasedStock, double impactFactor) {
    for (Stock stock : Stock.values()) {
      if (stock.getCategories().containsAll(purchasedStock.getCategories())) {
        stock.setValue(stock.getValue() + (purchasedStock.getValue() * impactFactor));
      }
    }
  }
}
