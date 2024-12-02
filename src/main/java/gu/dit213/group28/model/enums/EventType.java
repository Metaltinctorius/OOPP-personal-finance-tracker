package gu.dit213.group28.model.enums;

import gu.dit213.group28.model.Event;

public enum EventType {

  ONCE(0) {
    @Override
    public boolean process(Event event) {
      return true;
    }
  },
  REPEATING(0) {
    @Override
    public boolean process(Event event) {
      event.decrementIterations();
      return event.getIterationsLeft() <= 0; // Returns true if the iterations are completed.
    }
  },
  SEQUENTIAL(0) {
    @Override
    public boolean process(Event event) {
      event.advanceStage();
      return event.getStage() > event.getTotalStages(); // Returns true if the stage has passed the total stages.
    }
  };

  private final int stage; // Make the field `final` since it's immutable in enums.

  // Constructor must follow the constants.
  EventType(int stage) {
    this.stage = stage;
  }

  // Getter for stage
  public int getStage() {
    return stage;
  }

  // Abstract method to be implemented by each constant
  public abstract boolean process(Event event);
}
