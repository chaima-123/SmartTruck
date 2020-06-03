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
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.CommandeE;
import com.mycompany.myapp.entities.Famille;
import com.mycompany.myapp.entities.LigneCommande;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ServiceCommandeClient {

    public ArrayList<Commande> commande;
    public ArrayList<CommandeE> cmd;

    public ArrayList<fos_user> users;

    public static ServiceCommandeClient instance = null;
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

    public ArrayList<Commande> parseArticles(String jsonText) {
        try {
            commande = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Commande a = new Commande();

                float SM = Float.parseFloat(obj.get("montant").toString());
                //float SMX = Float.parseFloat(obj.get("seuilMax").toString());
                //  a.setRef_article(obj.get("refArticle").toString());
                a.setNum_commande(obj.get("num_commande").toString());
//                a.setPrix_achat(Float.parseFloat(obj.get("prixAchat").toString()));
//               a.setPrix_vente (Float.parseFloat(obj.get("prixVente").toString()));
                a.setMontant((int) SM);
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

//    public ArrayList<Commande> parsecommande(String jsonText){
//        try {
//            commande=new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            
//            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
//            for(Map<String,Object> obj : list){
//                Commande a = new Commande();
//             
//         /*       float SM = Float.parseFloat(obj.get("seuilMin").toString());
//                float SMX = Float.parseFloat(obj.get("seuilMax").toString());*/
//              //  a.setRef_article(obj.get("refArticle").toString());
//                a.setNum_commande(obj.get("num_commande").toString());
//                               float SM = Float.parseFloat(obj.get("montant").toString());
//               a.setMontant((int)SM);
//
////                a.setCode(obj.get("code").toString());
//               // a.setCodeFamille(Int.parseInt(obj.get("prixAchat").toString()));
//              // a.setPrix_vente (Float.parseFloat(obj.get("prixVente").toString()));
////               a.setSeuil_min((int)SM);
////                a.setSeuil_max((int)SMX);
////                a.setUnité(obj.get("unite").toString());
//             //  a.setCodeFamille((int)SMX);
////                a.setFournisseur((int)SMX);
//              
//               
//            }
//            
//            
//        } catch (IOException ex) {
//            
//        }
//        return commande;
//    }
//    
//    
    public fos_user getAllusers(String username, String password) {
        String url = Statics.BASE_URL + "/mobile/usermobile?username=" + username + "&password=" + password;

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users.isEmpty() ? null : users.get(0);

        //return (users.isEmpty()?null:users.get(0));
    }

    public ArrayList<fos_user> parseUsers(String jsonText) {
        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                fos_user a = new fos_user();

                // float SM = Float.parseFloat(obj.get("montant").toString());
                //float SMX = Float.parseFloat(obj.get("seuilMax").toString());
                a.setId((int) Float.parseFloat(obj.get("id").toString())); // chniya ligne hedhy ?
                a.setUsername(obj.get("username").toString());
                a.setPassword(obj.get("password").toString());
                System.out.println(obj.get("username").toString() + " " + obj.get("password").toString());

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

    public ArrayList<CommandeE> parseCommande(String jsonText) {
        try {

            //System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+jsonText);
            cmd = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" + list);
            for (Map<String, Object> obj : list) {
                CommandeE e = new CommandeE();

                String m = obj.get("refArticle").toString();
                e.setRef((m));
                // System.out.println("1111111111111111111111111111111111111111111"+m);
                String f = obj.get("designation").toString();
                e.setDesignation((f));
                int a = (int) Float.parseFloat(obj.get("qte").toString());
                e.setQte(a);
                e.setEtat((obj.get("etat").toString()));

                cmd.add(e);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(cmd);
        return cmd;
    }

     public ArrayList<CommandeE> parseCommandes(String jsonText) {
        try {
            cmd = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> commandesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) commandesListJson.get("root");
            for (Map<String, Object> obj : list) {
                CommandeE t = new CommandeE();
                
                t.setRef((obj.get("refArticle").toString()));
                t.setNom(obj.get("nom").toString());
                t.setQte(((int) Float.parseFloat(obj.get("qte").toString())));
                
                cmd.add(t);
            }
        } catch (IOException ex) {
        }
        return cmd;
    }
    
    
//    public ArrayList<CommandeE> parseCommandeclient(String jsonText) {
//        try {
//
//            //System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+jsonText);
//            cmd = new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//
//            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
//            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" + list);
//            for (Map<String, Object> obj : list) {
//                CommandeE e = new CommandeE();
//
//                System.out.println("1111111111111111111111111111111111111111111" + cmd);
//                String f = obj.get("num_commande").toString();
//                System.out.println("ooooooooooooooooooooooooooooooooooooooooo" + f);
//
//                e.setNumCommande((f));
//
//                String m = obj.get("refArticle").toString();
//                System.out.println("ooooooooooooooooooooooooooooooooooooooooo" + m);
//
//                e.setRef((m));
//
//                int z = (int) Float.parseFloat(obj.get("qte").toString());
//                System.out.println("ooooooooooooooooooooooooooooooooooooooooo" + z);
//
//                e.setQte(z);
//
//                String c = obj.get("nom").toString();
//                System.out.println("ooooooooooooooooooooooooooooooooooooooooo" + c);
//
//                e.setNom((c));
//
//             
//
//                
//                cmd.add(e);
//
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//        System.out.println(cmd);
//        return cmd;
//    }

//        public ArrayList<CommandeE> getAllcommandes(){
//        String url = Statics.BASE_URL + "/mobile/listecommandeclient";
//     
//        System.out.println(url);
//        req.setUrl(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                String res = new String(req.getResponseData());
//                System.out.println("resultats user Part >>>>>>: "+res);
//              cmd = parseCommande(res);
//              req.removeResponseListener(this);
//
//            }
//        }); 
//        NetworkManager.getInstance().addToQueueAndWait(req);
//  
//       return cmd;
//        
//    }   
    public ArrayList<CommandeE> getAllCommandes() {
        String url = "http://127.0.0.1:8000/mobile/listecommandeclient";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String ger = new String(req.getResponseData());
                cmd = parseCommande(ger);
                // System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+ger);
                //System.out.println("66666666666666666666666666666666666666666666"+cmd);
                System.out.println(req.getResponseData());
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cmd;
    }

    public ArrayList<CommandeE> getAllCommandesclients() {
        String url = "http://127.0.0.1:8000/mobile/AllCommandeclients";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String ger = new String(req.getResponseData());
                //cmd = parseCommandeclient(ger);
                cmd=parseCommandes(ger);
                // System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+ger);
                //System.out.println("66666666666666666666666666666666666666666666"+cmd);
                System.out.println(req.getResponseData());
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cmd;
    }

    public boolean DeletecmdLigne(String t) {
        String url = "http://127.0.0.1:8000/mobile/deletecommandeclient/" + t;
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
        String url = Statics.BASE_URL + "/mobile/updatecommandemobile/" + t.getRef_article() + "/" + t.getQte();
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
