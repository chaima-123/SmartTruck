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
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.Famille;
import com.mycompany.myapp.entities.LigneCommande;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ServiceCommandeClient {
    
    public ArrayList<Commande> commande;
    
    public ArrayList<fos_user> users;
    
    public static ServiceCommandeClient instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCommandeClient() {
         req = new ConnectionRequest();
    }

    public static ServiceCommandeClient getInstance() {
        if (instance == null) {
            instance = new ServiceCommandeClient();
        }
        return instance;
    }

    

    public ArrayList<Commande> parseArticles(String jsonText){
        try {
            commande =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
               Commande a = new Commande();
             
               float SM = Float.parseFloat(obj.get("montant").toString());
               //float SMX = Float.parseFloat(obj.get("seuilMax").toString());
              //  a.setRef_article(obj.get("refArticle").toString());
                a.setNum_commande(obj.get("num_commande").toString());
//                a.setPrix_achat(Float.parseFloat(obj.get("prixAchat").toString()));
//               a.setPrix_vente (Float.parseFloat(obj.get("prixVente").toString()));
               a.setMontant((int)SM);
//                a.setSeuil_max((int)SMX);
//                a.setUnité(obj.get("unite").toString());
//              a.setU((int)SMX);
//                a.setFournisseur((int)SMX);*/
              commande.add(a);
               
            }
            
            
        } catch (IOException ex) {
            
        }
        return commande;
    }
    
    
    
    public ArrayList<Commande> parsecommande(String jsonText){
        try {
            commande=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Commande a = new Commande();
             
         /*       float SM = Float.parseFloat(obj.get("seuilMin").toString());
                float SMX = Float.parseFloat(obj.get("seuilMax").toString());*/
              //  a.setRef_article(obj.get("refArticle").toString());
                a.setNum_commande(obj.get("num_commande").toString());
                               float SM = Float.parseFloat(obj.get("montant").toString());
               a.setMontant((int)SM);

//                a.setCode(obj.get("code").toString());
               // a.setCodeFamille(Int.parseInt(obj.get("prixAchat").toString()));
              // a.setPrix_vente (Float.parseFloat(obj.get("prixVente").toString()));
//               a.setSeuil_min((int)SM);
//                a.setSeuil_max((int)SMX);
//                a.setUnité(obj.get("unite").toString());
             //  a.setCodeFamille((int)SMX);
//                a.setFournisseur((int)SMX);
              
               
            }
            
            
        } catch (IOException ex) {
            
        }
        return commande;
    }
    
    
    public ArrayList<Commande> getAllcommandes(){
        String url = "http://localhost/pidev/web/app_dev.php/mobile/listcommande";
     
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String res = new String(req.getResponseData());
                System.out.println("resultats user Part: "+res);
              commande = parseArticles(res);

            }
        }); 
        NetworkManager.getInstance().addToQueueAndWait(req);
  
       return commande;
        
    }     

    
    
    public fos_user  getAllusers( String username ,String password ){
        String url =Statics.BASE_URL +"/mobile/usermobile?username="+username + "&password=" +password  ;
     
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String res = new String(req.getResponseData());
                System.out.println("resultats user Part: "+res);
              users = parseUsers(res);

            }
        }); 
        NetworkManager.getInstance().addToQueueAndWait(req);
  
      return (users.isEmpty()?null:users.get(0));
        
    }    
    
        public ArrayList<fos_user> parseUsers(String jsonText){
        try {
            users =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
               fos_user a = new fos_user();
             
              // float SM = Float.parseFloat(obj.get("montant").toString());
               //float SMX = Float.parseFloat(obj.get("seuilMax").toString());
               a.setId((int)  Float.parseFloat(obj.get("id").toString()));
               a.setUsername(obj.get("username").toString());
               a.setPassword(obj.get("password").toString());

               // a.setNum_commande(obj.get("num_commande").toString());
//                a.setPrix_achat(Float.parseFloat(obj.get("prixAchat").toString()));
//               a.setPrix_vente (Float.parseFloat(obj.get("prixVente").toString()));
              // a.setMontant((int)SM);
//                a.setSeuil_max((int)SMX);
//                a.setUnité(obj.get("unite").toString());
//              a.setU((int)SMX);
//                a.setFournisseur((int)SMX);*/
              users.add(a);
               
            }
            
            
        } catch (IOException ex) {
            
        }
        return users;
    }
}
