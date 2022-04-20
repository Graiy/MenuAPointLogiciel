package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import static javafx.scene.paint.Color.LIME;
import static javafx.scene.paint.Color.RED;

public class DataController {

    @FXML
    private Text playerName;
    @FXML
    private Text nbIndice;
    @FXML
    private GridPane lvlFini;
    @FXML
    private GridPane lvlIndice;
    @FXML
    private GridPane nbTry;
    @FXML
    private GridPane krono;
    @FXML
    private ListView nivBonus;

    private JsonReader jr;
    private UiAffiche ui;
    private String name;
    private ObservableList<Button> buttons = FXCollections.observableArrayList();

    public DataController(JsonReader jr, UiAffiche ui, String name){
        this.jr = jr;
        this.ui = ui;
        this.name = name;
    }

    @FXML
    public void initialize(){
        playerName.setText(name);
        nbIndice.setText("Indices restants : "+jr.getIndiceRestant(name));

        //Créer les cercles pour les niveaux finis
        for(int i=0; i<jr.getLvlFinis(name).size(); i++){
            if(jr.getLvlFinis(name).get(i)) {
                lvlFini.add(new Circle(14, LIME), 0, i);
            } else {
                lvlFini.add(new Circle(14, RED), 0, i);
            }
        }

        //Créer les cercles pour noter les niveaux où ont été utilisé les indices
        for(int i=0; i<jr.getIndiceNiveau(name).size(); i++){
            if(jr.getIndiceNiveau(name).get(i)) {
                lvlIndice.add(new Circle(14, LIME), 0, i);
            } else {
                lvlIndice.add(new Circle(14, RED), 0, i);
            }
        }

        //Ecrit le nombre d'essais pris
        for(int i=0; i<jr.getEssais(name).size(); i++){
            nbTry.add(new Text(jr.getEssais(name).get(i).toString()), 0, i);
        }

        //Ecrit le temps pris par niveau
        for(int i=0; i<jr.getKrono(name).size(); i++){
            int toNum = ((Integer.parseInt(jr.getKrono(name).get(i).toString()))/60);
            krono.add(new Text(String.valueOf(toNum)), 0, i);
        }

        nivBonus.setItems(buttons);
        for(int i=0; i<this.jr.getListeBonusJoueur(name).size(); i++){
            addButton(this.jr.getListeBonusJoueur(name).get(i));
        }
    }

    public void comeBack(){
        this.ui.switchSceneHome();
    }

    @FXML
    public void addButton(String j){
        Button b = new Button(j);
        buttons.add(b);
        b.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> clickNivBonus(b.getText())));
    }

    public void clickNivBonus(String name){
        this.ui.switchBonusLevel(name);
    }
}
