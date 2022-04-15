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
        fxmlLoader.setControllerFactory(c->{return new MenuAPointController(jr);});
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        this.screenController = new ScreenController(scene);
        //screenController.addScreen("home", FXMLLoader.load(getClass().getResource("homeScreen.fxml")));
        for (String joueur : jr.getListeJoueur()) {
            screenController.addScreen(joueur, FXMLLoader.load(getClass().getResource("dataScreen.fxml")));
        }
        st.setTitle("Menu A Point Data Reader");
        st.setScene(scene);
        st.show();
    }

    public void switchScene() throws IOException {

    }
}
