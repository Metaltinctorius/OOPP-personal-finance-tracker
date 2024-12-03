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

  private Event(EventBuilder builder) {
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
    builder.setPlayerAction(this.action, action.getAmount(), action.getValue());
    return builder.build();
  }

  public static class EventBuilder {
    private final int id;
    private final String description;
    private final List<Sector> categories;
    private final double modifier;
    private final EventType type;
    private PlayerAction action;
    private int iterations;


    /** For sequential events */
    private int totalStages;
    private int stage;

    public EventBuilder(int  id, String description, EventType type, int iterations, List<Sector> categories, double modifier) {
      this.id = id;
      this.description = description;
      this.type = type;
      this.iterations = iterations;
      this.categories = categories;
      this.modifier = modifier;
    }

    /**
     * Optional field. Primarily intended to be used to define an event as a purchase
     * or sell made by the player.
     * @param action The action made by the player for "purchase asset" or "selling asset".
     * @return Builder returns itself.
     */
    public  EventBuilder setPlayerAction(PlayerAction action, int amount, double value){
      this.action = action;
      action.setAmount(amount);
      action.setValue(value);
      return this;
    }

    /**
     * Used for sequential events. To be done later.
     * @param stage The stage the sequence is currently at
     * @param totalStages The total number of stages in the event.
     * @return Returns builder.
     */
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

    /**
     * Used to validate the iterations specified in the json file, in order to make the handling
     * of events safer. No event that is set to "ONCE" can have iterations > 0, no event
     * set to "REPEATING" can have iterations < 1.
     * @param iterations Iterations either given from creating an event or the json file.
     */
    public void checkIterations(int iterations) {
      if (type == EventType.REPEATING && iterations <= 0) {
        throw new IllegalArgumentException("Repeating events must have iterations > 0: " + iterations);
      }
      if (type == EventType.ONCE && iterations != 0) {
        throw new IllegalArgumentException("One-time events must have iterations = 0: " + iterations);
      }
      this.iterations = iterations;
    }

    /**
     * Primary method to produce (create) an event. The builder calls this to create the event.
     * @return Event.
     */
    public Event build() {
      checkIterations(iterations);
      return new Event(this);
    }

  }
}
