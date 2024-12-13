package gu.dit213.group28.model.market;

/**
 * Represents a modifier that affects the trend of an asset or market.
 * Each modifier has a value and a limited number of iterations it remains active.
 */
public class TrendModifier {
  /** The value of the trend modification. */
  private double modifier;

  /** The number of iterations left before the modifier expires. */
  private int iterationsLeft;

  /**
   * Constructs a new TrendModifier instance.
   *
   * @param modifier  The value of the trend modification.
   * @param iterations The number of iterations the modifier will remain active.
   */
  public TrendModifier(double modifier, int iterations) {
    this.modifier = modifier;
    this.iterationsLeft = iterations;
  }

  /**
   * Retrieves the value of the trend modification.
   *
   * @return The modifier value.
   */
  public double getModifier() {
    return modifier;
  }

  /**
   * Retrieves the number of iterations left for the modifier.
   *
   * @return The iterations left.
   */
  public int getIterationsLeft() {
    return iterationsLeft;
  }

  /**
   * Decrements the number of iterations left by one.
   * If the iterations left are already zero, no action is taken.
   */
  public void decrementIterations() {
    if (iterationsLeft > 0) {
      iterationsLeft--;
    }
  }

  /**
   * Sets a new value for the trend modification.
   *
   * @param modifier The new modifier value.
   */
  public void setModifier(double modifier) {
    this.modifier = modifier;
  }
}
