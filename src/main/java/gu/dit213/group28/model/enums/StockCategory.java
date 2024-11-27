package gu.dit213.group28.model.enums;

public enum StockCategory {

  PHARMACEUTICALS(0.0),
  LIFESCIENCE(0.0),
  MILITARY(0.0);

  // ADD MORE

  private double multiplier;

  StockCategory(double multiplier){
    this.multiplier = multiplier;
  }

  public double getMultiplier(){
    return multiplier;
  }

  public void setMultiplier(double multiplier){
    this.multiplier = multiplier;
  }


}
