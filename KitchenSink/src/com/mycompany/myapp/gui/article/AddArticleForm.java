/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.Famille;
import com.mycompany.myapp.entities.Fournisseur;
import com.mycompany.myapp.services.ServiceArticle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author bhk
 */
public class AddArticleForm extends Form {

    ComboBox c = new ComboBox();
    public int code;
    public int id;

    public AddArticleForm(Form previous) {
        setTitle("Ajouter un nouveau article");
        setLayout(BoxLayout.y());

        Label l1 = new Label("Référence de l'article:");
        TextField tfRef = new TextField("", "Reference");
        Label l2 = new Label("Désignation:");
        TextField tfDes = new TextField("", "Designation");
        Label l3 = new Label("Code à barres:");
        TextField tfCode = new TextField("", "Code à barres");

        Label l4 = new Label("Unité:");
        ComboBox cbu = new ComboBox();
        cbu.addItem("milliltres");
        cbu.addItem("centilitres");
        cbu.addItem("litre");
        cbu.addItem("kg");
        Label l5 = new Label("Prix d'achat:");
        TextField tfPrix_ach = new TextField("", "Prix d'achat");
        Label l6 = new Label("Prix de vente:");
        TextField tfPrix_vente = new TextField("", "Prix de vente");
        Label l7 = new Label("Seuil minimum:");
        TextField tfSeuil_min = new TextField("", "Seuil minimum");
        Label l8 = new Label("Seuil maximum:");
        TextField tfSeuil_max = new TextField("", "Seuil maximum");
        Label l9 = new Label("Famille:");

        ComboBox cb = fillCombo();
        Label l10 = new Label("Fournisseur:");

        ComboBox cbF = fillCombof();

        Button btnValider = new Button("Ajouter l'article");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String val = (String) cb.getSelectedItem();
                String valeur = (String) cbF.getSelectedItem();
                ArrayList<Famille> f = ServiceArticle.getInstance().getCodeF(val);
                ArrayList<Fournisseur> four = ServiceArticle.getInstance().getID(valeur);

                for (Fournisseur ef : four) {

                    id = ef.getId();

                }
         
                for (Famille e : f) {

                    code = e.getCodeFamille();

                }
                

                try {
                    Article a = new Article(tfRef.getText(), tfDes.getText(), tfCode.getText(), Float.parseFloat(tfPrix_ach.getText()),
                            Float.parseFloat(tfPrix_vente.getText()), (String) cbu.getSelectedItem(), Integer.parseInt(tfSeuil_min.getText()),
                            Integer.parseInt(tfSeuil_max.getText()), 1, code);
                    if (ServiceArticle.getInstance().addArticle(a)) {
                        Dialog.show("Success", "Ajouté avec succès", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Field must be a number", new Command("OK"));
                }

//                }
            }
        });

        addAll(l1, tfRef, l2, tfDes, l3, tfCode, l5, tfPrix_ach, l6, tfPrix_vente, l4, cbu, l7, tfSeuil_min, l8, tfSeuil_max, l10, cbF, l9, cb, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public ComboBox fillCombo() {
        ComboBox cbf = new ComboBox();

        ArrayList<Famille> familles = ServiceArticle.getInstance().getAllFamilles();

        for (Famille f : familles) {
            cbf.addItem(f.getNomFamille());
        }

        return cbf;

    }

    public ComboBox fillCombof() {
        ComboBox cb = new ComboBox();

        ArrayList<Fournisseur> fournisseurs = ServiceArticle.getInstance().getAllFournisseurs();

        for (Fournisseur f : fournisseurs) {
            cb.addItem(f.getNomSociete());
        }

        return cb;

    }

}
