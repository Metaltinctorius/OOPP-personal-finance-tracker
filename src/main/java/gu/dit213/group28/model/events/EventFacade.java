package gu.dit213.group28.model.events;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.IeventFacade;
import java.util.List;
import java.util.Random;

/** Class that works as a facade and API for the event manager. */
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
   * Gets the basic tick event.
   *
   * @return Basic tick events.
   */
  @Override
  public Ievent getTickEvent(int tick) {
    return new EventTick(tick);
  }

  @Override
  public Ievent getBuyEvent(Sector sector, int quantity) {
    return new EventBuy(sector, quantity);
  }

  @Override
  public Ievent getSellEvent(Sector sector, int quantity) {
    return new EventSell(sector, quantity);
  }

  @Override
  public Ievent getPredefinedEvent() {
    Ievent e = eventManager.getRandomEvent();
    addEventToLog(e);
    return e;
  }

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
