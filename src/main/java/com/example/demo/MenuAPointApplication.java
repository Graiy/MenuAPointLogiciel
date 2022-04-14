package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuAPointApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //JsonReader jr = new JsonReader();
        //jr.readJson();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu-a-point-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("Menu A Point Data Reader");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
