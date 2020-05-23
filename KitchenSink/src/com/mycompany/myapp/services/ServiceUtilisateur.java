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
import com.mycompany.myapp.entities.fos_user;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class ServiceUtilisateur {

    public ArrayList<fos_user> utilisateurs;
    public static ServiceUtilisateur instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ConnectionRequest cr;

    public ServiceUtilisateur() {
        req = new ConnectionRequest();
    }

    public static ServiceUtilisateur getInstance() {
        if (instance == null) {
            instance = new ServiceUtilisateur();
        }
        return instance;
    }

    public boolean addUtilisateur(fos_user l) {
        String url = "http://localhost/pi1/test1.1/web/app_dev.php/userMobile/ajoututilisateur?nom=" + l.getNom() + "&prenom=" + l.getPrenom() + "&adresse=" + l.getAdresse() + "&telephone=" + l.getTelephone() + "&email=" + l.getEmail() + "&grade=Client" + "&username=" + l.getUsername() + "&password=" + l.getPassword() + "";
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

    public boolean addUtilisateurAdmin(fos_user l) {
        String url = "http://localhost/pi1/test1.1/web/app_dev.php/userMobile/ajoututilisateur?nom=" + l.getNom() + "&prenom=" + l.getPrenom() + "&adresse=" + l.getAdresse() + "&telephone=" + l.getTelephone() + "&email=" + l.getEmail() + "&grade=" + l.getGrade() + "&username=" + l.getUsername() + "&password=" + l.getPassword() + "";
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

    public ArrayList<fos_user> parseUtilisateurs(String jsonText) {
        try {
            System.out.println("jsontext: " + jsonText);
            utilisateurs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> utilisateursListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) utilisateursListJson.get("root");
            for (Map<String, Object> obj : list) {
                fos_user t = new fos_user();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setNom(obj.get("nom").toString());
                t.setPrenom(obj.get("prenom").toString());
                t.setAdresse(obj.get("adresse").toString());
                t.setTelephone(((int) Float.parseFloat(obj.get("telephone").toString())));
                t.setEmail(obj.get("email").toString());
                t.setGrade(obj.get("grade").toString());
                t.setUsername(obj.get("username").toString());
                t.setPassword(obj.get("password").toString());
                utilisateurs.add(t);
            }
        } catch (IOException ex) {
        }
        return utilisateurs;
    }

    public ArrayList<fos_user> getAllUtilisateurs() {
        String url = "http://localhost/pi1/test1.1/web/app_dev.php/userMobile/showutilisateur";
        req.setUrl(url);
        System.out.println("cr: " + req.getUrl());

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                // System.out.println("hello omaa jmai ");
                String res = new String(req.getResponseData());
                System.out.println("resultats: " + res);
                System.out.println(res);
                utilisateurs = parseUtilisateurs(res);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return utilisateurs;
    }

    public boolean updateUtilisateur(fos_user lv) {
        System.out.println("Modifier utilisateur:   " + lv);
        String url = "http://localhost/pi1/test1.1/web/app_dev.php/user/updateUtilisateur?id=" + lv.getId() + "&nom=" + lv.getNom() + "&prenom=" + lv.getPrenom() + "&adresse=" + lv.getAdresse() + "&telephone=" + lv.getTelephone() + "&email=" + lv.getEmail() + "&grade=" + lv.getGrade() + "&username=" + lv.getUsername() + "&password=" + lv.getPassword() + "";
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

    public ArrayList<fos_user> SearchByUsername(String username) {
        String url = "http://localhost/pi1/test1.1/web/app_dev.php/userMobile/searchuser/" + username;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                utilisateurs = parseUtilisateurs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return utilisateurs;
    }

    public fos_user U;

    public fos_user CheckLoginData(String username, String password) {
        ConnectionRequest con = new ConnectionRequest();

        con.setUrl("hhttp://localhost/pi1/test1.1/web/app_dev.php/userMobile/getuserpwd/username/" + username + "/password/" + password);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUtilisateur ser = new ServiceUtilisateur();
                System.out.println(new String(con.getResponseData()));
                U = ser.getUserEntity(new String(con.getResponseData()));

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

        return U;
    }

    public fos_user getUserEntity(String json) {
        fos_user t = null;
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));

            if (obj.size() == 0) {
                return null;
            }
            t = new fos_user();

            float id = Float.parseFloat(obj.get("id").toString());
            t.setId((int) id);
            t.setNom(obj.get("nom").toString());
            t.setPrenom(obj.get("prenom").toString());
            t.setAdresse(obj.get("adresse").toString());
            t.setTelephone(((int) Float.parseFloat(obj.get("telephone").toString())));
            t.setEmail(obj.get("email").toString());
            t.setGrade(obj.get("grade").toString());
            t.setUsername(obj.get("username").toString());
            t.setPassword(obj.get("password").toString());
        } catch (IOException ex) {
        }
        return t;
    }

    public fos_user getUserData(int user_id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi1/test1.1/web/app_dev.php/userMobile/getuserid/" + user_id);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUtilisateur ser = new ServiceUtilisateur();
                U = ser.getUserEntity(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return U;
    }

}
