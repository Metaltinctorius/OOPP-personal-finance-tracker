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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
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
   * Initializes the controller with the stage, logic, and view. The stage is used to create the
   * JavaFX window, the logic is used to control the game, and the view is used to display the game
   * state.
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
    view.setEventLog(eventLog);
    view.initView();
  }

  /** Defines the scene layout for the stage. */
  private Scene createScene() {
    BorderPane root = new BorderPane();
    GridPane centerGrid = createCenterGrid();
    populateCenterGrid(centerGrid);

    root.setCenter(centerGrid);
    root.setBottom(createLowerButtonPanel());
    root.setRight(createEventTextBox());
    return new Scene(root, 640, 480);
  }

  /** Creates a scene component showing events. */
  private TitledPane createEventTextBox() {
    eventLog = new Text();
    ScrollPane scrollPane = new ScrollPane(eventLog);
    scrollPane.setFitToWidth(true);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

    TitledPane titledPane = new TitledPane("News history", scrollPane);
    titledPane.setPrefWidth(400);
    titledPane.setPrefHeight(600);
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

    Button pauseResumeButton = new Button("Pause");
    pauseResumeButton.setPrefSize(80, 20);
    pauseResumeButton.setOnAction(event -> logic.pause());

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

  // Button state depending on model state.
  private void togglePauseResume(Button pauseResumeButton) {
    isPaused = !isPaused;
    if (isPaused) {
      pauseResumeButton.setText("Resume");
      // Add some action here that signals to model to pause
    } else {
      pauseResumeButton.setText("Pause");
      // Add some action here that signals to model to resume
    }
  }

  private GridPane createCenterGrid() {
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(0, 0, 15, 15));

    // Distributes the columns and rows sizes evenly in the center grid.
    for (int i = 0; i < 3; i++) {
      ColumnConstraints colConstraint = new ColumnConstraints();
      colConstraint.setPercentWidth(33.33);
      grid.getColumnConstraints().add(colConstraint);
    }
    for (int i = 0; i < 2; i++) {
      RowConstraints rowConstraint = new RowConstraints();
      rowConstraint.setPercentHeight(50);
      grid.getRowConstraints().add(rowConstraint);
    }

    return grid;
  }

  private void populateCenterGrid(GridPane grid) {
    Graphs graphs = new Graphs();
    Sector[] sectors = Sector.values();
    for (int i = 0; i < 6; i++) {
      // Create the graph/chart
      NumberAxis xAxis = new NumberAxis();
      NumberAxis yAxis = new NumberAxis();
      xAxis.setLabel("Game time");
      xAxis.setForceZeroInRange(false);
      yAxis.setLabel("Current value");
      yAxis.setForceZeroInRange(false);

      LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
      // Change titles to asset or sector type with a toString method and add the asset as a
      // parameter.
      lineChart.setTitle(sectors[i + 1].toString());

      // Sample data for the chart, implement with data from our model instead.
      // XYChart.Series<Number, Number> series = new XYChart.Series<>();
      lineChart.setLegendVisible(false);
      /*series.getData().add(new XYChart.Data<>(1, 5));
      series.getData().add(new XYChart.Data<>(2, 10));
      series.getData().add(new XYChart.Data<>(3, 15));
      lineChart.getData().add(series);*/

      // Adds the functionality under the graphs.

      TextField ownedField = new TextField();
      ownedField.setEditable(false);
      ownedField.setText("0");
      TextField quantityField = new TextField();
      quantityField.setPromptText("Quantity");
      TextField priceField = new TextField();
      priceField.setEditable(false);
      priceField.setText("0");
      graphs.addGraph(lineChart, sectors[i + 1], ownedField, priceField);
      Sector s = sectors[i + 1];
      Button buyButton = new Button("Buy");
      buyButton.setOnAction(
          event -> {
            logic.buyAsset(s, quantityField.getText());
          });
      Button sellButton = new Button("Sell");
      sellButton.setOnAction(
          event -> {
            logic.sellAsset(s, quantityField.getText());
          });

      HBox buySellControls = new HBox(10, buyButton, sellButton, new Label("Owned:"), ownedField);
      buySellControls.setAlignment(Pos.CENTER);

      HBox quantityPriceControls =
          new HBox(10, new Label("Qty:"), quantityField, new Label("Price:"), priceField);
      quantityPriceControls.setAlignment(Pos.CENTER);

      VBox graphAndControls = new VBox(10, lineChart, buySellControls, quantityPriceControls);
      graphAndControls.setAlignment(Pos.CENTER);
      VBox.setVgrow(lineChart, Priority.ALWAYS);

      grid.add(graphAndControls, i % 3, i / 3);
    }
    view.setGraphs(graphs);
  }
}
