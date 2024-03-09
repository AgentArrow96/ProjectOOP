package com.example.oop_todo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;
import java.util.*;

public class Main extends Application {
    double x, y = 0;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/oop_todo/fxml/main_page.fxml"))); 
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);

        // initializing the title and icon
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/oop_todo/icons/ToDoy(puppy).png")));
        stage.getIcons().add(icon);
        stage.setTitle("ToDoy");

        // move around
        root.setOnMousePressed(evt ->{
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        root.setOnMouseDragged(evt ->{
            stage.setX(evt.getScreenX()- x);
            stage.setY(evt.getScreenY()- y);
        });

        stage.setScene(scene);
        stage.show();
    }

    //Code continue here...
    public static void main(String[] args) {
        launch();
    }
}