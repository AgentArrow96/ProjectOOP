package com.example.oop_todo.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Arrays;
import java.util.List;

public class Controller {
    //Create objects
    @FXML
    private Label lblTitle;

    @FXML
    private Circle btnClose;

    @FXML
    private Circle btnMax;

    @FXML
    private Circle btnMin;

    @FXML
    private Button btnToday;

    @FXML
    private Button btnImportant;

    @FXML
    private Button btnPrevious;

    @FXML
    private Button btnSomeday;

    @FXML
    private Button btnTrash;

    @FXML
    private Button btnAddTask;

    public void initialize() {
        ButtonEffect(btnToday);
        ButtonEffect(btnImportant);
        ButtonEffect(btnPrevious);
        ButtonEffect(btnSomeday);
        ButtonEffect(btnTrash);
        CircleEffect(btnClose);
        CircleEffect(btnMax);
        CircleEffect(btnMin);
    }
    private void CircleEffect(Circle circle) {
        circle.setOnMouseEntered(event -> {
            circle.setStroke(Color.WHITE);
        });
        circle.setOnMouseExited(event -> {
            btnClose.setStroke(Color.rgb(255, 96, 92));
            btnMax.setStroke(Color.rgb(255, 189, 68));
            btnMin.setStroke(Color.rgb(0, 202, 78));
        });
    }
    private void ButtonEffect(Button button) { //when mouse enter and exit --> color changed
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-color: #d1cfd1;");
        });
        button.setOnMouseExited(event -> {
            button.setStyle("-fx-background-color: #c0bfc0;");
        });

        button.setOnMouseClicked(event -> {
            //Reset all the button
            ResetButton(btnToday);
            ResetButton(btnImportant);
            ResetButton(btnPrevious);
            ResetButton(btnSomeday);
            ResetButton(btnTrash);

            //make the text indent --> when button clicked
            button.setGraphicTextGap(25);

            //change font size and colour
            button.setFont(Font.font("Inter Semi Bold", FontWeight.BOLD, 22));
            button.setTextFill(Color.rgb(81, 81, 81));

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
            }
        });
    }
    private void ResetButton(Button button) {
        //All button in one list
        List<Button> buttons = Arrays.asList(btnToday, btnImportant, btnPrevious, btnSomeday, btnTrash);

        for (Button multipleButton : buttons) {
            //make the text not indent
            button.setGraphicTextGap(4);

            //reset the font size and colour
            button.setFont(Font.font("System", FontWeight.BOLD, 20));
            button.setTextFill(Color.rgb(123, 123, 123));

        }
    }
}