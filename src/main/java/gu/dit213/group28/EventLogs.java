package gu.dit213.group28;


import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.TitledPane;


public class EventLogs {
    private static Text eventLog;
    private static VBox eventLogContainer;
    //public Text eventLog;

    public TitledPane createEventLog(View view) {
        //eventLog = new Text();
        eventLogContainer = new VBox();
        eventLogContainer.setSpacing(12);
        ScrollPane scrollPane = new ScrollPane(eventLogContainer);
        scrollPane.setFitToWidth(true);

        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        TitledPane titledPane = new TitledPane("News history", scrollPane);
        titledPane.setPrefWidth(250);
        titledPane.setPrefHeight(600);
        titledPane.setStyle(
                "-fx-font-size: 16px; " + // Increase font size for the title
                        "-fx-text-fill: black; " + // Change the title text color
                        "-fx-border-width: 2px; " + // Border thickness
                        "-fx-border-radius: 5px; " + // Rounded border corners
                        "-fx-background-radius: 5px;" // Match border radius
        );
        view.setEventLog(this);
        return titledPane;
    }
    public void populateEventTextBox(String eventTitle) {
        //String existingText = eventLog.getText(); // Get current text
        //String updatedText = existingText + (existingText.isEmpty() ? "" : "\n") + eventTitle;
        Text eventTile = new Text(eventTitle);
        eventTile.setStyle("-fx-font-size: 14px; -fx-fill: blue; -fx-underline: false;");
        eventTile.setOnMouseEntered(event -> eventTile.setStyle("-fx-font-size: 14px; -fx-fill: blue; -fx-underline: false; -fx-cursor: hand;"));
        eventTile.setOnMouseExited(event -> eventTile.setStyle("-fx-font-size: 14px; -fx-fill: blue; -fx-underline: false;"));
        eventLogContainer.getChildren().add(0, eventTile);
    }

    public void clickableEvent(Text text) {
        text.setOnMouseClicked(event -> {

        });
    }
    public VBox getEventLogText() {
        return eventLogContainer;
        //Text text = eventLog.getText();
    }
}
