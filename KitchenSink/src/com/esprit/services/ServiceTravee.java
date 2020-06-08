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
import com.esprit.entities.Travee;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mac
 */
public class ServiceTravee {

    public ArrayList<Travee> travee;

    public static ServiceTravee instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    Travee t = new Travee();

    private ServiceTravee() {
        req = new ConnectionRequest();
    }

    public static ServiceTravee getInstance() {
        if (instance == null) {
            instance = new ServiceTravee();
        }
        return instance;
    }

    public ArrayList<Travee> parseTrav(String jsonText) {

        try {
            travee = new ArrayList();

            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                Travee tt = new Travee();

                //Création des tâches et récupération de leurs données
                float id = Float.parseFloat(obj.get("id").toString());
                float numTrav = Float.parseFloat(obj.get("numTrav").toString());
                //   System.out.println(id+"kkkkkkk"+numTrav);
                float idAlle = Float.parseFloat(obj.get("allee").toString());
                float idEmp = Float.parseFloat(obj.get("idEmp").toString());

                tt.setId((int) id);
                tt.setIdAllee((int) idAlle);
                tt.setIdEmp((int) idEmp);
                tt.setNumTrav((int) numTrav);
                // System.out.println(t);
                //System.out.println(t+"!!!!!NumTrav!!!!!!"+numTrav+"ll"+idEmp
                //  );
                
                // System.out.println(t);                          
               
                //Ajouter la tâche extraite de la réponse Json à la liste
                travee.add(tt);
               
            }

        } catch (IOException ex) {

        }

        return travee;
    }

    public ArrayList<Travee> getTravee(int numTrav, int idA) {
        String url = Statics.BASE_URL + "/getTrav/" + numTrav + "/" + idA;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                travee = parseTrav(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return travee;
    }

}
