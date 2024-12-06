package gu.dit213.group28.model.interfaces;

import gu.dit213.group28.model.MarketOutput;
import gu.dit213.group28.model.UserOutput;
import gu.dit213.group28.model.enums.Sector;
import java.util.List;

/**
 * This interface defines the methods that the model can use to update the view. Note that the
 * methods themselves are implemented in the view.
 */
public interface Iobserver {
  void update(String s);

  void updateGraphs(int xAxis, List<MarketOutput> mOutput, List<UserOutput> uOutput);

  void updateOwned(Sector sector, int amount);
  void updateCurrency(double currency);
  void updateProgress(int xAxis, double index, double player);
}
