/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.gui.fournisseur.AddFournisseurForm;
import com.mycompany.myapp.gui.fournisseur.ListFournisseursForm;

/**
 *
 * @author user
 */
public class ListeCommandesclients extends Form{
    
    
    
  Form current;

    public ListeCommandesclients() {
        current = this;
        setTitle("Gestion des commandes clients");
        setLayout(BoxLayout.y());

        add(new Label("Veuillez choisir une option"));
        Button btnListcmdclients = new Button("Afficher les commades clients");
        Button btncmdclients = new Button("Modifier les commandes");
        //Button btnUpdateFournisseur = new Button("Modifier un fournisseur");
        //Button btnDeleteFournisseur = new Button("Supprimer un fournisseur");

        btnListcmdclients.addActionListener(e -> new commandesclientsForm(current).show());
        btncmdclients.addActionListener(e -> new ListFournisseursForm(current).show());

        add(btnListcmdclients);
        add(btncmdclients);
        //add(btnUpdateFournisseur);
        //add(btnDeleteFournisseur);

        // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
   
}
