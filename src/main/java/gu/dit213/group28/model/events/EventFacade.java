package gu.dit213.group28.model.events;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.IeventFacade;

import gu.dit213.group28.model.interfaces.IeventLoader;
import java.util.List;
import java.util.Random;

public class EventFacade implements IeventFacade
{

  private final EventManager eventManager;
  private final Random rng = new Random();
  private double randomEventChance;

  public EventFacade() {
    this.eventManager = new EventManager();
    randomEventChance = -0.2;
  }

  /**
   * Adds an event to the event queue, this is useful for scheduled events.
   * @param event to add to the queue
   */
  public void addEventToQueue(Event event) {
    eventManager.addToEventQueue(event);
  }

  /**
   * Returns the event log (the history of passed events).
   * @return List <Event>
   */
  public List<Event> getEventLog() {
    return eventManager.getEventLog();
  }

  /**
   * Adds event to the log (history) of events.
   * @param event to store in the log
   */
  public void addEventToLog(Ievent event) {
    eventManager.addToEventLog((Event) event); // TODO MAYBE CHANGE FROM CASTING?
  }

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
