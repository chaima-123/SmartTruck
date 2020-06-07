/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.livreur;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;

/**
 *
 * @author Asus
 */
public class LivreurForm extends Form {

    Form current;

    public LivreurForm() { 

        current = this;
        setTitle("Gestion des livreurs");
        setLayout(BoxLayout.y());

        add(new Label("Veuillez choisir une option"));
        Button btnListLivreurs = new Button("Afficher les livreurs");
        Button btnAddLivreur = new Button("Ajouter un livreur");

        btnListLivreurs.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
        btnListLivreurs.getAllStyles().setFgColor(0x189fA5);
        
        btnAddLivreur.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
        btnAddLivreur.getAllStyles().setFgColor(0x189fA5);
        
        btnAddLivreur.addActionListener(e -> new AddLivreurForm(current).show());
        btnListLivreurs.addActionListener(e -> new ListLivreursForm(current).show());

        add(btnAddLivreur);
        add(btnListLivreurs);

    }
}
