package gu.dit213.group28.model.events;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.IeventFacade;

import java.util.List;
import java.util.Random;

public class EventFacade implements IeventFacade {

  private final EventLoader loader;
  private final EventManager eventManager;
  private final Random rng = new Random();
  private double randomEventChance;

  public EventFacade() {
    this.loader = new EventLoader();
    loader.loadEvents();
    this.eventManager = new EventManager(loader.getPredefinedEvents(), loader.getReservedIds());
    randomEventChance = -0.2;
  }

  public void addEventToQueue(Event event) {
    eventManager.addToEventQueue(event);
  }

  public List<Event> getEventLog() {
    return eventManager.getEventLog();
  }

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
