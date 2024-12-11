package gu.dit213.group28.model.interfaces;

import gu.dit213.group28.model.records.MarketOutput;
import gu.dit213.group28.model.records.UserOutput;
import gu.dit213.group28.model.enums.Sector;
import java.util.List;

/** This interface defines the methods that the model can use to update the view. */
public interface Iobserver {
  /** Updates the central graphs. */
  void updateGraphs(int xAxis, List<MarketOutput> mOutput, List<UserOutput> uOutput);

  /** Updates the quantity of assets owned by the player in each sector. */
  void updateOwned(Sector sector, int quantity, double value);

  /** Updates the players current currency. */
  void updateCurrency(double currency);

  /** Updates the players current progress. I.e. The index value vs the players value. */
  void updateProgress(int xAxis, double index, double player);

  /** Starts event with description. */
  void updateOnEvent(String event);

  /** Updates the event history box */
  void updateEventHistory(String testEventHistory);

  /**
   * Updates on pause or resume
   *
   * @param pause true if paused, false if resumed
   */
  void updatePause(boolean pause);
}
