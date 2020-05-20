/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.esprit.entities.Allee;
import com.esprit.services.ServiceAllee;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mac
 */
public class ListAlleeForm extends Form {

    public ListAlleeForm(Form previous) {
        setTitle("Listes des Allées");
//        
//        SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceAllee.getInstance().getAllAllee().toString());
//        add(sp);
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

        ArrayList<Allee> l = new ArrayList<>();
        l = ServiceAllee.getInstance().getAllAllee();

        Container cn = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container cntitle = new Container(new BoxLayout(BoxLayout.Y_AXIS));

//            Label nAl = new Label("Ligne de l'allée: ");
//           Label Nniv = new Label("Nombre de niveaux par travée: ");
//           Label nTrav = new Label("Nombre de Travée : ");
//             
//             cnT.add(nAl); cnT.add(Nniv); cnT.add(nTrav);
//        cn.add(cnT);
        for (int i = 0; i < l.size(); i++) {
            Allee a = l.get(i);
            int nn = l.get(i).getNbTrav();
            int id = l.get(i).getId();
            Label lig = new Label("Ligne de l'allée: " + l.get(i).getLigne());
            Label niv = new Label();
            Label tr = new Label("Nombre de travées: " + String.valueOf(l.get(i).getNbTrav()));

            Container cnx = new Container(new BoxLayout(BoxLayout.X_AXIS));

            Button btnModif = new Button("Modifier");
            Button btnSupp = new Button("Supprimer");
            cnx.add(btnModif);
            cnx.add(btnSupp);
            btnModif.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0xe16702), focusScrolling);
            btnModif.getAllStyles().setFgColor(0xe16702);
            btnModif.getAllStyles().setBackgroundGradientEndColor(0xe16702);

            btnSupp.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0xe16702), focusScrolling);
            btnSupp.getAllStyles().setFgColor(0xe16702);
            btnSupp.getAllStyles().setBackgroundGradientEndColor(0xe16702);

            niv.setText(String.valueOf("Nb de niveaux par travée: " + l.get(i).getNiveau()));
            
         Font mediumBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);


            Container cn1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            cn1.add(createForFont(mediumBoldSystemFont, lig));
            cn1.add(createForFont(mediumBoldSystemFont,tr));
            cn1.add(createForFont(mediumBoldSystemFont,niv));
            cn1.add(cnx);
            
            

            //  cn.add(niv.horizontalSpan(2));
            cn.add(cn1);

            btnModif.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Form fn = new Form("Modification l'Allée");

                    TextField tfL = new TextField();
                    Button conf = new Button("Confirmer");
                    SpanLabel i = new SpanLabel("Indiquer le nombre de travées,notez bien que "
                            + ""
                            + "ceci va engendrez des modifications au niveau des emplacements");

                    conf.getAllStyles().setFgColor(0xe16702);
                    conf.getAllStyles().setBackgroundGradientEndColor(0xe16702);
                      Style s = UIManager.getInstance().getComponentStyle("Title");

            FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_INFO, s);


            i.setIcon(icon);
            i.setIconPosition(BorderLayout.WEST);

                    tfL.setText((String.valueOf(nn)));

                    fn.add(i);
                    fn.add(tfL);
                    fn.add(conf);
                    fn.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

                    fn.show();

                    conf.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {

                            a.setNbTrav(Integer.parseInt(tfL.getText()));
                            try {

                                if (ServiceAllee.getInstance().updateAllee(a)) {
                                    System.out.println(a.getNbTrav());
                                    Dialog.show("Success", "Allée modifiée avec succèes", new Command("OK"));
                                    previous.showBack();

                                } else {
                                    Dialog.show("ERROR", "Erreur de modification", new Command("OK"));
                                }

                            } catch (NumberFormatException e) {
                                System.out.println(e.getMessage());
                                Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                            }
                        }
                    });

                }
            });

            btnSupp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
//                     try {
//                                if (ServiceAllee.getInstance().deleteAllee(a)) {
//                                    
//                                    
//                                    System.out.println(a.getNbTrav());
//                                    
//                                    Dialog.show("Success", "Allée supprimée avec succèes", new Command("OK"));
//                                 previous.showBack();
//                                    
//                                } else {
//                                    Dialog.show("ERROR", "Erreur de modification", new Command("OK"));
//                                }
//
//                            } catch (NumberFormatException e) {
//                                System.out.println(e.getMessage());
//                                Dialog.show("ERROR", "Status must be a number", new Command("OK"));
//                            }

                    if(Dialog.show("Confirmation", "Voulez-vous vraiment supprimer cet allée ?", "OK",
                            "Annuler"))
                    {
                         if (ServiceAllee.getInstance().deleteAllee(a)) {
                                    
                                                                      
                                    Dialog.show("Success", "Allée supprimée avec succèes", new Command("OK"));
                                 previous.showBack();
                                    
                                } else {
                                    Dialog.show("ERROR", "Erreur de modification", new Command("OK"));
                                }
                    }
                    
                    else{}
                    

                }
            });

        }

        add(cn);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
    
    
    
      private Label createForFont(Font fnt, Label s) {
        //Label l = new Label(s);
        s.getUnselectedStyle().setFont(fnt);
        return s;
    }
}
