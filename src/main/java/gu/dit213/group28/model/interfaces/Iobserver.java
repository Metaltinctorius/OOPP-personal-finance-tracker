package gu.dit213.group28.model.interfaces;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.records.MarketOutput;
import gu.dit213.group28.model.records.UserOutput;
import java.util.List;

/** This interface defines the methods that the model can use to update the view. */
public interface Iobserver {

  /**
   * Updates the central graphs.
   *
   * @param xaxisVal The current x-axis value.
   * @param marketOutputs The list of market outputs.
   * @param userOutputs The list of user outputs.
   */
  void updateGraphs(int xaxisVal, List<MarketOutput> marketOutputs, List<UserOutput> userOutputs);

  /**
   * Updates the quantity of assets owned by the player in each sector.
   *
   * @param sector The sector of the assets.
   * @param quantity The quantity of assets owned.
   * @param value The value of the assets owned.
   */
  void updateOwned(Sector sector, int quantity, double value);

  /**
   * Updates the players current currency.
   *
   * @param currency The players current currency.
   */
  void updateCurrency(double currency);

  /**
   * Updates the players current progress. I.e. The index value vs the players value.
   *
   * @param xaxisVal The current x-axis value.
   * @param index The index value.
   * @param player The players value.
   */
  void updateProgress(int xaxisVal, double index, double player);

  /**
   * Starts event with description.
   *
   * @param event The event description to show.
   */
  void updateOnEvent(String event);

  /**
   * Updates the history panel with news events.
   *
   * @param eventTitle The title of the event.
   * @param eventDescription The description of the event.
   */
  void updateEventHistory(String eventTitle, String eventDescription);

  /**
   * Updates the history panel with buy events.
   *
   * @param sector The sector of the assets.
   * @param quantity The quantity of assets bought.
   * @param value The value of the assets.
   */
  void updateBuyHistory(Sector sector, int quantity, double value);

  /**
   * Updates the history panel with sell events.
   *
   * @param sector The sector of the assets.
   * @param quantity The quantity of assets sold.
   * @param value The value of the assets.
   */
  void updateSellHistory(Sector sector, int quantity, double value);

  /**
   * Updates on pause or resume.
   *
   * @param pause true if paused, false if resumed
   */
  void updatePause(boolean pause);
}
