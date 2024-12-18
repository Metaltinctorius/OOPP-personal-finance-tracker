package gu.dit213.group28.view;

import gu.dit213.group28.model.records.UserOutput;
import gu.dit213.group28.model.enums.Sector;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

import java.util.IllegalFormatException;
import java.util.List;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class InfoBox {
  private final Info info;

  public InfoBox() {
    PieChart pie = new PieChart();
    pie.getData().add(new PieChart.Data("Currency", 100000));
    Sector[] sectors = Sector.values();
    for (int i = 1; i < sectors.length; i++) {
      pie.getData().add(new PieChart.Data(sectors[i].toString(), 0));
    }

    NumberAxis xAxis = new NumberAxis();
    NumberAxis yAxis = new NumberAxis();
    xAxis.setForceZeroInRange(false);
    xAxis.setVisible(false);
    yAxis.setForceZeroInRange(false);
    yAxis.setVisible(false);

    LineChart<Number, Number> line = new LineChart<>(xAxis, yAxis);

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

  public void updatePie(List<UserOutput> uOutput) {
    for (PieChart.Data p : info.pie.getData()) {
      for (UserOutput u : uOutput) {
        if (u.sector().toString().equals(p.getName())) {
          p.setPieValue(u.value() * u.quantity());
        }
      }
    }
  }

  public void updatePie(Sector sector, int quantity, double value) {
    for (PieChart.Data p : info.pie.getData()) {
      if (sector.toString().equals(p.getName())) {
        p.setPieValue(quantity * value);
      }
    }
  }

  public void updateLine(int xAxis, double index, double player) {
    XYChart.Series<Number, Number> is = info.line.getData().getFirst();
    XYChart.Series<Number, Number> ps = info.line.getData().getLast();
    is.getData().add(new XYChart.Data<>(xAxis, index));
    ps.getData().add(new XYChart.Data<>(xAxis, player));
  }

  public void updateCurrency(double currency) {
    try {
      info.currency.setText(String.format("%.2f", currency));
      info.pie.getData().getFirst().setPieValue(currency);
    } catch (IllegalFormatException ignored) {
    }
  }

  private record Info(PieChart pie, LineChart<Number, Number> line, TextField currency) {}
}
