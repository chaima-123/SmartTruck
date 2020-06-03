/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;

/**
 *
 * @author aa
 */
public class menuCommande extends Form{
    Form current;
    public menuCommande() {
        current=this;
        setTitle("Menu");
        setLayout(BoxLayout.y());
        
        add(new Label("Choisir une option"));
        
        Button btnList = new Button("Gestion des palettes");
        Label l= new Label();
                Label l1= new Label();

        Button btnCommande = new Button("gestion des commandes");

        btnList.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
        btnList.getAllStyles().setFgColor(0x189fA5);
        
        btnCommande.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
        btnCommande.getAllStyles().setFgColor(0x189fA5);
        btnList.addActionListener(e-> {
            try {
                new HomePalette(current).show();
            } catch (ParseException ex) {
            }
        });
         btnCommande.addActionListener(e-> {
            try {
                new HomeCommande(current).show();
            } catch (ParseException ex) {
            }
        });

        addAll(btnList,l,l1,btnCommande);
        
        
    }

    

    
}
