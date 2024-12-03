package gu.dit213.group28.model.events;

import gu.dit213.group28.model.enums.EventType;
import gu.dit213.group28.model.enums.PlayerAction;
import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.Ievent;
import gu.dit213.group28.model.interfaces.Ieventfacade;

import java.util.ArrayList;
import java.util.List;

public class EventFacade implements Ieventfacade {

  private final EventLoader loader;
  private final EventManager eventManager;


  public EventFacade() {
    this.loader = new EventLoader();
    this.eventManager = new EventManager(loader.getPredefinedEvents(), loader.getReservedIds());
  }

  public void addEventToQueue(OldEvent event) {
    eventManager.addToEventQueue(event);
  }

  public List<OldEvent> getEventLog() {
    return eventManager.getEventLog();
  }

  public OldEvent buyAsset(Sector sector, int amount, double value) {

    List < Sector> sectors = new ArrayList<>();
    sectors.add(sector);

    int id = eventManager.generateId();

    OldEvent.EventBuilder builder =
        new OldEvent.EventBuilder(
                id,
            sector.name(),
                EventType.ONCE,
                0,
            sectors
               )
            .setPlayerAction(PlayerAction.BUY_STOCK, amount, value);

    OldEvent event = builder.build();
    eventManager.addToEventLog(event);

    return event;
  }


  @Override
  public Ievent getEmpty() {
    return null;
  }
}
