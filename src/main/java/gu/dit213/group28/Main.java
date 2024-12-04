package gu.dit213.group28;

import gu.dit213.group28.model.Logic;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main class of the application. This class is responsible for starting the JavaFX application
 * and initializing the controller.
 */
public class Main extends Application {

  /** Initializes the JavaFX application. */
  @Override
  public void start(Stage stage) {
    Logic logic = new Logic();
    new Controller(stage, logic, new View(stage, logic.getObservable()));
  }

  /** Launches the JavaFX application. */
  public static void main(String[] args) {
    launch();
  }
}
