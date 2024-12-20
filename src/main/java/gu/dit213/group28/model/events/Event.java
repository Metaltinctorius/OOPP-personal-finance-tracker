package gu.dit213.group28.model.events;

import gu.dit213.group28.model.interfaces.Ievent;

/** Abstract class for all events. */
public abstract class Event implements Ievent {

  private int id;

  /**
   * Constructor for Event.
   *
   * @param id event ID
   */
  public Event(int id) {
    this.id = id;
  }

  /**
   * Getter for event ID.
   *
   * @return event ID
   */
  public int getId() {
    return id;
  }

  /**
   * Setter for event ID.
   *
   * @param id event ID
   */
  protected void setId(int id) {
    this.id = id;
  }
}
