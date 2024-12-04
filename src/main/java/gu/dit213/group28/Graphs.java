package gu.dit213.group28;

import gu.dit213.group28.model.UserOutput;
import gu.dit213.group28.model.enums.Sector;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

public class Graphs {
  private final List<SectorGraph> graphs;
  private int x;

  public Graphs() {
    graphs = new ArrayList<>();
  }

  public void addGraph(
      LineChart<Number, Number> graph, Sector sector, TextField oField, TextField pField) {
    graph.getData().add(new XYChart.Series<>());
    graphs.add(new SectorGraph(graph, sector, oField, pField));
  }

  public void updateOwnedField(Sector sector, int owned) {
    for (SectorGraph sg : graphs) {
      if (sg.sector == sector) {
        sg.oField.setText(String.valueOf(owned));
      }
    }
  }

  public void updateGraphs(int xAxis, List<UserOutput> marketOutput) {
    for (SectorGraph sg : graphs) {
      for (UserOutput output : marketOutput) {
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
