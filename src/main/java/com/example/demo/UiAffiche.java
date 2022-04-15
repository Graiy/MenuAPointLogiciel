package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UiAffiche {
    private Stage st;
    private JsonReader jr;
    private ScreenController screenController;

    public UiAffiche(Stage stage, JsonReader jr) throws IOException {
        this.st = stage;
        this.jr = jr;
        //MenuAPointController mpc = new MenuAPointController(jr);
        FXMLLoader fxmlLoader = new FXMLLoader(MenuAPointApplication.class.getResource("homeScreen.fxml"));
        fxmlLoader.setControllerFactory(c-> new MenuAPointController(jr, this));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        this.screenController = new ScreenController(scene);
        //screenController.addScreen("home", FXMLLoader.load(getClass().getResource("homeScreen.fxml")));
        FXMLLoader fxmlLoaderHome = new FXMLLoader(getClass().getResource("homeScreen.fxml"));
        screenController.addScreen("home", fxmlLoaderHome, jr, this);
        for (String joueur : jr.getListeJoueur()) {
            System.out.println(joueur);
            FXMLLoader fxmlLoaderData = new FXMLLoader(getClass().getResource("dataScreen.fxml"));
            this.screenController.addScreen(joueur, fxmlLoaderData, jr, this);
        }
        st.setTitle("Menu A Point Data Reader");
        st.setScene(scene);
        st.show();
    }

    public void switchSceneData(String name){
        this.screenController.activate(name);
    }

    public void switchSceneHome(){
        this.screenController.activate("home");
    }
}
