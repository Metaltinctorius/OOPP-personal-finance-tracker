package gu.dit213.group28.model;

import gu.dit213.group28.model.enums.CompanyStocks;
import gu.dit213.group28.model.enums.EventType;
import gu.dit213.group28.model.enums.PlayerAction;
import gu.dit213.group28.model.enums.StockCategory;
import gu.dit213.group28.model.events.EventLoader;
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
  private List <Event> predefinedEvents = new ArrayList<>();

  private final EventCalculator calculator;

  private final EventLoader loader;

  private final IdManager idManager;
  public void addEventToQueue(Event event) {
    eventQueue.add(event);
  }


  public EventFacade(){
    this.calculator = new EventCalculator();
    this.loader = new EventLoader();
    this.idManager = new IdManager();
    getPredefinedEvents();
  }

  public void getPredefinedEvents(){
    predefinedEvents = loader.getPredefinedEvents();
  }

  public void getReservedIds(){

  }


  public void buyStock(Stock stock, int amount) {
    if (amount > stock.getQuantity()) {
      throw new IllegalArgumentException("Required amount supersedes the current stock quantity, try a lower amount.");
    }
    if(amount > 0 ){
      for (int i = 0; i < amount; i++) {
        makeUserPurchase(1, stock);
      }
    } else {
      throw new IllegalArgumentException("Stock amount cannot be 0 or negative");
    }
  }

  private void makeUserPurchase(int amount, Stock stock){
    int quantity = stock.getQuantity(); // The amount of stocks available
    int newQuantity = quantity - amount; // How many are left after user purschase

    double newPrice = calculator.calculateNewPrice(stock.getValue(), amount, quantity);
    double newMultiplier = calculator.calculateMultiplier(stock.getMultiplier(), amount, quantity);

    Event.EventBuilder builder = new Event.EventBuilder(generateId(), stock.getCompanyStock().toString(), stock.getCompanyStock().getCategories(),
        EventType.ONCE, PlayerAction.BUY_STOCK);

    builder.build();

    adjustSectorValues();


  }

  public int generateId(){
    int bound = 2000;
    int id = new Random().nextInt(bound);
    for(Event e : predefinedEvents){
      if (e.getId() != id){
        return id;
      }
    }
  }

  public void adjustStock(Stock stock, int newQuantity, double newMultiplier, double newPrice){
    stock.setQuantity(newQuantity);
    stock.setValue(newPrice);
    stock.setMultiplier(newMultiplier);
  }
  public void adjustSectorValues(CompanyStocks purchasedStock, double impactFactor) {
    for (CompanyStocks stock : CompanyStocks.values()) {
      if (stock.getCategories().containsAll(purchasedStock.getCategories())) {
        stock.setValue(stock.getValue() + (purchasedStock.getValue() * impactFactor));
        stock.setMultiplier(stock.getQuantity() + (purchasedStock.getMultiplier()));
      }
    }
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
