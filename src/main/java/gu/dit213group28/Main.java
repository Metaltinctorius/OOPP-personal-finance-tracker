package gu.dit213group28;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
  /**
   * Instantiating a label and a scene to display the JavaFX version and Java version. Dummy code to
   * test the JavaFX environment. Feel free to replace with or expand to the dummy graph of our
   * weekly goal. If you do so however and want to push to gitHub, please notify others. Also feel
   * free to remove this comment since it is just a dummy JavaDoc test.
   */
  @Override
  public void start(Stage stage) {
    String javaVersion = System.getProperty("java.version");
    String javafxVersion = System.getProperty("javafx.version");
    Label l =
        new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
    Scene scene = new Scene(new StackPane(l), 640, 480);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}
