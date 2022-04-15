package com.example.demo;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;

import java.util.HashMap;

public class ScreenController {

    private HashMap<String, ScrollPane> screenMap = new HashMap<String, ScrollPane>();
    private Scene main;

    public ScreenController(Scene main){
        this.main = main;
    }

    protected void addScreen(String name, ScrollPane pane){
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
