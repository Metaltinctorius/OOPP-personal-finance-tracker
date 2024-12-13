package gu.dit213.group28;

import gu.dit213.group28.model.interfaces.Icontrollable;

import javafx.scene.Scene;

import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A controller class that initializes the JavaFX stage and passes it on to the view. Currently,
 * this class both initializes the stage and the buttons, which creates a dependency between the
 * model and the view. Alternatively, we might be able to separate the initialization of the stage
 * to happen somewhere else in a clean way.
 */
public class Controller {
  private final View view;
  private final Icontrollable model;

  /**
   * Initializes the controller with the stage, model, and view. The stage creates the JavaFX
   * window, the model is used to control the game, and the view is used to display the game state.
   *
   * @param stage The JavaFX stage.
   * @param model The model of the game.
   * @param view The view of the game.
   */
  public Controller(Stage stage, Icontrollable model, View view) {
    this.view = view;
    this.model = model;

    initStage(stage);
    model.init();
  }

  /** Initializes the JavaFX window container stage. */
  private void initStage(Stage stage) {
    LowerPanel low = new LowerPanel();
    Scene scene = createScene(low);

    stage.setScene(scene);
    low.setKeys(scene, model);
    stage.setTitle("Finance Tracker");
    //view.setEventLog(eventLog);
    view.initView();
  }

  /** Creates the scene for the JavaFX stage. */
  private Scene createScene(LowerPanel low) {
    BorderPane root = new BorderPane();

    InfoBox info = new InfoBox();
    EventLogs eventLog = new EventLogs();
    CenterGrid centerGrid = new CenterGrid();
    root.setCenter(centerGrid.createCenterGrid(model, view));
    root.setBottom(low.createLowerButtonPanel(model, view));

    TitledPane eventLogPane = eventLog.createEventLog(view);
    eventLogPane.prefWidthProperty().bind(root.widthProperty().multiply(0.2));
    root.setRight(eventLogPane);

    root.setLeft(info.createInfoBox(view));

    return new Scene(root, 1280, 720);
  }

}
