package com.example.demo;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MenuAPointApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Json File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Json", "*.json")); //Oblige à ne voir que les .json
        File selectedFile = fileChooser.showOpenDialog(stage);
        String path = selectedFile.getAbsolutePath();
        JsonReader jr = new JsonReader(path);
        UiAffiche ui = new UiAffiche(stage, jr);
    }

    public static void main(String[] args) {
        launch();
    }
}
