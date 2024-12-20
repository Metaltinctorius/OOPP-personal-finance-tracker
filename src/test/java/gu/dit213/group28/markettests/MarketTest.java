package gu.dit213.group28.markettests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.market.Market;
import gu.dit213.group28.model.market.TrendModifier;
import java.lang.reflect.Field;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test suite for the market instance. */
public class MarketTest {
  private Market market;

  /** Setup for the tests. */
  @BeforeEach
  public void setUp() {
    market = Market.getInstance();
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
  }

  /** Test that the market only can exist as a singleton instance. */
  @Test
  public void singletonTest() {
    Market market2 = Market.getInstance();
    assertSame(market, market2);
  }

  /** Test that the market can return the assets. */
  @Test
  public void getAssetsTest() {
    List<Asset> assets = market.getAssets();
    assertEquals(assets.size(), Sector.values().length - 1);
  }

  /** Test that the market starts with the fixed index value. */
  @Test
  public void indexValueTest() {
    double index = market.getIndexValue();
    assertEquals(100000, index, 1);
  }

  /** Test that the modifiers affects and changes the market trends. */
  @Test
  public void testTrendModifier() {
    market.addTrendModifier(new TrendModifier(.1, 1));
    assertEquals(1.10565, market.getTrend());
    market.addTrendModifier(new TrendModifier(.5, 1));
    assertEquals(1.60565, market.getTrend(), 0.001);
  }

  /** Test to return the modifiers currently in the market (remaining iterations). */
  @Test
  public void testGetTrendModifiers() {
    market.addTrendModifier(new TrendModifier(.1, 1));
    market.addTrendModifier(new TrendModifier(.1, 2));
    assertEquals(2, market.getTrendModifiers().size());
    market.decrementMarketModifiers();
    assertEquals(1, market.getTrendModifiers().size());
    market.decrementMarketModifiers();
    assertEquals(0, market.getTrendModifiers().size());
  }

  /** Test that the market can decrement all iterations of the modifiers. */
  @Test
  public void decrementAllTest() {
    market.addTrendModifier(new TrendModifier(.1, 1));
    market.getAssets().getFirst().addTrendModifier(new TrendModifier(.1, 1));
    assertEquals(1, market.getTrendModifiers().size());
    assertEquals(1, market.getAssets().getFirst().getTrendModifiers().size());
    market.decrementAllModifiers();
    assertEquals(0, market.getTrendModifiers().size());
    assertEquals(0, market.getAssets().getFirst().getTrendModifiers().size());
  }
}
