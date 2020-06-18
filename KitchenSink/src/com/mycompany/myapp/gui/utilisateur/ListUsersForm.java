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
                    model2.addItem("Username: " + l0.get(i).getUsername());
                    model2.addItem("Nom de l'utilisateur: " + l0.get(i).getNom());
                    model2.addItem("Prenom de l'utilisateur: " + l0.get(i).getPrenom());
                    model2.addItem("Adresse de l'utilisateur: " + l0.get(i).getAdresse());
                    model2.addItem("E-mail de l'utilisateur: " + l0.get(i).getEmail());
                    model2.addItem("Telephone de l'utilisateur: " + l0.get(i).getTelephone());
                    model2.addItem("Grade de l'utilisateur: " + l0.get(i).getGrade());
                    // model2.addItem("Mot de passe de l'utilisateur: "+l0.get(i).getPassword());

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
            Form hi = new Form();
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
            Label gradeLabel = new Label("Role:");
            gradeLabel.getAllStyles().setFgColor(0x228B22);
            Label grade = new Label(fer.getGrade());
            cGrade.add(gradeLabel);
            cGrade.add(grade);
            c1.add(cGrade);

//            Container cPassword = new Container(new BoxLayout(BoxLayout.X_AXIS));
//            Label passwordLabel = new Label("Password:");
//            passwordLabel.getAllStyles().setFgColor(0x228B22);
//            Label password = new Label(fer.getPassword());
//            cPassword.add(passwordLabel);
//            cPassword.add(password);
//            c1.add(cPassword);
            Button mod = new Button("Afficher les details");

            mod.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
            mod.getAllStyles().setFgColor(0x189fA5);

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
            c1.add(mod);
            c1.add(btnDeleteUser);
            c1.add(espace);
            add(c1);
            
            
            
            //modif
            mod.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    Form hi = new Form();
                    hi.setTitle("Details de Mr " + fer.getUsername());
                    Container c9 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                    Container c0 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L0 = new Label("Username ");
                    TextField username = new TextField(fer.getUsername());
                    c0.add(L0);
                    c0.add(username);
                    c9.add(c0);
                    
                    Container c10 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L1 = new Label("Nom ");
                    TextField nom = new TextField(fer.getNom());
                    c10.add(L1);
                    c10.add(nom);
                    c9.add(c10);
                    Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L2 = new Label("Prenom:           ");
                    TextField Prenom = new TextField(fer.getPrenom());
                    c2.add(L2);
                    c2.add(Prenom);
                    c9.add(c2);
                    Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L3 = new Label("telephone:            ");
                    TextField tel = new TextField(Integer.toString(fer.getTelephone()));
                    c3.add(L3);
                    c3.add(tel);
                    c9.add(c3);
                    
                    Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L = new Label("Email:        ");
                    TextField email = new TextField(fer.getEmail());
                    c.add(L);
                    c.add(email);
                    c9.add(c);
                    
                    Container c4 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L4 = new Label("Adresse:        ");
                    TextField ville = new TextField(fer.getAdresse());
                    c4.add(L4);
                    c4.add(ville);
                    c9.add(c4);
                    Container c5 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L5 = new Label("Role:        ");
                    TextField grade = new TextField(fer.getGrade());
                    c4.add(L5);
                    c4.add(grade);
                    c9.add(c5);

                    Button mod = new Button("modifier");
                    mod.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
                    mod.getAllStyles().setFgColor(0x189fA5);
                    mod.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            fos_user u = new fos_user(fer.getId(),nom.getText(),prenom.getText(),ville.getText(), Integer.parseInt(tel.getText()), email.getText(), grade.getText(),username.getText());
                            if (ServiceUtilisateur.getInstance().updateUtilisateur(u)) {
                                Dialog.show("ERREUR", "Modification echouee", "OK", null);
                            } else {
                                Dialog.show("Success", "Modification avec succes", "OK", null);
                                new ListUsersForm(previous).show();
                            }
                        }
                    });

                    hi.add(c9);
                    hi.add(mod);
                    hi.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());
                    hi.show();
                }
            });

           

        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}

/*          
*/