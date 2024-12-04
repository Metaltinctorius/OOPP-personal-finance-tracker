package gu.dit213.group28;

import gu.dit213.group28.model.UserOutput;
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

  @Override
  public void update(String s) {
    Text beelieve = new Text(s);
    beelieve.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    BorderPane b = (BorderPane) stage.getScene().getRoot();
    GridPane g = (GridPane) b.getCenter();
    g.getChildren().clear();
    g.add(beelieve, 10, 5);
  }

  public void updateGraphs(int xAxis, List<UserOutput> output) {
    Platform.runLater(() -> graphs.updateGraphs(xAxis, output));
  }

  // TODO: Figure out how to connect events to text box

}
