package gu.dit213.group28;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.events.EventFacade;
import gu.dit213.group28.model.events.EventPredef;
import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.market.Market;
import gu.dit213.group28.model.market.TrendModifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PredefTest {

  EventFacade facade;

  Market market;

  Asset asset;

  @BeforeEach
  public void setup() {
    facade = new EventFacade();
    market = Market.getInstance();

    asset = new Asset("NASDAQ", "name", Sector.HEALTHCARE, 100.0);
    market.addAsset(asset);
  }

  @Test
  public void testAddModifier() {
    EventPredef event = (EventPredef) facade.getPredefinedEvent();
    TrendModifier mod = new TrendModifier(event.getModifier(), event.getID());
    asset.addTrendModifier(mod);
    System.out.println(mod);
    System.out.println(asset.getTrendModifiers().getFirst().getModifier());
    Assertions.assertTrue(asset.getTrendModifiers().contains(mod));
  }
}
