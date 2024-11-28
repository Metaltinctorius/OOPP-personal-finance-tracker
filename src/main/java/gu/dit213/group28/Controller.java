package gu.dit213.group28;

import gu.dit213.group28.model.Icontrollable;
import gu.dit213.group28.model.Logic;
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
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
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
  private final Icontrollable logic;
  private boolean isPaused = false;

  Controller(Stage stage, Icontrollable transactionHandler, Icontrollable logic, View view) {
    this.stage = stage;
    this.transactionHandler = transactionHandler;
    this.logic = logic;
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

    Button makeTransactionButton = new Button("Make Transaction");
    makeTransactionButton.setPrefSize(150, 20);
    makeTransactionButton.setOnAction(event -> createTransactionDialog());

    Button editTransactionButton = new Button("Edit Transaction");
    editTransactionButton.setPrefSize(150, 20);
    // do some setOnAction stuff here, remove/change transaction logic

    Button pauseResumeButton = new Button("Pause");
    pauseResumeButton.setPrefSize(100, 20);
    pauseResumeButton.setOnAction(event -> togglePauseResume(pauseResumeButton));

    Button info = new Button("info");
    info.setPrefSize(100, 20);
    // do some setOnAction stuff here

    // Game speed multiplier
    Slider gameSpeedSlider = new Slider(1, 3, 2);
    gameSpeedSlider.setShowTickMarks(true);
    gameSpeedSlider.setMajorTickUnit(1);
    gameSpeedSlider.setBlockIncrement(1);
    gameSpeedSlider.setMinorTickCount(0);

    // Showing game speed interactively in a label next to the slider
    Label gameSpeedLabel = new Label("Game Speed: Normal");
    gameSpeedLabel.setTextFill(Color.WHITE);

    gameSpeedSlider
        .valueProperty()
        .addListener(
            (obs, oldVal, newVal) -> {
              int snappedValue = newVal.intValue();
              gameSpeedSlider.setValue(snappedValue);
              alterGameSpeed(snappedValue);
              if (snappedValue == 1) {
                gameSpeedLabel.setText("Game Speed: Slow");
              } else if (snappedValue == 2) {
                gameSpeedLabel.setText("Game Speed: Normal");
              } else {
                gameSpeedLabel.setText("Game Speed: Fast");
              }
            });

    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    buttonPanel
        .getChildren()
        .addAll(
            makeTransactionButton,
            editTransactionButton,
            pauseResumeButton,
            gameSpeedSlider,
            gameSpeedLabel,
            spacer,
            info);
    return buttonPanel;
  }

  private void alterGameSpeed(int snappedValue) {
    // Add some action here that signals to model to update game speed.
    switch (snappedValue) {
      case 1:
        logic.setSpeedSlow();
        break;
      case 2:
        logic.setSpeedNormal();
        break;
      case 3:
        logic.setSpeedFast();
        break;
      default:
        logic.setSpeedNormal();
        break;
    }
  }

  // Button state depending on model state
  private void togglePauseResume(Button pauseResumeButton) {
    isPaused = !isPaused;
    if (isPaused) {
      pauseResumeButton.setText("Resume");
      // Add some action here that signals to model to pause
    } else {
      pauseResumeButton.setText("Pause");
      // Add some action here that signals to model to resume
    }
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
