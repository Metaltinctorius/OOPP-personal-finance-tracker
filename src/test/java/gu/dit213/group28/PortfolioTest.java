package gu.dit213.group28;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.market.Market;
import gu.dit213.group28.model.user.Portfolio;
import gu.dit213.group28.model.user.PortfolioEntry;
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
  }
}
