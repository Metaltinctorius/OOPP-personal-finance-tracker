package gu.dit213.group28;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.market.Market;
import gu.dit213.group28.model.user.Portfolio;
import gu.dit213.group28.model.user.PortfolioEntry;
import gu.dit213.group28.model.market.TrendModifier;
import gu.dit213.group28.model.enums.AssetType;
import gu.dit213.group28.model.enums.Sector;
import java.time.LocalDate;
import java.util.ArrayList;

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
    asset = new Asset("S&P500", "Index Fund", Sector.INDEX, 100);
    market = Market.getInstance();
    market.addAsset(asset);
  }

  @AfterEach
  public void tearDown() {
    market = null;
    asset = null;
    portfolio = null;
  }

  @Test
  public void testBuyAsset() {
    portfolio.addEntry(new PortfolioEntry(asset.getSector(), 5, asset.getPrice()));
    Sector ownedSector = portfolio.getEntries().getFirst().getSector();
    Asset ownedAsset = null;
    for (Asset asset : market.getAssets()) {
      if (asset.getSector().equals(ownedSector)) {
        ownedAsset = asset;
        break;
      }
    }
    assertEquals(asset, ownedAsset);
  }

  @Test
  public void testGetTrendAsset() {
    asset.addTrendModifier(new TrendModifier(.1, 1));
    assertEquals(1.1, asset.getTrend());
  }

  @Test
  public void testUpdatePrice() {
    portfolio.addEntry(new PortfolioEntry(asset.getSector(), 5, asset.getPrice()));
    asset.updatePrice();
    assertEquals(110, asset.getPrice(), 0.001);
  }

  @Test
  public void testPriceModifier() {
    portfolio.addEntry(new PortfolioEntry(asset.getSector(), 5, asset.getPrice()));
    asset.addTrendModifier(new TrendModifier(.1, 1));
    asset.updatePrice();
    assertEquals(120, asset.getPrice(), 0.001);
  }

  /*@Test
  public void testRemoveModifier() {
    asset.addTrendModifier(new TrendModifier(.1, 1));
    asset.addTrendModifier(new TrendModifier(.2, 2));
    asset.addTrendModifier(new TrendModifier(.3, 3));
    asset.removeTrendModifier(2);
    assertEquals(1.4, asset.getTrend());
  }*/
}
