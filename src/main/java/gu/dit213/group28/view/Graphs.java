package gu.dit213.group28.view;

import gu.dit213.group28.model.enums.Sector;
import gu.dit213.group28.model.records.MarketOutput;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

/** Class for creating graphs for the sectors. */
public class Graphs {
  private final List<SectorGraph> graphs;

  /** Fields for setting the y-axis to adjust based on the highest measured Sector value. */
  private double globalMax = 0;

  private double globalMin = 500;
  private final double buffer = 300;

  /** Constructor for the Graphs class. */
  public Graphs() {
    graphs = new ArrayList<>();
  }

  /**
   * Method for adding a graph and two text fields to the center grid. The components are stored in
   * a record called SectorGraph. The method modifies the input parameters and adds each SectorGraph
   * to the list of sector graphs
   *
   * @param lineChart The line chart to be added to the center grid.
   * @param sector The sector to be added to the center grid.
   * @param ownedField The text field for displaying the owned value of the sector.
   * @param priceField The text field for displaying the price of the sector.
   * @param colour The colour of the sector.
   */
  public void addGraph(
      LineChart<Number, Number> lineChart,
      Sector sector,
      TextField ownedField,
      TextField priceField,
      String colour) {

    ownedField.setPrefWidth(150);
    ownedField.setEditable(false);
    ownedField.setText("0");
    ownedField.setMinWidth(Region.USE_PREF_SIZE);

    priceField.setPrefWidth(120);
    priceField.setMinWidth(Region.USE_PREF_SIZE);
    priceField.setEditable(false);
    priceField.setText("0");

    lineChart.setCreateSymbols(false);
    lineChart.setHorizontalGridLinesVisible(false);
    lineChart.setHorizontalZeroLineVisible(false);
    lineChart.setVerticalGridLinesVisible(false);
    lineChart.setVerticalZeroLineVisible(false);
    lineChart.setLegendVisible(false);

    XYChart.Series<Number, Number> c = new XYChart.Series<>();
    Platform.runLater(() -> c.getNode().setStyle("-fx-stroke: " + colour + ";"));
    lineChart.getData().add(c);
    graphs.add(new SectorGraph(lineChart, sector, ownedField, priceField));
  }

  /**
   * Method for updating the owned field of a sector. The method iterates through the list of sector
   * graphs and updates the owned field of the sector that matches the input parameter.
   *
   * @param sector The sector to be updated.
   * @param owned The new value of the owned field.
   */
  public void updateOwnedField(Sector sector, int owned) {
    for (SectorGraph sg : graphs) {
      if (sg.sector == sector) {
        sg.ownedField.setText(String.valueOf(owned));
      }
    }
  }

  /**
   * Method for updating the graphs with the latest market outputs (prices). It also normalizes the
   * graphs y-axis to the graph having the highest price at any current time, making it easier to
   * monitor how successful each sector is.
   *
   * @param xaxisVal The current x-axis value.
   * @param marketOutputs The list of market outputs to update the graphs with.
   */
  public void updateGraphs(int xaxisVal, List<MarketOutput> marketOutputs) {

    for (MarketOutput m : marketOutputs) {
      if (m.value() > globalMax + buffer) {
        globalMax = m.value();
      }
      if (m.value() < globalMin - buffer) {
        globalMin = m.value();
      }
    }

    for (SectorGraph sg : graphs) {
      for (MarketOutput output : marketOutputs) {
        if (sg.sector() == output.sector()) {
          sg.priceField.setText(String.format("%.2f", output.value()));
          XYChart.Series<Number, Number> s = sg.graph.getData().getFirst();
          if (s.getData().size() > 40) {
            s.getData().removeFirst();
          }
          s.getData().add(new XYChart.Data<>(xaxisVal, output.value()));
        }
      }
      NumberAxis yaxisVal = (NumberAxis) sg.graph.getYAxis();
      yaxisVal.setAutoRanging(false);
      yaxisVal.setUpperBound(globalMax + buffer);
      yaxisVal.setLowerBound(globalMin - buffer);
    }
  }

  private record SectorGraph(
      LineChart<Number, Number> graph, Sector sector, TextField ownedField, TextField priceField) {}
}
