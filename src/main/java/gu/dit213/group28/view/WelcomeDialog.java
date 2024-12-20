package gu.dit213.group28.view;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/** Class for creating a welcome dialog. */
public class WelcomeDialog {

  /** Constructor for the WelcomeDialog. */
  public WelcomeDialog() {}

  /** Creates a welcome dialog that is shown when the game is started. */
  public void createWelcomeDialog() {

    Dialog<String> dialog = new Dialog<>();
    dialog.setTitle("Stock Inc");

    ButtonType closeButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
    dialog.getDialogPane().getButtonTypes().add(closeButton);

    dialog.setContentText(
        """
            Welcome to Stock Inc!

            Stock Inc is a stock market simulation game where you can invest in different sectors.

            The goal is to beat index by buying and selling stocks.

            You can choose game speed either by using the buttons or pressing key 1, 2 or 3.

            You can pause and resume the game by pressing the button or the s key.

            Good luck and have fun!""");

    dialog.showAndWait();
  }
}
