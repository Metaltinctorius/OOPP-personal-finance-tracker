package gu.dit213.group28.eventtests;

import gu.dit213.group28.model.enums.EventType;
import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.events.EventFacade;
import gu.dit213.group28.model.events.EventPredef;
import gu.dit213.group28.model.interfaces.ImarketEx;
import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.market.Market;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEventPredef {

  ImarketEx market;
  EventFacade facade;
  EventPredef event;

  @Test
  public void test_sectors_not_empty() {
    List<Asset> assets = market.getAssets();
    event.execute(market);

    double actual = 0.0;
    double expected;
    for (Asset a : assets) {
      if (a.getSector() == Sector.HEALTHCARE) {
        actual = a.getTrend();
      }
    }
    expected = event.getModifier();
    Assertions.assertEquals(actual, expected);
  }

  @BeforeEach
  public void setup() {
    event = createEvent();
    facade = new EventFacade();
    market = Market.getInstance();
  }

  private EventPredef createEvent() {
    List<Sector> sectors = new ArrayList<>();
    sectors.add(Sector.HEALTHCARE);
    return new EventPredef(99, "test event", "for testing", EventType.ONCE, 1, sectors, 0.2);
  }
}
