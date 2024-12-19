package gu.dit213.group28.eventtests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.events.EventBuy;
import gu.dit213.group28.model.market.Market;
import gu.dit213.group28.model.user.Portfolio;
import java.lang.reflect.Field;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventBuyTest {
  private EventBuy eventBuy;
  private Sector sector;
  private Market market;
  private Portfolio portfolio;

  @BeforeEach
  public void setup() {
    sector = Sector.HEALTHCARE;
    market = Market.getInstance();
    eventBuy = new EventBuy(sector, 5);
    portfolio = new Portfolio(10000);
    portfolio.addRecord(sector, 5);
  }

  @AfterEach
  public void tearDown() throws NoSuchFieldException, IllegalAccessException {
    eventBuy = null;
    Field instance = Market.class.getDeclaredField("instance");
    instance.setAccessible(true);
    instance.set(null, null);
    portfolio = null;
  }

  @Test
  public void testExecutePortfolio() {
    eventBuy.execute(portfolio);
    assertEquals(10, portfolio.getRecordQuantity(sector));
  }

  @Test
  public void testUserUpdatedPrice() {
    eventBuy = new EventBuy(sector, 5);
    eventBuy.execute(market);
    eventBuy.execute(portfolio);
    assertEquals(7500, portfolio.getCurrency());
  }

  @Test
  public void testBadBuy1() {
    eventBuy = new EventBuy(sector, 1000000);
    eventBuy.execute(market);
    eventBuy.execute(portfolio);
    assertEquals(2, eventBuy.getId());
  }

  @Test
  public void testBadBuy2() {
    portfolio = new Portfolio(0);
    eventBuy.execute(market);
    eventBuy.execute(portfolio);
    assertEquals(2, eventBuy.getId());
    assertEquals(0, portfolio.getRecordQuantity(sector));
    assertEquals(0, portfolio.getCurrency());
  }
}
