package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    private JsonReader jr;
    private UiAffiche ui;
    private String name;

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
            krono.add(new Text(jr.getKrono(name).get(i).toString()), 0, i);
        }
    }

    public void comeBack(ActionEvent event){
        this.ui.switchSceneHome();
    }
}
