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

/** Test suite for the buy event. */
public class EventBuyTest {
  private EventBuy eventBuy;
  private Sector sector;
  private Market market;
  private Portfolio portfolio;

  /** Setting up the requirements for the test to operate on. */
  @BeforeEach
  public void setup() {
    sector = Sector.HEALTHCARE;
    market = Market.getInstance();
    eventBuy = new EventBuy(sector, 5);
    portfolio = new Portfolio(10000);
    portfolio.addRecord(sector, 5);
  }

  /**
   * Important to run between each test in order to reset the market instance, since it is a
   * singleton.
   *
   * @throws NoSuchFieldException Exception for when the name would be wrong
   * @throws IllegalAccessException Exception for when the instanced object is not accessible (when
   *     setAccessible is set to null).
   */
  @AfterEach
  public void tearDown() throws NoSuchFieldException, IllegalAccessException {
    eventBuy = null;
    Field instance = Market.class.getDeclaredField("instance");
    instance.setAccessible(true);
    instance.set(null, null);
    portfolio = null;
  }

  /**
   * Test the user quantity increase of assets after purchase. Expects: The quantity in the
   * portfolio to increase by the purchased amount.
   */
  @Test
  public void testExecutePortfolio() {
    eventBuy.execute(portfolio);
    assertEquals(10, portfolio.getRecordQuantity(sector));
  }

  /**
   * Test the user amount(currency) decreases after purchasing assets. Expects: The user currency
   * (money) to decrease after a purchase.
   */
  @Test
  public void testUserUpdatedPrice() {
    eventBuy = new EventBuy(sector, 5);
    eventBuy.execute(market);
    eventBuy.execute(portfolio);
    assertEquals(7500, portfolio.getCurrency());
  }

  /**
   * Tests the case when user tries to purchase a quantity of assets that would evaluate to a larger
   * price than the user currently can afford. Expects: Expects to purchase the quantity the user
   * can afford, up to the most affordable quantity.
   */
  @Test
  public void testBadBuy1() {
    eventBuy = new EventBuy(sector, 1000000);
    eventBuy.execute(market);
    eventBuy.execute(portfolio);
    assertEquals(2, eventBuy.getId());
  }

  /**
   * Tests the case when the user tries to purchase assets without sufficient funds (currency).
   * Expects:
   */
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
