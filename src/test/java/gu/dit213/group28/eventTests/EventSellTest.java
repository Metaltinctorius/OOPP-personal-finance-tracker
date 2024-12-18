package gu.dit213.group28.eventTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.events.EventSell;
import gu.dit213.group28.model.market.Market;
import gu.dit213.group28.model.user.Portfolio;
import java.lang.reflect.Field;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventSellTest {
  private EventSell eventSell;
  private Sector sector;
  private Market market;
  private Portfolio portfolio;

  @BeforeEach
  public void setup() {
    sector = Sector.HEALTHCARE;
    market = Market.getInstance();
    eventSell = new EventSell(sector, 5);
    portfolio = new Portfolio(10000);
    portfolio.addRecord(sector, 5);
  }

  @AfterEach
  public void tearDown() throws NoSuchFieldException, IllegalAccessException {
    eventSell = null;
    Field instance = Market.class.getDeclaredField("instance");
    instance.setAccessible(true);
    instance.set(null, null);
    portfolio = null;
  }

  @Test
  public void testExecutePortfolio() {
    eventSell.execute(portfolio);
    assertEquals(0, portfolio.getRecordQuantity(sector));
  }

  @Test
  public void testUserUpdatedPrice() {
    eventSell = new EventSell(sector, 5);
    eventSell.execute(market);
    eventSell.execute(portfolio);
    assertEquals(portfolio.getCurrency(), 12500);
  }
}
