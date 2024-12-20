package gu.dit213.group28.view;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.records.UserOutput;
import java.util.IllegalFormatException;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * A class responsible for creating and managing the information box displayed on the left side of
 * the screen. The information box includes a pie chart to show percentage owned in each sector
 * compared to totally owned assets. A line chart showing both index and the players total assets
 * value, and a text field for displaying available currency.
 */
public class InfoBox {
  private final Info info;

  /**
   * Constructs an InfoBox by initializing its components: A pie chart to display data such as
   * currency and sector values. A line chart to represent the index and player values. A text field
   * to display the current currency that the player has available. These components are grouped
   * into a record for encapsulation.
   */
  public InfoBox() {
    PieChart pie = new PieChart();
    pie.getData().add(new PieChart.Data("Currency", 100000));
    Sector[] sectors = Sector.values();
    for (int i = 1; i < sectors.length; i++) {
      pie.getData().add(new PieChart.Data(sectors[i].toString(), 0));
    }

    NumberAxis xaxis = new NumberAxis();
    NumberAxis yaxis = new NumberAxis();
    xaxis.setForceZeroInRange(false);
    xaxis.setVisible(false);
    yaxis.setForceZeroInRange(false);
    yaxis.setVisible(false);

    LineChart<Number, Number> line = new LineChart<>(xaxis, yaxis);

    line.setHorizontalGridLinesVisible(false);
    line.setHorizontalZeroLineVisible(false);
    line.setVerticalGridLinesVisible(false);
    line.setVerticalZeroLineVisible(false);
    line.setLegendVisible(false);
    line.setPrefHeight(250);
    line.getData().add(new XYChart.Series<>());
    line.getData().add(new XYChart.Series<>());
    line.setCreateSymbols(false);

    TextField currency = new TextField();
    currency.setPromptText("Currency");
    currency.setEditable(false);
    currency.setText("0");
    currency.setMaxWidth(100);

    info = new Info(pie, line, currency);
  }

  /**
   * Creates the information box by adding the pie chart, line chart, and currency text field to a
   * VBox. The VBox is then returned.
   *
   * @param view The view that the information box is associated with.
   * @return The VBox containing the pie chart, line chart, and currency text field.
   */
  public VBox createInfoBox(View view) {
    Region spacer1 = new Region();
    spacer1.setMaxHeight(50);
    VBox infoBox = new VBox(10, info.pie, info.line, info.currency, spacer1);

    VBox.setVgrow(spacer1, Priority.ALWAYS);
    infoBox.setPrefWidth(200);
    infoBox.setAlignment(Pos.BOTTOM_CENTER);
    view.setInfoBox(this);
    return infoBox;
  }

  /**
   * Updates the pie chart with the data from the list of UserOutputs. The pie chart is updated by
   * iterating through the data and setting the pie value of each sector to the product of the value
   * and quantity of the UserOutput.
   *
   * @param userOutput The list of UserOutputs to update the pie chart with.
   */
  public void updatePie(List<UserOutput> userOutput) {
    for (PieChart.Data p : info.pie.getData()) {
      for (UserOutput u : userOutput) {
        if (u.sector().toString().equals(p.getName())) {
          p.setPieValue(u.value() * u.quantity());
        }
      }
    }
  }

  /**
   * Updates specific slices (sectors) of the pie chart. The pie chart is updated by iterating
   * through the data and setting the pie value of the sector to the product of the value and
   * quantity owned by the player.
   *
   * @param sector The sector to update the pie chart with.
   * @param quantity The quantity of the sector to update the pie chart with.
   * @param value The value of the sector to update the pie chart with.
   */
  public void updatePie(Sector sector, int quantity, double value) {
    for (PieChart.Data p : info.pie.getData()) {
      if (sector.toString().equals(p.getName())) {
        p.setPieValue(quantity * value);
      }
    }
  }

  /**
   * Updates the line chart with the index and player values. The line chart is updated by adding a
   * new data point to the index and player series.
   *
   * @param xaxisVal The x-axis value to update the line chart with.
   * @param index The index value to update the line chart with.
   * @param player The player value to update the line chart with.
   */
  public void updateLine(int xaxisVal, double index, double player) {
    XYChart.Series<Number, Number> is = info.line.getData().getFirst();
    XYChart.Series<Number, Number> ps = info.line.getData().getLast();
    is.getData().add(new XYChart.Data<>(xaxisVal, index));
    ps.getData().add(new XYChart.Data<>(xaxisVal, player));
  }

  /**
   * Updates the currency text field with the currency value the player currently have available.
   *
   * @param currency The currency value to update the text field with.
   */
  public void updateCurrency(double currency) {
    try {
      info.currency.setText(String.format("%.2f", currency));
      info.pie.getData().getFirst().setPieValue(currency);
    } catch (IllegalFormatException e) {
      System.out.println("Invalid currency");
    }
  }

  private record Info(PieChart pie, LineChart<Number, Number> line, TextField currency) {}
}
