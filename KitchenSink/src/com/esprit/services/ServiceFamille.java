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
import com.esprit.entities.Emplacement;
import com.esprit.entities.Famille;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mac
 */
public class ServiceFamille {

    public ArrayList<Famille> familles;

    public static ServiceFamille instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceFamille() {
        req = new ConnectionRequest();
    }

    public static ServiceFamille getInstance() {
        if (instance == null) {
            instance = new ServiceFamille();
        }
        return instance;
    }

    public ArrayList<Famille> parseTasks(String jsonText) {
        try {
            familles = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {

                //Création des tâches et récupération de leurs données
                Famille t = new Famille();
                float id = Float.parseFloat(obj.get("codeFamille").toString());
                String nomF = obj.get("nomFamille").toString();

                t.setCodeFamille((int) id);
                t.setNomFamille(nomF);
                //Ajouter la tâche extraite de la réponse Json à la liste
                familles.add(t);
            }

        } catch (IOException ex) {

        }

        return familles;
    }
    
    
    
    
      public ArrayList<Famille> getAllFamille(){
        String url = Statics.BASE_URL+"/allFamille";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                familles = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return familles;
    }
      
      
       public ArrayList<Famille> getFamille(String nom){
        String url = Statics.BASE_URL+"/SearchFam/"+nom;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                familles = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return familles;
    }


}
