package gu.dit213.group28.view;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * A class that creates the right side panel of the GUI, which contains the history of news, buy and
 * sell events.
 */
public class EventLogs {
  private static VBox eventLogContainer;

  /** Constructor for the EventLogs class. It initializes the EventLogs. */
  public EventLogs() {}

  /**
   * Creates the right side panel of the GUI, which contains the history of news, buy and sell
   * events.
   *
   * @param view The view of the game.
   * @return The right side panel of the GUI.
   */
  public TitledPane createEventLog(View view) {

    eventLogContainer = new VBox();
    eventLogContainer.setSpacing(12);

    ScrollPane scrollPane = new ScrollPane(eventLogContainer);
    scrollPane.setFitToWidth(true);

    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

    TitledPane titledPane = new TitledPane("History", scrollPane);
    titledPane.setPrefWidth(250);
    titledPane.setPrefHeight(600);
    titledPane.setStyle(
        "-fx-font-size: 16px; "
            + "-fx-text-fill: black; "
            + "-fx-border-width: 2px; "
            + "-fx-border-radius: 5px; "
            + "-fx-background-radius: 5px;");
    view.setEventLog(this);
    return titledPane;
  }

  /**
   * Populates the right side panel with news events.
   *
   * @param eventTitle The title of the event.
   */
  public void populateHistoryWithNewsEvent(String eventTitle) {
    Text eventTile = new Text(eventTitle);
    eventTile.setStyle("-fx-font-size: 14px; -fx-fill: blue; -fx-underline: false;");
    eventTile.setOnMouseEntered(
        event ->
            eventTile.setStyle(
                "-fx-font-size: 14px; -fx-fill: blue; -fx-underline: false; -fx-cursor: hand;"));
    eventTile.setOnMouseExited(
        event -> eventTile.setStyle("-fx-font-size: 14px; -fx-fill: blue; -fx-underline: false;"));
    eventLogContainer.getChildren().add(0, eventTile);
  }

  /**
   * Populates the right side panel with buy events.
   *
   * @param sector The sector of the assets.
   * @param quantity The quantity of assets bought.
   * @param value The value of the assets.
   */
  public void populateHistoryWithBuyEvent(String sector, int quantity, double value) {
    double sumValue = quantity * value;
    String formattedValue = String.format("%.2f", sumValue);
    Text buyTile =
        new Text(
            "You bought "
                + quantity
                + " assets"
                + "\r\n"
                + "of "
                + sector
                + " for "
                + formattedValue);
    buyTile.setStyle("-fx-font-size: 14px; -fx-fill: green; -fx-underline: false;");
    eventLogContainer.getChildren().add(0, buyTile);
  }

  /**
   * Populates the right side panel with sell events.
   *
   * @param sector The sector of the assets.
   * @param quantity The quantity of assets sold.
   * @param value The value of the assets.
   */
  public void populateHistoryWithSellEvent(String sector, int quantity, double value) {
    double sumValue = quantity * value;
    String formattedValue = String.format("%.2f", sumValue);
    Text sellTile =
        new Text(
            "You sold "
                + quantity
                + " assets"
                + "\r\n"
                + "of "
                + sector
                + " for "
                + formattedValue);
    sellTile.setStyle("-fx-font-size: 14px; -fx-fill: red; -fx-underline: false;");
    eventLogContainer.getChildren().add(0, sellTile);
  }

  /**
   * Getter for the event log text VBox.
   *
   * @return The VBox event log container.
   */
  public VBox getEventLogText() {
    return eventLogContainer;
  }
}
