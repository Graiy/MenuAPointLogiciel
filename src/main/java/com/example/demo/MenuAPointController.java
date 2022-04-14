package com.example.demo;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class MenuAPointController {
    @FXML
    private Button add;
    @FXML
    private ListView<Button> listeBoutons;
    private ObservableList<Button> buttons = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        JsonReader jr = new JsonReader();
        jr.readJson();
        listeBoutons.setItems(buttons);

        for(int i=0; i<jr.getListeJoueur().size(); i++){
            addButton(jr.getListeJoueur().get(i));
        }
    }

    @FXML
    public void addButton(String j){
        Button b = new Button(j);
        buttons.add(b);
        b.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> click()));
    }

    public void click(){

    }
}
