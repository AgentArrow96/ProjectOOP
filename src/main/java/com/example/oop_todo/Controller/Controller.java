package com.example.oop_todo.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    protected ImageView myImageView;

    @FXML
    protected VBox vboxTaskContainer;

    @FXML
    protected ScrollPane sclTaskList;

    @FXML
    private ImageView imgClose;

    @FXML
    private ImageView imgMax;

    @FXML
    private ImageView imgMin;

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
        // initialize the effect method for all the button in the program
        SideBarButton_Effect(btnToday);
        SideBarButton_Effect(btnImportant);
        SideBarButton_Effect(btnPrevious);
        SideBarButton_Effect(btnSomeday);
        SideBarButton_Effect(btnTrash);
        AppBarButton_Effect(btnClose);
        AppBarButton_Effect(btnMin);
        AppBarButton_Effect(btnMax);
        AddNewTaskButton_Effect(btnAddTask);
        SwitchModeButton_Effect(btnMode);
        ClearButton_Effect(btnClear);

        // put all the control that affected by the switchTheme method
        st = new SwitchTheme(lblTitle, btnToday, btnImportant, btnPrevious, btnSomeday, btnTrash, btnClear,
                btnMode, btnAddTask, parent, vboxSide, vboxAppBar, myImageView, progressBar,
                vboxTaskContainer, sclTaskList);
    }

    // Method to handle hover effect of App Bar's circles button
    protected void AppBarButton_Effect(Button button) {
        button.setOnMouseEntered(event -> {
            if (button == btnClose) {
                imgClose.setVisible(true);
            } else if (button == btnMin) {
                imgMin.setVisible(true);
            } else {
                imgMax.setVisible(true);
            }
        });
        button.setOnMouseExited(event -> {
            if (button == btnClose) {
                imgClose.setVisible(false);
            } else if (button == btnMin) {
                imgMin.setVisible(false);
            } else {
                imgMax.setVisible(false);
            }
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
                button.setStyle("-fx-background-color: " + _LightMode.getButtonHoverColor() + ";");
            else
                button.setStyle("-fx-background-color: " + _DarkMode.getButtonHoverColor() + ";");
        });
        button.setOnMouseExited(event -> {
            if (isLightMode)
                button.setStyle("-fx-background-color: " + _LightMode.getButtonColor() + ";");
            else // dark mode
                button.setStyle("-fx-background-color: " + _DarkMode.getButtonColor() + ";");
        });
    }

    private void SwitchModeButton_Effect(Button button){
        button.setOnMouseEntered(event -> { // Hover
            try {
                if (isLightMode){ // light mode - black background, white icon
                    button.setStyle("-fx-background-color: " + _DarkMode.getAnchorPaneColor() + ";");
                    setImageForButton(button, "/com/example/oop_todo/icons/light_mode/dark(in_dark).png", 30);
                }
                if(!isLightMode){ // dark mode - white background, black icon
                    button.setStyle("-fx-background-color: " + _LightMode.getAnchorPaneColor() + ";");
                    setImageForButton(button, "/com/example/oop_todo/icons/dark_mode/light(in_light).png", 30);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        });

        button.setOnMouseExited(event -> {
            try{
                if (isLightMode){ // light mode - white background, black icon
                    button.setStyle("-fx-background-color: " + _LightMode.getAnchorPaneColor() + ";");
                    setImageForButton(button, "/com/example/oop_todo/icons/light_mode/dark(in_light).png",30);
                }
                if(!isLightMode){ // dark mode - black background, white icon
                    button.setStyle("-fx-background-color: " + _DarkMode.getAnchorPaneColor() + ";");
                    setImageForButton(button, "/com/example/oop_todo/icons/dark_mode/light(in_dark).png", 30);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    private void ClearButton_Effect(Button button){
        button.setOnMouseEntered(event -> { // Hover
            button.setStyle("-fx-background-color: " + _LightMode.getIconColor() + ";");
            button.setTextFill(Color.web("#DBDBDB"));
            setImageForButton(button, "/com/example/oop_todo/icons/clear(hover).png", 40);
        });
        button.setOnMouseExited(event -> {
            setImageForButton(button, "/com/example/oop_todo/icons/clear.png", 40);

            if (isLightMode){
                button.setStyle("-fx-background-color: " + _LightMode.getButtonColor() + ";");
                button.setTextFill(Color.web(_LightMode.getSecondaryFontColor()));
            }
            else{ // dark mode
                button.setStyle("-fx-background-color: " + _DarkMode.getButtonColor() + ";");
                button.setTextFill(Color.web(_DarkMode.getSecondaryFontColor()));
            }
        });
    }

    private void setImageForButton(Button button, String imagePath, double size) {
        try {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(size); // Adjust the width as needed
            imageView.setFitHeight(size); // Adjust the height as needed
            button.setGraphic(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to display the addNewTask.fxml form
    @FXML
    private void onClickAddNewTask(ActionEvent event) throws IOException{
        // load the addNewTask.fxml form
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
    }

    // Method to handle button hover effects and change the style
    private void SideBarButton_Effect(Button button) { //when mouse enter and exit --> color changed
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
            button.setFont(Font.font("Inter Semi Bold", FontWeight.BOLD, 22));

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