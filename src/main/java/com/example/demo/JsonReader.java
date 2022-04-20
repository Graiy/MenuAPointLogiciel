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

    private String nomJson;
    private Hashtable<String, JSONObject> data;
    private ArrayList<String> listeJoueur;
    private Set<String> setBonusLevel;
    private Hashtable<String, ArrayList<String>> listLvlBonusPerPlayer;

    public JsonReader(String path){
        this.nomJson = path;
        this.data = new Hashtable<String, JSONObject>();
        this.listeJoueur = new ArrayList<>();
        setBonusLevel = new HashSet<>();
        listLvlBonusPerPlayer = new Hashtable<>();
        readJson();
    }

    public void readJson(){
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(this.nomJson)){

            Object obj = jsonParser.parse(reader);

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray donneesList = (JSONArray) jsonObject.get("donnees");

            donneesList.forEach( emp -> parseDonneesObject((JSONObject) emp));
            for (String joueur:this.listeJoueur) {
                this.createListeBonus(joueur);
            }

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

    public String getIndiceRestant(String joueur){
        JSONObject dataJoueur = data.get(joueur);
        return dataJoueur.get("indiceRestant").toString();
    }

    public ArrayList<Boolean> getLvlFinis(String joueur){
        JSONObject dataJoueur = data.get(joueur);
        return (ArrayList<Boolean>)dataJoueur.get("niveauxFinis");
    }

    public ArrayList<Boolean> getIndiceNiveau(String joueur){
        JSONObject dataJoueur = data.get(joueur);
        return (ArrayList<Boolean>) dataJoueur.get("indiceNiveau");
    }

    public ArrayList getEssais(String joueur){
        JSONObject dataJoueur = data.get(joueur);
        return (ArrayList) dataJoueur.get("essaies");
    }

    public ArrayList getKrono(String joueur){
        JSONObject dataJoueur = data.get(joueur);
        return (ArrayList) dataJoueur.get("chronoNiveau");
    }

    public void createListeBonus(String joueur){
        JSONObject obj = (JSONObject) data.get(joueur).get("niveauxBonusFinis");
        System.out.println(obj);
        ArrayList<String> array = new ArrayList<>();
        for(Object o : obj.keySet()) {
            array.add(o.toString());
        }
        listLvlBonusPerPlayer.put(joueur, array);
    }

    public ArrayList<String> getListeBonusJoueur(String joueur){
        return listLvlBonusPerPlayer.get(joueur);
    }
    public Boolean getLvlBonusFini(String joueur, String key){
        JSONObject obj = (JSONObject) data.get(joueur).get("niveauxBonusFinis");
        return (Boolean) obj.get(key);
    }

    public Boolean getIndiceBonus(String joueur, String key){
        JSONObject obj = (JSONObject) data.get(joueur).get("indiceBonus");
        return (Boolean) obj.get(key);
    }

    public String getEssaisBonus(String joueur, String key){
        JSONObject obj = (JSONObject) data.get(joueur).get("essaiesBonus");
        if (obj.isEmpty()){
            return null;
        }else {
            return obj.get(key).toString();
        }
    }

    public String getKronoBonus(String joueur, String key){
        JSONObject obj = (JSONObject) data.get(joueur).get("chronoNiveauBonus");
        if (obj.isEmpty()){
            return null;
        }else {
            return obj.get(key).toString();
        }
    }
}
