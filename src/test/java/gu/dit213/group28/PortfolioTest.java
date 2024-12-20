package gu.dit213.group28;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.market.TrendModifier;
import gu.dit213.group28.model.market.Market;
import gu.dit213.group28.model.user.Portfolio;
import gu.dit213.group28.model.user.PortfolioRecord;
import java.lang.reflect.Field;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PortfolioTest {
  private Market market;
  private Portfolio portfolio;
  private Asset asset;

  @BeforeEach
  public void setUp() {
    portfolio = new Portfolio(10000);
    asset = new Asset("HC", "Healthcare Fund", Sector.HEALTHCARE, 100);
    market = Market.getInstance(); // Trend = 1.00565
    market.addAsset(asset);
  }

  @AfterEach
  public void tearDown() throws NoSuchFieldException, IllegalAccessException {
    Field instance = Market.class.getDeclaredField("instance");
    instance.setAccessible(true);
    instance.set(null, null);
    asset = null;
    portfolio = null;
  }

  @Test
  public void testBuyAsset() {
    portfolio.addRecord(asset.getSector(), 5);
    Sector ownedSector = portfolio.getRecords().getFirst().getSector();

    Asset ownedAsset = null;
    for (Asset asset : market.getAssets()) {
      if (asset.getSector().equals(ownedSector)) {
        ownedAsset = asset;
        break;
      }
    }
    assertEquals(asset.getSector(), ownedAsset.getSector());
  }

  @Test
  public void testGetTrendAsset() {
    asset.addTrendModifier(new TrendModifier(.1, 1));
    assertEquals(.1, asset.getTrend());
  }

  @Test
  public void testUpdatePrice() {
    asset.updatePrice();
    double expectedPrice = 100 * 1.00565;
    assertEquals(expectedPrice, asset.getPrice(), expectedPrice * 0.025);
  }

  @Test
  public void testPriceModifier() {
    asset.addTrendModifier(new TrendModifier(.1, 1));
    asset.updatePrice();
    double expectedPrice = 100 * (0.1 + 1.00565);
    assertEquals(expectedPrice, asset.getPrice(), expectedPrice * 0.025);
  }

  @Test
  public void testDecrementMods() {
    asset.addTrendModifier(new TrendModifier(.1, 2));
    asset.decrementAssetModifiers();
    assertEquals(1, asset.getTrendModifiers().getFirst().getIterationsLeft());
  }

  @Test
  public void testRemoveModifier() {
    asset.addTrendModifier(new TrendModifier(.1, 1));
    asset.decrementAssetModifiers();
    assertEquals(0, asset.getTrendModifiers().size());
  }

  @Test
  public void testMarketModifierDecrement() {
    market.addTrendModifier(new TrendModifier(.1, 2));
    market.decrementMarketModifiers();
    assertEquals(1, market.getTrendModifiers().getFirst().getIterationsLeft());
  }

  @Test
  public void testMarketModifierRemove() {
    market.addTrendModifier(new TrendModifier(.1, 1));
    market.decrementMarketModifiers();
    assertEquals(0, market.getTrendModifiers().size());
  }

  @Test
  void testDecrementAllMods() {
    asset.addTrendModifier(new TrendModifier(.1, 2));
    market.addTrendModifier(new TrendModifier(.1, 1));
    market.decrementAllModifiers();
    assertEquals(0, market.getTrendModifiers().size());
    assertEquals(1, asset.getTrendModifiers().getFirst().getIterationsLeft());
    market.decrementAllModifiers();
    assertEquals(0, market.getTrendModifiers().size());
    assertEquals(0, asset.getTrendModifiers().size());
    assertEquals(1.00565, market.getTrend());
  }
}
