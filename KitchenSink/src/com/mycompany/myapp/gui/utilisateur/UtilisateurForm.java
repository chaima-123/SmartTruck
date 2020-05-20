/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.utilisateur;

import com.mycompany.myapp.gui.livreur.*;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Asus
 */
public class UtilisateurForm extends Form {

    Form current;

    public UtilisateurForm() { //Form previous

        current = this;
        setTitle("Gestion des Utilisateurs");
        setLayout(BoxLayout.y());

        add(new Label("Veuillez choisir une option"));
        Button btnListUsers = new Button("Afficher les Utilisateurs");
        Button btnAddUser = new Button("Ajouter un Utilisateur");
        // Button btnUpdateLivreur = new Button("Modifier un livreur");
        Button btnDeleteUser = new Button("Supprimer un Utilisateur");

        btnAddUser.addActionListener(e -> new AddUserForm(current).show());
        btnListUsers.addActionListener(e -> new ListUsersForm(current).show());

        add(btnAddUser);
        add(btnListUsers);
        // add(btnUpdateLivreur);
        add(btnDeleteUser);

        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
