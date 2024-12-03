package gu.dit213.group28.model.market;

public class TrendModifier
{
  private double modifier;
  private final int id;

  public TrendModifier(double modifier, int id)
  {
    this.modifier = modifier;
    this.id = id;
  }

  public double getModifier()
  {
    return modifier;
  }

  public int getId()
  {
    return id;
  }

  public void setModifier(double modifier)
  {
    this.modifier = modifier;
  }
}
