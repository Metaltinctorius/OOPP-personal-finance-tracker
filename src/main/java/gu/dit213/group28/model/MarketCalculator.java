package gu.dit213.group28.model;

import java.util.Random;

public class MarketCalculator
{

  public MarketCalculator() {}

  /**
   *    * CHATGPT: Price Goes Up: In most market scenarios, if a player buys more stocks, the demand
   *    * increases, which could drive the price up. The increase can be proportional to the number of
   *    * stocks purchased relative to the market supply.
   * @param currentPrice
   * @param amountPurchased
   * @param totalAvailable
   * @return
   */
  public double calculateNewPrice(double currentPrice, int amountPurchased, int totalAvailable) {
    double demandFactor = (double) amountPurchased / totalAvailable;
    double priceIncrease = currentPrice * demandFactor * generateRandomDouble(0.1, 0.4);
    return currentPrice + priceIncrease;
  }

  /**
   *  In some scenarios, the multiplier might decrease if owning too much of the same stock reduces
   *  returns, simulating "diminishing returns" due to market saturation.
   * @param currentMultiplier
   * @param amount
   * @param totalAvailable
   * @return
   */
  public double calculateMultiplier(double currentMultiplier, int amount, int totalAvailable) {
    double randomness = generateRandomDouble(0.01, 0.08);
    double increaseFactor = 2.5 + randomness;
    double decreaseFactor = 0.0009 + randomness;
    double incrementValue = increaseFactor * (amount / (double) totalAvailable);
    double newMultiplier = currentMultiplier + incrementValue - decreaseFactor;

    System.out.println("currentMultiplier: " + currentMultiplier);
    System.out.println("increaseFactor: " + increaseFactor);
    System.out.println("decreaseFactor: " + decreaseFactor);
    System.out.println("incrementValue: " + incrementValue);
    System.out.println("newMultiplier: " + newMultiplier);

    // Ensure multiplier doesn't go negative
    return newMultiplier;
    //return Math.max(newMultiplier, 0.0);
  }





  public static double generateRandomDouble(double min, double max) {
    if (min > max) {
      throw new IllegalArgumentException("Min cannot be greater than max.");
    }
    Random random = new Random();
    return min + (max - min) * random.nextDouble();
  }

}
