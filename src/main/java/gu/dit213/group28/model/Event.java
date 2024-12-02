package gu.dit213.group28.model;

import gu.dit213.group28.model.enums.EventType;
import gu.dit213.group28.model.enums.PlayerAction;
import gu.dit213.group28.model.enums.Sector;
import java.util.List;

public class Event {


  private final int id;
  private final String description;
  private final EventType type;
  private final PlayerAction action;
  private final List<Sector> categories;

  private final double modifier;

  /** For repeating events */
  private int iterationsLeft;

  /** For sequential events */
  private final int totalStages;
  private int stage;

  private Event(EventBuilder builder) {
    this.id = builder.id;
    this.description = builder.description;
    this.type = builder.type;
    this.categories = builder.categories;
    this.modifier = builder.modifier;
    this.iterationsLeft = builder.iterationsLeft;
    this.totalStages = builder.totalStages;
    this.stage = builder.stage;
    this.action = builder.action;
  }

  public int getId() { return id; }

  public String getDescription() {
    return description;
  }

  public EventType getType() {
    return type;
  }

  public PlayerAction getAction(){ return action ;}

  public List<Sector> getCategories() {
    return categories;
  }

  public double getModifier() { return modifier; }

  public int getIterationsLeft() {
    return iterationsLeft;
  }

  public int getTotalStages() {
    return totalStages;
  }

  public int getStage() {
    return stage;
  }

  public void decrementIterations() {
    if (iterationsLeft > 0) iterationsLeft--;
  }

  public void advanceStage() {
    if (stage <= totalStages) stage++;
  }

  /**
   * The event object can call this method to produce a copy of itself.
   * This is to avoid references
   * @return Returns a copy of the Event, instead of a reference to the event.
   */
  public Event copy(){
    EventBuilder builder = new EventBuilder(this. id, this.description, this.categories, this.modifier, this.type);

    if(type == EventType.REPEATING){
      builder.setIterations(this.iterationsLeft);
    }
    if(type == EventType.SEQUENTIAL){
      builder.setStage(this.stage, this.totalStages);
    }

    builder.setPlayerAction(this.action);



    return builder.build();
  }

  public static class EventBuilder {

    private final int id;
    private final String description;
    private final List<Sector> categories;

    private final double modifier;
    private final EventType type;

    private PlayerAction action;

    /** For repeating events */
    private int iterationsLeft;

    /** For sequential events */
    private int totalStages;

    private int stage;

    public EventBuilder(int  id, String description, List<Sector> categories,double modifier, EventType type) {
      this.id = id;
      this.description = description;
      this.categories = categories;
      this.modifier = modifier;
      this.type = type;
    }

    public  EventBuilder setPlayerAction(PlayerAction action){
      System.out.println("INSIDE BUILDER: " + action);
      this.action = action;
      return this;
    }

    public EventBuilder setStage(int stage, int totalStages) {
      if (type != EventType.SEQUENTIAL) {
        throw new IllegalStateException(
            "Can only set stages for sequential event types! Invalid argument: " + type);
      }
      if(totalStages <= 0 || stage < 0) {
        throw new IllegalStateException("Stages and totalStages need to be larger than 0");
      }
      this.stage = stage;
      this.totalStages = totalStages;
      return this;
    }

    public EventBuilder setIterations(int iterationsLeft) {
      if (type != EventType.REPEATING) {
        throw new IllegalStateException(
            "Can only set iterations for repeating event types! Invalid argument: " + type);
      }
      if( iterationsLeft <= 0){
        throw new IllegalStateException("REPEATING events must have positive iterations.");
      }
      this.iterationsLeft = iterationsLeft;
      return this;
    }

    public Event build() {
      return new Event(this);
    }
  }
}
