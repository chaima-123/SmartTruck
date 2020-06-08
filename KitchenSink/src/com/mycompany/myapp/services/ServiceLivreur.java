/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Livreur;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class ServiceLivreur {

    public ArrayList<Livreur> livreurs;
    public static ServiceLivreur instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    String result = "";

    public ServiceLivreur() {
        req = new ConnectionRequest();
    }

    public static ServiceLivreur getInstance() {
        if (instance == null) {
            instance = new ServiceLivreur();
        }
        return instance;
    }

    public boolean addLivreur(Livreur l) {
        String url = "http://localhost/pi1/test1.1/web/app_dev.php/adminMobile/ajoutlivreurMob?nom=" + l.getNom() + "&prenom=" + l.getPrenom() + "&ville=" + l.getVille() + "&telephone=" + l.getTelephone() + "";
        req.setUrl(url);
        System.out.println("url: " + url);
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

    public ArrayList<Livreur> parseLivreurs(String jsonText) {
        try {
            System.out.println("jdontext: " + jsonText);
            livreurs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> livreursListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) livreursListJson.get("root");
            for (Map<String, Object> obj : list) {
                Livreur t = new Livreur();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setNom(obj.get("nom").toString());
                t.setPrenom(obj.get("prenom").toString());
                t.setVille(obj.get("ville").toString());
                t.setTelephone(((int) Float.parseFloat(obj.get("telephone").toString())));
                livreurs.add(t);
            }
        } catch (IOException ex) {
        }
        return livreurs;
    }

    public ArrayList<Livreur> getAllLivreurs() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi1/test1.1/web/app_dev.php/adminMobile/showlivreurMob");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceLivreur ser = new ServiceLivreur();
                livreurs = ser.parseLivreurs(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return livreurs;
    }

    public boolean modifierLivreur(Livreur ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pi1/test1.1/web/app_dev.php/adminMobile/updateliv/" + ta.getId()
                + "?nom=" + ta.getNom()
                + "&prenom=" + ta.getPrenom()
                + "&ville=" + ta.getVille()
                + "&telephone=" + ta.getTelephone();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return resultOK;
    }
    
    public String DeleteLivreur(Livreur c) {
        String url = "http://localhost/pi1/test1.1/web/app_dev.php/adminMobile/deleteliv?id=" + c.getId();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        System.out.println(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String data = new String(req.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> tasksListJson;
                    tasksListJson = j.parseJSON(new CharArrayReader(data.toCharArray()));
                    result = (String) tasksListJson.get("body");

                } catch (IOException ex) {
                    ex.getMessage();
                }
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

    public ArrayList<Livreur> SearchByNom(String nom) {
        String url = "http://localhost/pi1/test1.1/web/app_dev.php/adminMobile/searchlivreur/" + nom;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                livreurs = parseLivreurs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return livreurs;
    }

}
