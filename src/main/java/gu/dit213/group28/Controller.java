package gu.dit213.group28;

import gu.dit213.group28.model.interfaces.Icontrollable;
import java.time.LocalDate;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * A controller class that initializes the JavaFX stage and passes it on to the view. Currently,
 * this class both initializes the stage and the buttons, which creates a dependency between the
 * model and the view. Alternatively, we might be able to separate the initialization of the stage
 * to happen somewhere else in a clean way.
 */
public class Controller {
  private final Stage stage;
  private final Icontrollable transactionHandler;
  private final View view;

  Controller(Stage stage, Icontrollable transactionHandler, View view) {
    this.stage = stage;
    this.transactionHandler = transactionHandler;
    this.view = view;

    initStage();
  }

  private void initStage() {
    BorderPane root = new BorderPane();
    root.setBottom(createLowerButtonPanel());
    root.setCenter(createCenterGrid());
    root.setTop(createUpperButtonPanel());

    Scene scene = new Scene(root, 640, 480);
    stage.setScene(scene);
    stage.setTitle("Finance Tracker");
    view.initView();
  }

  private HBox createLowerButtonPanel() {
    HBox buttonPanel = new HBox();
    buttonPanel.setPadding(new Insets(15, 12, 15, 12));
    buttonPanel.setSpacing(10);
    buttonPanel.setStyle("-fx-background-color: #336699;");

    /*
        Button b1 = new Button("b1");
        b1.setPrefSize(100, 20);
        b1.setOnAction(event -> logic.beeOne());

        Button b2 = new Button("b2");
        b2.setPrefSize(100, 20);
        b2.setOnAction(event -> logic.beeTwo());
    */
    Button b3 = new Button("Make Transaction");
    b3.setPrefSize(150, 20);
    b3.setOnAction(event -> createTransactionDialog());

    Button b4 = new Button("Edit Transaction");
    b4.setPrefSize(150, 20);
    // do some setOnAction stuff here, remove/change transaction logic

    Button b5 = new Button("info");
    b4.setPrefSize(100, 20);
    // do some setOnAction stuff here

    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    buttonPanel.getChildren().addAll(b3, b4, spacer, b5);
    return buttonPanel;
  }

  private HBox createUpperButtonPanel() {
    HBox buttonPanel = new HBox();
    buttonPanel.setPadding(new Insets(15, 12, 15, 12));
    buttonPanel.setSpacing(10);
    buttonPanel.setStyle("-fx-background-color: #336699;");

    Button modeB1 = new Button("Mode Button 1");
    modeB1.setPrefSize(150, 20);
    // do some setOnAction stuff here

    Button modeB2 = new Button("Mode Button 2");
    modeB2.setPrefSize(150, 20);
    // do some setOnAction stuff here

    Button modeB3 = new Button("Mode Button 3");
    modeB3.setPrefSize(150, 20);
    // do some setOnAction stuff here

    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    buttonPanel.getChildren().addAll(spacer, modeB1, modeB2, modeB3);

    return buttonPanel;
  }

  // Creates the whole popup dialog box for creating a transaction
  // TODO: Decide on how to handle exceptions and where to do it
  private void createTransactionDialog() {
    Dialog<List<String>> dialog = new Dialog<>();
    dialog.setHeaderText("Enter amount, transaction type and date");

    ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

    // Different box types for the fields in the dialog box
    TextField amount = new TextField();
    amount.setPromptText("Amount");

    ComboBox<String> transactionType = new ComboBox<>();
    transactionType.getItems().addAll("Income", "Expense");
    transactionType.setPromptText("Transaction Type");

    DatePicker date = new DatePicker();
    date.setValue(LocalDate.now());
    date.setPromptText("Select Date");

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.add(new Label("Amount"), 0, 0);
    grid.add(amount, 1, 0);
    grid.add(new Label("Type"), 0, 1);
    grid.add(transactionType, 1, 1);
    grid.add(new Label("Date:"), 0, 2);
    grid.add(date, 1, 2);

    dialog.getDialogPane().setContent(grid);

    dialog.setResultConverter(
        dialogButton -> {
          if (dialogButton == okButtonType) {
            // Dialog box consists of three fields, return input from user as a list of strings
            return List.of(
                amount.getText(), transactionType.getValue(), date.getValue().toString());
          }
          return null;
        });

    // Send to the model (TransactionHandler) to insert the transaction
    dialog
        .showAndWait()
        .ifPresent(
            inputList -> {
              transactionHandler.insertTransaction(inputList);
              System.out.println("Amount: " + inputList.get(0));
              System.out.println("TransactionType: " + inputList.get(1));
              System.out.println("Date:" + inputList.get(2));
            });
  }

  private GridPane createCenterGrid() {
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(0, 30, 0, 30));

    return grid;
  }
}
