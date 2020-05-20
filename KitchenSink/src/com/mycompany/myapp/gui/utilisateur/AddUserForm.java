/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.utilisateur;

import com.mycompany.myapp.gui.livreur.*;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.services.ServiceUtilisateur;

/**
 *
 * @author Asus
 */
public class AddUserForm extends Form {

    public AddUserForm(Form previous) {
        setTitle("Ajouter un nouveau utilisateur");
        setLayout(BoxLayout.y());

        TextField tfNom = new TextField("", "Nom", 20, TextField.ANY);
        FontImage.setMaterialIcon(tfNom.getHintLabel(), FontImage.MATERIAL_PERSON);
        TextField tfPrenom = new TextField("", "Prenom", 20, TextField.ANY);
        FontImage.setMaterialIcon(tfPrenom.getHintLabel(), FontImage.MATERIAL_PERSON);
        TextField tfAdresse = new TextField("", "Adresse", 2, 20);
        FontImage.setMaterialIcon(tfAdresse.getHintLabel(), FontImage.MATERIAL_LIBRARY_BOOKS);
        TextField tfTelephone = new TextField("", "Telephone", 8, 20);
        FontImage.setMaterialIcon(tfTelephone.getHintLabel(), FontImage.MATERIAL_LIBRARY_BOOKS);
        TextField tfEmail = new TextField("", "E-mail", 20, TextField.EMAILADDR);
        FontImage.setMaterialIcon(tfEmail.getHintLabel(), FontImage.MATERIAL_EMAIL);
        ComboBox rolesList = new ComboBox("Client", "Administrateur");
        String role = rolesList.getSelectedItem().toString();
        TextField tfUsername = new TextField("", "Identifiant", 20, TextField.ANY);
        FontImage.setMaterialIcon(tfUsername.getHintLabel(), FontImage.MATERIAL_PERSON);
        TextField tfPassword = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
        FontImage.setMaterialIcon(tfPassword.getHintLabel(), FontImage.MATERIAL_LOCK);

        Validator val = new Validator();
        val.setValidationFailureHighlightMode(Validator.HighlightMode.UIID);
        val.addConstraint(tfNom, new LengthConstraint(2, "Name must have at least 2 characters")).
                addConstraint(tfPrenom, new LengthConstraint(2, "prenom must have at least 2 characters")).
                addConstraint(tfAdresse, new LengthConstraint(2, "adresse must have at least 2 characters")).
                addConstraint(tfTelephone, new LengthConstraint(8, "Name must have at least 8 numbers")).
                addConstraint(tfEmail, RegexConstraint.validEmail("E-Mail must be a valid email address")).
                addConstraint(tfUsername, new LengthConstraint(2, "Name must have at least 2 charachters ")).
                addConstraint(tfPassword, new LengthConstraint(2, "Password must have at least 6 characters"));

        Button btnValider = new Button("Créer un nouveau compte");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ToastBar.showMessage("Ajout en cours...", FontImage.MATERIAL_INFO);
                fos_user f = new fos_user(tfNom.getText(), tfPrenom.getText(), tfAdresse.getText(), Integer.parseInt(tfTelephone.getText()), tfEmail.getText(), role, tfUsername.getText(), tfPassword.getText());
                if (ServiceUtilisateur.getInstance().addUtilisateurAdmin(f)) {
                        Dialog.show("Success", "Utilisateur ajouté avec succés", "OK", null);
                    } else {
                        Dialog.show("ERREUR", "Echec à l'ajout", "OK", null);
                    }
            }

        });

        addAll(tfNom, tfPrenom, tfAdresse, tfTelephone, tfEmail, rolesList, tfUsername, tfPassword, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
