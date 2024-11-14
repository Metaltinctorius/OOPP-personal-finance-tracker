package gu.dit213.group28;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Logic;

public class Main extends Application {

  /**
   * Instantiating a label and a scene to display the JavaFX version and Java version. Dummy code to
   * test the JavaFX environment. Feel free to replace with or expand to the dummy graph of our
   * weekly goal. If you do so however and want to push to gitHub, please notify others. Also feel
   * free to remove this comment since it is just a dummy JavaDoc test.
   */
  @Override
  public void start(Stage stage) {
    Logic logic = new Logic();
    Controller controller = new Controller(stage, logic, new View(stage, logic));
  }

  public static void main(String[] args) {
    launch();
  }
}
