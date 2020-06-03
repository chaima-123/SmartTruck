/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.CommandeE;
import com.mycompany.myapp.entities.Famille;
import com.mycompany.myapp.entities.LigneCommande;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ServiceArticle {
    
    
    public ArrayList<Article> articles;
      public LinkedHashMap<String, Object> famille = new LinkedHashMap<>();
    
    public static ServiceArticle instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceArticle() {
         req = new ConnectionRequest();
    }

    public static ServiceArticle getInstance() {
        if (instance == null) {
            instance = new ServiceArticle();
        }
        return instance;
    }

    public boolean addArticle(Article a) {
        String url = Statics.BASE_URL + "api/addarticle?ref_article=" + a.getRef_article() + "&designation=" + a.getDesignation()
                + "&code=" + a.getCode() + "&unite=" +a.getUnité() + "&prix_achat=" +a.getPrix_achat()
                + "&prix_vente=" +a.getPrix_vente()+ "&seuil_min=" +a.getSeuil_min()+ "&seuil_max=" + a.getSeuil_max()
                + "&famille=" +a.getFamille() + "&fournisseur=" +a.getFournisseur();
        req.setUrl(url);
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

    public ArrayList<Article> parseArticles(String jsonText){
           
        try {
            articles=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                Article a = new Article();
              //  System.out.println("tgoooooooooooooooooooooooooooooooooooooooooo"+obj.get("refArticle"));
         /*       float SM = Float.parseFloat(obj.get("seuilMin").toString());
                float SMX = Float.parseFloat(obj.get("seuilMax").toString());*/
                a.setRef_article(obj.get("refArticle").toString());
                a.setDesignation(obj.get("designation").toString());
                a.setImg(obj.get("img").toString());
//                a.setPrix_achat(Float.parseFloat(obj.get("prixAchat").toString()));
               a.setPrix_vente (Float.parseFloat(obj.get("prixVente").toString()));
               
                        famille = (LinkedHashMap) obj.get("Famille");
               Famille fml = new Famille();
               
//                for (Map.Entry <String, Object> entry : famille.entrySet()) {
//                    
//                    if (entry.getKey().contains("nomFamille")) {
//                   
//           
//            
//                        fml.setNomFamille(entry.getValue().toString());
//                    }
//                }
             /*   a.setSeuil_min((int)SM);
                a.setSeuil_max((int)SMX);
                a.setUnité(obj.get("unite").toString());
                a.setFamille((int)SMX);
//                a.setFournisseur((int)SMX);*/
                //System.out.println(a);
                a.setFamille(fml);
                
                 articles.add(a);
               
            }
           
            
            
        } catch (IOException ex) {
            
        }
        return articles;
    }
    
    
    
    public ArrayList<Article> parsefamille(String jsonText){
        try {
            articles=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Famille a = new Famille();
             
         /*       float SM = Float.parseFloat(obj.get("seuilMin").toString());
                float SMX = Float.parseFloat(obj.get("seuilMax").toString());*/
              //  a.setRef_article(obj.get("refArticle").toString());
                a.setNomFamille(obj.get("nomFamille").toString());
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
        return articles;
    }
    
    
    public ArrayList<Article> getAllArticles(){
        String url = "http://localhost/pidev/web/app_dev.php/mobile/list";
     
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String res = new String(req.getResponseData());
                 
                //System.out.println("resultats user Part: "+res);
              articles = parseArticles(res);
               req.removeResponseListener(this);

            }
        }); 
        NetworkManager.getInstance().addToQueueAndWait(req);
  
        return articles;
        
    }      
    
    
    
    
    
    
    
    
    
    
    
    
    
    public ArrayList<Article> getAllFamilles(){
        String url = "http://localhost/pidev/web/app_dev.php/mobile/listfamille/boissons";
     
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String res = new String(req.getResponseData());
                System.out.println("resultats user Part: "+res);
              articles = parseArticles(res);

            }
        }); 
        NetworkManager.getInstance().addToQueueAndWait(req);
  
        return articles;
        
    }      
    
    
    
    
     public boolean addcommande(LigneCommande t) {
         
       
  
         System.out.println((t.getQte()));
        
        String url = Statics.BASE_URL + "/mobile/ajoutcommandeclient/"
                + t.getRef_article()+"/"+ t.getQte() ;  
        
        req.setUrl(url);
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

    

        
        
        
        
        
        
        
        
        
        
        
        
        
    }
     
     
     
    /* public boolean recherche(String nomFamille) {
        String url = Statics.BASE_URL + "/mobile/listfamille?nomFamille=" + nomFamille;
                
        
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
    }
     */

   
        
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                req.getResponseData();
//                System.out.println("zzfffffffffffffffffffffffffff"+articles);
//                req.removeResponseListener(this);
//            }
//
//          
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return articles;
    
    
