/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.fournisseur;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
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

        //cn.add(rech);
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
                    model2.addItem("Nom de la societe: "+l0.get(i).getNomSociete());
                    model2.addItem("Cin du fournisseur: "+l0.get(i).getCin());
                    model2.addItem("Adresse du fournisseur: "+l0.get(i).getAdresse());
                    model2.addItem("E-mail du fournisseur: "+l0.get(i).getEmail());
                    model2.addItem("Telephone du fournisseur: "+l0.get(i).getTelephone());
                    model2.addItem("Fax du fournisseur: "+l0.get(i).getFax());

                    List liste = new List(model2);
                    getStyle().setBgColor(0xffffff);
                    f.addComponent(liste);
                    f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());
                    f.getToolbar().setTitle(("Resultats de la recherche"));
                    f.show();

                }

            }
        });

        for (Fournisseur fer : fournisseurs) {
            Form hi = new Form();
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label id = new Label("" + Integer.toString(fer.getId()));

            Container cNom = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label nomLabel = new Label("Nom de la société:");
            nomLabel.getAllStyles().setFgColor(0x228B22);
            Label nomSociete = new Label(fer.getNomSociete());
            cNom.add(nomLabel);
            cNom.add(nomSociete);
            c1.add(cNom);

            Container cCin = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label cinLabel = new Label("Cin du fournisseur:");
            cinLabel.getAllStyles().setFgColor(0x228B22);
            Label cin = new Label(Integer.toString(fer.getCin()));
            cCin.add(cinLabel);
            cCin.add(cin);
            c1.add(cCin);

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

            Button btnDeleteFournisseur = new Button("Supprimer ce fournisseur");
            btnDeleteFournisseur.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
            btnDeleteFournisseur.getAllStyles().setFgColor(0x189fA5);
            Button btn = new Button("Afficher les détails");
            btn.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
            btn.getAllStyles().setFgColor(0x189fA5);

            Label espace = new Label(" "
                    + " "
                    + " ");

            c1.add(btn);
            c1.add(btnDeleteFournisseur);
            c1.add(espace);

            add(c1);

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    Form hi = new Form();
                    hi.setTitle("Details de " + fer.getNomSociete());
                    Container c9 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                    Container c10 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L1 = new Label("Nom societe");
                    TextField nomSociete = new TextField(fer.getNomSociete());
                    c10.add(L1);
                    c10.add(nomSociete);
                    c9.add(c10);
                    Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L2 = new Label("cin:           ");
                    TextField cin = new TextField(Integer.toString(fer.getCin()));
                    c2.add(L2);
                    c2.add(cin);
                    c9.add(c2);
                    Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L3 = new Label("telephone:            ");
                    TextField tel = new TextField(Integer.toString(fer.getTelephone()));
                    c3.add(L3);
                    c3.add(tel);
                    c9.add(c3);
                    Container c4 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L4 = new Label("fax:        ");
                    TextField fax = new TextField(Integer.toString(fer.getFax()));
                    c4.add(L4);
                    c4.add(fax);
                    c9.add(c4);

                    Container c6 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L7 = new Label("email:                ");
                    TextField email = new TextField(fer.getEmail());
                    c6.add(L7);
                    c6.add(email);
                    c9.add(c6);
                    Container c7 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L8 = new Label("adresse:                      ");
                    TextField adresse = new TextField(fer.getAdresse());
                    c7.add(L8);
                    c7.add(adresse);
                    c9.add(c7);

                    Button mod = new Button("modifier");
                    mod.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
                    mod.getAllStyles().setFgColor(0x189fA5);
                    mod.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            Fournisseur fr = new Fournisseur(fer.getId(), Integer.parseInt(cin.getText()), email.getText(), adresse.getText(), Integer.parseInt(tel.getText()), Integer.parseInt(fax.getText()), nomSociete.getText());
                            if (ServiceFournisseur.getInstance().modifierFournisseur(fr)) {
                                Dialog.show("ERREUR", "Modification echouee", "OK", null);

                            } else {
                                Dialog.show("Success", "Modification avec succes", "OK", null);
                            }

                        }
                    });

                    hi.add(c9);
                    hi.add(mod);
                    hi.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());
                    hi.show();
                }
            });

            btnDeleteFournisseur.addActionListener((eeee) -> {

                if (Dialog.show("Confirmation", "Voulez-vous vraiment supprimer ce fournisseur ? ", "OK", "ANNULER")) {
                    String result = ServiceFournisseur.getInstance().DeleteFournisseur(fer);
                    if (!result.equals("Error")) {
                        Dialog.show("Success", result, "OK", null);
                        new ListFournisseursForm(previous).show();
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } else {

                }
            });
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
