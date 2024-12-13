package gu.dit213.group28;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.interfaces.Icontrollable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class CenterGrid {

  public static GridPane createCenterGrid(Icontrollable model, View view) {
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
    populateCenterGrid(grid, model, view);
    return grid;
  }

  private static void populateCenterGrid(GridPane grid, Icontrollable model, View view) {
    Graphs graphs = new Graphs();
    Sector[] sectors = Sector.values();
    for (int i = 0; i < 6; i++) {
      Sector s = sectors[i + 1];

      // Create the graph/chart
      NumberAxis xAxis = new NumberAxis();
      NumberAxis yAxis = new NumberAxis();
      xAxis.setForceZeroInRange(false);
      yAxis.setForceZeroInRange(false);

      LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
      lineChart.setTitle(s.toString());

      // Adds the functionality under the graphs.
      TextField ownedField = new TextField();

      TextField priceField = new TextField();

      graphs.addGraph(lineChart, s, ownedField, priceField, getColour(s));

      TextField quantityField = new TextField();
      quantityField.setPrefWidth(100);
      quantityField.setPromptText("Quantity");
      quantityField.setMinWidth(Region.USE_PREF_SIZE);
      HBox buySellControls = getHBox(s, quantityField, ownedField, model);

      HBox quantityPriceControls =
          new HBox(10, new Label("Qty:"), quantityField, new Label("Price:"), priceField);
      quantityPriceControls.setAlignment(Pos.CENTER);
      quantityPriceControls.setMinWidth(Region.USE_PREF_SIZE);

      VBox graphAndControls = new VBox(10, lineChart, buySellControls, quantityPriceControls);
      graphAndControls.setAlignment(Pos.CENTER);
      VBox.setVgrow(lineChart, Priority.ALWAYS);

      grid.add(graphAndControls, i % 3, i / 3);
    }
    view.setGraphs(graphs);
  }

  private static HBox getHBox(
      Sector s, TextField quantityField, TextField ownedField, Icontrollable model) {
    Button buyButton = new Button("Buy");
    buyButton.setMinWidth(Region.USE_PREF_SIZE);
    buyButton.setOnAction(
        event -> {
          model.buyAsset(s, quantityField.getText());
        });
    Button sellButton = new Button("Sell");
    sellButton.setMinWidth(Region.USE_PREF_SIZE);
    sellButton.setOnAction(
        event -> {
          model.sellAsset(s, quantityField.getText());
        });

    HBox buySellControls = new HBox(10, buyButton, sellButton, new Label("Owned:"), ownedField);
    buySellControls.setAlignment(Pos.CENTER);
    return buySellControls;
  }

  private static String getColour(Sector s) {
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
