package gu.dit213.group28;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Icontrollable;

/**
 * A controller class that initializes the JavaFX stage and passes it on to the view. Currently,
 * this class both initializes the stage and the buttons, which creates a dependency between the
 * model and the view. This is fine and made it easier for me to set up a quick test but different
 * from what we agreed on. Alternatively, we might be able to separate the initialization of the
 * stage to happen somewhere else in a clean way.
 */
public class Controller {
  private final Stage stage;
  private final Icontrollable logic;
  private final View view;

  Controller(Stage stage, Icontrollable logic, View view) {
    this.stage = stage;
    this.logic = logic;
    this.view = view;

    initStage();
  }

  private void initStage() {
    BorderPane root = new BorderPane();
    root.setBottom(createButtonPanel());
    root.setCenter(createCenterGrid());

    Scene scene = new Scene(root, 640, 480);
    stage.setScene(scene);
    stage.setTitle("Finance Tracker");
    view.initView();
  }

  private HBox createButtonPanel() {
    HBox buttonPanel = new HBox();
    buttonPanel.setPadding(new Insets(15, 12, 15, 12));
    buttonPanel.setSpacing(10);
    buttonPanel.setStyle("-fx-background-color: #336699;");

    Button b1 = new Button("b1");
    b1.setPrefSize(100, 20);
    b1.setOnAction(event -> logic.beeOne());

    Button b2 = new Button("b2");
    b2.setPrefSize(100, 20);
    b2.setOnAction(event -> logic.beeTwo());

    buttonPanel.getChildren().addAll(b1, b2);

    return buttonPanel;
  }

  private GridPane createCenterGrid() {
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(0, 30, 0, 30));

    return grid;
  }
}
