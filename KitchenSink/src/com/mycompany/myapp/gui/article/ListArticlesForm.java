package com.mycompany.myapp.gui;

import com.codename1.ui.Button;

import com.codename1.ui.ComboBox;

import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;

import com.codename1.ui.Label;
import com.codename1.ui.TextField;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.Famille;
import com.mycompany.myapp.entities.Fournisseur;
import com.mycompany.myapp.services.ServiceArticle;

import java.util.ArrayList;

/**
 *
 * @author nannn
 */
public class ListArticlesForm extends Form {

    private Form current;
    public Form previous;
    private Resources theme;
    public String nom;
    public String nomS;

    public ListArticlesForm() {

    }

    public ListArticlesForm(Form current, Form previous) {
//        if(current != null){
//            current.show();
//            return;
//        }

        Form h = new Form("Liste des articles", BoxLayout.y());
        h.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        //setTitle("List articles");
        ArrayList<Article> art = ServiceArticle.getInstance().getAllArticles();
        for (Article e : art) {
            String des = e.getDesignation();
            Label l = new Label(des);

            System.out.println(l);
            Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container c3 = new Container(new FlowLayout(CENTER, CENTER));
            Button supp = new Button("Supprimer");

            Button modif = new Button("Modifier");
            c1.add(l);
            c1.add(supp);
            c1.add(modif);

            h.add(c1);
            h.show();

            modif.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Form h4 = new Form("Modifier article", BoxLayout.y());
                    h4.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {

                        h.showBack();

                    });
                    TextField tfRef = new TextField("", "Reference");
                    TextField tfDes = new TextField("", "Designation");
                    TextField tfCode = new TextField("", "Code à barres");

                    TextField tfPrix_ach = new TextField("", "Prix d'achat");
                    TextField tfPrix_vente = new TextField("", "Prix de vente");
                    TextField tfSeuil_min = new TextField("", "Seuil minimum");
                    TextField tfSeuil_max = new TextField("", "Seuil maximum");

                    Button btnModifier = new Button("Modifier l'article");
                    Label l1 = new Label("Référence de l'article:");

                    Label l2 = new Label("Désignation:");

                    Label l3 = new Label("Code à barres:");

                    Label l4 = new Label("Unité:");

                    Label l5 = new Label("Prix d'achat:");

                    Label l6 = new Label("Prix de vente:");

                    Label l7 = new Label("Seuil minimum:");

                    Label l8 = new Label("Seuil maximum:");

                    Label l9 = new Label("Famille:");

                    Label l10 = new Label("Fournisseur:");

                    tfDes.setText("Eau_05");
                    tfCode.setText("7412589963");

                    tfPrix_ach.setText("200");
                    tfPrix_vente.setText("600");
                    tfRef.setText("REF147");
                    tfSeuil_min.setText("200");
                    tfSeuil_max.setText("4500");

                    ComboBox cb = new ComboBox();
                    cb.addItem("safia");
                    cb.addItem("delice");
                    cb.addItem("vitalait");
                    cb.addItem("Jannet");
                    ComboBox cbf = new ComboBox();
                    cbf.addItem("eaux");
                    cbf.addItem("lait");
                    cbf.addItem("jus");
                    cbf.addItem("boissons_gazeuses");
                    ComboBox cbu = new ComboBox();
                    cbu.addItem("milliltres");
                    cbu.addItem("centilitres");
                    cbu.addItem("litre");
                    cbu.addItem("kg");

                    Container c4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    c4.add(l1);
                    c4.add(tfDes);
                    c4.add(l2);
                    c4.add(tfRef);
                    c4.add(l3);
                    c4.add(tfCode);
                    c4.add(l5);
                    c4.add(tfPrix_ach);
                    c4.add(l6);
                    c4.add(tfPrix_vente);
                    c4.add(l4);
                    c4.add(cbu);
                    c4.add(l7);
                    c4.add(tfSeuil_min);
                    c4.add(l8);
                    c4.add(tfSeuil_max);
                    c4.add(l9);
                    c4.add(cbf);
                    c4.add(l10);
                    c4.add(cb);
                    c4.add(btnModifier);

                    h4.add(c4);
                    h4.show();

//                    btnModifier.addPointerPressedListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent evt) {
//                       Dialog.setDefaultBlurBackgroundRadius(8);
//
//btnModifier.addActionListener((e) -> Dialog.show("", "", "OK", null));
//                        h4.add(btnModifier);
//                        h4.show();}
//                    });
                    btnModifier.addActionListener((e) -> Dialog.show("", "Modifié avec succès", "OK", null));

                    // h4.add(btnModifier);
                }

            });

            supp.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    if (Dialog.show("Etes-vous sure de supprimer ", "cet article?", "Oui", "Non")) {
                        ServiceArticle.getInstance().deleteArticle(des);

                        c1.remove();

                        h.show();
                    } else {

                        h.show();
                    }

                }

            });

//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
            l.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    String product = l.getText();

                    Form details = new Form("Details de l'article");
                    ArrayList<Article> art = ServiceArticle.getInstance().getArticle(product);
                    details.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {

                        h.showBack();

                    });

                    for (Article e : art) {

                        Label l1 = new Label("Code à barres:" + e.getCode());
                        Label l2 = new Label("Référence:" + e.getRef_article());
                        Label l3 = new Label("Unité:" + e.getUnité());

                        Label l4 = new Label("Prix d'achat:" + String.valueOf(e.getPrix_achat()));
                        Label l5 = new Label("Prix de vente:" + String.valueOf(e.getPrix_vente()));
                        Label l6 = new Label("Seuil minimum:" + String.valueOf(e.getSeuil_max()));
                        Label l7 = new Label("Seuil maximum:" + String.valueOf(e.getSeuil_min()));
                        l1.getAllStyles().setFgColor(0x81A9DD);
                        l2.getAllStyles().setFgColor(0x81A9DD);
                        l3.getAllStyles().setFgColor(0x81A9DD);
                        l4.getAllStyles().setFgColor(0x81A9DD);
                        l5.getAllStyles().setFgColor(0x81A9DD);
                        l6.getAllStyles().setFgColor(0x81A9DD);
                        l7.getAllStyles().setFgColor(0x81A9DD);

                        int code = e.getFamille();
                        ArrayList<Famille> f = ServiceArticle.getInstance().getNomFamille(code);

                        for (Famille fam : f) {

                            nom = fam.getNomFamille();

                        }
                        Label l8 = new Label("Famille:" + nom);
                        l8.getAllStyles().setFgColor(0x81A9DD);
                        int id = e.getFournisseur();
                        ArrayList<Fournisseur> fr = ServiceArticle.getInstance().getNomSociete(id);

                        for (Fournisseur four : fr) {

                            nomS = four.getNomSociete();

                        }
                        Label l9 = new Label("Fournisseur:" + nomS);
                        l9.getAllStyles().setFgColor(0x81A9DD);

                        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                        c2.add(l1);
                        c2.add(l2);
                        c2.add(l3);
                        c2.add(l4);
                        c2.add(l5);
                        c2.add(l6);
                        c2.add(l7);
                        c2.add(l8);
                        c2.add(l9);
                        details.add(c2);
                        details.show();
                    }

                }
            });
        }

    }

}
