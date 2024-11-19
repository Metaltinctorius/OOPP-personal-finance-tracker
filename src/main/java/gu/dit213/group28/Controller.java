package gu.dit213.group28;

import gu.dit213.group28.model.Icontrollable;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * A controller class that initializes the JavaFX stage and passes it on to the view. Currently,
 * this class both initializes the stage and the buttons, which creates a dependency between the
 * model and the view. This is fine and made it easier for me to set up a quick test but different
 * from what we agreed on. Alternatively, we might be able to separate the initialization of the
 * stage to happen somewhere else in a clean way.
 */
public class Controller {
  private final Stage stage;
  private final Icontrollable logic;
  private final View view;

  Controller(Stage stage, Icontrollable logic, View view) {
    this.stage = stage;
    this.logic = logic;
    this.view = view;

    initStage();
  }

  private void initStage() {
    BorderPane root = new BorderPane();
    root.setBottom(createButtonPanel());
    root.setCenter(createCenterGrid());

    Scene scene = new Scene(root, 640, 480);
    stage.setScene(scene);
    stage.setTitle("Finance Tracker");
    view.initView();
  }

  private HBox createButtonPanel() {
    HBox buttonPanel = new HBox();
    buttonPanel.setPadding(new Insets(15, 12, 15, 12));
    buttonPanel.setSpacing(10);
    buttonPanel.setStyle("-fx-background-color: #336699;");

    Button b1 = new Button("b1");
    b1.setPrefSize(100, 20);
    b1.setOnAction(event -> logic.beeOne());

    Button b2 = new Button("b2");
    b2.setPrefSize(100, 20);
    b2.setOnAction(event -> logic.beeTwo());

    Button edit = new Button("edit");
    edit.setPrefSize(100, 20);
    edit.setOnAction(
        event -> {
          // Using list in case we want to add more fields in the future
          Dialog<List<String>> dialog = new Dialog<>();
          TextInputDialog inputDialog = new TextInputDialog();
          inputDialog.setTitle("Edit");
          inputDialog.setHeaderText("Enter amount transaction type and date");

          ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
          dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

          // Input fields for the dialog box
          TextField amount = new TextField();
          amount.setPromptText("Amount");

          TextField income = new TextField();
          income.setPromptText("Income");

          TextField date = new TextField();
          date.setPromptText("Date");

          GridPane grid = new GridPane();
          grid.setHgap(10);
          grid.setVgap(10);
          grid.add(new Label("Amount"), 0, 0);
          grid.add(amount, 1, 0);
          grid.add(new Label("Income:"), 0, 1);
          grid.add(income, 1, 1);
          grid.add(new Label("Date:"), 0, 2);
          grid.add(date, 1, 2);

          dialog.getDialogPane().setContent(grid);

          dialog.setResultConverter(
              dialogButton -> {
                if (dialogButton == okButtonType) {
                  // Dialog box consists of two fields, income and expense, return them as a list
                  // Might have to change this depending on how we want to handle the data
                  return List.of(amount.getText(), income.getText(), date.getText());
                }
                return null;
              });

          // Depending on how we want to handle the data, this part probably needs to be changed.
          dialog
              .showAndWait()
              .ifPresent(
                  inputList -> {
                    System.out.println("Amount: " + inputList.get(0));
                    System.out.println("TransactionType: " + inputList.get(1));
                    System.out.println("Date:" + inputList.get(2));
                  });
        });

    buttonPanel.getChildren().addAll(b1, b2, edit);
    return buttonPanel;
  }

  private GridPane createCenterGrid() {
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(0, 30, 0, 30));

    return grid;
  }
}
