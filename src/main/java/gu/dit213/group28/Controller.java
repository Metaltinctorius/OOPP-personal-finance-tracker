package gu.dit213.group28;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.Icontrollable;

import gu.dit213.group28.view.CenterGrid;
import gu.dit213.group28.view.EventLogs;
import gu.dit213.group28.view.InfoBox;
import gu.dit213.group28.view.LowerPanel;
import gu.dit213.group28.view.View;
import gu.dit213.group28.view.WelcomeDialog;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * The controller class is responsible for initializing and managing the interaction between the
 * JavaFX stage, the model, and the view. It sets up the JavaFX stage, initializes the view, and act
 * as a mediator between the view and the model for handling user interactions.
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
    LowerPanel lowerPanel = new LowerPanel();
    Scene scene = createScene(lowerPanel);

    stage.setScene(scene);
    setKeys(scene, model, lowerPanel);
    stage.setTitle("Finance Tracker");
    view.initView();
    WelcomeDialog welcomeDialog = new WelcomeDialog();
    welcomeDialog.createWelcomeDialog();
  }

  /** Creates the scene for the JavaFX stage. */
  private Scene createScene(LowerPanel low) {
    BorderPane root = new BorderPane();
    GridPane grid = new GridPane();
    CenterGrid centerGrid = new CenterGrid();
    InfoBox info = new InfoBox();
    EventLogs eventLog = new EventLogs();

    TitledPane eventLogPane = eventLog.createEventLog(view);
    eventLogPane.prefWidthProperty().bind(root.widthProperty().multiply(0.2));

    centerGrid.populateCenterGrid(grid, view);
    root.setCenter(grid);
    root.setBottom(low.createLowerButtonPanel(view));
    root.setRight(eventLogPane);
    root.setLeft(info.createInfoBox(view));

    setOnBuySellButtonActionForEverySector(centerGrid);
    setOnButtonActionLowerPanel(low);

    return new Scene(root, 1600, 720);
  }

  private void setOnBuySellButtonActionForEverySector(CenterGrid centerGrid) {
    Sector[] sectors = Sector.values();
    for (int i = 0; i < 6; i++) {
      Sector sector = sectors[i + 1];
      Button buyButton = centerGrid.getBuyButton(sector);
      Button sellButton = centerGrid.getSellButton(sector);
      TextField quantityField = centerGrid.getQuantityField(sector);
      setOnBuySellButtonAction(buyButton, sellButton, sector, quantityField);
    }
  }

  private void setOnBuySellButtonAction(
      Button buyButton, Button sellButton, Sector sector, TextField quantityField) {
    buyButton.setOnAction(event -> model.buyAsset(sector, quantityField.getText()));
    sellButton.setOnAction(event -> model.sellAsset(sector, quantityField.getText()));
  }

  private void setOnButtonActionLowerPanel(LowerPanel lowerPanel) {
    Button pauseButton = lowerPanel.getPauseButton();
    pauseButton.setOnAction(event -> model.pauseAndResume());

    Button slowButton = lowerPanel.getSlowButton();
    slowButton.setOnAction(
        event -> {
          model.setSpeedSlow();
          lowerPanel.getGameSpeedLabel().setText("Speed: Slow");
        });

    Button normalButton = lowerPanel.getNormalButton();
    normalButton.setOnAction(
        event -> {
          model.setSpeedNormal();
          lowerPanel.getGameSpeedLabel().setText("Speed: Normal");
        });

    Button fastButton = lowerPanel.getFastButton();
    fastButton.setOnAction(
        event -> {
          model.setSpeedFast();
          lowerPanel.getGameSpeedLabel().setText("Speed: Fast");
        });
  }

  private void setKeys(Scene scene, Icontrollable model, LowerPanel lowerPanel) {
    scene.addEventHandler(
        KeyEvent.KEY_PRESSED,
        event -> {
          if (event.getCode() == KeyCode.S) {
            model.pauseAndResume();
          }
          if (event.getCode() == KeyCode.DIGIT1) {
            model.setSpeedSlow();
            lowerPanel.getGameSpeedLabel().setText("Speed: Slow");
          }
          if (event.getCode() == KeyCode.DIGIT2) {
            model.setSpeedNormal();
            lowerPanel.getGameSpeedLabel().setText("Speed: Normal");
          }
          if (event.getCode() == KeyCode.DIGIT3) {
            model.setSpeedFast();
            lowerPanel.getGameSpeedLabel().setText("Speed: Fast");
          }
        });
  }
}
