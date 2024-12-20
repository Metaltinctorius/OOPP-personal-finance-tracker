package gu.dit213.group28.eventtests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.events.EventSell;
import gu.dit213.group28.model.market.Market;
import gu.dit213.group28.model.user.Portfolio;
import java.lang.reflect.Field;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test suite to test the cases of purchasing events. */
public class EventSellTest {
  private EventSell eventSell;
  private Sector sector;
  private Market market;
  private Portfolio portfolio;

  /** Setting up the required functionality for the tests to operate on. */
  @BeforeEach
  public void setup() {
    sector = Sector.HEALTHCARE;
    market = Market.getInstance();
    eventSell = new EventSell(sector, 5);
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
    eventSell = null;
    Field instance = Market.class.getDeclaredField("instance");
    instance.setAccessible(true);
    instance.set(null, null);
    portfolio = null;
  }

  /**
   * Test the user quantity decrease of assets after sale. Expects: The quantity in the portfolio to
   * decrease by the purchased amount.
   */
  @Test
  public void testExecutePortfolio() {
    eventSell.execute(portfolio);
    assertEquals(0, portfolio.getRecordQuantity(sector));
  }

  /**
   * Test the user amount(currency) increases after selling assets. Expects: The user currency
   * (money) to increase after a sale.
   */
  @Test
  public void testUserUpdatedPrice() {
    eventSell.execute(market);
    eventSell.execute(portfolio);
    assertEquals(12500, portfolio.getCurrency());
  }

  /**
   * Tests the case when a user tries to sell more assets that they have. Expects the quantity the
   * user holds to be sold, discarding the remaining quantity the user tried to sell.
   */
  @Test
  public void testBadSell1() {
    eventSell = new EventSell(sector, 1000000);
    eventSell.execute(market);
    eventSell.execute(portfolio);
    assertEquals(4, eventSell.getId());
  }

  /**
   * Tests the case when the user tries to sell assets that they do not have (when quantity is 0).
   */
  @Test
  public void testBadSell2() {
    portfolio = new Portfolio(10000);
    eventSell.execute(market);
    eventSell.execute(portfolio);
    assertEquals(4, eventSell.getId());
    assertEquals(10000, portfolio.getCurrency());
    assertEquals(0, portfolio.getRecordQuantity(sector));
  }
}
