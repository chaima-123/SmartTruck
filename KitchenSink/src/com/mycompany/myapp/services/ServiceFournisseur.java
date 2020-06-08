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
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Fournisseur;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class ServiceFournisseur {

    public ArrayList<Fournisseur> fournisseurs;
    public static ServiceFournisseur instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    String result = "";

    public ServiceFournisseur() {
        req = new ConnectionRequest();
    }

    public static ServiceFournisseur getInstance() {
        if (instance == null) {
            instance = new ServiceFournisseur();
        }
        return instance;
    }

    public boolean addFournisseur(Fournisseur l) {
        String url = "http://localhost/pi1/test1.1/web/app_dev.php/adminMobile/ajoutfournisseurMobile?cin=" + l.getCin() + "&email=" + l.getEmail() + "&adresse=" + l.getAdresse() + "&telephone=" + l.getTelephone() + "&fax=" + l.getFax() + "&nomSociete=" + l.getNomSociete() + "";
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

    public boolean modifierFournisseur(Fournisseur ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pi1/test1.1/web/app_dev.php/adminMobile/updatefsr/" + ta.getId()
                + "?cin=" + ta.getCin()
                + "&email=" + ta.getEmail()
                + "&adresse=" + ta.getAdresse()
                + "&telephone=" + ta.getTelephone()
                + "&fax=" + ta.getFax()
                + "&nomSociete=" + ta.getNomSociete();

        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println("modification= "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return resultOK;
    }

    public String DeleteFournisseur(Fournisseur c) {
        String url = "http://localhost/pi1/test1.1/web/app_dev.php/adminMobile/deletefsr?id=" + c.getId();
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

    public ArrayList<Fournisseur> parseFournisseurs(String jsonText) {
        try {
            fournisseurs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> fournisseursListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) fournisseursListJson.get("root");
            for (Map<String, Object> obj : list) {
                Fournisseur t = new Fournisseur();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setCin(((int) Float.parseFloat(obj.get("cin").toString())));
                t.setEmail(obj.get("email").toString());
                t.setAdresse(obj.get("adresse").toString());
                t.setTelephone(((int) Float.parseFloat(obj.get("telephone").toString())));
                t.setFax(((int) Float.parseFloat(obj.get("fax").toString())));
                t.setNomSociete(obj.get("nomSociete").toString());
                fournisseurs.add(t);
            }
        } catch (IOException ex) {
        }
        return fournisseurs;
    }

    public ArrayList<Fournisseur> getAllFournisseurs() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi1/test1.1/web/app_dev.php/adminMobile/showfournisseurMobile");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceFournisseur ser = new ServiceFournisseur();
                fournisseurs = ser.parseFournisseurs(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return fournisseurs;
    }

    public ArrayList<Fournisseur> SearchByNomSociete(String nomSociete) {
        String url = "http://localhost/pi1/test1.1/web/app_dev.php/adminMobile/searchfsr/" + nomSociete;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                fournisseurs = parseFournisseurs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return fournisseurs;
    }

}
