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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.CommandeE;
import com.mycompany.myapp.entities.LigneCommande;
import com.mycompany.myapp.entities.Mail;
import com.mycompany.myapp.entities.Palette;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Service2 {
      public static Service2 instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
        public ArrayList<CommandeE> cmd;

    private Service2() {
         req = new ConnectionRequest();
    }

    public static Service2 getInstance() {
        if (instance == null) {
            instance = new Service2();
        }
        return instance;
    }
     public Boolean UpdateCommande(CommandeE t) {
        String url = Statics.BASE_URL +"/update/"+t.getNumCommande()+"?montant="+t.getMontant()+"&dateCommande="+t.getDateCommande()+"&etat="+t.getEtat();      
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //req.pause();
        return resultOK;
    }
    public boolean envoyermail(Mail t) {
        String url = Statics.BASE_URL +"/mail2/"+t.getMail()+"/"+t.getSujet()+"/"+t.getObjet();      
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //req.pause();
        return resultOK;
    }
    
    
    public ArrayList<CommandeE> parseCmdEnCours(String jsonText){
        try {
           cmd=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                CommandeE t = new CommandeE();
              
                t.setRef(obj.get("1").toString());
                 t.setDesignation(obj.get("designation").toString());
                 t.setCode(obj.get("codeBarres").toString());
                 t.setQte((int)Float.parseFloat(obj.get("qte").toString()));
           
               cmd.add(t);
                            

            }
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            
        }
        return cmd;
    }
    
    public ArrayList<CommandeE> getAllCommandeEncours(){
        String url = Statics.BASE_URL+"/palette/commande";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cmd = parseCmdEnCours(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cmd;
    }
    
    public ArrayList<CommandeE> parseCmd(String jsonText) throws ParseException{
        try {
          cmd=new ArrayList<>();
         System.out.println("llllll"+jsonText);
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.println(list);
            for(Map<String,Object> obj : list){
               System.out.println("99999999999");
                CommandeE e = new CommandeE();
           
                String n= obj.get("numCommande").toString();
               e.setNumCommande((n));
              
               
              e.setEtat((obj.get("etat").toString()));
                e.setMontant(Float.parseFloat(obj.get("montant").toString()));
                DateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            e.setDateCommande(simpleDateFormat.parse(obj.get("dateLivraison").toString()));
                    e.setDateLivraison(simpleDateFormat.parse(obj.get("dateReception").toString()));

               
               cmd.add(e);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());      
        }
        
      System.out.println("jjjjjj"+cmd);
        return cmd;
    }
    

     public ArrayList<CommandeE> findCommande(String num) {
        String url = Statics.BASE_URL + "/find/"+num;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    cmd = parseCmd(new String(req.getResponseData()));
                    System.out.println(cmd +"====");
                } catch (ParseException ex) {
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cmd;
    }
    
    public boolean addCmd(Commande t) {
        String url = Statics.BASE_URL + "/new/" +t.getId_commande()+"?numCommande="+ t.getNum_commande()+"&montant="+t.getMontant()+"&dateCommande="+t.getDate_commande()+"&dateLivraison="+t.getDate_livraison();      
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
