package gu.dit213.group28;

import gu.dit213.group28.model.Iobserver;
import gu.dit213.group28.model.Observable;
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

  View(Stage stage, Observable observable) {
    this.stage = stage;
    observable.addObserver(this);
  }

  public void initView() {
    stage.show();
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
}
