package gu.dit213.group28;

import gu.dit213.group28.model.enums.Sector;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class CenterGrid {

  public LineChart<Number, Number> createGraph(Sector sector) {
    NumberAxis xAxis = new NumberAxis();
    NumberAxis yAxis = new NumberAxis();
    xAxis.setForceZeroInRange(false);
    yAxis.setForceZeroInRange(false);

    LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
    lineChart.setTitle(sector.toString());
    return lineChart;
  }

  public TextField createQuantityField() {
    TextField quantityField = new TextField();
    quantityField.setPrefWidth(100);
    quantityField.setPromptText("0");
    quantityField.setMinWidth(Region.USE_PREF_SIZE);
    return quantityField;
  }

  public HBox quantityPriceField(TextField quantityField, TextField priceField) {
    HBox quantityPriceControls =
        new HBox(10, new Label("Qty:"), quantityField, new Label("Price:"), priceField);
    quantityPriceControls.setAlignment(Pos.CENTER);
    quantityPriceControls.setMinWidth(Region.USE_PREF_SIZE);
    return quantityPriceControls;
  }

  public Button createButton(String text) {
    Button button = new Button(text);
    button.setMinWidth(Region.USE_PREF_SIZE);
    return button;
  }

  /**
   * Buy and sell button together with the owned field, wrapped in a horizontal box for scene
   * alignment of the grid
   */
  public HBox buySellOwnedBox(Button buyButton, Button sellButton, TextField ownedField) {
    HBox buySellControls = new HBox(10, buyButton, sellButton, new Label("Owned:"), ownedField);
    buySellControls.setAlignment(Pos.CENTER);
    buySellControls.setMinWidth(Region.USE_PREF_SIZE);
    return buySellControls;
  }

  /** Graph and controls wrapped in a vertical box for scene alignment of the grid */
  public VBox graphAndControlsBox(
      LineChart<Number, Number> lineChart, HBox buySellOwnedBox, HBox quantityPriceControls) {
    VBox graphAndControls = new VBox(10, lineChart, buySellOwnedBox, quantityPriceControls);
    graphAndControls.setAlignment(Pos.CENTER);
    VBox.setVgrow(lineChart, Priority.ALWAYS);
    return graphAndControls;
  }
}
