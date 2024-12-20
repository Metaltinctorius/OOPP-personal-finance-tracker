package gu.dit213.group28.model.enums;

/** Enum for different types of predefined events. */
public enum EventType {

  /** Marker for a one time event. */
  ONCE(0),
  /** Marker for a repeating event. */
  REPEATING(0);

  private final int stage;

  EventType(int stage) {
    this.stage = stage;
  }
}
