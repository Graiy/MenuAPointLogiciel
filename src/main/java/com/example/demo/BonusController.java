package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import static javafx.scene.paint.Color.LIME;
import static javafx.scene.paint.Color.RED;

public class BonusController {

    @FXML
    private Text nomText;
    @FXML
    private GridPane result;
    private JsonReader jr;
    private UiAffiche ui;
    private String nameLvl;
    private String nameJoueur;

    public BonusController(JsonReader jr, UiAffiche ui, String nameLvl, String nameJoueur){
        this.jr = jr;
        this.ui = ui;
        this.nameLvl = nameLvl;
        this.nameJoueur = nameJoueur;
    }

    @FXML
    public void initialize(){
        nomText.setText(nameLvl);

        if(jr.getLvlBonusFini(nameJoueur, nameLvl) != null){
            if(jr.getLvlBonusFini(nameJoueur, nameLvl)) {
                result.add(new Circle(14, LIME), 1, 0);
            } else {
                result.add(new Circle(14, RED), 1, 0);
            }
        }

        if(jr.getIndiceBonus(nameJoueur, nameLvl) != null){
            if(jr.getIndiceBonus(nameJoueur, nameLvl)) {
                result.add(new Circle(14, LIME), 1, 1);
            } else {
                result.add(new Circle(14, RED), 1, 1);
            }
        }

        if(jr.getEssaisBonus(nameJoueur, nameLvl) != null){
            result.add(new Text(jr.getEssaisBonus(nameJoueur, nameLvl)), 1, 2);
        }

        if(jr.getKronoBonus(nameJoueur, nameLvl) != null){
            int toNum = ((Integer.parseInt(jr.getKronoBonus(nameJoueur, nameLvl)))/60);
            result.add(new Text(String.valueOf(toNum)), 1, 3);
        }

    }

    public void comeBackLeRetour(){
        this.ui.switchSceneData(nameJoueur);
    }
}
