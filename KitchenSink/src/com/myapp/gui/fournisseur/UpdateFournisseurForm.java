/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.fournisseur;

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
import com.mycompany.myapp.entities.Fournisseur;
import com.mycompany.myapp.services.ServiceFournisseur;

/**
 *
 * @author Asus
 */
public class UpdateFournisseurForm extends Form {

    Form current;

    public UpdateFournisseurForm(Form previous, int id, int cin, int fax, String e, String add, int t, String n) {

        current = this;
        System.out.println("email:  " + previous);
        //TextField identif = new TextField(id);
        TextField cinn = new TextField(cin);
        TextField email = new TextField(e);
        TextField adresse = new TextField(add);
        TextField tel = new TextField("" + t);
        TextField faxx = new TextField("" + fax);
        TextField nomsociete = new TextField(n);
        Button btnUpadte = new Button("update");

        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        //add(identif);
        add(cinn);
        add(email);
        add(adresse);
        add(tel);
        add(faxx);
        add(nomsociete);
        add(btnUpadte);
        btnUpadte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Fournisseur fr = new Fournisseur(id, 6, e, add, fax, t, n);

                if (ServiceFournisseur.getInstance().modifierFournisseur(fr)) {
                    Dialog.show("Success", "Modification avec succes", "OK", null);
                } else {
                    Dialog.show("ERREUR", "Modification echouee", "OK", null);
                }

            }
        });

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ee -> previous.showBack());
    }

}

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.myapp.gui.fournisseur;
//
//import com.codename1.ui.Button;
//import com.codename1.ui.Command;
//import com.codename1.ui.Container;
//import com.codename1.ui.Dialog;
//import com.codename1.ui.FontImage;
//import com.codename1.ui.Form;
//import com.codename1.ui.Label;
//import com.codename1.ui.TextField;
//import com.codename1.ui.events.ActionEvent;
//import com.codename1.ui.events.ActionListener;
//import com.codename1.ui.layouts.BoxLayout;
//import com.mycompany.myapp.entities.Fournisseur;
//import com.mycompany.myapp.services.ServiceFournisseur;
//
///**
// *
// * @author Asus
// */
//public class UpdateFournisseurForm extends Form {
//
//    Form current;
//
//    public UpdateFournisseurForm(Form previous, int id, int fax, String e, String add, int t, String n) {
//
//        current = this;
//        System.out.println("email:  " + previous);
//        Button btnUpadte = new Button("update");
//
//        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//
//        Container cNom = new Container(new BoxLayout(BoxLayout.X_AXIS));
//        Label nomLabel = new Label("Nom de la société:");
//        nomLabel.getAllStyles().setFgColor(0x228B22);
//        TextField nomsociete = new TextField(n);
//        cNom.add(nomLabel);
//        cNom.add(nomsociete);
//        c1.add(cNom);
//
//        Container cEmail = new Container(new BoxLayout(BoxLayout.X_AXIS));
//        Label emailLabel = new Label("Email:");
//        emailLabel.getAllStyles().setFgColor(0x228B22);
//        TextField email = new TextField(e);
//        cEmail.add(emailLabel);
//        cEmail.add(email);
//        c1.add(cEmail);
//
//        Container cAdresse = new Container(new BoxLayout(BoxLayout.X_AXIS));
//        Label adresseLabel = new Label("Adresse:");
//        adresseLabel.getAllStyles().setFgColor(0x228B22);
//        TextField adresse = new TextField(add);
//        cAdresse.add(adresseLabel);
//        cAdresse.add(adresse);
//        c1.add(cAdresse);
//
//        Container ctelephone = new Container(new BoxLayout(BoxLayout.X_AXIS));
//        Label telephoneLabel = new Label("Telephone:");
//        telephoneLabel.getAllStyles().setFgColor(0x228B22);
//        TextField telephone = new TextField("" + t);
//        ctelephone.add(telephoneLabel);
//        ctelephone.add(telephone);
//        c1.add(ctelephone);
//
//        Container cFax = new Container(new BoxLayout(BoxLayout.X_AXIS));
//        Label faxLabel = new Label("Fax:");
//        faxLabel.getAllStyles().setFgColor(0x228B22);
//        TextField faxx = new TextField("" + fax);
//        cFax.add(faxLabel);
//        cFax.add(faxx);
//        c1.add(cFax);
//
//        add(c1);
//        add(btnUpadte);
//        btnUpadte.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                Fournisseur fr = new Fournisseur(id, 3232, e, add, fax, t, n);
//
//                if (ServiceFournisseur.getInstance().modifierFournisseur(fr)) {
//                    Dialog.show("Success", "Modification avec succes", "OK", null);
//                } else {
//                   // Dialog.show("ERREUR", "Modification echouee", "OK", null);
//                }
//            }
//        });
//
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ee -> previous.showBack());
//    }
//
//}
