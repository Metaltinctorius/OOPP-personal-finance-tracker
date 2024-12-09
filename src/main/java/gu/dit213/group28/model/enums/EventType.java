package gu.dit213.group28.model.enums;

import gu.dit213.group28.model.events.EventPredef;

public enum EventType {
  ONCE(0),
  REPEATING(0);

  private final int stage;

  EventType(int stage) {
    this.stage = stage;
  }

}
