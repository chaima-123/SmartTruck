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
import com.mycompany.myapp.entities.Fournisseur;
import com.mycompany.myapp.entities.Palette;
import com.mycompany.myapp.entities.Stock;
import com.mycompany.myapp.gui.ListArticlesForm;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceArticle {

    public ArrayList<Article> articles;
    public ArrayList<Article> article;
    public ArrayList<Famille> familles;
    public ArrayList<Famille> family;
    public ArrayList<Stock> stock;
    public ArrayList<Fournisseur> fournisseurs;
    public ArrayList<Fournisseur> fournisseur;

    public static ServiceArticle instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceArticle() {
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
                + "&code=" + a.getCode() + "&unite=" + a.getUnité() + "&prix_achat=" + a.getPrix_achat()
                + "&prix_vente=" + a.getPrix_vente() + "&seuil_min=" + a.getSeuil_min() + "&seuil_max=" + a.getSeuil_max()
                +"&fournisseur=" + a.getFournisseur()+ "&famille=" + a.getFamille() ;
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

    public ArrayList<Article> parseArticles(String jsonText) {
        try {
            articles = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Article a = new Article();

                float SM = Float.parseFloat(obj.get("seuilMin").toString());
                float SMX = Float.parseFloat(obj.get("seuilMax").toString());
                float Fam = Float.parseFloat(obj.get("famille").toString());
                float Four = Float.parseFloat(obj.get("fournisseur").toString());

                a.setRef_article(obj.get("refArticle").toString());
                a.setDesignation(obj.get("designation").toString());
                a.setCode(obj.get("code").toString());
                a.setPrix_achat(Float.parseFloat(obj.get("prixAchat").toString()));
                a.setPrix_vente(Float.parseFloat(obj.get("prixVente").toString()));
                a.setSeuil_min((int) SM);
                a.setSeuil_max((int) SMX);
                a.setUnité(obj.get("unite").toString());
                a.setFamille((int) Fam);
                a.setFournisseur((int) Four);
                articles.add(a);
            }

        } catch (IOException ex) {

        }
        return articles;
    }

    public ArrayList<Famille> parseFamilles(String jsonText) {
        try {
            familles = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Famille f = new Famille();

                f.setNomFamille(obj.get("nomFamille").toString());

                familles.add(f);
            }

        } catch (IOException ex) {

        }
        return familles;
    }

    public ArrayList<Fournisseur> parseNomSociete(String jsonText) {
        try {
            fournisseurs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Fournisseur f = new Fournisseur();

                f.setNomSociete(obj.get("nomSociete").toString());

                fournisseurs.add(f);
            }

        } catch (IOException ex) {

        }
        return fournisseurs;
    }

    public ArrayList<Stock> parseStock(String jsonText) {
        try {
            stock = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Stock s = new Stock();

                float SM = Float.parseFloat(obj.get("seuilMin").toString());
                float SMX = Float.parseFloat(obj.get("seuilMax").toString());
                float numLot = Float.parseFloat(obj.get("numLot").toString());
                float quantiteTot = Float.parseFloat(obj.get("quantiteTotale").toString());
                s.setSeuil_min((int) SM);
                s.setSeuil_max((int) SMX);
                s.setNum_lot((int) numLot);
                s.setQuantiteTotale((int) quantiteTot);
                s.setRef_article(obj.get("refArticle").toString());
                s.setDesignation(obj.get("designation").toString());

                stock.add(s);

            }

        } catch (IOException ex) {

        }
        return stock;
    }

    public ArrayList<Article> parseDes(String jsonText) {
        try {
            article = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Article a = new Article();
                //int codeF = Integer.parseInt(obj.get("codeFamille").toString());

                //   float codeFamille = Float.parseFloat(obj.get("codeFamille").toString());
                // f.setCodeFamille((int) Float.parseFloat(obj.get("codeFamille").toString()) );
                // f.setCodeFamille((int)codeFamille);
                a.setDesignation(obj.get("designation").toString());

                article.add(a);
            }

        } catch (IOException ex) {

        }
        return article;
    }

    public ArrayList<Famille> parseFCode(String jsonText) {
        try {
            family = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Famille f = new Famille();
                //int codeF = Integer.parseInt(obj.get("codeFamille").toString());

                float codeFamille = Float.parseFloat(obj.get("codeFamille").toString());
                // f.setCodeFamille((int) Float.parseFloat(obj.get("codeFamille").toString()) );
                f.setCodeFamille((int) codeFamille);
                //f.setNomFamille(obj.get("nomFamille").toString());

                family.add(f);
            }

        } catch (IOException ex) {

        }
        return family;
    }

    public ArrayList<Fournisseur> parseID(String jsonText) {
        try {
            fournisseur = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Fournisseur f = new Fournisseur();

                float id = Float.parseFloat(obj.get("id").toString());

                f.setId((int) id);

                fournisseur.add(f);
            }

        } catch (IOException ex) {

        }
        return fournisseur;
    }

    public ArrayList<Famille> getAllFamilles() {
        String url = Statics.BASE_URL + "api/getfamille";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                familles = parseFamilles(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return familles;

    }
    
     public ArrayList<Fournisseur> getAllFournisseurs() {
        String url = Statics.BASE_URL + "api/getfournisseur";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                fournisseurs = parseNomSociete(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return fournisseurs;

    }
    

    public ArrayList<Stock> getStock() {
        String url = Statics.BASE_URL + "api/showstock";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                stock = parseStock(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return stock;

    }

    public ArrayList<Famille> getNomFamille(int Code) {
        String url = Statics.BASE_URL + "api/getnom?codeFamille=" + Code;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                familles = parseFamilles(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return familles;

    }
    
      public ArrayList<Fournisseur> getNomSociete(int id) {
        String url = Statics.BASE_URL + "api/getnomsociete?id=" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                fournisseurs = parseNomSociete(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return fournisseurs;

    }

    public ArrayList<Famille> getCodeF(String nomF) {
        String url = Statics.BASE_URL + "api/getcode?nomFamille=" + nomF;
        System.out.println(nomF);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                family = parseFCode(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return family;

    }
    
    public ArrayList<Fournisseur> getID(String nomS) {
        String url = Statics.BASE_URL + "api/getcode?nomSociete=" + nomS;
        System.out.println(nomS);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                fournisseur = parseID(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return fournisseur;

    }

    public ArrayList<Void> deleteArticle(String des) {
        String url = Statics.BASE_URL + "api/deletearticle?designation=" + des;

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                new String(req.getResponseData());
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return null;

    }

    public ArrayList<Article> getAllArticles() {
        String url = Statics.BASE_URL + "api/showarticle";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articles = parseArticles(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }

    public ArrayList<Article> getArticle(String product) {

        String url = Statics.BASE_URL + "api/getarticle?designation=" + product;

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articles = parseArticles(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }
    
    public ArrayList<Stock> getStockDetails(String des) {

        String url = Statics.BASE_URL + "api/stockdes?designation=" + des;

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                stock = parseStock(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return stock;
    }

}




