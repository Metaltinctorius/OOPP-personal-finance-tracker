package gu.dit213.group28;

import gu.dit213.group28.model.MarketOutput;
import gu.dit213.group28.model.UserOutput;
import gu.dit213.group28.model.enums.Sector;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

public class Graphs {
  private final List<SectorGraph> graphs;
  private int x;

  public Graphs() {
    graphs = new ArrayList<>();
  }

  public void addGraph(
      LineChart<Number, Number> graph, Sector sector, TextField oField, TextField pField, String colour) {
    XYChart.Series<Number, Number> c = new XYChart.Series<>();
    Platform.runLater(() ->c.getNode().setStyle("-fx-stroke: " + colour+";"));
    graph.getData().add(c);
    graphs.add(new SectorGraph(graph, sector, oField, pField));
  }

  public void updateOwnedField(Sector sector, int owned) {
    for (SectorGraph sg : graphs) {
      if (sg.sector == sector) {
        sg.oField.setText(String.valueOf(owned));
      }
    }
  }

  public void updateGraphs(int xAxis, List<MarketOutput> mOutput) {
    for (SectorGraph sg : graphs) {
      for (MarketOutput output : mOutput) {
        if (sg.sector() == output.sector()) {
          sg.pField.setText(String.format("%.2f", output.value()));
          XYChart.Series<Number, Number> s = sg.graph.getData().getFirst();
          if (s.getData().size() > 15) {
            s.getData().removeFirst();
          }
          s.getData().add(new XYChart.Data<>(xAxis, output.value()));
        }
      }
    }
  }

  private record SectorGraph(
      LineChart<Number, Number> graph, Sector sector, TextField oField, TextField pField) {}
}
