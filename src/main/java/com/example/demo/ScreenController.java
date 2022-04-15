package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;

import java.io.IOException;
import java.util.HashMap;

public class ScreenController {

    private HashMap<String, ScrollPane> screenMap = new HashMap<String, ScrollPane>();
    private Scene main;

    public ScreenController(Scene main){
        this.main = main;
    }

    protected void addScreen(String name, FXMLLoader fxmlLoader, JsonReader jr, UiAffiche ui) throws IOException {
        if(name == "home"){
            fxmlLoader.setControllerFactory(c-> new MenuAPointController(jr, ui));
        }
        else{
            fxmlLoader.setControllerFactory(c-> new DataController(jr, ui, name));
        }

        ScrollPane pane = fxmlLoader.load();
        screenMap.put(name, pane);
    }

    protected void removeScreen(String name){
        screenMap.remove(name);
    }

    protected void activate(String name){
        main.setRoot(screenMap.get(name));
    }

    protected void getScreens(){
        System.out.println(screenMap.keySet());
    }
}
