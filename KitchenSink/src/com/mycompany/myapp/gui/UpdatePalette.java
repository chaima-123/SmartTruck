/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
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
import com.mycompany.myapp.entities.Fournisseur;
import com.mycompany.myapp.entities.Palette;
import com.mycompany.myapp.services.ServiceFournisseur;
import com.mycompany.myapp.services.ServicePalette;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class UpdatePalette extends Form {

    Form current;

    public UpdatePalette(Form previous,int n,int q,Date d,String co) {

        current = this;
        // Form hi = new Form();
                   setTitle("Information de la palette numero " + n);

                    Label lqte = new Label("Quantite: ");
                    TextField tfName = new TextField(""+q);
                   // tfName.setText(Integer.toString(p.getQte()));
                    Label ldate = new Label("Date d'expiration: ");
                    Picker dateP = new Picker();
                    dateP.setDate(d);
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
                   
                    Button btnM = new Button("Modifier");
                    btnM.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
                    btnM.getAllStyles().setFgColor(0x189fA5);

                    Button btnS = new Button("Supprimer");
                    btnS.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
                    btnS.getAllStyles().setFgColor(0x189fA5);
                           Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                    addAll(lqte, tfName, larticle, cmbArticle, lcode, cmbCode, ldate, dateP, btnS);
                    add(btnM);

                    Palette P = new Palette(n, Integer.parseInt(tfName.getText()), (Date) dateP.getValue(), (String) cmbArticle.getSelectedItem(), (String) cmbCode.getSelectedItem());
btnM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                                       //  System.out.println("ok");
                                        ServicePalette.getInstance().UpdatePalette(P);
   
            }
        });
//                    btnM.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent evt) {
//                            System.out.println("ok");
//                            if ((tfName.getText().length() == 0) || dateP.getValue().toString().length() == 0) {
//                                Dialog.show("Erreur", "Tous les champs sont obligatoires", new Command("OK"));
//                            } else {
//                                if (ServicePalette.getInstance().UpdatePalette(P)) {
//
//                                    Dialog.show("Modification", "Palette modifiee avec succes", "ok", null);
//
//                                } else {
//                                    Dialog.show("ERROR", "Server error", new Command("OK"));
//                                }
//
//                            }
//                        }
//                    });

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

                            } 
                        }
                    });

                    //hi.add(c1);

//                    hi.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent evt) {
//                            try {
//                                new ListTasksForm(current).show();
//                            } catch (ParseException ex) {
//                            }
//                        }
//                    });
                   // hi.show();
                           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ee -> previous.showBack());

    }
}