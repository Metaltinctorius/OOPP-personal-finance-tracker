package gu.dit213.group28;

import gu.dit213.group28.model.Model;
import gu.dit213.group28.view.View;
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
    Model model = new Model();

    new Controller(stage, model, new View(stage, model.getObservable()));
  }

  /**
   * Launches the JavaFX application.
   *
   * @param args The arguments passed to the application.
   */
  public static void main(String[] args) {
    launch();
  }

  @Override
  public void stop() throws Exception {
    super.stop();
    System.exit(0);
  }
}
