package com.example.demo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader{

    protected String nomJson;
    protected Hashtable<String, JSONObject> data;
    protected ArrayList<String> listeJoueur;

    public JsonReader(){
        this.nomJson = "donnees.json";
        this.data = new Hashtable<String, JSONObject>();
        this.listeJoueur = new ArrayList<>();
    }

    public void readJson(){
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(this.nomJson)){

            Object obj = jsonParser.parse(reader);

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray donneesList = (JSONArray) jsonObject.get("donnees");

            donneesList.forEach( emp -> parseDonneesObject((JSONObject) emp));

            System.out.println(listeJoueur);
            System.out.println(data);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void parseDonneesObject(JSONObject donnees){
        //Créer le dico
        data.put(donnees.get("joueur").toString(), donnees);
        listeJoueur.add(donnees.get("joueur").toString());
    }

    public ArrayList<String> getListeJoueur(){
        return listeJoueur;
    }

    public int getIndiceRestant(String joueur){
        JSONObject dataJoueur = data.get(joueur);
        return (int)dataJoueur.get("indiceRestant");
    }

    public ArrayList getLvlFinis(String joueur){
        JSONObject dataJoueur = data.get(joueur);
        return (ArrayList)dataJoueur.get("niveauxFinis");
    }

    public ArrayList getIndiceNiveau(String joueur){
        JSONObject dataJoueur = data.get(joueur);
        return (ArrayList) dataJoueur.get("indiceNiveau");
    }

    public ArrayList getEssais(String joueur){
        JSONObject dataJoueur = data.get(joueur);
        return (ArrayList) dataJoueur.get("essaies");
    }

    public ArrayList getKrono(String joueur){
        JSONObject dataJoueur = data.get(joueur);
        return (ArrayList) dataJoueur.get("chronoNiveau");
    }
}
