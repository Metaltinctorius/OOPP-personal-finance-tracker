package gu.dit213.group28.model.events;

import gu.dit213.group28.model.enums.Sector;

public class EventBuy extends Event {

  private final Sector sector;
  private final int amount;

  private double value;

  public EventBuy(int id, String description, Sector sector, int amount) {
    super(id, description);
    this.sector = sector;
    this.amount = amount;
  }

  public Sector getSector() {
    return sector;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public int getAmount() {
    return amount;
  }


  public void execute() {}

  public void unpackage() {
    // return something
  }
}
