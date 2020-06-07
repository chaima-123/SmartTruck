/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.utilisateur;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Border;
import com.mycompany.myapp.entities.Fournisseur;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.services.ServiceFournisseur;
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

        TextField rech = new TextField("", "Rechercher un utilisateur");
        Container cn = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container cnToll = new Container(new BorderLayout());
        TextField zoneRecherche = new TextField();
        zoneRecherche.setHint("Rechercher par Username");
        Button boutonRecherche = new Button("ok");

        cnToll.addComponent(BorderLayout.CENTER, zoneRecherche);
        cnToll.addComponent(BorderLayout.EAST, boutonRecherche);
        Toolbar toolbar = new Toolbar();
        setToolbar(toolbar);
        toolbar.setTitleComponent(cnToll);

        boutonRecherche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form f = new Form();

                cn.setScrollableY(true);
                ArrayList<fos_user> l0 = ServiceUtilisateur.getInstance().SearchByUsername(zoneRecherche.getText());
                for (int i = 0; i < l0.size(); i++) {
                    DefaultListModel model2 = new DefaultListModel();
                    model2.addItem("Username: "+l0.get(i).getUsername());
                    model2.addItem("Nom de l'utilisateur: "+l0.get(i).getNom());
                    model2.addItem("Prenom de l'utilisateur: "+l0.get(i).getPrenom());                    
                    model2.addItem("Adresse de l'utilisateur: "+l0.get(i).getAdresse());
                    model2.addItem("E-mail de l'utilisateur: "+l0.get(i).getEmail());
                    model2.addItem("Telephone de l'utilisateur: "+l0.get(i).getTelephone());
                    model2.addItem("Grade de l'utilisateur: "+l0.get(i).getGrade());
                    model2.addItem("Mot de passe de l'utilisateur: "+l0.get(i).getPassword());
                    
                    List liste = new List(model2);
                    getStyle().setBgColor(0xffffff);
                    f.addComponent(liste);
                    f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());
                    f.getToolbar().setTitle(("Resultats de la recherche"));
                    f.show();

                }

            }
        });

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

            modifbtn.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
            modifbtn.getAllStyles().setFgColor(0x189fA5);

            Button btnDeleteUser = new Button("Supprimer cet utilisateur");

            btnDeleteUser.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
            btnDeleteUser.getAllStyles().setFgColor(0x189fA5);

            btnDeleteUser.addActionListener((eeee) -> {

                if (Dialog.show("Confirmation", "Voulez-vous vraiment supprimer cet utilisateur ? ", "OK", "ANNULER")) {
                    String result = ServiceUtilisateur.getInstance().DeleteUtilisateur(fer);
                    if (!result.equals("Error")) {
                        Dialog.show("Success", result, "OK", null);
                        new ListUsersForm(previous).show();
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } else {

                }
            });

            Label espace = new Label(" "
                    + " "
                    + " ");
            c1.add(modifbtn);
            c1.add(btnDeleteUser);
            c1.add(espace);
            add(c1);

        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
