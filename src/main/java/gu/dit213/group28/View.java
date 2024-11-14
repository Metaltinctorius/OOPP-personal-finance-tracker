package gu.dit213.group28;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.IObserver;
import model.Observable;

/**
 * Very messy and temporary view that was thrown together for testing. Not 100% sure how to bind the
 * controller and view together here, very new to JavaFX so there is probably better ways to handle
 * all of this.
 */
public class View implements IObserver {
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
