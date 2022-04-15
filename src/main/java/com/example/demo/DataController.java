package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class DataController {

    @FXML
    private Label playerName;

    private JsonReader jr;
    private UiAffiche ui;

    public DataController(JsonReader jr, UiAffiche ui){
        this.jr = jr;
        this.ui = ui;
    }

    @FXML
    public void initialize(){

    }

    public void comeBack(ActionEvent event){
        this.ui.switchSceneHome();
    }
}
