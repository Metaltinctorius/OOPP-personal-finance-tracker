package gu.dit213.group28;

import gu.dit213.group28.model.interfaces.Icontrollable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class LowerPanel {
  private Label gameSpeedLabel;
  private Button pauseButton;

  public HBox createLowerButtonPanel(Icontrollable model, View view) {
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
    // Show game speed interactively in a label next to the slider

    pauseButton = new Button("Pause");
    pauseButton.setPrefSize(80, 20);
    pauseButton.setOnAction(event -> model.pauseAndResume());

    Button slowButton = new Button("Slow");
    slowButton.setPrefSize(80, 20);
    slowButton.setOnAction(
        event -> {
          model.setSpeedSlow();
          gameSpeedLabel.setText("Speed: Slow");
        });

    Button normalButton = new Button("Normal");
    normalButton.setPrefSize(80, 20);
    normalButton.setOnAction(
        event -> {
          model.setSpeedNormal();
          gameSpeedLabel.setText("Speed: Normal");
        });

    Button fastButton = new Button("Fast");
    fastButton.setPrefSize(80, 20);
    fastButton.setOnAction(
        event -> {
          model.setSpeedFast();
          gameSpeedLabel.setText("Speed: Fast");
        });

    buttonPanel
        .getChildren()
        .addAll(
            spacer1, pauseButton, spacer2, slowButton, normalButton, fastButton, gameSpeedLabel);

    view.setLowerPanel(this);
    return buttonPanel;
  }

  public void setKeys(Scene scene, Icontrollable model) {
    scene.addEventHandler(
        KeyEvent.KEY_PRESSED,
        event -> {
          if (event.getCode() == KeyCode.S) {
            model.pauseAndResume();
          }
          if (event.getCode() == KeyCode.DIGIT1) {
            model.setSpeedSlow();
            gameSpeedLabel.setText("Speed: Slow");
          }
          if (event.getCode() == KeyCode.DIGIT2) {
            model.setSpeedNormal();
            gameSpeedLabel.setText("Speed: Normal");
          }
          if (event.getCode() == KeyCode.DIGIT3) {
            model.setSpeedFast();
            gameSpeedLabel.setText("Speed: Fast");
          }
        });
  }

  public void updatePauseButton(boolean paused) {
    if (!paused) {
      pauseButton.setText("Pause");
    } else {
      pauseButton.setText("Resume");
    }
  }
}
