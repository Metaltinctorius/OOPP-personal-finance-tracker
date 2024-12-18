package gu.dit213.group28.view;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.TitledPane;


/**
 * A class that creates the right side panel of the GUI, which contains the history of news, buy and sell events.
 */
public class EventLogs {
  private static VBox eventLogContainer;

    /**
     * Creates the right side panel of the GUI, which contains the history of news, buy and sell events.
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
            + // Increase font size for the title
            "-fx-text-fill: black; "
            + // Change the title text color
            "-fx-border-width: 2px; "
            + // Border thickness
            "-fx-border-radius: 5px; "
            + // Rounded border corners
            "-fx-background-radius: 5px;" // Match border radius
        );
    view.setEventLog(this);
    return titledPane;
  }
  /** Populates the right side panel with news events */
  public void populateEventTextBox(String eventTitle) {
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
  /** Populates the right side panel with buy events */
  public void populateBuyTextBox(String sector, int quantity, double value){
      double sumValue = quantity * value;
      String formattedValue = String.format("%.2f", sumValue);
      Text buyTile = new Text("You bought " + quantity + " assets" + "\r\n" +"of " + sector + " for " + formattedValue);
      buyTile.setStyle("-fx-font-size: 14px; -fx-fill: green; -fx-underline: false;");
      eventLogContainer.getChildren().add(0, buyTile);
  }
  /** Populates the right side panel with sell events */
  public void populateSellTextBox(String sector, int quantity, double value){
      double sumValue = quantity * value;
      String formattedValue = String.format("%.2f", sumValue);
      Text sellTile = new Text("You sold " + quantity + " assets" + "\r\n" +"of " + sector + " for " + formattedValue);
      sellTile.setStyle("-fx-font-size: 14px; -fx-fill: red; -fx-underline: false;");
      eventLogContainer.getChildren().add(0, sellTile);
  }


  public VBox getEventLogText() {
    return eventLogContainer;
  }

}
