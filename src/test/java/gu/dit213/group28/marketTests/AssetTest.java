package gu.dit213.group28.marketTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.market.Market;
import gu.dit213.group28.model.user.Portfolio;
import gu.dit213.group28.model.market.TrendModifier;
import gu.dit213.group28.model.enums.Sector;

import java.lang.reflect.Field;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AssetTest {
  private Market market;
  private Asset asset;

  @BeforeEach
  public void setUp() {
    asset = new Asset("S&P500", "Index Fund", Sector.INDEX, 100);
    market = Market.getInstance();
    market.addAsset(asset);
  }

  @AfterEach
  public void tearDown() throws NoSuchFieldException, IllegalAccessException {
    Field instance = Market.class.getDeclaredField("instance");
    instance.setAccessible(true);
    instance.set(null, null);
    asset = null;
  }

  @Test
  public void testGetTrendAsset() {
    asset.addTrendModifier(new TrendModifier(.1, 1));
    assertEquals(0.1, asset.getTrend());
  }

  @Test
  public void testUpdatePrice() {
    double unexpected = asset.getPrice();
    asset.updatePrice();
    assertEquals(unexpected, asset.getPrice(), 4);
    assertNotEquals(unexpected, asset.getPrice());
  }

  @Test
  public void testPriceModifier() {
    double unexpected = asset.getPrice();
    asset.addTrendModifier(new TrendModifier(.1, 1));
    asset.updatePrice();
    assertEquals(unexpected, asset.getPrice(), 13.4);
    assertNotEquals(unexpected, asset.getPrice());
  }

  @Test
  public void testGetTrendModifiers() {
    asset.addTrendModifier(new TrendModifier(.1, 1));
    asset.addTrendModifier(new TrendModifier(.1, 2));
    assertEquals(2, asset.getTrendModifiers().size());
    asset.updatePrice();
    asset.decrementAssetModifiers();
    assertEquals(1, asset.getTrendModifiers().size());
    asset.updatePrice();
    asset.decrementAssetModifiers();
    assertEquals(0, asset.getTrendModifiers().size());
  }

  @Test
  public void testPriceHistory() {
    asset.addTrendModifier(new TrendModifier(.1, 1));
    assertEquals(0, asset.getHistoricalPrices().size());
    asset.updatePrice();
    assertEquals(1, asset.getHistoricalPrices().size());
  }
}
