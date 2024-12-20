package gu.dit213.group28.eventtests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.events.EventTick;
import gu.dit213.group28.model.market.Market;
import gu.dit213.group28.model.user.Portfolio;
import java.lang.reflect.Field;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Tests for EventTick class. */
public class EventTickTest {
  private EventTick eventTick;
  private Sector sector;
  private Market market;
  private Portfolio portfolio;

  /** Sets up variables for tests. */
  @BeforeEach
  public void setup() {
    sector = Sector.HEALTHCARE;
    market = Market.getInstance();
    eventTick = new EventTick(0);
    portfolio = new Portfolio(10000);
    portfolio.addRecord(sector, 5);
  }

  /** Resets variables for tests. */
  @AfterEach
  public void tearDown() throws NoSuchFieldException, IllegalAccessException {
    eventTick = null;
    Field instance = Market.class.getDeclaredField("instance");
    instance.setAccessible(true);
    instance.set(null, null);
    portfolio = null;
  }

  /** Testing event execute on market. */
  @Test
  public void testEventTickMarket() {
    assertEquals(500, market.getAssets().getFirst().getPrice());
    eventTick.execute(market);
    assertNotEquals(500, market.getAssets().getFirst().getPrice());
  }

  /** Testing event execute on User/Portfolio. */
  @Test
  public void testEventTickUser() {
    EventTick eventTick2 = new EventTick(0);
    eventTick2.execute(market);
    eventTick2.execute(portfolio);
    double test = eventTick2.getPlayerValue();
    eventTick.execute(market);
    eventTick.execute(portfolio);
    assertNotEquals(test, eventTick.getPlayerValue());
  }
}
