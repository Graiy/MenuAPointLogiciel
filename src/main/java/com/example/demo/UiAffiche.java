package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class UiAffiche {
    private Stage st;
    private JsonReader jr;
    private ScreenController screenController;

    public UiAffiche(Stage stage, JsonReader jr) throws IOException {
        this.st = stage;
        this.jr = jr;
        creationScreen();
    }

    public void creationScreen() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(MenuAPointApplication.class.getResource("homeScreen.fxml"));
        fxmlLoader.setControllerFactory(c-> new MenuAPointController(jr, this));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);

        this.screenController = new ScreenController(scene);
        FXMLLoader fxmlLoaderHome = new FXMLLoader(getClass().getResource("homeScreen.fxml"));
        screenController.addScreen("home", fxmlLoaderHome, jr, this, "home");

        for (String joueur : jr.getListeJoueur()) {
            FXMLLoader fxmlLoaderData = new FXMLLoader(getClass().getResource("dataScreen.fxml"));
            this.screenController.addScreen(joueur, fxmlLoaderData, jr, this, "data");

            for(String nomBonus : jr.getListeBonusJoueur(joueur)){
                FXMLLoader fxmlLoaderBonus = new FXMLLoader(getClass().getResource("bonusScreen.fxml"));
                this.screenController.addScreen(joueur, nomBonus, fxmlLoaderBonus, jr, this, "bonus");
            }

        }

        st.setTitle("Menu A Point Data Reader");
        st.setScene(scene);
        st.show();
    }

    public void newScreen() throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Json File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Json", "*.json")); //Oblige à ne voir que les .json
        File selectedFile = fileChooser.showOpenDialog(this.st);
        String path = selectedFile.getAbsolutePath();
        this.jr = new JsonReader(path);

        creationScreen();
    }

    public void switchSceneData(String name){
        this.screenController.activate(name);
    }

    public void switchSceneHome(){
        this.screenController.activate("home");
    }

    public void switchBonusLevel(String name){
        this.screenController.activate(name);
    }
}
