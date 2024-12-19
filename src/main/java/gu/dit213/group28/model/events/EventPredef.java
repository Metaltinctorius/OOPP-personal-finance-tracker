package gu.dit213.group28.model.events;

import gu.dit213.group28.model.enums.EventType;
import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.ImarketEx;
import gu.dit213.group28.model.interfaces.IuserEx;
import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.market.TrendModifier;
import java.util.List;

public class EventPredef extends Event {

  private final List<Sector> sectors;
  private final String title;
  private final EventType type;
  private final int iterations;
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
   * Returns the list of sectors for the event.
   *
   * @return List of sectors
   */
  public List<Sector> getSectors() {
    return sectors;
  }

  /**
   * Method that executes the event. This adds the events modifier to the market.
   *
   * @param m ImarketEx to be executed upon
   */
  @Override
  public void execute(ImarketEx m) {
    TrendModifier mod = new TrendModifier(this.getModifier(), this.getIterations());
    List<Asset> assets = m.getAssets();
    if (this.getSectors().isEmpty()) {
      m.addTrendModifier(mod);
    } else {
      for (Asset a : assets) {
        for (Sector s : this.getSectors()) {
          if (a.getSector() == s) {
            a.addTrendModifier(mod);
          }
        }
      }
    }
  }

  @Override
  public void execute(IuserEx u) {}
}
