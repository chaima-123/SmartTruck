/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.fournisseur;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Fournisseur;
import com.mycompany.myapp.services.ServiceFournisseur;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class ListFournisseursForm extends Form {

    Form current;

    public ListFournisseursForm(Form previous) {
        current = this;
        setTitle("Liste des fournissuers");

        SpanLabel sp = new SpanLabel();
        ArrayList<Fournisseur> fournisseurs;
        fournisseurs = ServiceFournisseur.getInstance().getAllFournisseurs();
	
	TextField rech = new TextField("", "Rechercher un fournisseur");
        Container cn = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container cnToll = new Container(new BorderLayout());
        TextField zoneRecherche = new TextField();
        zoneRecherche.setHint("Rechercher par nom de societe");
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
                ArrayList<Fournisseur> l0 = ServiceFournisseur.getInstance().SearchByNomSociete(zoneRecherche.getText());
                for (int i = 0; i < l0.size(); i++) {
                    DefaultListModel model2 = new DefaultListModel();
                    model2.addItem(l0.get(i).getNomSociete());

                    List liste = new List(model2);
                    getStyle().setBgColor(0xffffff);
                    f.addComponent(liste);
                    f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
                    f.getToolbar().setTitle(("Resultats de la recherche"));
                    f.show();

                }

            }
        });       
        


        for (Fournisseur fer : fournisseurs) {
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label id = new Label("" + fer.getId());

            Container cCin = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label cinLabel = new Label("Cin du fournisseur:");
            cinLabel.getAllStyles().setFgColor(0x228B22);
            Label cin = new Label(Integer.toString(fer.getCin()));
            cCin.add(cinLabel);
            cCin.add(cin);
            c1.add(cCin);

            Container cNom = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label nomLabel = new Label("Nom de la société:");
            nomLabel.getAllStyles().setFgColor(0x228B22);
            Label nomSociete = new Label(fer.getNomSociete());
            cNom.add(nomLabel);
            cNom.add(nomSociete);
            c1.add(cNom);

            Container cEmail = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label emailLabel = new Label("Email:");
            emailLabel.getAllStyles().setFgColor(0x228B22);
            Label email = new Label(fer.getEmail());
            cEmail.add(emailLabel);
            cEmail.add(email);
            c1.add(cEmail);

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

            Container cFax = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label faxLabel = new Label("Fax:");
            faxLabel.getAllStyles().setFgColor(0x228B22);
            Label fax = new Label(Integer.toString(fer.getFax()));
            cFax.add(faxLabel);
            cFax.add(fax);
            c1.add(cFax);

            Button modifbtn = new Button("Modifier ce fournisseur");
            Button btnDeleteFournisseur = new Button("Supprimer ce fournisseur");

            Label espace = new Label(" "
                    + " "
                    + " ");

            //c1.add(id);
            c1.add(modifbtn);
            c1.add(btnDeleteFournisseur);
            c1.add(espace);

            add(c1);

            btnDeleteFournisseur.addActionListener((eeee) -> {

                if (Dialog.show("Confirmation", "Voulez-vous vraiment supprimer ce fournisseur ? ", "OK", "ANNULER")) {
                    String result = ServiceFournisseur.getInstance().DeleteFournisseur(fer);
                    if (!result.equals("Error")) {
                        Dialog.show("Success", result, "OK", null);
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } else {

                }
            });

            modifbtn.addActionListener((ej) -> {
                new UpdateFournisseurForm(current, Integer.parseInt(id.getText()),  Integer.parseInt(cin.getText()), Integer.parseInt(fax.getText()), email.getText(), adresse.getText(), Integer.parseInt(telephone.getText()), nomSociete.getText()).show();
            });

            // nomSociete.addPointerPressedListener(e -> new UpdateFournisseurForm(current, Integer.parseInt(id.getText()), Integer.parseInt(fax.getText()), email.getText(), adresse.getText(), Integer.parseInt(telephone.getText()), nomSociete.getText()).show());
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
