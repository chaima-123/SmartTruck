/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
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
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
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
public class AffecterFamille extends Form {

    public AffecterFamille(Form previous) {
            setTitle("Oragnisation des emplacements Par Famille");

            Container cn = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            ComboBox comboFam = new ComboBox();
            ComboBox comboAllee = new ComboBox();
            Label l1 = new Label("Sélectionner la famille");
            Label l2 = new Label("Choisissez la ligne de l'allée");
            // SpanLabel info= new SpanLabel ("Veuillez choisir les emplacemets à reserver par famille");
            Label l3 = new Label("Nombre de travées pour cet l'allée");
            Label l4 = new Label("Nombre de niveaux");

            Button btnAffecter = new Button("Affecter");

            ArrayList<Famille> familles = ServiceFamille.getInstance().getAllFamille();
            for (Famille p : familles) {
                comboFam.addItem(p.getNomFamille());

            }

            comboAllee.getStyle().setMargin(LEFT, TOP);

            ArrayList<Allee> l = new ArrayList<>();

            l = ServiceAllee.getInstance().getAllAllee();
            for (Allee p : l) {
                comboAllee.addItem(p.getLigne());
                //   nbTrav = p.getNbTrav();

            }

            TextField nbT = new TextField();
            TextField nbN = new TextField();
            SpanLabel n = new SpanLabel();

            comboAllee.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    String A = comboAllee.getSelectedItem().toString();

                    nbT.setText("4");
                    nbT.setEditable(false);
                    nbN.setText("3");
                    nbN.setEditable(false);
                    n.setText("Soit 50 emplacements ");

                }
            });
            btnAffecter.getAllStyles().setFgColor(0xe16702);
                    btnAffecter.getAllStyles().setBackgroundGradientEndColor(0xe16702);

            btnAffecter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    String nomF = comboFam.getSelectedItem().toString();
                                    Dialog.show("Success", "Emplacements afféctées avec succèes", new Command("OK"));

                }
            });

            comboFam.setWidth(50);

            Font mediumItalicMonospaceFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);

            ImageViewer iv = new ImageViewer();

            Style s = UIManager.getInstance().getComponentStyle("Title");

            FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_INFO, s);

            n.setIcon(icon);
            n.setIconPosition(BorderLayout.WEST);
            cn.add(l1);

            cn.add(comboFam);
            cn.add(l2);
            cn.add(comboAllee);
            cn.add(l3);
            cn.add(nbT);

            cn.add(l4);
            cn.add(nbN);
            cn.add(n);
            cn.add(btnAffecter);

            add(cn);

            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        
    }

    private Label createForFont(Font fnt, Label s) {
        //Label l = new Label(s);
        s.getUnselectedStyle().setFont(fnt);
        return s;
    }

}
