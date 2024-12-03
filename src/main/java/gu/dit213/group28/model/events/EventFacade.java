package gu.dit213.group28.model.events;

import gu.dit213.group28.model.enums.EventType;
import gu.dit213.group28.model.enums.PlayerAction;
import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.market.Asset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventFacade {

  private final EventLoader loader;
  private final EventManager eventManager;
  private final PurchasingManager purchasingManager;

  public EventFacade() {
    this.loader = new EventLoader();
    this.eventManager = new EventManager(loader.getPredefinedEvents(), loader.getReservedIds());
    this.purchasingManager = new PurchasingManager();
  }

  public void addEventToQueue(Event event) {
    eventManager.addToEventQueue(event);
  }

  public List<Event> getEventLog() {
    return eventManager.getEventLog();
  }

  public Event buyAsset(Asset asset, int amount, double value) {
    List <Sector> sectors = new ArrayList<>();
    sectors.add(asset.getSector());
    int id = eventManager.generateId();

    Event.EventBuilder builder = new Event.EventBuilder(id, asset.getName(), EventType.ONCE, 0,sectors,0.0 )
            .setPlayerAction(PlayerAction.BUY_STOCK, amount, value);

    Event event = builder.build();
    eventManager.addToEventLog(event);

    return event;
  }


}
