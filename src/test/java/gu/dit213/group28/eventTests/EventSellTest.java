package gu.dit213.group28.eventTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.events.EventSell;
import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.market.Market;
import gu.dit213.group28.model.user.Portfolio;
import org.junit.jupiter.api.AfterEach;
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
    asset = new Asset("AstraZeneca", "Pharmaceutical", sector, 100);
    market = Market.getInstance();
    market.addAsset(asset);
    eventSell = new EventSell(sector, 10);
    portfolio = new Portfolio(1000);
    portfolio.addRecord(sector, 50);
  }

  @AfterEach
  public void tearDown() {
    eventSell = null;
    market.getAssets().clear();
    portfolio = null;
    asset = null;
  }

  @Test
  public void testSellEventMarket() {
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
}
