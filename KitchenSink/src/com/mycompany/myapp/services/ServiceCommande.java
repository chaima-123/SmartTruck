
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.CommandeE;
import com.mycompany.myapp.entities.LigneCommande;
import com.mycompany.myapp.entities.Mail;
import com.mycompany.myapp.entities.Palette;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aa
 */
public class ServiceCommande {
    
        public ArrayList<CommandeE> cmd;
        public ArrayList<Commande> Lastcmd;
                public ArrayList<CommandeE> cmds;



    public static ServiceCommande instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCommande() {
         req = new ConnectionRequest();
    }

    public static ServiceCommande getInstance() {
        if (instance == null) {
            instance = new ServiceCommande();
        }
        return instance;
    }
    
    
    
     
    
       public ArrayList<Commande> parseLastCmd(String jsonText){
        try {
           Lastcmd=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Commande t = new Commande();
              
                t.setId_commande((int)Float.parseFloat(obj.get("idCommande").toString()));
                 
               Lastcmd.add(t);

            }
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            
        }
        return Lastcmd;
    }
    
    public ArrayList<Commande> getlastCommande(){
        String url = Statics.BASE_URL+"/last";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Lastcmd = parseLastCmd(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Lastcmd;
    }
    public boolean addArticle(LigneCommande t) {
        System.out.println(t.getRefArticle());
        String url = Statics.BASE_URL + "/palette/list/"+t.getRefArticle()+"?qte="+ t.getQte() ;      
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
    
    public boolean UpdateLigne(LigneCommande t) {
        String url = Statics.BASE_URL + "/qte/"+t.getRefArticle()+"?qte="+ t.getQte();      
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
    public boolean DeleteLigne(LigneCommande t) {
        String url = Statics.BASE_URL + "/deleteligne/" +t.getRefArticle();      
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
    
    
    
    public ArrayList<CommandeE> parseCmd(String jsonText) throws ParseException{
        try {
          cmd=new ArrayList<>();
           // System.out.println("llllll"+jsonText);
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.println(list);
            for(Map<String,Object> obj : list){
              //  System.out.println("99999999999");
                CommandeE e = new CommandeE();
            int id= (int)Float.parseFloat(obj.get("idCommande").toString());
                e.setId(id);
                String n= obj.get("numCommande").toString();
               e.setNumCommande((n));
               String m= obj.get("refArticle").toString();
                e.setRef((m));
                int a= (int)Float.parseFloat(obj.get("qte").toString());
                e.setQte(a);
              e.setEtat((obj.get("etat").toString()));
                e.setMontant(Float.parseFloat(obj.get("montant").toString()));
                DateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            e.setDateCommande(simpleDateFormat.parse(obj.get("dateLivraison").toString()));
                   // e.setDateLivraison(simpleDateFormat.parse(obj.get("dateReception").toString()));

                e.setNomSociete((obj.get("nomsociete").toString()));
                              //  System.out.println("oooo"+ e);
               cmd.add(e);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());      
        }
        
        //System.out.println("jjjjjj"+cmd);
        return cmd;
    }
    

    
    public ArrayList<CommandeE> getAllCommande() {
        String url = Statics.BASE_URL+"/Allcommande";
        req.setUrl(url);
        System.out.println("req: " + req.getUrl());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                try {
                    //System.out.println(res);
                    
                    cmd = parseCmd(new String(req.getResponseData()));
                    
                    // System.out.println("bbb :" + cmd);
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cmd;
    }
    
    
    
//      public ArrayList<Commande> parseCmde(String jsonText) throws ParseException{
//        try {
//          Lastcmd=new ArrayList<>();
//            //System.out.println("llllll"+jsonText);
//            JSONParser j = new JSONParser();
//            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            
//            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
//            System.out.println(list);
//            for(Map<String,Object> obj : list){
//               
//                Commande e = new Commande();
//            
//                String n= obj.get("numCommande").toString();
//               e.setNum_commande((n));
//              
//              e.setEtat((obj.get("etat").toString()));
//                e.setMontant(Float.parseFloat(obj.get("montant").toString()));
//                DateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//                e.setDate_commande(simpleDateFormat.parse(obj.get("dateCommande").toString()));
//                e.setDate_livraison(simpleDateFormat.parse(obj.get("dateLivraison").toString()));
//
//                               
//              Lastcmd.add(e);
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());      
//        }
//       
//        return Lastcmd;
//    }
            
       
    
}
