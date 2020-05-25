/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.utilisateur;

import com.mycompany.myapp.gui.livreur.*;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Livreur;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.services.ServiceLivreur;
import com.mycompany.myapp.services.ServiceUtilisateur;

/**
 *
 * @author Asus
 */
public class UpdateUserForm extends Form {

    Form current;

    public UpdateUserForm(Form previous, int id, String name, String pren, String adresse, int tel, String mail, String role, String identif, String pwd) {
        current = this;
        System.out.println("nom: " + name);
        TextField nom = new TextField(name);
        TextField prenom = new TextField(pren);
        TextField ville = new TextField(adresse);
        TextField telephone = new TextField(tel);
        TextField email = new TextField(mail);
        TextField grade = new TextField(role);
        TextField username = new TextField(identif);
        TextField password = new TextField(pwd);

        Button btnUpadte = new Button("update");
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        add(nom);
        add(prenom);
        add(ville);
        add(telephone);
        add(email);
        add(grade);
        add(username);
        add(password);
        add(btnUpadte);

        btnUpadte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                fos_user u = new fos_user(id, tel, name, pren, adresse, mail, role, identif, pwd);
                if (ServiceUtilisateur.getInstance().updateUtilisateur(u)) {
                    Dialog.show("Success", "Modification avec succes", "OK", null);
		    new ListUsersForm(current).show();
                } else {
                    Dialog.show("ERROR", "Modification echouee", "OK", null);
                }

            }
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ee -> previous.showBack());

    }

}
