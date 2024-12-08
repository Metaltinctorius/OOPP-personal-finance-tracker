package gu.dit213.group28;

import gu.dit213.group28.model.records.MarketOutput;
import gu.dit213.group28.model.records.UserOutput;
import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.Iobserver;
import gu.dit213.group28.model.Observable;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/** The main view class, update various components after being sent a notice by its observer */
public class View implements Iobserver {
  private final Stage stage;
  private Graphs graphs;
  private InfoBox info;
  private Text eventLog;

  /** The main view class, update various components after being sent a notice by its observer */
  View(Stage stage, Observable observable) {
    this.stage = stage;
    observable.addObserver(this);
  }

  /** Initializes the view. */
  public void initView() {
    stage.show();
  }

  /** Sets the eventLog component of the view. */
  public void setEventLog(Text eventLog) {
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
  public void updateOwned(Sector sector, int quantity) {
    Platform.runLater(() -> graphs.updateOwnedField(sector, quantity));
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

  /** Starts event with description. */
  @Override
  public void updateOnEvent(String eventMessage) {
    Platform.runLater(
        () -> {
          Dialog<String> dialog = new Dialog<>();
          dialog.setTitle("Event notification");

          /*
          // Buttons for events with choices
          ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.CANCEL_CLOSE);
          dialog.getDialogPane().getButtonTypes().add(okButton);
          // setOnAction for okButton

          ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
          dialog.getDialogPane().getButtonTypes().add(noButton);
          // setOnAction for noButton
          */
          ButtonType closeButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
          dialog.getDialogPane().getButtonTypes().add(closeButton);

          Label messageLabel = new Label(eventMessage);
          dialog.getDialogPane().setContent(messageLabel);
          dialog.showAndWait();
        });
  }

  /** Updates the event history box */
  @Override
  public void updateEventHistory(String event) {
    Platform.runLater(
        () -> {
          eventLog.setText(eventLog.getText() + "\n" + event);
        });
  }
}
