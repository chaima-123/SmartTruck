/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.Allee;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mac
 */
public class ServiceAllee {

    public ArrayList<Allee> tasks;
   public    Allee aa = new Allee();


    public static ServiceAllee instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceAllee() {
        req = new ConnectionRequest();
    }

    public static ServiceAllee getInstance() {
        if (instance == null) {
            instance = new ServiceAllee();
        }
        return instance;
    }

    public boolean addAllee(Allee t) {
        String url = Statics.BASE_URL + "/add?ligne="
                + t.getLigne() + "&nbTrav=" + t.getNbTrav() + "&niv=" + t.getNiveau();
        //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Allee> parseTasks(String jsonText) {
        try {
            tasks = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Allee t = new Allee();
                float id = Float.parseFloat(obj.get("id").toString());
                float niv = Float.parseFloat(obj.get("niv").toString());
                float nbTrav = Float.parseFloat(obj.get("nbTrav").toString());

                t.setId((int) id);
                t.setNiveau((int) niv);
                t.setNbTrav((int) nbTrav);

                t.setLigne(((obj.get("ligne").toString())));
                //Ajouter la tâche extraite de la réponse Json à la liste
                tasks.add(t);
            }

        } catch (IOException ex) {

        }
       
        return tasks;
    }
    
    public Allee parseAllee(String jsonText) {

        try {
            tasks = new ArrayList<>();

            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                float id = Float.parseFloat(obj.get("id").toString());
                float niv = Float.parseFloat(obj.get("niv").toString());
                float nbTrav = Float.parseFloat(obj.get("nbTrav").toString());

                aa.setId((int) id);
                aa.setNiveau((int) niv);
                aa.setNbTrav((int) nbTrav);

                aa.setLigne(((obj.get("ligne").toString())));
                //Ajouter la tâche extraite de la réponse Json à la liste
          }

        } catch (IOException ex) {

        }
       
        return aa;
    }

    public ArrayList<Allee> getAllAllee() {
        String url = Statics.BASE_URL + "/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    
    
    
    public boolean updateAllee(Allee t) {
        String url = Statics.BASE_URL + "/update/"+t.getId()+"/"+t.getNbTrav();
        //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                  resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);                          
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
      public boolean deleteAllee(Allee t) {
        String url = Statics.BASE_URL + "/delete/"+t.getId();
        //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                  resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);                          
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
      
      
       public Allee getAllee(String t) {
        String url = Statics.BASE_URL + "/getAllee/"+t;
        //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                aa = parseAllee(new String(req.getResponseData()));
                req.removeResponseListener(this);                          
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return aa;
    }
}
