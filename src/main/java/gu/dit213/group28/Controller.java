package gu.dit213.group28;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.Icontrollable;
import java.time.LocalDate;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
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

  public Controller(Stage stage, Icontrollable logic, View view) {
    this.stage = stage;
    this.view = view;
    this.logic = logic;

    initStage();
    // OBS!!! IT IS VITAL THAT LOGIC.INIT() IS CALLED LAST!!
    logic.init();
  }

  private void initStage() {
    BorderPane root = new BorderPane();
    GridPane centerGrid = createCenterGrid();
    populateCenterGrid(centerGrid);

    root.setCenter(centerGrid);
    root.setBottom(createLowerButtonPanel());
    root.setRight(createEventTextBox());
    root.setLeft(createInfoBox());

    Scene scene = new Scene(root, 1280, 720);
    stage.setScene(scene);
    stage.setTitle("Finance Tracker");
    view.initView();
  }

  // Unsure how to connect the event log to the facade. Probably using Platform.runLater
  // somehow.
  private ScrollPane createEventTextBox() {
    Text eventLog = new Text();
    eventLog.setWrappingWidth(250);

    ScrollPane scrollPane = new ScrollPane(eventLog);
    scrollPane.setFitToWidth(true);
    scrollPane.setPrefWidth(250);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    return scrollPane;
  }

  private HBox createLowerButtonPanel() {
    HBox buttonPanel = new HBox();
    buttonPanel.setPadding(new Insets(15, 12, 15, 12));
    buttonPanel.setSpacing(10);
    buttonPanel.setStyle("-fx-background-color: #336699;");

    Button menuButton = new Button("menu");
    menuButton.setPrefSize(80, 20);
    // do a setOnAction stuff here, implement when ready
    // menuButton.setOnAction(event -> placeholder );

    Button pauseResumeButton = new Button("Pause");
    pauseResumeButton.setPrefSize(80, 20);
    pauseResumeButton.setOnAction(event -> togglePauseResume(pauseResumeButton));

    Button info = new Button("info");
    info.setPrefSize(80, 20);
    // do some setOnAction stuff here

    // Game speed multiplier
    Slider gameSpeedSlider = new Slider(1, 3, 2);
    gameSpeedSlider.setShowTickMarks(true);
    gameSpeedSlider.setMajorTickUnit(1);
    gameSpeedSlider.setBlockIncrement(1);
    gameSpeedSlider.setMinorTickCount(0);

    // Showing game speed interactively in a label next to the slider
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

    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    buttonPanel
        .getChildren()
        .addAll(menuButton, pauseResumeButton, gameSpeedSlider, gameSpeedLabel, spacer, info);
    return buttonPanel;
  }

  private void alterGameSpeed(int snappedValue) {
    // Change these calls if needed when connecting to the model.
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
  grid.setPrefWidth(800);
    return grid;
  }

  private void populateCenterGrid(GridPane grid) {
    Graphs graphs = new Graphs();
    Sector[] sectors = Sector.values();
    for (int i = 0; i < 6; i++) {
      // Create the graph/chart
      NumberAxis xAxis = new NumberAxis();
      NumberAxis yAxis = new NumberAxis();
      xAxis.setForceZeroInRange(false);
      yAxis.setForceZeroInRange(false);

      LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
      lineChart.setCreateSymbols(false);
      lineChart.setHorizontalGridLinesVisible(false);
      lineChart.setHorizontalZeroLineVisible(false);
      lineChart.setVerticalGridLinesVisible(false);
      lineChart.setVerticalZeroLineVisible(false);
      lineChart.setLegendVisible(false);
      // Change titles to asset or sector type with a toString method and add the asset as a
      // parameter.
      lineChart.setTitle(sectors[i + 1].toString());

      // Sample data for the chart, implement with data from our model instead.
      // XYChart.Series<Number, Number> series = new XYChart.Series<>();
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
      graphs.addGraph(lineChart, sectors[i + 1], ownedField, priceField, getColour(sectors[i +1]));
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
  private VBox createInfoBox(){
    Region spacer1 = new Region();
    spacer1.setMaxHeight(50);
    PieChart pieChart = new PieChart();
    pieChart.getData().add(new PieChart.Data("Currency", 0));
    Sector[] sectors = Sector.values();
    for (int i = 1; i < sectors.length; i++){
      pieChart.getData().add(new PieChart.Data(sectors[i].toString(), 0));
    }
    NumberAxis xAxis = new NumberAxis();
    NumberAxis yAxis = new NumberAxis();
    xAxis.setForceZeroInRange(false);
    xAxis.setVisible(false);
    yAxis.setForceZeroInRange(false);
    yAxis.setVisible(false);

    LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
    lineChart.setHorizontalGridLinesVisible(false);
    lineChart.setHorizontalZeroLineVisible(false);
    lineChart.setVerticalGridLinesVisible(false);
    lineChart.setVerticalZeroLineVisible(false);
    lineChart.setLegendVisible(false);
    lineChart.setPrefHeight(250);
    lineChart.getData().add(new XYChart.Series<>());
    lineChart.getData().add(new XYChart.Series<>());
    lineChart.setCreateSymbols(false);


    TextField currencyField = new TextField();
    currencyField.setPromptText("Currency");
    currencyField.setEditable(false);
    currencyField.setText("0");
    currencyField.setMaxWidth(100);

    InfoBox info = new InfoBox(pieChart, lineChart, currencyField);
    view.setInfoBox(info);
    VBox infoBox = new VBox(10, pieChart, lineChart, currencyField, spacer1);
    VBox.setVgrow(spacer1, Priority.ALWAYS);
    infoBox.setPrefWidth(250);
    infoBox.setAlignment(Pos.BOTTOM_CENTER);
    return infoBox;
  }

  private String getColour(Sector s){
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
