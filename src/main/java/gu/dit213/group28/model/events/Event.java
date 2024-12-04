package gu.dit213.group28.model.events;

import gu.dit213.group28.model.interfaces.Ievent;

public abstract class Event implements Ievent {

  private final int id;

  public Event(int id) {
    this.id = id;
  }

  public int getID() {
    return id;
  }
}
