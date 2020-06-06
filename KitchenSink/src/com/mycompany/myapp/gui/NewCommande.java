/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import static com.codename1.io.Log.p;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.CommandeE;
import com.mycompany.myapp.entities.LigneCommande;
import com.mycompany.myapp.services.ServiceArticle;
import com.mycompany.myapp.services.ServiceCommandeClient;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class NewCommande extends Form {

    Image img = null;
    ImageViewer iv = null;
    EncodedImage ec;
    String url;
    Image mask;

    public NewCommande(Form f) {
        this.setLayout(BoxLayout.y());
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListeArticle(f).show());
        setTitle("Voici  votre commande ");

        ArrayList<CommandeE> cmd = ServiceCommandeClient.getInstance().getAllCommandes();

        //SpanLabel s= new SpanLabel(cmd);
        //add(s);
        Form hi = new Form(BoxLayout.y());

        for (CommandeE cm : cmd) {
            Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));

//
            Label L1 = new Label("Article: " + cm.getRef());
            Label L9 = new Label(cm.getRef());
            
            TextField tq = new TextField(Integer.toString(cm.getQte()));
//Label L = new Label("quantité à modifier ");
            Label L3 = new Label("Désignation:" + cm.getDesignation());
            Label L4 = new Label("Etat:" + cm.getEtat());
            Label L5= new Label("Montant");
            Button btn = new Button("Supprimer");
            Button btn1 = new Button("modifier");
            Label s= new Label ("=====================================");

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    LigneCommande L = new LigneCommande(Integer.parseInt(tq.getText()), L9.getText());
                    if (Dialog.show("Supression", "Voulez-vous vraiment supprimer cet article?", "Oui", "Annuler")) {
                        if (ServiceCommandeClient.getInstance().DeletecmdLigne(L9.getText())) {
                            new NewCommande(f).show();
                        }
                    } else {
                        hi.show();
                    }

                }
            });

            btn.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
            btn.getAllStyles().setFgColor(0x189fA5);
            c.addAll(L1, tq, L3, L4, btn, btn1,L5,s);

            add(c);

            btn1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    LigneCommande L = new LigneCommande(Integer.parseInt(tq.getText()), cm.getRef());

                    if ((tq.getText().length() == 0)) {
                        Dialog.show("Erreur", "Il faut choisir une quantite!", new Command("OK"));
                    } else {

                        if (ServiceCommandeClient.getInstance().UpdateLigne(L)) {
                            if (Dialog.show("modification", "Ligne de commande modifie avec succès", "ok", null)) {
                                new NewCommande(f).show();
                                
                            } else {
                                Dialog.show("ERROR", "Server error", new Command("OK"));
                            }
                            

                        }

                    }
                }
            });
            btn.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
            btn.getAllStyles().setFgColor(0x189fA5);

            btn1.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
            btn1.getAllStyles().setFgColor(0x189fA5);

        }

    }

}
