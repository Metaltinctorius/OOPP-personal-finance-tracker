package gu.dit213.group28.model.events;

import gu.dit213.group28.model.enums.EventType;
import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.ImarketEx;
import gu.dit213.group28.model.interfaces.IuserEx;
import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.market.TrendModifier;
import java.util.List;

/** This class represents predefined events that can be loaded from a json file. */
public class EventPredef extends Event {

  private final List<Sector> sectors;
  private final String title;
  private final EventType type;
  private final int iterations;
  private final String description;
  private final double modifier;

  /**
   * Constructor for EventPredef.
   *
   * @param id The id of the event.
   * @param title The title of the event.
   * @param description The description of the event.
   * @param type The type of the event.
   * @param iterations The number of iterations the event will last.
   * @param sectorCategories The sectors the event will affect.
   * @param modifier The modifier of the event.
   */
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

  /**
   * Returns the description of the event.
   *
   * @return Description of the event
   */
  public String getDescription() {
    return description;
  }

  /**
   * Returns the title of the event.
   *
   * @return Title of the event
   */
  public String getTitle() {
    return title;
  }

  /**
   * Returns the type of the event.
   *
   * @return Type of the event
   */
  public EventType getType() {
    return type;
  }

  /**
   * Returns the number of iterations the event will last.
   *
   * @return Number of iterations
   */
  public int getIterations() {
    return iterations;
  }

  /**
   * Returns the modifier of the event.
   *
   * @return Modifier of the event
   */
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
   * Executes event on given ImarketEx. This adds the events modifier to the market.
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

  /**
   * Executes event on given IuserEx.
   *
   * @param u IuserEx to be executed upon
   */
  @Override
  public void execute(IuserEx u) {}
}
