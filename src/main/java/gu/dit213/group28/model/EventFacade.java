package gu.dit213.group28.model;

import gu.dit213.group28.model.enums.EventType;
import gu.dit213.group28.model.enums.PlayerAction;
import gu.dit213.group28.model.events.EventLoader;
import java.util.List;
import java.util.Random;

public class EventFacade {





  private final EventLoader loader;
  private final EventManager eventManager;
  private final PurchasingManager purchasingManager;

  public EventFacade(){
    this.loader = new EventLoader();
    this.eventManager = new EventManager(loader.getPredefinedEvents(), loader.getReservedIds());
    this.purchasingManager = new PurchasingManager();
  }


  public void addEventToQueue(Event event) {
    eventManager.scheduleEvent(event);
  }

  public List<Event> getEventLog(){
    return eventManager.getEventLog();
  }

  public void buyStock(Stock stock, int amount){
    int id = eventManager.generateId();
    purchasingManager.buyStock(stock, amount);
    Event.EventBuilder builder = new Event.EventBuilder(id, stock.getCompanyStock().toString(), EventType.ONCE, 0, stock.getCompanyStock().getCategories(), 0.5).setPlayerAction(PlayerAction.BUY_STOCK);

    Event event = builder.build();
    eventManager.addToEventLog(event);
  }






  public static double generateRandomDouble(double min, double max) {
    if (min > max) {
      throw new IllegalArgumentException("Min cannot be greater than max.");
    }
    Random random = new Random();
    return min + (max - min) * random.nextDouble();
  }

}
