package gu.dit213.group28.model.events;

import gu.dit213.group28.model.MarketOutput;
import gu.dit213.group28.model.UserOutput;
import gu.dit213.group28.model.interfaces.ImarketEx;
import gu.dit213.group28.model.interfaces.IuserEx;
import gu.dit213.group28.model.market.Asset;
import gu.dit213.group28.model.user.PortfolioRecord;

import java.util.ArrayList;
import java.util.List;

public class EventTick extends Event {
  public final List<MarketOutput> mOutput;
  public final int tick;
  private double currency;
  private double indexValue;
  private double playerValue;
  public final List<UserOutput> uOutput;

  public EventTick(int id, int tick) {
    super(id);
    mOutput = new ArrayList<>();
    this.tick = tick;
    uOutput = new ArrayList<>();
  }
  public double getCurrency(){
    return currency;
  }
  public double getIndexValue(){
    return indexValue;
  }
  public double getPlayerValue(){
    return playerValue;
  }

  @Override
  public void execute(ImarketEx m) {
    for (Asset a : m.getAssets()) {
      a.updatePrice();
      mOutput.add(new MarketOutput(a.getSector(), a.getPrice()));
    }
    indexValue = m.getIndexValue();
  }

  @Override
  public void execute(IuserEx u) {
    for (PortfolioRecord p : u.getRecords()) {
      for (MarketOutput m : mOutput) {
        if (p.getSector() == m.sector()) {
          uOutput.add(new UserOutput(p.getSector(), p.getQuantity(), m.value()));
        }
      }
    }
    currency = u.getMoney();
    playerValue = u.getTotalValue(mOutput);
  }
}
