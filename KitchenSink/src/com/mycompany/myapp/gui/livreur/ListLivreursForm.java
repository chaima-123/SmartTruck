/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.livreur;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Livreur;
import com.mycompany.myapp.services.ServiceLivreur;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class ListLivreursForm extends Form {

    Form current;
    
    public ListLivreursForm(Form previous) {

        current = this;
        setTitle("Liste des livreurs");

        SpanLabel sp = new SpanLabel();
        ArrayList<Livreur> livreurs;
        livreurs = ServiceLivreur.getInstance().getAllLivreurs();
        System.out.println(livreurs);

        for (Livreur lv : livreurs) {
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label id = new Label("" + lv.getId());

            Container cNom = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label nomLabel = new Label("Nom: ");
            nomLabel.getAllStyles().setFgColor(0x228B22);
            Label nom = new Label(lv.getNom());
            cNom.add(nomLabel);
            cNom.add(nom);
            c1.add(cNom);

            Container cPrenom = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label prenomLabel = new Label("Prenom:");
            prenomLabel.getAllStyles().setFgColor(0x228B22);
            Label prenom = new Label(lv.getPrenom());
            cPrenom.add(prenomLabel);
            cPrenom.add(prenom);
            c1.add(cPrenom);

            Container cVille = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label villeLabel = new Label("Ville:");
            villeLabel.getAllStyles().setFgColor(0x228B22);
            Label ville = new Label(lv.getVille());
            cVille.add(villeLabel);
            cVille.add(ville);
            c1.add(cVille);

            Container ctelephone = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label telephoneLabel = new Label("Telephone:");
            telephoneLabel.getAllStyles().setFgColor(0x228B22);
            Label telephone = new Label(Integer.toString(lv.getTelephone()));
            ctelephone.add(telephoneLabel);
            ctelephone.add(telephone);
            c1.add(ctelephone);

            Label espace = new Label(" "
                    + " "
                    + " ");
            Button btnUpdateLivreur = new Button("Modifier ce livreur");
            Button btnDeleteLivreur = new Button("Supprimer ce livreur");

            c1.add(btnUpdateLivreur);
            c1.add(btnDeleteLivreur);
            c1.add(espace);
            add(c1);

            btnDeleteLivreur.addActionListener((eeee) -> {

                if (Dialog.show("Confirmation", "Voulez-vous vraiment supprimer ce livreur ? ", "OK", "ANNULER")) {
                    String result = ServiceLivreur.getInstance().DeleteLivreur(lv);
                    System.out.println("result= " + result);
                    if (!result.equals("Error")) {
                        Dialog.show("Success", result, "OK", null);
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } else {

                }
            });

            btnUpdateLivreur.addActionListener((ej) -> {
                new UpdateLivreurForm(current, Integer.parseInt(id.getText()), nom.getText(), prenom.getText(), ville.getText(), Integer.parseInt(telephone.getText())).show();

            });

//            nom.addPointerPressedListener(e -> new UpdateLivreurForm(current, Integer.parseInt(id.getText()), nom.getText(), prenom.getText(), ville.getText(), Integer.parseInt(telephone.getText())).show());
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
