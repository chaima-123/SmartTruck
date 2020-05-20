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
import com.esprit.entities.Emplacement;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mac
 */
public class ServiceEmp {

    public ArrayList<Emplacement> tasks;

    public static ServiceEmp instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEmp() {
        req = new ConnectionRequest();
    }

    public static ServiceEmp getInstance() {
        if (instance == null) {
            instance = new ServiceEmp();
        }
        return instance;
    }

    public ArrayList<Emplacement> parseTasks(String jsonText) {
        try {
            tasks = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {

                //Création des tâches et récupération de leurs données
                Emplacement t = new Emplacement();
                float id = Float.parseFloat(obj.get("id").toString());
                String niv = obj.get("intitule").toString();
                String nbTrav = obj.get("codeEmp").toString();
                String etat = obj.get("etat").toString();

                t.setEtat(etat);
                t.setId((int) id);

                t.setCodeEmp(niv);
                t.setIntitule(nbTrav);

                //Ajouter la tâche extraite de la réponse Json à la liste
                tasks.add(t);
            }

        } catch (IOException ex) {

        }

        return tasks;
    }

    public ArrayList<Emplacement> getAllEmp() {
        String url = Statics.BASE_URL + "/allEmp";
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

    public ArrayList<Emplacement> SearchByEtat(String etat) {
        String url = Statics.BASE_URL + "/searchEtat/" + etat;
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

    public ArrayList<Emplacement> SearchByCode() {
        String url = Statics.BASE_URL + "/allEmp";
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
}
