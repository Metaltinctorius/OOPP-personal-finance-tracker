package gu.dit213.group28;

import gu.dit213.group28.model.UserOutput;
import gu.dit213.group28.model.enums.Sector;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Graphs {
  private final List<SectorGraph> graphs;
  private int x;

  public Graphs() {
    graphs = new ArrayList<>();
  }

  public void addGraph(LineChart<Number, Number> graph, Sector sector) {
    graph.getData().add(new XYChart.Series<>());
    graphs.add(new SectorGraph(graph, sector));
  }

  public void updateGraphs(int xAxis, List<UserOutput> marketOutput) {
    for (SectorGraph sg : graphs) {

      for (UserOutput output : marketOutput) {
        if (sg.sector() == output.sector()) {
          XYChart.Series<Number, Number> s = sg.graph.getData().getFirst();
          if (s.getData().size() > 15) {
            s.getData().removeFirst();
          }
          s.getData().add(new XYChart.Data<>(xAxis, output.value()));
        }
      }
    }
  }

  private record SectorGraph(LineChart<Number, Number> graph, Sector sector) {}
}
