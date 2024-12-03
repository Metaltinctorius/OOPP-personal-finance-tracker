package gu.dit213.group28;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gu.dit213.group28.model.Asset;
import gu.dit213.group28.model.Market;
import gu.dit213.group28.model.Portfolio;
import gu.dit213.group28.model.PortfolioEntry;
import gu.dit213.group28.model.PriceRecord;
import gu.dit213.group28.model.TrendModifier;
import gu.dit213.group28.model.enums.AssetType;
import gu.dit213.group28.model.enums.Sector;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PortfolioTest
{
  private Market market;
  private Portfolio portfolio;
  private Asset asset;



  @BeforeEach
  public void setUp()
  {
    portfolio = new Portfolio();
    asset = new Asset("S&P500", "Index Fund", AssetType.INDEX_FUND, Sector.INDEX,  100, new ArrayList<>());
    market = Market.getInstance("NYSE", .1);
    market.addAsset(asset);
  }

  @AfterEach
  public void tearDown()
  {
    market = null;
    asset = null;
    portfolio = null;
  }

  @Test
  public void testBuyAsset()
  {
    portfolio.addEntry(new PortfolioEntry(asset, 5, asset.getPrice(), LocalDate.now()));
    Asset owned = portfolio.getEntries().getFirst().getAsset();
    assertEquals(asset, owned);
  }

  @Test
  public void testGetTrendAsset()
  {
    asset.addTrendModifier(new TrendModifier(.1, 1));
    assertEquals(1.1, asset.getTrend());
  }

  @Test
  public void testUpdatePrice()
  {
    portfolio.addEntry(new PortfolioEntry(asset, 5, asset.getPrice(), LocalDate.now()));
    asset.updatePrice();
    assertEquals(110, asset.getPrice(), 0.001);
  }

  @Test
  public void testPriceModifier()
  {
    portfolio.addEntry(new PortfolioEntry(asset, 5, asset.getPrice(), LocalDate.now()));
    asset.addTrendModifier(new TrendModifier(.1, 1));
    asset.updatePrice();
    assertEquals(120, asset.getPrice(), 0.001);
  }

  @Test
  public void testRemoveModifier()
  {
    asset.addTrendModifier(new TrendModifier(.1, 1));
    asset.addTrendModifier(new TrendModifier(.2, 2));
    asset.addTrendModifier(new TrendModifier(.3, 3));
    asset.removeTrendModifier(2);
    assertEquals(1.4, asset.getTrend());
  }

}



