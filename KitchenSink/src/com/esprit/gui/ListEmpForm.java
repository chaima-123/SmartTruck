/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.codescan.CodeScanner;
import com.codename1.codescan.ScanResult;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
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
import com.esprit.entities.Allee;
import com.esprit.entities.Emplacement;
import com.esprit.services.ServiceAllee;
import com.esprit.services.ServiceEmp;
import java.util.ArrayList;

/**
 *
 * @author mac
 */
public class ListEmpForm extends Form {

    public ListEmpForm(Form previous) {
        setTitle("Listes des Emplacements");
//        
//        SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceAllee.getInstance().getAllAllee().toString());
//        add(sp);
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

        ArrayList<Emplacement> l = new ArrayList<>();

        l = ServiceEmp.getInstance().getAllEmp();

        Container cn = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//        Container cntitle = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//        Container cnT = new Container(new BoxLayout(BoxLayout.X_AXIS));

//            Label nAl = new Label("Allee");
//            Label Nniv = new Label("Niveau");
//             Label nTrav = new Label("Nb Trav");
//             
//             cnT.add(nAl); cnT.add(Nniv); cnT.add(nTrav);
//        cn.add(cnT);
// 
        TextField rech = new TextField("", "Rechercher un emplacement");

        //cn.add(rech);
        Container cnToll = new Container(new BorderLayout());
        TextField zoneRecherche = new TextField();
        zoneRecherche.setHint("Rechercher");
        Button boutonRecherche = new Button("ok");
        cnToll.addComponent(BorderLayout.CENTER, zoneRecherche);
        cnToll.addComponent(BorderLayout.EAST, boutonRecherche);
        Toolbar toolbar = new Toolbar();
        setToolbar(toolbar);
        toolbar.setTitleComponent(cnToll);

        Command quitter = new Command("Quitter");
        toolbar.addCommandToSideMenu(quitter);

        boutonRecherche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("iiii");
                Form f = new Form();

                Command quitter = new Command("Quitter");
                toolbar.addCommandToSideMenu(quitter);
                cn.setScrollableY(true);
                ArrayList<Emplacement> l2 = ServiceEmp.getInstance().SearchByEtat(zoneRecherche.getText());
                for (int i = 0; i < l2.size(); i++) {
                    System.out.println(l2.get(i).getCodeEmp());
                    DefaultListModel model2 = new DefaultListModel();
                    model2.addItem(l2.get(i).getIntitule() + "  " + l2.get(i).getEtat());

                    List liste = new List(model2);
                    getStyle().setBgColor(0xffffff);
                    f.addComponent(liste);
                    f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
                    f.getToolbar().addCommandToSideMenu(quitter);
                    f.getToolbar().setTitle(("Resultat de la recherche"));
                    f.show();

                }

            }
        });

        for (int i = 0; i < l.size(); i++) {
            Emplacement s = l.get(i);

//            DefaultListModel model = new DefaultListModel();
//            model.addItem(l.get(i).getIntitule());
//
//            List listePays = new List(model);
//            //  listePays.
//            listePays.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//                    Form f = new Form();
//                    f.show();
//
//                }
//            });
//            cn.addComponent(listePays);
            Label emp = new Label("Code emlacement: " + l.get(i).getIntitule());
            Label niv = new Label();
            niv.setText(String.valueOf("Etat: " + l.get(i).getEtat()));
            Button Details = new Button("Afficher les détails");

  Font mediumBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);

            Container cn1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            cn1.add(createForFont(mediumBoldSystemFont, emp));
            cn1.add(createForFont(mediumBoldSystemFont,niv));
            cn1.add(Details);
            cn.add(cn1);
            
          //  Details.getAllStyles().setBorder(Border.createGrooveBorder(CENTER,0xe16702),focusScrolling);
            Details.getAllStyles().setFgColor(0xe16702);
            Details.getAllStyles().setBackgroundGradientEndColor(0xe16702);

            Details.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    Form fn = new Form("Détails de l'emplacement");

                    Label emp = new Label("Code de L'emplacement: " + s.getIntitule());
                    Label intit = new Label("Intitulé: " + s.getCodeEmp());

                    Label niv = new Label();
                    niv.setText(String.valueOf("Etat: " + s.getEtat()));
                    fn.add(emp);
                    fn.add(intit);
                    fn.add(niv);
                    fn.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

                    fn.show();

                }
            });
        }

//        cn.setScrollableY(true);
//        
//        DefaultListModel model=new DefaultListModel(); 
//        model.addItem("République dominicaine");
//        model.addItem("Japon");
//      
//        List listePays=new List(model);
//        listePays.setFixedSelection(List.FIXED_NONE_CYCLIC);
//        cn.addComponent(listePays); 
        add(cn);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
    
    
     private Label createForFont(Font fnt, Label s) {
        //Label l = new Label(s);
        s.getUnselectedStyle().setFont(fnt);
        return s;
    }
}
