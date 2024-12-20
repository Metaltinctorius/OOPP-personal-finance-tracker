package gu.dit213.group28.markettests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.market.Market;
import gu.dit213.group28.model.market.TrendModifier;
import java.lang.reflect.Field;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test suite for asset tests. */
public class AssetTest {
  private Market market;
  private Asset asset;

  /** Setup for the tests. */
  @BeforeEach
  public void setUp() {
    asset = new Asset(Sector.INDEX, 100);
    market = Market.getInstance();
    market.addAsset(asset);
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
    Field instance = Market.class.getDeclaredField("instance");
    instance.setAccessible(true);
    instance.set(null, null);
    asset = null;
  }

  /** Test that a trend modifier is added to an asset. */
  @Test
  public void testGetTrendAsset() {
    asset.addTrendModifier(new TrendModifier(.1, 1));
    assertEquals(0.1, asset.getTrend());
  }

  /** Test that the asset price is updated after the updatePrice() method call. */
  @Test
  public void testUpdatePrice() {
    double unexpected = asset.getPrice();
    asset.updatePrice();
    assertEquals(unexpected, asset.getPrice(), 4);
    assertNotEquals(unexpected, asset.getPrice());
  }

  /** Test that the modifier affects the price (value) of the asset. */
  @Test
  public void testPriceModifier() {
    double unexpected = asset.getPrice();
    asset.addTrendModifier(new TrendModifier(.1, 1));
    asset.updatePrice();
    assertEquals(unexpected, asset.getPrice(), 13.4);
    assertNotEquals(unexpected, asset.getPrice());
  }

  /** Test to receive the modifiers for an asset. */
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
}
