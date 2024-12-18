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

public class Graphs {
  private final List<SectorGraph> graphs;

  /** Fields for setting the y-axis to adjust based on the highest measured Sector value. */
  private double globalMax = 0;

  private double globalMin = 500;
  private final double buffer = 300;

  public Graphs() {
    graphs = new ArrayList<>();
  }

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

  public void updateOwnedField(Sector sector, int owned) {
    for (SectorGraph sg : graphs) {
      if (sg.sector == sector) {
        sg.oField.setText(String.valueOf(owned));
      }
    }
  }

  public void updateGraphs(int xAxis, List<MarketOutput> mOutput) {

    for (MarketOutput m : mOutput) {
      if (m.value() > globalMax + buffer) {
        globalMax = m.value();
      }
      if (m.value() < globalMin - buffer) {
        globalMin = m.value();
      }
    }
    System.out.println(globalMax + buffer);

    for (SectorGraph sg : graphs) {
      for (MarketOutput output : mOutput) {
        if (sg.sector() == output.sector()) {
          sg.pField.setText(String.format("%.2f", output.value()));
          XYChart.Series<Number, Number> s = sg.graph.getData().getFirst();
          if (s.getData().size() > 50) {
            s.getData().removeFirst();
          }
          s.getData().add(new XYChart.Data<>(xAxis, output.value()));
        }
      }
      NumberAxis yAxis = (NumberAxis) sg.graph.getYAxis();
      yAxis.setAutoRanging(false);
      yAxis.setUpperBound(globalMax + buffer);
      yAxis.setLowerBound(globalMin - buffer);
    }
  }

  private record SectorGraph(
      LineChart<Number, Number> graph, Sector sector, TextField oField, TextField pField) {}
}
