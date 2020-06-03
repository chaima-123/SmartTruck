/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;

/**
 *
 * @author aa
 */
public class HomeCommande extends Form{
Form current;
    public HomeCommande(Form previous) throws ParseException {
        current=this;
        setTitle("Gestion des commandes");
        setLayout(BoxLayout.y());
        
        add(new Label("Choisir une option"));
        
        Button btnList = new Button("Liste des commandes");
        Label t = new Label();
        Label l= new Label();
                Label l1= new Label();
        Button btnCommande = new Button("Ajouter commande");

        btnList.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
        btnList.getAllStyles().setFgColor(0x189fA5);
                   
         btnCommande.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
         btnCommande.getAllStyles().setFgColor(0x189fA5);
        btnList.addActionListener(e-> {
            try {
                new ListCommandeForm(current).show();
            } catch (ParseException ex) {
            }
        });
                btnCommande.addActionListener(e-> new listArticle(current).show());

        addAll(btnList,t,l,l1,btnCommande);
        
        
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

        
    }
    
    
}

