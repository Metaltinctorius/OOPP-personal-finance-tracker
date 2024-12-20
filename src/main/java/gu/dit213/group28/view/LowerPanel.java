package gu.dit213.group28.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/**
 * A class that creates the lower panel of the GUI, which contains buttons for controlling the game
 * speed.
 */
public class LowerPanel {

  private Label gameSpeedLabel;
  private Button pauseButton;
  private Button slowButton;
  private Button normalButton;
  private Button fastButton;

  /** Constructor for the LowerPanel class. It initializes the LowerPanel. */
  public LowerPanel() {}

  /**
   * Creates the lower panel of the GUI, which contains buttons for controlling the game speed.
   *
   * @param view The view of the game.
   * @return The lower panel of the GUI.
   */
  public HBox createLowerButtonPanel(View view) {
    HBox buttonPanel = new HBox();
    buttonPanel.setPadding(new Insets(15, 12, 15, 12));
    buttonPanel.setSpacing(10);
    buttonPanel.setStyle("-fx-background-color: #336699;");

    Region spacer1 = new Region();
    spacer1.setMinWidth(200);
    Region spacer2 = new Region();
    spacer2.setMinWidth(400);

    gameSpeedLabel = new Label("Speed: Normal");
    gameSpeedLabel.setTextFill(Color.WHITE);

    slowButton = createButton("Slow");
    normalButton = createButton("Normal");
    fastButton = createButton("Fast");
    pauseButton = createButton("Pause");

    buttonPanel
        .getChildren()
        .addAll(
            spacer1, pauseButton, spacer2, slowButton, normalButton, fastButton, gameSpeedLabel);
    view.setLowerPanel(this);
    return buttonPanel;
  }

  private Button createButton(String name) {
    Button button = new Button(name);
    button.setPrefSize(80, 20);
    return button;
  }

  /**
   * Method to retrieve the label that displays the current game speed.
   *
   * @return The label that displays the current game speed.
   */
  public Label getGameSpeedLabel() {
    return gameSpeedLabel;
  }

  /**
   * Method to retrieve the pause button.
   *
   * @return The pause button.
   */
  public Button getPauseButton() {
    return pauseButton;
  }

  /**
   * Method to retrieve the slow button.
   *
   * @return The slow button.
   */
  public Button getSlowButton() {
    return slowButton;
  }

  /**
   * Method to retrieve the normal button.
   *
   * @return The normal button.
   */
  public Button getNormalButton() {
    return normalButton;
  }

  /**
   * Method to retrieve the fast button.
   *
   * @return The fast button.
   */
  public Button getFastButton() {
    return fastButton;
  }

  /**
   * Method to update the pause button label.
   *
   * @param paused A boolean that indicates whether the game is paused or not.
   */
  public void updatePauseButton(boolean paused) {
    if (!paused) {
      pauseButton.setText("Pause");
    } else {
      pauseButton.setText("Resume");
    }
  }
}
