package gu.dit213.group28.model.enums;

import gu.dit213.group28.model.Event;

public enum EventType {

  ONCE {
    @Override
    public boolean process(Event event) {
      return true;
    }
  },
  REPEATING {
    @Override
    public boolean process(Event event) {
      event.decrementIterations();
      return event.getIterationsLeft() <= 0; // Returns true of the iterations are completed.
    }
  },
  SEQUENTIAL {
    @Override
    public boolean process(Event event) {
      event.advanceStage();
      return event.getStage() > event.getTotalStages(); // Returns true if the stage has passed the total stages.
    }
  };


  public abstract boolean process(Event event);
}
