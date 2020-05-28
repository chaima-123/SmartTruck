/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
import com.codename1.location.GeofenceListener;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;

import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

import com.mycompany.myapp.entities.Stock;
import com.mycompany.myapp.services.ServiceArticle;
import java.io.IOException;
import java.util.ArrayList;

public class ListStockForm extends Form {

    private Form current;
    public Form previous;
    private Resources theme;
    public String etat;
    public Label d6;
    public int quantite;
    public int seuil_min;
    public int seuil_max;
    

    public ListStockForm() {
    }

    
    
   
    
    public ListStockForm(Form current, Form previous) throws IOException {

        Form h = new Form("Visualisation du Stock", BoxLayout.y());
        h.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        ArrayList<Stock> stock = ServiceArticle.getInstance().getStock();

        for (Stock s : stock) {
            int quantite = s.getQuantiteTotale();
            int seuil_min = s.getSeuil_min();
            int seuil_max = s.getSeuil_max();
            String des = s.getDesignation();
            Label l1 = new Label("" + s.getDesignation());
            l1.getAllStyles().setFgColor(0x819CDD);

            Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

            if (quantite < seuil_min) {
                etat = "STOCK ALERTE";
            } else if (quantite > seuil_max) {
                etat = "SURSTOCKAGE";
            } else {
                etat = "Stock en sécurité";
            }
            if (etat.equals("SURSTOCKAGE")) {
                Image img = Image.createImage("/yes.png");
                l1.setIcon(img);  
            }
            if (etat.equals("STOCK ALERTE")) {
                Image img = Image.createImage("/yes.png");
                l1.setIcon(img);  
                 notif y=new notif();
                 y.onEntered(des);
            
            }
//            System.out.println(etat);

            c1.add(l1);

            h.add(c1);
            h.show();

            l1.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    Form details = new Form("Details du stock");
                   // details.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
                   details.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> {
                        
                            h.showBack();
                        
                    });
                    ArrayList<Stock> stockDetails = ServiceArticle.getInstance().getStockDetails(des);
                    int quantite = s.getQuantiteTotale();
                    int seuil_min = s.getSeuil_min();
                    int seuil_max = s.getSeuil_max();
                    for (Stock d : stockDetails) {
                        Label d1 = new Label("-Designation-:" + d.getDesignation());
                        Label d2 = new Label("-Référence-:" + d.getRef_article());
                        Label d3 = new Label("-Seuil maximum-:" + String.valueOf(d.getSeuil_max()));
                        Label d4 = new Label("-Seuil minimum-:" + String.valueOf(d.getSeuil_min()));
                        Label d5 = new Label("-Quantité:-" + String.valueOf(d.getQuantiteTotale()));
                        d1.getAllStyles().setFgColor(0x819CDD);
                        d2.getAllStyles().setFgColor(0x819CDD);
                        d3.getAllStyles().setFgColor(0x819CDD);
                        d4.getAllStyles().setFgColor(0x819CDD);
                        d5.getAllStyles().setFgColor(0x819CDD);

                        if (quantite < seuil_min) {
                            etat = "STOCK ALERTE";
                        } else if (quantite > seuil_max) {
                            etat = "SURSTOCKAGE";
                        } else {
                            etat = "Stock en sécurité";
                        }
                        Label d6 = new Label("-Etat du stock:-" + etat);

                        if (etat.equals("SURSTOCKAGE")) {
                            d6.getAllStyles().setFgColor(0xff0000);
                        }
                        if (etat.equals("STOCK ALERTE")) {
                            d6.getAllStyles().setFgColor(0xff0000);
                        }
                        Container c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        c3.add(d1);
                        c3.add(d2);
                        c3.add(d4);
                        c3.add(d3);
                        c3.add(d5);
                        c3.add(d6);
                        details.add(c3);
                        details.show();

                    }

                }
            });

        }
    }

   

}
