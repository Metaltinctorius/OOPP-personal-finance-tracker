package gu.dit213.group28.model.market;

public class TrendModifier {
  private double modifier;
  private int iterationsLeft;

  public TrendModifier(double modifier, int iterations) {
    this.modifier = modifier;
    this.iterationsLeft = iterations;
  }

  public double getModifier() {
    return modifier;
  }

  public int getIterationsLeft() {
    return iterationsLeft;
  }

  public void decrementIterations() {
    if (iterationsLeft > 0) {
      iterationsLeft--;
    }
  }

  public void setModifier(double modifier) {
    this.modifier = modifier;
  }
}
