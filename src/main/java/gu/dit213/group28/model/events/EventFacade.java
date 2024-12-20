package gu.dit213.group28.model.events;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.IeventFacade;
import java.util.List;
import java.util.Random;

/** This class works as a facade and API for the event manager. */
public class EventFacade implements IeventFacade {

  private final EventManager eventManager;
  private final Random rng = new Random();
  private double randomEventChance;

  /**
   * Constructor for EventFacade. Initializes the event manager and loads the events from the json
   * file.
   */
  public EventFacade() {
    this.eventManager = new EventManager();
    eventManager.loadEvents(eventManager.getEventFile());
    randomEventChance = -0.2;
  }

  public void loadTestEvents() {
    eventManager.loadEvents(eventManager.getTestFile());
  }

  /**
   * Returns the event log (the history of passed events).
   *
   * @return The list of events.
   */
  public List<Ievent> getEventLog() {
    return eventManager.getEventLog();
  }

  /**
   * Adds event to the log (history) of events.
   *
   * @param event to store in the log
   */
  public void addEventToLog(Ievent event) {
    eventManager.addToEventLog(event);
  }

  /**
   * Creates a basic tick event.
   *
   * @param tick The current game tick.
   * @return The tick event.
   */
  @Override
  public Ievent getTickEvent(int tick) {
    return new EventTick(tick);
  }

  /**
   * Creates a buy event.
   *
   * @param sector The sector to buy from.
   * @param quantity The quantity to buy.
   * @return The buy event.
   */
  @Override
  public Ievent getBuyEvent(Sector sector, int quantity) {
    return new EventBuy(sector, quantity);
  }

  /**
   * Creates a sell event.
   *
   * @param sector The sector to sell from.
   * @param quantity The quantity to sell.
   * @return The sell event.
   */
  @Override
  public Ievent getSellEvent(Sector sector, int quantity) {
    return new EventSell(sector, quantity);
  }

  /**
   * Gets a random predefined event from the predefined event list.
   *
   * @return The predefined event.
   */
  @Override
  public Ievent getPredefinedEvent() {
    Ievent e = eventManager.getRandomEvent();
    addEventToLog(e);
    return e;
  }

  /**
   * Checks if a random event should be created. Each call increases the probability for an event to
   * be created.
   *
   * @return True if a random event should be created.
   */
  @Override
  public boolean isRandomEventReady() {
    if (rng.nextDouble() < randomEventChance) {
      randomEventChance = -0.2;
      return true;
    }
    randomEventChance += 0.2;
    return false;
  }
}
