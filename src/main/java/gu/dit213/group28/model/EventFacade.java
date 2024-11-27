package gu.dit213.group28.model;

import gu.dit213.group28.model.enums.Stock;
import gu.dit213.group28.model.enums.StockCategory;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class EventFacade {


  /** A queue for scheduled events */
  private final Queue<Event> eventQueue = new LinkedList<>();

  /** List of every stock on the market, and their multipliers */
  private final List <Stock> stocks = new ArrayList<>();

  /**
   * A list of all events that have been initiated during the game's lifecycle.
   */
  private final List <Event> eventLog = new ArrayList<>();

  /**
   * The List of predefined events from the JSON file.
   */
  private final List <Event> predefinedEvents = new ArrayList<>();

  private final EventCalculator calculator = new EventCalculator();
  public void addEventToQueue(Event event) {
    eventQueue.add(event);
  }





  public void makeUserPurchase(int amount, Stock stock){
    double newPrice = calculator.calculateNewPrice(stock.getValue(), amount, 10000); // totalAvailable needs to fetch from market

    stock.setValue(newPrice);


  }


  public Event getNextEvent() {
    if (eventQueue.isEmpty()) {
      throw new IllegalStateException("No events in the event queue");
    }
    return eventQueue.poll().copy();
  }

  public static int generateRandomIndex(List<Event> events) {
    if (events == null || events.isEmpty()) {
      throw new IllegalArgumentException("List must not be null or empty");
    }
    return new Random().nextInt(events.size());
  }

  public static double generateRandomDouble(double min, double max) {
    if (min > max) {
      throw new IllegalArgumentException("Min cannot be greater than max.");
    }
    Random random = new Random();
    return min + (max - min) * random.nextDouble();
  }


  public void createEvent() {

    int index = generateRandomIndex(gameEvents);
    Event event = gameEvents.get(index).copy();
    Event.EventBuilder builder =
        new Event.EventBuilder(event.getId(), event.getDescription(), event.getCategories(), event.getType(), event.getAction());
    Event event2 = builder.build();
    List <StockCategory> categories = event2.getCategories();
    for(StockCategory category : categories){
      category.setMultiplier(0.01);
    }
  }
}
