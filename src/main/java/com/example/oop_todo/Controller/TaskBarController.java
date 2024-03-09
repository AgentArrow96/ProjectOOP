package com.example.oop_todo.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TaskBarController {
    @FXML
    private Label lblTitle;
    public void setlblTitle(int count){
        lblTitle.setText("Task " + count);
    }
}
