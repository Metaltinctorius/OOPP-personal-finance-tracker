package gu.dit213.group28.model.events;

import gu.dit213.group28.model.enums.EventType;
import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.ImarketEx;
import gu.dit213.group28.model.interfaces.IuserEx;
import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.market.Market;
import gu.dit213.group28.model.market.TrendModifier;
import java.util.List;
import org.bson.internal.BsonUtil;

public class EventPredef extends Event {

  private final List<Sector> sectors;
  private final String title;
  private final EventType type;
  private int iterations;
  private final String description;
  private final double modifier;

  public EventPredef(
      int id,
      String title,
      String description,
      EventType type,
      int iterations,
      List<Sector> sectorCategories,
      double modifier) {
    super(id);
    this.title = title;
    this.type = type;
    this.description = description;
    this.iterations = iterations;
    this.modifier = modifier;
    this.sectors = sectorCategories;
  }

  public String getDescription() {
    return description;
  }

  public String getTitle() {
    return title;
  }

  public EventType getType() {
    return type;
  }

  public int getIterations() {
    return iterations;
  }

  public double getModifier() {
    return modifier;
  }

  /**
   * Used to validate the iterations specified in the json file, in order to make the handling of
   * events safer. No event that is set to "ONCE" can have iterations > 0, no event set to
   * "REPEATING" can have iterations < 1.
   *
   * @param iterations Iterations either given from creating an event or the json file.
   */
  public void checkIterations(int iterations) {
    if (type == EventType.REPEATING && iterations <= 0) {
      throw new IllegalArgumentException(
          "Repeating events must have iterations > 0: " + iterations);
    }
    if (type == EventType.ONCE && iterations != 0) {
      throw new IllegalArgumentException("One-time events must have iterations = 0: " + iterations);
    }
    this.iterations = iterations;
  }

  /** Used to decrement iterations for repeating events. */
  public void decrementIterations() {
    if (iterations > 0) iterations--;
  }

  public void addSectorToList(Sector sector) {
    sectors.add(sector);
    if (!sectors.contains(sector)) {
      throw new Error("Failed to add sector");
    }
  }


  public List<Sector> getSectors()
  {
    return sectors;
  }

  @Override
  public Sector getSector()
  {
    return null;
  }

  @Override
  public void execute(ImarketEx m) {
    TrendModifier mod = new TrendModifier(this.getModifier(), this.getIterations());
    System.out.println(mod);
    System.out.println(this.getModifier());
    List <Asset>assets = m.getAssets();


    for(Asset a : assets){ // FIX
      if(a.getSector() == this.getSectors().getFirst()){
        System.out.println(this.getSectors().getFirst());
        System.out.println(mod);
        a.addTrendModifier(mod);
      }
    }
  }

  @Override
  public void execute(IuserEx u) {}

  // FOR SEQUENTIAL EVENTS
  /*


  private int totalStages;
  private int stage;

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
   * Used for sequential events. To be done later.
   * @param stage The stage the sequence is currently at
   * @param totalStages The total number of stages in the event.
   * @return Returns builder.

  public OldEvent.EventBuilder setStage(int stage, int totalStages) {
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
  */
}
