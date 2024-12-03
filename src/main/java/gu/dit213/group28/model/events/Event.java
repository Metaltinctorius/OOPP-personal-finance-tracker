package gu.dit213.group28.model.events;

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
  private int iterations;


  /** For sequential events */
  private final int totalStages;
  private int stage;

  public Event(EventBuilder builder) {
    this.id = builder.id;
    this.description = builder.description;
    this.type = builder.type;
    this.iterations = builder.iterations;
    this.categories = builder.categories;
    this.modifier = builder.modifier;
    this.totalStages = builder.totalStages; // For sequential events
    this.stage = builder.stage; // For sequential events
    this.action = builder.action;
  }

  public int getId() { return id; }

  public String getDescription() {
    return description;
  }

  public EventType getType() {
    return type;
  }

  public int getIterations() {
    return iterations;
  }

  public List<Sector> getCategories() {
    return categories;
  }

  public double getModifier() { return modifier; }

  public PlayerAction getAction(){ return action ;}


  /** ** ** For sequential events ** ** **/
  public int getTotalStages() {
    return totalStages;
  }
  public int getStage() {
    return stage;
  }
  public void advanceStage() {
    if (stage <= totalStages) stage++;
  }


  /**
   * Used to decrement iterations for repeating events.
   */
  public void decrementIterations() {
    if (iterations > 0) iterations--;
  }

  /**
   * The event object can call this method to produce a copy of itself.
   * This is to avoid references being passed around.
   * @return Returns a copy of the Event, instead of a reference to the event.
   */
  public Event copy(){
    EventBuilder builder = new EventBuilder(this. id, this.description, this.type, this.iterations,  this.categories, this.modifier);
    if(type == EventType.SEQUENTIAL){
      builder.setStage(this.stage, this.totalStages);
    }
    builder.setPlayerAction(this.action);
    return builder.build();
  }


}
