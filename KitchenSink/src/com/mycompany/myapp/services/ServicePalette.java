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
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.CommandeE;
import com.mycompany.myapp.entities.Emplacement;
import com.mycompany.myapp.entities.Palette;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServicePalette {

    public ArrayList<Palette> palettes;
    public ArrayList<Article> articles;
    public ArrayList<Emplacement> emps;

    public static ServicePalette instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicePalette() {
        req = new ConnectionRequest();
    }

    public static ServicePalette getInstance() {
        if (instance == null) {
            instance = new ServicePalette();
        }
        return instance;
    }

    public boolean addPalette(Palette t) {
        String url = Statics.BASE_URL + "/palette/palette/new/" + t.getRef() + "/" + t.getCodeEmp() + "?qte=" + t.getQte() + "&dateExpiration=" + t.getDate_expiration();

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

    public ArrayList<Palette> parsePalette(String jsonText) {
        try {
            palettes = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Palette t = new Palette();

                try {
                    t.setNum_lot(((int) Float.parseFloat(obj.get("numLot").toString())));

                    t.setQte(((int) Float.parseFloat(obj.get("qte").toString())));
                    t.setRef(obj.get("1").toString());
                    t.setCodeEmp(obj.get("2").toString());

                    DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    t.setDate_expiration(simpleDateFormat.parse(obj.get("dateExpiration").toString()));
                    palettes.add(t);
                } catch (ParseException ex) {
                }

                //  System.out.println(obj.get("codeemp").toString());
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
        return palettes;
    }

    public ArrayList<Palette> getAllPAlette() {
        String url = Statics.BASE_URL + "/palette/palette/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                palettes = parsePalette(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return palettes;
    }
    
    public ArrayList<Palette> findPalette(int num) {
        String url = Statics.BASE_URL + "/palette/palette/find/"+num;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                palettes = parsePalette(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return palettes;
    }

    public ArrayList<Article> parseArticle(String jsonText) {

        try {
            articles = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Article a = new Article();

                a.setRefArticle((obj.get("refArticle")).toString());
                a.setDesignation((obj.get("designation")).toString());
                a.setCode(((obj.get("codeBarres")).toString()));
                a.setPrix(Float.parseFloat((obj.get("prixVente")).toString()));

                articles.add(a);
                //  System.out.println(obj.get("codeemp").toString());

            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
        return articles;
    }

    public ArrayList<Article> getAllArticle() {
        String url = Statics.BASE_URL + "/palette/palette/article";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articles = parseArticle(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }

    public ArrayList<Emplacement> parseEmp(String jsonText) {
        try {
            emps = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Emplacement t = new Emplacement();

                t.setCodeEmp(obj.get("codeemp").toString());
                //t.setCodeEmp(obj.get("codeemp").toString());

                emps.add(t);
                //  System.out.println(obj.get("codeemp").toString());

            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
        return emps;
    }

    public ArrayList<Emplacement> getAllEmp() {
        String url = Statics.BASE_URL + "/palette/palette/emp";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                emps = parseEmp(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return emps;
    }

    public boolean UpdatePalette(Palette t) {
        String url = Statics.BASE_URL + "/palette/palette/update/" + t.getNum_lot() + "/" + t.getRef() + "/" + t.getCodeEmp() + "?qte=" + t.getQte()+ "&dateexpiration=" + t.getDate_expiration();
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

    public boolean DeletePalette(Palette t) {
        String url = Statics.BASE_URL + "/palette/palette/delete/" + t.getNum_lot();
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
