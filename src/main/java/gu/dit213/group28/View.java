package gu.dit213.group28;

import gu.dit213.group28.model.MarketOutput;
import gu.dit213.group28.model.UserOutput;
import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.Iobserver;
import gu.dit213.group28.model.Observable;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Very messy and temporary view that was thrown together for testing. Not 100% sure how to bind the
 * controller and view together here, very new to JavaFX so there is probably better ways to handle
 * all of this. Figure this out when we are ready to show graphs.
 */
public class View implements Iobserver {
  private final Stage stage;
  private GridPane center;
  private Graphs graphs;
  private InfoBox info;

  View(Stage stage, Observable observable) {
    this.stage = stage;
    observable.addObserver(this);
  }

  public void initView() {
    BorderPane root = (BorderPane) stage.getScene().getRoot();
    center = (GridPane) root.getCenter();
    stage.show();
  }

  public void setGraphs(Graphs graphs) {
    this.graphs = graphs;
  }
  public void setInfoBox(InfoBox info){
    this.info = info;
  }

  @Override
  public void update(String s) {
    Text beelieve = new Text(s);
    beelieve.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    BorderPane b = (BorderPane) stage.getScene().getRoot();
    GridPane g = (GridPane) b.getCenter();
    g.getChildren().clear();
    g.add(beelieve, 10, 5);
  }

  @Override
  public void updateGraphs(int xAxis,List<MarketOutput> mOutput, List<UserOutput> uOutput) {
    Platform.runLater(() -> {graphs.updateGraphs(xAxis, mOutput);
    info.updatePie(uOutput);});
  }

  @Override
  public void updateOwned(Sector sector, int amount) {
    Platform.runLater(() -> {graphs.updateOwnedField(sector, amount);});
  }
  public void updateCurrency(double currency) {
    Platform.runLater(() -> info.updateCurrency(currency));
  }
  public void updateProgress(int xAxis, double index, double player) {
    Platform.runLater(() -> info.updateLine(xAxis, index, player));
  }

  // TODO: Figure out how to connect events to text box

}
