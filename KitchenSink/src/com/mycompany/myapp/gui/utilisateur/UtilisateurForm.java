/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.utilisateur;

import com.mycompany.myapp.gui.livreur.*;
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
public class UtilisateurForm extends Form {

    Form current;

    public UtilisateurForm() { //Form previous

        current = this;
        //setTitle("Gestion des Utilisateurs");
        setLayout(BoxLayout.y());

        add(new Label("Veuillez choisir une option"));
        Button btnListUsers = new Button("Afficher les Utilisateurs");
        Button btnAddUser = new Button("Ajouter un Utilisateur");

        Label espace = new Label(" "
                    + " ");
        
        btnListUsers.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
        btnListUsers.getAllStyles().setFgColor(0x189fA5);

        btnAddUser.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
        btnAddUser.getAllStyles().setFgColor(0x189fA5);

        btnAddUser.addActionListener(e -> new AddUserForm(current).show());
        btnListUsers.addActionListener(e -> new ListUsersForm(current).show());

        add(btnListUsers);
        add(espace);
        add(btnAddUser);

        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
