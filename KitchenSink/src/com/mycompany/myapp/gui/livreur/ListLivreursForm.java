/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.livreur;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
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
import com.mycompany.myapp.entities.Livreur;
import com.mycompany.myapp.services.ServiceFournisseur;
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

        TextField rech = new TextField("", "Rechercher un livreur");
        Container cn = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container cnToll = new Container(new BorderLayout());
        TextField zoneRecherche = new TextField();
        zoneRecherche.setHint("Rechercher par nom");
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
                ArrayList<Livreur> l0 = ServiceLivreur.getInstance().SearchByNom(zoneRecherche.getText());
                for (int i = 0; i < l0.size(); i++) {
                    DefaultListModel model2 = new DefaultListModel();
                    model2.addItem("Nom: "+l0.get(i).getNom());
                    model2.addItem("Prenom: "+l0.get(i).getPrenom());
                    model2.addItem("Ville: "+l0.get(i).getVille());
                    model2.addItem("Telephone: "+l0.get(i).getTelephone());

                    List liste = new List(model2);
                    getStyle().setBgColor(0xffffff);
                    f.addComponent(liste);
                    f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());
                    f.getToolbar().setTitle(("Resultats de la recherche"));
                    f.show();

                }

            }
        });

        for (Livreur lv : livreurs) {
            Form hi = new Form();
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label id = new Label("" + Integer.toString(lv.getId()));

            Container cNom = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label nomLabel = new Label("Nom");
            nomLabel.getAllStyles().setFgColor(0x228B22);
            Label nomSociete = new Label(lv.getNom());
            cNom.add(nomLabel);
            cNom.add(nomSociete);
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

            Button btnDeleteLivreur = new Button("Supprimer ce livreur");
            btnDeleteLivreur.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
            btnDeleteLivreur.getAllStyles().setFgColor(0x189fA5);

            Button btn = new Button("Afficher les détails");
            btn.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
            btn.getAllStyles().setFgColor(0x189fA5);

            c1.add(btn);
            c1.add(btnDeleteLivreur);
            c1.add(espace);
            add(c1);

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    Form hi = new Form();
                    hi.setTitle("Details de Mr " + lv.getNom());
                    Container c9 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                    Container c10 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L1 = new Label("Nom ");
                    TextField nom = new TextField(lv.getNom());
                    c10.add(L1);
                    c10.add(nom);
                    c9.add(c10);
                    Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L2 = new Label("Prenom:           ");
                    TextField Prenom = new TextField(lv.getPrenom());
                    c2.add(L2);
                    c2.add(Prenom);
                    c9.add(c2);
                    Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L3 = new Label("telephone:            ");
                    TextField tel = new TextField(Integer.toString(lv.getTelephone()));
                    c3.add(L3);
                    c3.add(tel);
                    c9.add(c3);
                    Container c4 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L4 = new Label("Ville:        ");
                    TextField ville = new TextField(lv.getVille());
                    c4.add(L4);
                    c4.add(ville);
                    c9.add(c4);

                    Button mod = new Button("modifier");
                    mod.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
                    mod.getAllStyles().setFgColor(0x189fA5);
                    mod.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            Livreur liv = new Livreur(lv.getId(), nom.getText(), prenom.getText(), ville.getText(), Integer.parseInt(tel.getText()));
                            if (ServiceLivreur.getInstance().modifierLivreur(liv)) {
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

            btnDeleteLivreur.addActionListener((eeee) -> {
                if (Dialog.show("Confirmation", "Voulez-vous vraiment supprimer ce livreur ? ", "OK", "ANNULER")) {
                    String result = ServiceLivreur.getInstance().DeleteLivreur(lv);
                    System.out.println("result= " + result);
                    if (!result.equals("Error")) {
                        Dialog.show("Success", result, "OK", null);
                        new ListLivreursForm(previous).show();
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
