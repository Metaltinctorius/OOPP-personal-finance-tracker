package gu.dit213.group28.view;

import gu.dit213.group28.model.Observable;
import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.Iobserver;
import gu.dit213.group28.model.records.MarketOutput;
import gu.dit213.group28.model.records.UserOutput;
import java.util.List;
import javafx.application.Platform;
import javafx.stage.Stage;

/** The main view class, update various components after being sent a notice by its observer. */
public class View implements Iobserver {
  private final Stage stage;
  private Graphs graphs;
  private InfoBox info;
  private LowerPanel low;
  private EventLogs eventLog;

  /**
   * The main view class, update various components after being sent a notice by its observer.
   *
   * @param stage The instance of stage.
   * @param observable The instance of the observable entity.
   */
  public View(Stage stage, Observable observable) {
    this.stage = stage;
    observable.addObserver(this);
  }

  /** Initializes the view. */
  public void initView() {
    stage.show();
  }

  /**
   * Sets the eventLog component of the view.
   *
   * @param eventLog the instance of the event log.
   */
  public void setEventLog(EventLogs eventLog) {
    this.eventLog = eventLog;
  }

  /**
   * Sets the central graphs component of the view.
   *
   * @param graphs the instance of the graph
   */
  public void setGraphs(Graphs graphs) {
    this.graphs = graphs;
  }

  /**
   * Sets the InfoBox component of the view.
   *
   * @param info the instance of InfoBox.
   */
  public void setInfoBox(InfoBox info) {
    this.info = info;
  }

  /**
   * Sets the Lowerpanel component of the view.
   *
   * @param low the instance of LowerPanel.
   */
  public void setLowerPanel(LowerPanel low) {
    this.low = low;
  }

  /** Updates the central graphs. */
  @Override
  public void updateGraphs(
      int xaxisVal, List<MarketOutput> marketOutputs, List<UserOutput> userOutputs) {
    Platform.runLater(
        () -> {
          graphs.updateGraphs(xaxisVal, marketOutputs);
          info.updatePie(userOutputs);
        });
  }

  /** Updates the quantity of assets owned by the player in each sector. */
  @Override
  public void updateOwned(Sector sector, int quantity, double value) {
    Platform.runLater(
        () -> {
          graphs.updateOwnedField(sector, quantity);
          info.updatePie(sector, quantity, value);
        });
  }

  /** Updates the players current currency. */
  @Override
  public void updateCurrency(double currency) {
    Platform.runLater(() -> info.updateCurrency(currency));
  }

  /** Updates the players current progress. I.e. The index value vs the players value. */
  @Override
  public void updateProgress(int xaxisVal, double index, double player) {
    Platform.runLater(() -> info.updateLine(xaxisVal, index, player));
  }

  /** Shows an event pop-up window with event description when event triggers. */
  @Override
  public void updateOnEvent(String eventMessage) {
    Platform.runLater(
        () -> {
          updatePause(false);
          EventPopUp.createEventDialog(eventMessage);
          updatePause(true);
        });
  }

  /** Updates the history panel with news events. */
  @Override
  public void updateEventHistory(String eventTitle, String eventDescription) {
    Platform.runLater(
        () -> {
          eventLog.populateHistoryWithNewsEvent(eventTitle);
          eventLog
              .getEventLogText()
              .getChildren()
              .getFirst()
              .setOnMouseClicked(event2 -> updateOnEvent(eventDescription));
        });
  }

  /** Updates the history panel with buy events. */
  @Override
  public void updateBuyHistory(Sector sector, int quantity, double value) {
    Platform.runLater(
        () -> eventLog.populateHistoryWithBuyEvent(sector.toString(), quantity, value));
  }

  /** Updates the history panel with sell events. */
  @Override
  public void updateSellHistory(Sector sector, int quantity, double value) {
    Platform.runLater(
        () -> eventLog.populateHistoryWithSellEvent(sector.toString(), quantity, value));
  }

  /** Updates the pause button in the lower panel. */
  @Override
  public void updatePause(boolean pause) {
    Platform.runLater(() -> low.updatePauseButton(pause));
  }
}
