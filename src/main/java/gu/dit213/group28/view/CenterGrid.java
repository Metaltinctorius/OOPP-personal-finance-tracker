package gu.dit213.group28.view;

import gu.dit213.group28.model.enums.Sector;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Class that populates the center grid which is instantiated in the initStage() method of
 * controller. It populates with the graphs, buttons and information fields.
 */
public class CenterGrid {

  private final Map<Sector, Button> buyButtonMap = new HashMap<>();
  private final Map<Sector, Button> sellButtonMap = new HashMap<>();
  private final Map<Sector, TextField> quantityFieldMap = new HashMap<>();

  /** Constructor for the CenterGrid class. It initializes the CenterGrid. */
  public CenterGrid() {}

  /**
   * Populates the center grid with the graphs, controls and information fields.
   *
   * @param grid The grid to populate.
   * @param view The view to populate.
   */
  public void populateCenterGrid(GridPane grid, View view) {
    Graphs graphs = new Graphs();
    Sector[] sectors = Sector.values();
    for (int i = 0; i < 6; i++) {
      Sector sector = sectors[i + 1];

      buyButtonMap.put(sector, createButton("Buy"));
      sellButtonMap.put(sector, createButton("Sell"));

      TextField quantityField = createQuantityField();
      quantityFieldMap.put(sector, quantityField);

      LineChart<Number, Number> lineChart = createGraph(sector);
      TextField priceField = new TextField();
      TextField ownedField = new TextField();

      HBox buySellOwnedBox =
          createBuySellOwnedBox(buyButtonMap.get(sector), sellButtonMap.get(sector), ownedField);
      HBox quantityPriceBox = createQuantityPriceFieldBox(quantityField, priceField);
      VBox vbox = createGraphAndControlsBox(lineChart, buySellOwnedBox, quantityPriceBox);

      grid.add(vbox, i % 3, i / 3);
      graphs.addGraph(lineChart, sector, ownedField, priceField, getColour(sector));
    }
    view.setGraphs(graphs);
  }

  private LineChart<Number, Number> createGraph(Sector sector) {
    NumberAxis xaxis = new NumberAxis();
    NumberAxis yaxis = new NumberAxis();
    xaxis.setForceZeroInRange(false);
    yaxis.setForceZeroInRange(false);

    LineChart<Number, Number> lineChart = new LineChart<>(xaxis, yaxis);
    lineChart.setTitle(sector.toString());
    return lineChart;
  }

  private TextField createQuantityField() {
    TextField quantityField = new TextField();
    quantityField.setPrefWidth(100);
    quantityField.setPromptText("0");
    quantityField.setMinWidth(Region.USE_PREF_SIZE);
    return quantityField;
  }

  private HBox createQuantityPriceFieldBox(TextField quantityField, TextField priceField) {
    HBox quantityPriceControls =
        new HBox(10, new Label("Qty:"), quantityField, new Label("Price:"), priceField);
    quantityPriceControls.setAlignment(Pos.CENTER);
    quantityPriceControls.setMinWidth(Region.USE_PREF_SIZE);
    return quantityPriceControls;
  }

  private Button createButton(String text) {
    Button button = new Button(text);
    button.setMinWidth(Region.USE_PREF_SIZE);
    return button;
  }

  /**
   * Getter to be used in the controller for every buy button depending on sector. Receives the Key
   * as input and returns the Value in the Key/Value pair.
   *
   * @param sector The sector to get the buy button for.
   * @return The buy button for the sector.
   */
  public Button getBuyButton(Sector sector) {
    return buyButtonMap.get(sector);
  }

  /**
   * Getter to be used in the controller for every sell button depending on sector. Receives the Key
   * as input and returns the Value in the Key/Value pair.
   *
   * @param sector The sector to get the sell button for.
   * @return The sell button for the sector.
   */
  public Button getSellButton(Sector sector) {
    return sellButtonMap.get(sector);
  }

  /**
   * Getter to be used in the controller for every quantity field depending on sector. Receives the
   * Key as input and returns the Value in the Key/Value pair.
   *
   * @param sector The sector to get the quantity field for.
   * @return The quantity field for the sector.
   */
  public TextField getQuantityField(Sector sector) {
    return quantityFieldMap.get(sector);
  }

  private HBox createBuySellOwnedBox(Button buyButton, Button sellButton, TextField ownedField) {
    HBox buySellControls = new HBox(10, buyButton, sellButton, new Label("Owned:"), ownedField);
    buySellControls.setAlignment(Pos.CENTER);
    buySellControls.setMinWidth(Region.USE_PREF_SIZE);
    return buySellControls;
  }

  private VBox createGraphAndControlsBox(
      LineChart<Number, Number> lineChart, HBox buySellOwnedBox, HBox quantityPriceControls) {
    VBox graphAndControls = new VBox(10, lineChart, buySellOwnedBox, quantityPriceControls);
    graphAndControls.setAlignment(Pos.CENTER);
    VBox.setVgrow(lineChart, Priority.ALWAYS);
    return graphAndControls;
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
