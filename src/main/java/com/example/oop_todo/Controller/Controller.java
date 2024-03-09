package com.example.oop_todo.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Controller {
    //Create objects
    @FXML
    protected ProgressBar progressBar;

    @FXML
    protected Label lblProgress;

    @FXML
    protected VBox vboxAppBar;

    @FXML
    protected VBox vboxSide;

    @FXML
    protected AnchorPane parent;

    @FXML
    protected Label lblTitle;

    @FXML
    protected Button btnClose;

    @FXML
    protected Button btnMax;

    @FXML
    protected Button btnMin;

    @FXML
    protected Button btnToday;

    @FXML
    protected Button btnImportant;

    @FXML
    protected Button btnPrevious;

    @FXML
    protected Button btnSomeday;

    @FXML
    protected Button btnTrash;

    @FXML
    protected Button btnAddTask;

    @FXML
    protected Button btnClear;

    @FXML
    protected Button btnMode;

    @FXML
    protected ImageView tasklist;

    @FXML
    protected ImageView myImageView;

    // set the color scheme of light and dark theme
    protected final LightModeColorScheme _LightMode = new LightModeColorScheme();
    protected final DarkModeColorScheme _DarkMode = new DarkModeColorScheme();

    // Set the default to Light Theme
    protected static boolean isLightMode = true;

    private SwitchTheme st;

    // set the initial coordinate (x,y) of form
    private double x,y = 0;

    // Invoke the method once main_page.fxml have been loaded in Main.java
    public void initialize() {
        ButtonEffect(btnToday);
        ButtonEffect(btnImportant);
        ButtonEffect(btnPrevious);
        ButtonEffect(btnSomeday);
        ButtonEffect(btnTrash);
        AppBarButtonEffect(btnClose);
        AppBarButtonEffect(btnMin);
        AppBarButtonEffect(btnMax);
        AddNewTaskButton_Effect(btnAddTask);
        vboxTaskContainer.setPadding(new javafx.geometry.Insets(20));

        // put all the control that affected by the switchTheme method
        st = new SwitchTheme(lblTitle, btnToday, btnImportant, btnPrevious, btnSomeday, btnTrash, btnClear,
                btnMode, btnAddTask, parent, vboxSide, vboxAppBar, myImageView, progressBar, tasklist);
    }

    // Method to handle hover effect of App Bar's circles button
    protected void AppBarButtonEffect(Button button) {
        button.setOnMouseEntered(event -> {
            if (isLightMode)
                button.setStyle("-fx-border-width: 2; -fx-border-color: " +  _LightMode.getTertiaryFontColor() + ";");
            else
                button.setStyle("-fx-border-width: 2; -fx-border-color: " +  _DarkMode.getTertiaryFontColor() + ";");
        });
        button.setOnMouseExited(event -> {
            button.setStyle("-fx-border-width: 1; -fx-border-color: transparent;");
        });
    }

    // Method to switch the color theme on main_page.fxml
    @FXML
    private void onClickSwitchMode(ActionEvent event){
        isLightMode = !isLightMode;
        // Selection statement for switching theme
        if (isLightMode){ // light mode theme
            st.setLightTheme();

        } else { // dark mode theme
            st.setDarkTheme();
        }
    }

    // Method to handle hover effect for btnAddTask
    private void AddNewTaskButton_Effect(Button button){
        button.setOnMouseEntered(event -> { // Hover
            if (isLightMode)
                button.setStyle("-fx-background-color: " + _LightMode.getButtonHoverColor());
            else
                button.setStyle("-fx-background-color: " + _DarkMode.getButtonHoverColor());
        });
        button.setOnMouseExited(event -> {
            if (isLightMode)
                button.setStyle("-fx-background-color: " + _LightMode.getButtonColor());
            else // dark mode
                button.setStyle("-fx-background-color: " + _DarkMode.getButtonColor());
        });
    }

    private static int counter = 0;

    @FXML
    private VBox vboxTaskContainer;

    @FXML
    private ScrollPane scrTask;

    // Method to display the addNewTask.fxml form
    @FXML
    private void onClickAddNewTask(ActionEvent event) throws IOException {
        // load the addNewTask.fxml form
        /*
        Parent form = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/oop_todo/fxml/addNewTask.fxml")));
        Stage stage = new Stage();
        Scene scene = new Scene(form);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);

        //move around
        form.setOnMousePressed(evt ->{
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        form.setOnMouseDragged(evt ->{
            stage.setX(evt.getScreenX()- x);
            stage.setY(evt.getScreenY()- y);
        });

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.show();
        */
        try {
            // Load the custom control from custom.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/oop_todo/fxml/task_bar.fxml"));

            // set the root control (Pane) of Custom Control to be the loaded UI
            Pane customControl = loader.load();

            counter++;
            // Access the CustomController instance
            TaskBarController customController = loader.getController();

            // Access lblTitle using the getter
            customController.setlblTitle(counter);

            // Set spacing for the custom control before added into the vbox
            vboxTaskContainer.setSpacing(10);

            // Add the custom control to the VBox
            vboxTaskContainer.getChildren().add(customControl);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an error message)
        }
    }

    // Method to handle button hover effects and change the style
    private void ButtonEffect(Button button) { //when mouse enter and exit --> color changed
        button.setOnMouseEntered(event -> { //Hover
            if (isLightMode)
                button.setStyle("-fx-background-color: " + _LightMode.getButtonHoverColor());
            else
                button.setStyle("-fx-background-color: " + _DarkMode.getButtonHoverColor());
        });
        button.setOnMouseExited(event -> {
            if (isLightMode)
                button.setStyle("-fx-background-color: " + _LightMode.getButtonColor());
            else // dark mode
                button.setStyle("-fx-background-color: " + _DarkMode.getButtonColor());
        });

        button.setOnMouseClicked(event -> {
            // Reset all the button properties
            ResetButton(btnToday);
            ResetButton(btnImportant);
            ResetButton(btnPrevious);
            ResetButton(btnSomeday);
            ResetButton(btnTrash);

            //make the text indent --> when button clicked
            button.setGraphicTextGap(25);

            // change font size, color of focused button
            button.setFont(Font.font("System", FontWeight.BOLD, 22));

            // check the current color theme
            if (isLightMode)
                button.setTextFill(Color.web(_LightMode.getSecondaryFontColor()));
            else // dark mode
                button.setTextFill(Color.web(_DarkMode.getSecondaryFontColor()));

            // default state
            progressBar.setVisible(true);
            lblProgress.setVisible(true);
            btnClear.setVisible(false);

            if (button == btnToday){
                lblTitle.setText("Today");
                lblTitle.setFont(Font.font("System", FontWeight.BOLD, 40));
            }
            else if (button == btnImportant) {
                lblTitle.setText("Important");
                lblTitle.setFont(Font.font("System", FontWeight.BOLD, 40));
            }
            else if (button == btnPrevious) {
                lblTitle.setText("Previous");
                lblTitle.setFont(Font.font("System", FontWeight.BOLD, 40));
            }
            else if (button == btnSomeday) {
                lblTitle.setText("Someday");
                lblTitle.setFont(Font.font("System", FontWeight.BOLD, 40));
            }
            else if (button == btnTrash) {
                lblTitle.setText("Trash");
                lblTitle.setFont(Font.font("System", FontWeight.BOLD, 40));

                // hide the progress bar and show the clear button
                progressBar.setVisible(false);
                lblProgress.setVisible(false);
                btnClear.setVisible(true);
            }
        });
    }

    // Method to reset the style properties for all side menu buttons
    private void ResetButton(Button button) {
        //All button in one list
        List<Button> buttons = Arrays.asList(btnToday, btnImportant, btnPrevious, btnSomeday, btnTrash);

        for (Button multipleButton : buttons) {
            //make the text not indent
            button.setGraphicTextGap(4);

            //reset the font size and colour
            button.setFont(Font.font("System", FontWeight.BOLD, 20));
            button.setTextFill(Color.web(_LightMode.getTertiaryFontColor()));
        }
    }

    // Method to handle the termination of the program
    @FXML
    private void onClickTerminateProgram(ActionEvent event) {
        Platform.exit(); // terminate the whole program
    }
}