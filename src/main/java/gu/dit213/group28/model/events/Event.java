package gu.dit213.group28.model.events;

import gu.dit213.group28.model.interfaces.Ievent;

/** Abstract class for all events. */
public abstract class Event implements Ievent {

  private int id;

  public Event(int id) {
    this.id = id;
  }

  /**
   * Getter for event ID
   *
   * @return event ID
   */
  public int getID() {
    return id;
  }

  protected void setID(int id) {
    this.id = id;
  }
}
