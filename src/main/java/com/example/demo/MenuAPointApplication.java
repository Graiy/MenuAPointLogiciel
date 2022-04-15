package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuAPointApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        JsonReader jr = new JsonReader();
        UiAffiche ui = new UiAffiche(stage, jr);
    }

    public static void main(String[] args) {
        launch();
    }
}
