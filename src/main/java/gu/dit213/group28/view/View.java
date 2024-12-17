package gu.dit213.group28.view;

import gu.dit213.group28.model.Observable;
import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.Iobserver;
import gu.dit213.group28.model.records.MarketOutput;
import gu.dit213.group28.model.records.UserOutput;
import java.util.List;
import javafx.application.Platform;
import javafx.stage.Stage;

/** The main view class, update various components after being sent a notice by its observer */
public class View implements Iobserver {
  private final Stage stage;
  private Graphs graphs;
  private InfoBox info;
  private LowerPanel low;
  private EventLogs eventLog;

  /** The main view class, update various components after being sent a notice by its observer */
  public View(Stage stage, Observable observable) {
    this.stage = stage;
    observable.addObserver(this);
  }

  /** Initializes the view. */
  public void initView() {
    stage.show();
  }

  /** Sets the eventLog component of the view. */
  public void setEventLog(EventLogs eventLog) {
    this.eventLog = eventLog;
  }

  /** Sets the central graphs component of the view. */
  public void setGraphs(Graphs graphs) {
    this.graphs = graphs;
  }

  /** Sets the InfoBox component of the view. */
  public void setInfoBox(InfoBox info) {
    this.info = info;
  }

  /** Sets the Lowerpanel component of the view. */
  public void setLowerPanel(LowerPanel low) {
    this.low = low;
  }

  /** Updates the central graphs. */
  @Override
  public void updateGraphs(int xAxis, List<MarketOutput> mOutput, List<UserOutput> uOutput) {
    Platform.runLater(
        () -> {
          graphs.updateGraphs(xAxis, mOutput);
          info.updatePie(uOutput);
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
  public void updateProgress(int xAxis, double index, double player) {
    Platform.runLater(() -> info.updateLine(xAxis, index, player));
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

  /** Updates the event history box. */
  @Override
  public void updateEventHistory(String eventTitle, String eventDescription) {
    Platform.runLater(
        () -> {
          // eventLog.setText(eventLog.getText() + "\n" + event);
          eventLog.populateEventTextBox(eventTitle);
          eventLog
              .getEventLogText()
              .getChildren()
              .getFirst()
              .setOnMouseClicked(
                  event2 -> {
                    updateOnEvent(eventDescription);
                  });
        });
  }

  /** Updates the pause button in the lower panel. */
  @Override
  public void updatePause(boolean pause) {
    Platform.runLater(() -> low.updatePauseButton(pause));
  }
}
