package com.example.oop_todo.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.time.LocalDate;

public class Add_Check_Update_Delete_TaskController extends Controller{
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOK;

    @FXML
    private Button btnUpdate;

    @FXML
    private CheckBox chkPriority;

    @FXML
    private DatePicker dpDueDate;

    @FXML
    private Label lblAppTitle;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblDueDate;

    @FXML
    private Label lblTaskTitle;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtTitle;

    @FXML
    private Button btnCloseOnForm;

    @FXML
    private AnchorPane parent;

    @FXML
    private VBox vboxFormAppBar;

    // set the color scheme of light and dark theme
    private final LightModeColorScheme _LightMode = new LightModeColorScheme();
    private final DarkModeColorScheme _DarkMode = new DarkModeColorScheme();

    // Method handle the initial properties once the fxml being load
    public void initialize(){ // called the function once addNewTask.fxml have been loaded
        InheritMainPageTheme();
        super.AppBarButtonEffect(btnCloseOnForm);
    }

    // Method to inherit the current Color Theme from main_page.fxml
    public void InheritMainPageTheme(){
        if (isLightMode){
            setLightTheme();
        }else{
            setDarkTheme();
        }
    }

    // Method to handle switch of form to Light Theme
    private void setLightTheme(){
        vboxFormAppBar.setStyle(
            "-fx-background-radius: 12 12 0 0;"+
            "-fx-border-radius: 12 12 0 0;" +
           "-fx-background-color: " + _LightMode.getAppBarColor()
        );

        parent.setStyle(
                "-fx-background-color: " + _LightMode.getAnchorPaneColor() + ";" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;"
        );

        txtTitle.setStyle(
                "-fx-background-color: " + _LightMode.getFieldColor() + ";" +
                "-fx-text-fill: " + _LightMode.getSecondaryFontColor() + ";"
        );

        txtDescription.setStyle(
                "-fx-control-inner-background: " + _LightMode.getFieldColor() + ";" +
                "-fx-text-fill: " + _LightMode.getSecondaryFontColor() + ";" +
                "-fx-border-color: " + _LightMode.getAnchorPaneColor()
        );

        btnCancel.setStyle("-fx-background-color: " + _LightMode.getFieldColor());
        btnCancel.setTextFill(Color.web(_LightMode.getPrimaryFontColor()));

        lblTaskTitle.setTextFill(Color.web(_LightMode.getPrimaryFontColor()));
        lblDescription.setTextFill(Color.web(_LightMode.getPrimaryFontColor()));
        lblDueDate.setTextFill(Color.web(_LightMode.getPrimaryFontColor()));

        chkPriority.setTextFill(Color.web(_LightMode.getPrimaryFontColor()));
        parent.getStyleClass().clear();
        parent.getStyleClass().add("chk-light-mode");

        dpDueDate.setStyle("-fx-control-inner-background: "+ _LightMode.getFieldColor());
        parent.getStyleClass().clear();
        parent.getStyleClass().add("dp-light-mode");
        Region arrowButton = (Region) dpDueDate.lookup(".arrow-button");
        // set the color of calendar icon background
        if (arrowButton != null) {
            arrowButton.setStyle("-fx-background-color: " + _LightMode.getProgressBar_TrackColor());

            // Apply styles to the DatePicker's calendar icon
            Region arrow = (Region) arrowButton.lookup(".arrow");
            if (arrow != null) {
                arrow.setStyle( "-fx-background-color: #837C7C;");
            }
        }
    }

    // Method to handle switch of form to Dark Theme
    private void setDarkTheme(){
        vboxFormAppBar.setStyle(
                "-fx-background-radius: 12 12 0 0;" +
                "-fx-border-radius: 12 12 0 0;" +
                "-fx-background-color: " + _DarkMode.getAppBarColor()
        );

        parent.setStyle(
                "-fx-background-color: " + _DarkMode.getAnchorPaneColor() + ";" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;"
        );

        txtTitle.setStyle(
                "-fx-background-color: " + _DarkMode.getFieldColor() + ";" +
                "-fx-text-fill: " + _DarkMode.getSecondaryFontColor()
        );

        txtDescription.setStyle(
                "-fx-control-inner-background: " + _DarkMode.getFieldColor() + ";" +
                "-fx-text-fill: " + _DarkMode.getSecondaryFontColor() + ";" +
                "-fx-background-color: " + _DarkMode.getAnchorPaneColor() +";"
        );

        btnCancel.setStyle("-fx-background-color: " + _DarkMode.getFieldColor());
        btnCancel.setTextFill(Color.web(_DarkMode.getPrimaryFontColor()));

        lblTaskTitle.setTextFill(Color.web(_DarkMode.getPrimaryFontColor()));
        lblDescription.setTextFill(Color.web(_DarkMode.getPrimaryFontColor()));
        lblDueDate.setTextFill(Color.web(_DarkMode.getPrimaryFontColor()));

        chkPriority.setTextFill(Color.web(_DarkMode.getPrimaryFontColor()));
        parent.getStyleClass().clear();
        parent.getStyleClass().add("chk-dark-mode");

        dpDueDate.setStyle("-fx-control-inner-background: "+ _DarkMode.getFieldColor());
        parent.getStyleClass().clear();
        parent.getStyleClass().add("dp-dark-mode");
        Region arrowButton = (Region) dpDueDate.lookup(".arrow-button");
        // set the color of calendar icon background
        if (arrowButton != null) {
            arrowButton.setStyle("-fx-background-color: " + _DarkMode.getProgressBar_TrackColor());

            // Apply styles to the DatePicker's calendar icon
            Region arrow = (Region) arrowButton.lookup(".arrow");
            if (arrow != null) {
                arrow.setStyle( "-fx-background-color: #FFFFFF;");
            }
        }
    }

    // Method to handle the close of form
    @FXML
    private void onCloseButtonClick(ActionEvent event) {

        // Get the stage associated with the button
        Stage stage = (Stage) btnCloseOnForm.getScene().getWindow();
        // Close the stage
        stage.close();
    }


    private TaskList taskList;
    // Method to set TaskList, call this from the main application controller
    public void setTaskList(String filename) {
        this.taskList = new TaskList(filename);
    }
    // This method is called when the "Add New" button is clicked
    @FXML
    private void handleAddTaskAction(ActionEvent event) {
        try{
            setTaskList("C:\\Users\\ammar\\IdeaProjects\\ProjectOOP\\target\\classes\\com\\example\\oop_todo\\text_file\\tasks.txt");
        } catch(Exception e){
            System.out.println("ERROR HERE");
            System.out.println("SHARVIN");
        }

        System.out.println("at least it works");
        // Get the task details from the form
        String title = txtTitle.getText();
        String description = txtDescription.getText();
        LocalDate dueDate = dpDueDate.getValue();
        boolean isPriority = chkPriority.isSelected();

        if (this.taskList == null) {
            // TaskList has not been initialized, handle this case appropriately
            System.err.println("TaskList is not initialized.");
            return;
        }
        else {
            // Add the new task to the task list which will create a new Task object with a unique ID
            taskList.addTask(title, description, dueDate, isPriority);

            // Optionally, you can clear the form fields after adding the task
            txtTitle.clear();
            txtDescription.clear();
            dpDueDate.setValue(null); // or set to a default value
            chkPriority.setSelected(false);

            // Close the dialog or update the view as needed
            // ...
        }
    }



}

