package gu.dit213.group28.marketTests;

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

public class MarketTest {
  private Market market;

  @BeforeEach
  public void setUp() {
    market = Market.getInstance();
  }

  @AfterEach
  public void tearDown() throws NoSuchFieldException, IllegalAccessException {
    Field instance = Market.class.getDeclaredField("instance");
    instance.setAccessible(true);
    instance.set(null, null);
  }

  @Test
  public void singletonTest() {
    Market market2 = Market.getInstance();
    assertSame(market, market2);
  }

  @Test
  public void getAssetsTest() {
    List<Asset> assets = market.getAssets();
    assertEquals(assets.size(), Sector.values().length - 1);
  }

  @Test
  public void indexValueTest() {
    double index = market.getIndexValue();
    assertEquals(100000, index, 1);
  }

  @Test
  public void testTrendModifier() {
    market.addTrendModifier(new TrendModifier(.1, 1));
    assertEquals(1.10565, market.getTrend());
    market.addTrendModifier(new TrendModifier(.5, 1));
    assertEquals(1.60565, market.getTrend(), 0.001);
  }

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
