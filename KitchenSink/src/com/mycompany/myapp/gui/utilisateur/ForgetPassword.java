/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.utilisateur;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Mail;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.services.ServiceUtilisateur;
import com.mycompany.myapp.services.TwilioSMS;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class ForgetPassword extends Form {

    public ForgetPassword(Form previous) {
        setTitle("Mot de passe oublié");
        setLayout(BoxLayout.y());

        TextField tfUsername = new TextField("", "Username", 20, TextField.ANY);
        FontImage.setMaterialIcon(tfUsername.getHintLabel(), FontImage.MATERIAL_PERSON);

        TextField tfEmail = new TextField("", "Email", 20, TextField.ANY);
        FontImage.setMaterialIcon(tfUsername.getHintLabel(), FontImage.MATERIAL_MAIL);

        Validator val = new Validator();
        val.setValidationFailureHighlightMode(Validator.HighlightMode.UIID);
        val.addConstraint(tfUsername, new LengthConstraint(2, "Username doit avoir au minimum 2 lettres"));

        Button btnValider = new Button("Récupérer mot de passe");
        
        btnValider.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
        btnValider.getAllStyles().setFgColor(0x189fA5);
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ArrayList<fos_user> utilisateurs;
                utilisateurs = ServiceUtilisateur.getInstance().SearchByUsername(tfUsername.getText());

                for (fos_user fer : utilisateurs) {
                    if (tfEmail.getText().equals(fer.getEmail())) {
                        ToastBar.showMessage("Récupération en cours...", FontImage.MATERIAL_INFO);
                        Label nom = new Label("Cher utilisateur Mr/Mme " + fer.getNom() + " " + fer.getPrenom());
                        Label pwd = new Label("Votre mot de passe est: " + fer.getPassword());

                        String Text = ("Cher utilisateur Mr/Mme " + fer.getNom() + " " + fer.getPrenom() + "Votre mot de passe est: " + fer.getPassword());
                        if (Dialog.show("Confirmation", "Voulez-vous envoyer le mot de passe sur mail? ", "OK", "ANNULER")) {
//                            Form hi = new Form ("Envoyer un mail");
//                            Container cMail = new Container(BoxLayout.y());
//                            TextField to = new TextField("", "Envoyer à");
//                            TextField objet = new TextField("", "Objet");
//                            TextField msg = new TextField("", "Message");
//                            
//                            Mail m = new Mail (to.getText(),objet.getText(),msg.getText());
//                            
//                            cMail.add(to);
//                            cMail.add(objet);
//                            cMail.add(msg);
//                            
//                            ServiceUtilisateur.getInstance().sendMail(m);
//                            
//                            hi.add(cMail);
//                            hi.show();
                            ServiceUtilisateur.getInstance().envoyerMail(5);
                            Dialog.show("Succes", "Mail envoyé ", "OK", null);
                        } else {
                            System.out.println(nom);
                            System.out.println(pwd);
                            add(nom);
                            add(pwd);
                        }
                    } else {
                        Dialog.show("Erreur", "Verifier votre email", "OK", null);
                    }

                }
            }
        });

        add(tfUsername);
        add(tfEmail);
        add(btnValider);

    }

}
