package gu.dit213.group28.view;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;

/** A class that creates a pop-up dialog window when an event occurs. */
public class EventPopUp {

  /** Constructor for the EventPopUp class. It initializes the EventPopUp. */
  public EventPopUp() {}

  /**
   * Method that creates the pop-up dialog window when an event occurs.
   *
   * @param eventMessage The message to be displayed in the dialog window.
   */
  public static void createEventDialog(String eventMessage) {
    Dialog<String> dialog = new Dialog<>();
    dialog.setTitle("Event notification");

    ButtonType closeButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
    dialog.getDialogPane().getButtonTypes().add(closeButton);

    Label messageLabel = new Label(eventMessage);
    dialog.getDialogPane().setContent(messageLabel);

    dialog.showAndWait();
  }
}
