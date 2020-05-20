/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Allee;
import com.esprit.entities.Famille;
import com.esprit.services.ServiceAllee;
import com.esprit.services.ServiceFamille;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mac
 */
public class GestionFamille extends Form {

    public GestionFamille(Form previous) {
        setTitle("Listes des Familles");
//        
//        SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceAllee.getInstance().getAllAllee().toString());
//        add(sp);
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

        ArrayList<Famille> l = new ArrayList<>();
        l = ServiceFamille.getInstance().getAllFamille();

        Container cn = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Container cn11 = new Container(new BoxLayout(BoxLayout.X_AXIS));

      
//        cn.add(cnT);


            TextField AddFam = new TextField("", "Ajouter une Famille ");
            Button ok = new Button("ok");
          
            
            cn11.addAll(AddFam,ok);
          
            
        for (int i = 0; i < l.size(); i++) {
          
                Famille a = l.get(i);
                int nn = l.get(i).getCodeFamille();
                String nomF = l.get(i).getNomFamille();


                Font materialFont = FontImage.getMaterialDesignFont();
                int size = Display.getInstance().convertToPixels(6, true);
                materialFont = materialFont.derive(size, Font.STYLE_PLAIN);
                Button btnModif = new Button("Modifier");
                //btnModif.setIcon(FontImage.create  ("\uE161", btnModif.getUnselectedStyle(), materialFont));

                ImageViewer iv = new ImageViewer();

            
                Button btnSupp = new Button("Supprimer");

                Container cn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                cn1.add(nomF);
                cn1.add(btnModif);
                cn1.add(btnSupp);

                cn.add(cn1);

                btnModif.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Form fn = new Form("Modification l'Allée");

                        TextField tfL = new TextField();
                        Button conf = new Button("ok");
                        tfL.setText((String.valueOf(nn)));
                        fn.add(tfL);
                        fn.add(conf);

                        fn.show();
                        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

//                    conf.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent evt) {
//
//
//                            a.setNbTrav(Integer.parseInt(tfL.getText()));
//                            try {
//
//                                if (ServiceAllee.getInstance().updateAllee(a)) {
//                                    System.out.println(a.getNbTrav());
//                                    Dialog.show("Success", "Allée modifiée avec succèes", new Command("OK"));
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
//                        }
//                    });
                    }
                });

                btnSupp.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
//                     try {
//
//                                if (ServiceAllee.getInstance().deleteAllee(a)) {
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
//

                    }
                });
            

        }

        addAll(cn11,cn);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
