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
import com.codename1.ui.Calendar;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
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
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.Emplacement;
import com.mycompany.myapp.entities.Palette;
import com.mycompany.myapp.services.ServicePalette;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.Vector;

public class ListTasksForm extends Form {

    Form current;

    public ListTasksForm(Form previous) throws ParseException {
        current = this;
        setTitle("Liste des palettes");

        ArrayList<Palette> palettes = null;
        palettes = ServicePalette.getInstance().getAllPAlette();
        Form hi = new Form();
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        for (Palette p : palettes) {
            Label l = new Label("Numero de lot: " + Integer.toString(p.getNum_lot()));

            Label l1 = new Label("Quantite: " + Integer.toString(p.getQte()));
            Label l2 = new Label("Reference Article: " + p.getRef());
            Label l3 = new Label("Date d'expiration: " + p.getDate_expiration());
            Label l4 = new Label("Code emplacement: " + p.getCodeEmp());
            Button btnMod = new Button("Afficher");
            btnMod.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
            btnMod.getAllStyles().setFgColor(0x189fA5);
            c.add(l);
            c.add(l1);
            c.add(l2);
            c.add(l3);
            c.add(l4);
            c.add(btnMod);
            // add(c);

            btnMod.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Form hi = new Form();
                    hi.setTitle("Information de la palette numero " + p.getNum_lot());
                    Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                    Label lqte = new Label("Quantite: ");
                    TextField tfName = new TextField(""+(Integer.toString(p.getQte())));
                   // tfName.setText(Integer.toString(p.getQte()));
                    Label ldate = new Label("Date d'expiration: ");
                    Picker dateP = new Picker();
                    dateP.setDate(p.getDate_expiration());
                    // Date value = (Date) dateP.getValue();
                    Label larticle = new Label("Article: ");

                    ComboBox cmbArticle = new ComboBox();
                    ComboBox cmbCode = new ComboBox();

                    Label lcode = new Label("Emplacement: ");

                    ArrayList<Article> articles = ServicePalette.getInstance().getAllArticle();
                    for (Article p : articles) {
                        cmbArticle.addItem(p.getRefArticle());
                    }

                    ArrayList<Emplacement> emps = ServicePalette.getInstance().getAllEmp();
                    for (Emplacement p : emps) {
                        cmbCode.addItem(p.getCodeEmp());
                        //System.out.println(p.getCodeEmp());
                    }
                    cmbArticle.setSelectCommandText(p.getRef());
                    cmbCode.setSelectCommandText(p.getCodeEmp());

                    Button btnM = new Button("Modifier");
                    btnM.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
                    btnM.getAllStyles().setFgColor(0x189fA5);

                    Button btnS = new Button("Supprimer");
                    btnS.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
                    btnS.getAllStyles().setFgColor(0x189fA5);

                    Palette P = new Palette((p.getNum_lot()), Integer.parseInt(tfName.getText()), (Date) dateP.getValue(), (String) cmbArticle.getSelectedItem(), (String) cmbCode.getSelectedItem());

                    btnM.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            if ((tfName.getText().length() == 0) || dateP.getValue().toString().length() == 0) {
                                Dialog.show("Erreur", "Tous les champs sont obligatoires", new Command("OK"));
                            } else {
                                if (ServicePalette.getInstance().UpdatePalette(P)) {

                                    Dialog.show("Modification", "Palette modifiee avec succes", "ok", null);

                                } else {
                                    Dialog.show("ERROR", "Server error", new Command("OK"));
                                }

                            }
                        }
                    });

                    btnS.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            if (Dialog.show("Supression", "Voulez-vous vraiment supprimer cette palette?", "Oui", "Annuler")) {
                                if (ServicePalette.getInstance().DeletePalette(P)) {
                                    try {
                                        new ListTasksForm(current).show();
                                    } catch (ParseException ex) {
                                    }
                                } else {
                                    Dialog.show("ERROR", "Server error", new Command("OK"));
                                }

                            } else {
                                hi.show();
                            }

                        }
                    });

                    c1.addAll(lqte, tfName, larticle, cmbArticle, lcode, cmbCode, ldate, dateP, btnM, btnS);
                    hi.add(c1);

                    hi.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            try {
                                new ListTasksForm(current).show();
                            } catch (ParseException ex) {
                            }
                        }
                    });
                    hi.show();

                }

            });

        }

        add(c);
        //show();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
