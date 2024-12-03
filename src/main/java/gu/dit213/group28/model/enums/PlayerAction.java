package gu.dit213.group28.model.enums;

public enum PlayerAction {



  NONE(0, 0.0),
  SELL_STOCK(0, 0.0),
  BUY_STOCK(0,0.0);


  private int amount;
  private double value;

  PlayerAction(int amount, double value){
    this.amount = amount;
    this.value = value;
  }

  public void setAmount(int amount){
    this.amount = amount;
  }

  public int getAmount(){
    return amount;
  }

  public void setValue(double value){
    this.value = value;
  }

  public double getValue(){
    return value;
  }


}
