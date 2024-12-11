package gu.dit213.group28;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.Icontrollable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A controller class that initializes the JavaFX stage and passes it on to the view. Currently,
 * this class both initializes the stage and the buttons, which creates a dependency between the
 * model and the view. Alternatively, we might be able to separate the initialization of the stage
 * to happen somewhere else in a clean way.
 */
public class Controller {
  private final Stage stage;
  private final View view;
  private boolean isPaused = false;
  private final Icontrollable logic;
  private Text eventLog;

  /**
   * Initializes the controller with the stage, logic, and view. The stage creates the JavaFX
   * window, the logic is used to control the game, and the view is used to display the game state.
   *
   * @param stage The JavaFX stage.
   * @param logic The logic of the game.
   * @param view The view of the game.
   */
  public Controller(Stage stage, Icontrollable logic, View view) {
    this.stage = stage;
    this.view = view;
    this.logic = logic;

    initStage(stage);
    // OBS!!! IT IS VITAL THAT LOGIC.INIT() IS CALLED LAST!!
    logic.init();
  }

  /** Initializes the JavaFX window container stage. */
  private void initStage(Stage stage) {
    Scene scene = createScene();
    stage.setScene(scene);
    stage.setTitle("Finance Tracker");
    view.initView();
  }

  /** Creates the scene for the JavaFX stage. */
  private Scene createScene() {
    BorderPane root = new BorderPane();
    GridPane centerGrid = new GridPane();
    populateCenterGrid(centerGrid);

    root.setCenter(centerGrid);
    root.setBottom(createLowerButtonPanel());
    root.setRight(createEventTextBox());
    root.setLeft(createInfoBox());
    return new Scene(root, 1280, 720);
  }

  /** Creates a scene component showing events. */
  private TitledPane createEventTextBox() {
    eventLog = new Text();
    ScrollPane scrollPane = new ScrollPane(eventLog);
    scrollPane.setFitToWidth(true);

    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

    TitledPane titledPane = new TitledPane("News history", scrollPane);
    titledPane.setPrefWidth(200);
    titledPane.setPrefHeight(600);
    view.setEventLog(eventLog);
    return titledPane;
  }

  /** Creates a scene component with buttons for the bottom part of the scene. */
  private HBox createLowerButtonPanel() {
    HBox buttonPanel = new HBox();
    buttonPanel.setPadding(new Insets(15, 12, 15, 12));
    buttonPanel.setSpacing(10);
    buttonPanel.setStyle("-fx-background-color: #336699;");

    Button menuButton = new Button("Menu");
    menuButton.setPrefSize(80, 20);
    // do a setOnAction stuff here, implement when ready

    Button pauseResumeButton = new Button("Resume/Pause");
    pauseResumeButton.setPrefSize(80, 20);
    pauseResumeButton.setOnAction(event -> logic.pauseAndResume());

    // Alter game speed with a slider, 1 is slow, 2 is normal, 3 is fast
    Slider gameSpeedSlider = new Slider(1, 3, 2);
    gameSpeedSlider.setShowTickMarks(true);
    gameSpeedSlider.setMajorTickUnit(1);
    gameSpeedSlider.setBlockIncrement(1);
    gameSpeedSlider.setMinorTickCount(0);

    // Show game speed interactively in a label next to the slider
    Label gameSpeedLabel = new Label("Game Speed: Normal");
    gameSpeedLabel.setTextFill(Color.WHITE);

    gameSpeedSlider
        .valueProperty()
        .addListener(
            (obs, oldVal, newVal) -> {
              int snappedValue = newVal.intValue();
              gameSpeedSlider.setValue(snappedValue);
              alterGameSpeed(snappedValue);
              if (snappedValue == 1) {
                gameSpeedLabel.setText("Game Speed: Slow");
              } else if (snappedValue == 2) {
                gameSpeedLabel.setText("Game Speed: Normal");
              } else {
                gameSpeedLabel.setText("Game Speed: Fast");
              }
            });

    buttonPanel
        .getChildren()
        .addAll(menuButton, pauseResumeButton, gameSpeedSlider, gameSpeedLabel);
    return buttonPanel;
  }

  private void alterGameSpeed(int snappedValue) {
    switch (snappedValue) {
      case 1:
        logic.setSpeedSlow();
        break;
      case 2:
        logic.setSpeedNormal();
        break;
      case 3:
        logic.setSpeedFast();
        break;
      default:
        logic.setSpeedNormal();
        break;
    }
  }

  /* Button state depending on model state. Ignore for now.
    private void togglePauseResume(Button pauseResumeButton) {
      logic.pauseAndResume();
      isPaused = !isPaused;
      if (isPaused) {
        pauseResumeButton.setText("Resume");
      } else {
        pauseResumeButton.setText("Pause");
      }
    }
  */

  private void populateCenterGrid(GridPane grid) {
    CenterGrid centerGrid = new CenterGrid();
    Graphs graphs = new Graphs();
    Sector[] sectors = Sector.values();
    for (int i = 0; i < 6; i++) {
      Sector sector = sectors[i + 1];
      LineChart<Number, Number> lineChart = centerGrid.createGraph(sector);

      TextField ownedField = new TextField();
      TextField priceField = new TextField();
      TextField quantityField = centerGrid.createQuantityField();

      Button buyButton = centerGrid.createButton("Buy");
      Button sellButton = centerGrid.createButton("Sell");
      buyButton.setOnAction(event -> logic.buyAsset(sector, quantityField.getText()));
      sellButton.setOnAction(event -> logic.sellAsset(sector, quantityField.getText()));

      HBox buySellOwnedBox = centerGrid.buySellOwnedBox(buyButton, sellButton, ownedField);
      HBox quantityPriceBox = centerGrid.quantityPriceField(quantityField, priceField);
      VBox vbox = centerGrid.graphAndControlsBox(lineChart, buySellOwnedBox, quantityPriceBox);

      grid.add(vbox, i % 3, i / 3);
      graphs.addGraph(lineChart, sector, ownedField, priceField, getColour(sector));
    }
    view.setGraphs(graphs);
  }

  private VBox createInfoBox() {
    InfoBox info = new InfoBox();
    view.setInfoBox(info);

    return info.createInfoBox();
  }

  private String getColour(Sector s) {
    return switch (s) {
      case INFORMATION_TECHNOLOGY -> "#fba71b";
      case FINANCIALS -> "#57b757";
      case REAL_ESTATE -> "#41a9c9";
      case HEALTHCARE -> "#4258c9";
      case CONSUMER_STAPLES -> "#9a42c8";
      case UTILITIES -> "#c84164";
      default -> "#888888";
    };
  }
}
