package gu.dit213.group28;

import gu.dit213.group28.model.MarketOutput;
import gu.dit213.group28.model.UserOutput;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

import java.util.IllegalFormatException;
import java.util.List;


public class InfoBox {
    private final Info info;
    public InfoBox (PieChart pie, LineChart<Number, Number> line, TextField currency){
        info = new Info(pie, line, currency);
    }
    public void updatePie(List<UserOutput> uOutput){
        for (PieChart.Data p : info.pie.getData()) {
            for (UserOutput u : uOutput) {
                if (u.sector().toString().equals(p.getName())) {
                    p.setPieValue(u.value()*u.quantity());
                }
            }
        }
    }
    public void updateLine(int xAxis, double index, double player){
        XYChart.Series<Number, Number> is = info.line.getData().getFirst();
        XYChart.Series<Number, Number> ps = info.line.getData().getLast();
        is.getData().add(new XYChart.Data<>(xAxis, index));
        ps.getData().add(new XYChart.Data<>(xAxis, player));
    }
    public void updateCurrency(double currency){
        try {
            info.currency.setText(String.format("%.2f", currency));
            info.pie.getData().getFirst().setPieValue(currency);
        } catch (IllegalFormatException ignored) {}
    }
    private record Info (PieChart pie, LineChart<Number, Number> line, TextField currency){}
}
