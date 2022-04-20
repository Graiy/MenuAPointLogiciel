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

    protected void addScreen(String name, FXMLLoader fxmlLoader, JsonReader jr, UiAffiche ui, String typeScreen) throws IOException {
        switch(typeScreen){
            case "home" :
                fxmlLoader.setControllerFactory(c-> new MenuAPointController(jr, ui));
                ScrollPane pane = fxmlLoader.load();
                screenMap.put(name, pane);
                break;
            case "data" :
                fxmlLoader.setControllerFactory(c-> new DataController(jr, ui, name));
                ScrollPane paneData = fxmlLoader.load();
                screenMap.put(name, paneData);
                break;
        }
    }

    protected void addScreen(String nameJoueur, String nameLvl, FXMLLoader fxmlLoader, JsonReader jr, UiAffiche ui, String typeScreen) throws IOException {
        switch(typeScreen){
            case "home" :
                fxmlLoader.setControllerFactory(c-> new MenuAPointController(jr, ui));
                ScrollPane pane = fxmlLoader.load();
                screenMap.put(nameJoueur, pane);
                break;
            case "data" :
                fxmlLoader.setControllerFactory(c-> new DataController(jr, ui, nameJoueur));
                ScrollPane paneData = fxmlLoader.load();
                screenMap.put(nameJoueur, paneData);
                break;
            case "bonus" :
                fxmlLoader.setControllerFactory(c-> new BonusController(jr, ui, nameLvl, nameJoueur));
                ScrollPane paneBonus = fxmlLoader.load();
                screenMap.put(nameLvl, paneBonus);
                break;
        }
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
