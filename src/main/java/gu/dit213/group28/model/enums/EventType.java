package gu.dit213.group28.model.enums;

/** Enum for different types of predefined events. */
public enum EventType {
  ONCE(0),
  REPEATING(0);
  private final int stage;

  EventType(int stage) {
    this.stage = stage;
  }
}
