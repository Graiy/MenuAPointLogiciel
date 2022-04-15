package com.example.demo;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class MenuAPointController {
    @FXML
    private ListView<Button> listeBoutons;
    private ObservableList<Button> buttons = FXCollections.observableArrayList();
    private JsonReader jr;
    private UiAffiche ui;

    public MenuAPointController(JsonReader jr, UiAffiche ui){
        this.jr = jr;
        this.ui = ui;
    }

    @FXML
    public void initialize(){
        listeBoutons.setItems(buttons);

        for(int i=0; i<this.jr.getListeJoueur().size(); i++){
            addButton(this.jr.getListeJoueur().get(i));
        }
    }

    @FXML
    public void addButton(String j){
        Button b = new Button(j);
        buttons.add(b);
        b.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> click(b.getText())));
    }

    public void click(String name){
        this.ui.switchSceneData(name);
    }
}
