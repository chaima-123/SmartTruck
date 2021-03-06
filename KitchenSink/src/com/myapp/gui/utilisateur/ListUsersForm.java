/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.utilisateur;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.services.ServiceUtilisateur;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class ListUsersForm extends Form {

    Form current;

    public ListUsersForm(Form previous) {

        current = this;
        setTitle("Liste des utilisateurs");

        SpanLabel sp = new SpanLabel();
        ArrayList<fos_user> utilisateurs;
        utilisateurs = ServiceUtilisateur.getInstance().getAllUtilisateurs();
        System.out.println(utilisateurs);

        for (fos_user fer : utilisateurs) {
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label id = new Label("" + fer.getId());

            Container cUsername = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label usernameLabel = new Label("Username:");
            usernameLabel.getAllStyles().setFgColor(0x228B22);
            Label username = new Label(fer.getUsername());
            cUsername.add(usernameLabel);
            cUsername.add(username);
            c1.add(cUsername);

            Container cNom = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label nomLabel = new Label("Nom: ");
            nomLabel.getAllStyles().setFgColor(0x228B22);
            Label nom = new Label(fer.getNom());
            cNom.add(nomLabel);
            cNom.add(nom);
            c1.add(cNom);

            Container cPrenom = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label prenomLabel = new Label("Prenom:");
            prenomLabel.getAllStyles().setFgColor(0x228B22);
            Label prenom = new Label(fer.getPrenom());
            cPrenom.add(prenomLabel);
            cPrenom.add(prenom);
            c1.add(cPrenom);

            Container cAdresse = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label adresseLabel = new Label("Adresse:");
            adresseLabel.getAllStyles().setFgColor(0x228B22);
            Label adresse = new Label(fer.getAdresse());
            cAdresse.add(adresseLabel);
            cAdresse.add(adresse);
            c1.add(cAdresse);

            Container ctelephone = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label telephoneLabel = new Label("Telephone:");
            telephoneLabel.getAllStyles().setFgColor(0x228B22);
            Label telephone = new Label(Integer.toString(fer.getTelephone()));
            ctelephone.add(telephoneLabel);
            ctelephone.add(telephone);
            c1.add(ctelephone);

            Container cEmail = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label emailLabel = new Label("Email:");
            emailLabel.getAllStyles().setFgColor(0x228B22);
            Label email = new Label(fer.getEmail());
            cEmail.add(emailLabel);
            cEmail.add(email);
            c1.add(cEmail);

            Container cGrade = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label gradeLabel = new Label("Grade:");
            gradeLabel.getAllStyles().setFgColor(0x228B22);
            Label grade = new Label(fer.getGrade());
            cGrade.add(gradeLabel);
            cGrade.add(grade);
            c1.add(cGrade);

            Container cPassword = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label passwordLabel = new Label("Password:");
            passwordLabel.getAllStyles().setFgColor(0x228B22);
            Label password = new Label(fer.getPassword());
            cPassword.add(passwordLabel);
            cPassword.add(password);
            c1.add(cPassword);
            Button modifbtn = new Button("Modifier cet utilisateur");
            Button btnDeleteFournisseur = new Button("Supprimer cet utilisateur");

            Label espace = new Label(" "
                    + " "
                    + " ");
            c1.add(modifbtn);
            c1.add(btnDeleteFournisseur);
            c1.add(espace);
            add(c1);

            username.addPointerPressedListener(e -> new UpdateUserForm(current, Integer.parseInt(id.getText()), nom.getText(), prenom.getText(), adresse.getText(), Integer.parseInt(telephone.getText()), email.getText(), grade.getText(), username.getText(), password.getText()).show());
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
