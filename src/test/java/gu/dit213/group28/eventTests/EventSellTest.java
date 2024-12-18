package gu.dit213.group28.eventTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.events.EventSell;
import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.market.Market;
import gu.dit213.group28.model.user.Portfolio;
import gu.dit213.group28.model.user.PortfolioEntry;
import java.lang.reflect.Field;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventSellTest {
  private EventSell eventSell;
  private Sector sector;
  private Market market;
  private Portfolio portfolio;
  private Asset asset;

  @BeforeEach
  public void setup() {
    sector = Sector.HEALTHCARE;
    market = Market.getInstance();
    eventSell = new EventSell(sector, 1);
    portfolio = new Portfolio(10000);
    portfolio.addRecord(sector, 10);
  }

  @AfterEach
  public void tearDown() throws NoSuchFieldException, IllegalAccessException{
    eventSell = null;
    market.getAssets().clear();
    portfolio = null;
    asset = null;

    Field instance = Market.class.getDeclaredField("instance");
    instance.setAccessible(true);
    instance.set(null, null);
    asset = null;

  }

  @Test
  public void testSellEventExecuteMarket() {
    eventSell.execute(market);
    assertEquals(
        1000, eventSell.getValue()); // getValue() should be the same as value in execute() right?
  }

  @Test
  public void testGetSector() {
    assertEquals(Sector.HEALTHCARE, eventSell.getSector());
  }

  @Test
  public void testGetQuantity() {
    assertEquals(10, eventSell.getQuantity());
  }

  @Test
  public void testGetOwned() {
    assertEquals(0, eventSell.getOwned());
  }

  @Test
  public void testExecutePortfolio() {
    eventSell.execute(portfolio);
    assertEquals(40, portfolio.getRecordQuantity(sector));
  }

  @Test
  public void testSellEvent(){
    double price = 0;
    for(Asset a : market.getAssets()){
      if (a.getSector() == sector){
        price = price + a.getPrice();
      }
    }
    double expected = portfolio.getCurrency() + price * eventSell.getQuantity();
    eventSell.execute(market);
    eventSell.execute(portfolio);

    System.out.println("Expected: " + expected);
    double upperBound = expected + 1000; // Due to randomizer in the createAssets initializer
    double lowerBound = expected - 1000; // Due to randomizer in the createAssets initializer

    System.out.println(portfolio.getCurrency());
    Assertions.assertTrue(lowerBound <= portfolio.getCurrency() && portfolio.getCurrency() <= upperBound);

    System.out.println(eventSell.getOwned());
  }
}
