/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.CommandeE;
import com.mycompany.myapp.entities.Palette;
import com.mycompany.myapp.services.ServiceCommande;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author aa
 */
public class ListCommandeForm extends Form {

    Form current;

    public ListCommandeForm(Form previous) throws ParseException {
        current = this;
        setTitle("Liste des commandes d'entree");
        ArrayList<CommandeE> cmd = ServiceCommande.getInstance().getAllCommande();

        //SpanLabel s= new SpanLabel(cmd);
        //add(s);
        Form hi = new Form();
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        for (CommandeE cm : cmd) {
            System.out.println("hii");
//

            Label L1 = new Label("Numero commande: " + cm.getNumCommande());

            Label L2 = new Label("Article: " + cm.getRef());

            Label L3 = new Label("Quantité: " + Integer.toString(cm.getQte()));

            Label L4 = new Label("Fournisseur: " + cm.getNomSociete());

            Label L5 = new Label("Date Livraison: " + cm.getDateCommande().toString());

            Label L7 = new Label("Montant: " + Float.toString(cm.getMontant()));

            Label L8 = new Label("Etat:" + cm.getEtat());

            Button btn = new Button("Afficher");
            btn.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
            btn.getAllStyles().setFgColor(0x189fA5);
            c.addAll(L1, L2, L3, L4, L5, L7, L8, btn);

//                    
//                    Label L13= new Label("Date Commande: 2020-01-25 ");
//                    Picker date= new Picker();
//                    String sDate1="2020-01-25";
//                    String sDate2="2020-01-30";
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    Form hi = new Form();
                    hi.setTitle("Details de la commande " + cm.getNumCommande());
                    Container c9 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                    Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L1 = new Label("Numero commande");
                    TextField tnum = new TextField(cm.getNumCommande());
                    c1.add(L1);
                    c1.add(tnum);
                    c9.add(c1);
                    Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L2 = new Label("Article:           ");
                    TextField tarticle = new TextField(cm.getRef());
                    c2.add(L2);
                    c2.add(tarticle);
                    c9.add(c2);
                    Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L3 = new Label("Quantité:            ");
                    TextField tq = new TextField(Integer.toString(cm.getQte()));
                    c3.add(L3);
                    c3.add(tq);
                    c9.add(c3);
                    Container c4 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L4 = new Label("Fournisseur:        ");
                    TextField tf = new TextField(cm.getNomSociete());
                    c4.add(L4);
                    c4.add(tf);
                    c9.add(c4);

                    Container c5 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L5 = new Label("Date Livraison:     ");
                    Picker date = new Picker();
                    // TextField date = new TextField(cm.getDateCommande().toString());
                    c5.add(L5);
                    c5.add(date);
                    c9.add(c5);

                    Container c6 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L7 = new Label("Montant:                ");
                    TextField tm = new TextField(Float.toString(cm.getMontant()));
                    c6.add(L7);
                    c6.add(tm);
                    c9.add(c6);
                    Container c7 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label L8 = new Label("Etat:                      ");
                    // TextField te = new TextField(cm.getEtat());
                    ComboBox cmb = new ComboBox("en attente", "confirmée", "annulée");
                    c7.add(L8);
                    c7.add(cmb);
                    c9.add(c7);
//
                    Button btn = new Button("Modifier");
                    btn.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
                    btn.getAllStyles().setFgColor(0x189fA5);
                    c9.add(btn);

                    hi.add(c9);
                      hi.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

                    hi.show();
                    
                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                                    Date value = (Date) date.getValue();

                            CommandeE cmde= new CommandeE((cm.getId()),Integer.parseInt(tq.getText()) , cm.getRef(), cm.getNumCommande(),  cmb.getSelectedItem().toString(), value, Float.parseFloat(tm.getText()));
                        }
                    });
                }
            });
            
        }
        add(c);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
