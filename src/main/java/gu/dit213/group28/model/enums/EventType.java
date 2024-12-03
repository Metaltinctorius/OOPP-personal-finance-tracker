package gu.dit213.group28.model.enums;

import gu.dit213.group28.model.events.OldEvent;

public enum EventType {

  ONCE(0) {
    @Override
    public boolean process(OldEvent event) {
      return true;
    }
  },
  REPEATING(0) {
    @Override
    public boolean process(OldEvent event) {
      event.decrementIterations();
      return event.getIterations() <= 0; // Returns true if the iterations are completed.
    }
  },
  SEQUENTIAL(0) {
    @Override
    public boolean process(OldEvent event) {
      event.advanceStage();
      return event.getStage() > event.getTotalStages(); // Returns true if the stage has passed the total stages.
    }
  };

  private final int stage;


  EventType(int stage) {
    this.stage = stage;
  }

  // Getter for stage
  public int getStage() {
    return stage;
  }


  public abstract boolean process(OldEvent event);
}
