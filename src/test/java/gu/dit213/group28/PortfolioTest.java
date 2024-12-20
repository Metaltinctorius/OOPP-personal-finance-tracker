package gu.dit213.group28;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.market.Market;
import gu.dit213.group28.model.records.MarketOutput;
import gu.dit213.group28.model.user.Portfolio;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Tests for Portfolio. */
public class PortfolioTest {
  private Market market;
  private Portfolio portfolio;
  private Asset asset;

  /** Sets up a portfolio, asset and market. */
  @BeforeEach
  public void setUp() {
    portfolio = new Portfolio(10000);
    asset = new Asset(Sector.HEALTHCARE, 100);
    market = Market.getInstance(); // Trend = 1.00565
    market.addAsset(asset);
  }

  /**
   * Resets everything including market singleton.
   *
   * @throws NoSuchFieldException exception from setting market instance to null.
   * @throws IllegalAccessException exception from setting market instance to null.
   */
  @AfterEach
  public void tearDown() throws NoSuchFieldException, IllegalAccessException {
    Field instance = Market.class.getDeclaredField("instance");
    instance.setAccessible(true);
    instance.set(null, null);
    asset = null;
    portfolio = null;
  }

  /** Tests adding an asset. */
  @Test
  public void testAddAsset() {
    portfolio.addRecord(asset.getSector(), 5);
    int res = portfolio.getRecordQuantity(asset.getSector());
    assertEquals(5, res);
  }

  /**
   * Tests adding assets twice (the first time assets are added creates the portfolio record, the
   * second just updates it).
   */
  @Test
  public void testAddExistingAsset() {
    portfolio.addRecord(asset.getSector(), 5);
    portfolio.addRecord(asset.getSector(), 5);
    int res = portfolio.getRecordQuantity(asset.getSector());
    assertEquals(10, res);
  }

  /** Tests get total value. */
  @Test
  public void testGetTotalValue() {
    portfolio.addRecord(asset.getSector(), 5);
    List<MarketOutput> mo = new ArrayList<>();
    mo.add(new MarketOutput(asset.getSector(), 10));
    double res = portfolio.getTotalValue(mo);
    assertEquals(10050, res);
  }
}
