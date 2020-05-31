/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.Famille;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ServiceFamille {

    
     public ArrayList<Article> article;
    
    public static ServiceFamille instance=null;
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

   

    public ArrayList<Article> parseTasks(String jsonText){
        try {
            article=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

           
            
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Famille t = new Famille();
                t.setNomFamille(obj.get("nomFamille").toString());

                
                //Ajouter la tâche extraite de la réponse Json à la liste
//               aArticle.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return article;
    }
    
    public ArrayList<Article> getAllTasks(){
        String url = Statics.BASE_URL+"user/list";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                article = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return article;
    }
    
}
