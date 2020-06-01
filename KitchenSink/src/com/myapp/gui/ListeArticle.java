/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
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
import com.mycompany.myapp.services.ServiceArticle;
import java.io.IOException;
import com.mycompany.myapp.entities.Article;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListeArticle extends Form {

    Image img = null;
    ImageViewer iv = null;
    EncodedImage ec;
    String url;
    Image mask;

    public ListeArticle() {
        this.setLayout(BoxLayout.y());
        this.setTitle("Liste Article");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> this.showBack());
        removeAll();
        display();
    }
    
    public void display()
    {
         Container c1 = new Container(BoxLayout.x());
        // Label rechercher = new Label("Rechercher");
        TextField rechercheTF = new TextField("", "Famille");
          Button search = new Button("Search");
        c1.addAll(/*rechercher*/search, rechercheTF);
        
        add(c1);
           ArrayList<Article> listearticle1 = ServiceArticle.getInstance().getAllArticles();
                //System.out.println(famille);
                for (Article art : listearticle1) {
                    //  if (art.getFamille().getNonfamiile().equals(famille)) {
//                    System.out.println(art.getDesignation());
                    //code image
                    Container cImages = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    try {
                        //System.out.println("picture name " + art.getImg());
                        System.out.println(art.getImg());
                        url = "http://localhost/pidev/web/" + art.getImg();
                        ec = EncodedImage.create("/users.jpg");
                          } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                        Image img = URLImage.createToStorage(ec, url, url, URLImage.RESIZE_SCALE);
                        iv = new ImageViewer(URLImage.createToStorage(ec, url, url, URLImage.RESIZE_SCALE));

                        float prx = art.getPrix_vente();
                        TextField tf = new TextField("", "Quantité");
                        Button btn = new Button("Ajouter");
                        btn.addActionListener((evet) -> {
                            String ref = art.getRef_article();
                            String qte = tf.getText();
                            if(ServiceArticle.getInstance().addcommande(ref, qte)){
                              Dialog.show("Success", "commande ajoutée", new Command("OK"));}
                          
                            new ListeArticle().show();
                        });
                        System.out.println(prx);
                        Label designantion = new Label("designantion : " + art.getDesignation());
                        Label prix = new Label("prix de vente : " + art.getPrix_vente());
                        Container x = BoxLayout.encloseX(iv, BoxLayout.encloseY(BoxLayout.encloseX(btn, tf), designantion, prix));
                        add(x);
                  

                
                }
        search.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                ArrayList<Article> listearticle = ServiceArticle.getInstance().getAllArticles();
                String famille = rechercheTF.getText();
                //System.out.println(famille);
                for (Article art : listearticle) {
                    if(rechercheTF.getText().compareTo(art.getFamille().getNomFamille())==0){
                    //  if (art.getFamille().getNonfamiile().equals(famille)) {
//                    System.out.println(art.getDesignation());
                    //code image
                    Container cImages = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    try {
                        //System.out.println("picture name " + art.getImg());
                        System.out.println(art.getImg());
                        url = "http://localhost/pidev/web/" + art.getImg();
                        ec = EncodedImage.create("/users.jpg");
                          } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                        Image img = URLImage.createToStorage(ec, url, url, URLImage.RESIZE_SCALE);
                        iv = new ImageViewer(URLImage.createToStorage(ec, url, url, URLImage.RESIZE_SCALE));

                        float prx = art.getPrix_vente();
                        TextField tf = new TextField("", "Quantité");
                        Button btn = new Button("Ajouter");
                        btn.addActionListener((evet) -> {
                            String ref = art.getRef_article();
                            String qte = tf.getText();
                            if(ServiceArticle.getInstance().addcommande(ref, qte)){
                              Dialog.show("Success", "commande ajoutée", new Command("OK"));}
                          
                            new ListeArticle().show();
                        });
                        System.out.println(prx);
                        Label designantion = new Label("designantion : " + art.getDesignation());
                        Label prix = new Label("prix de vente : " + art.getPrix_vente());
                        Container x = BoxLayout.encloseX(iv, BoxLayout.encloseY(BoxLayout.encloseX(btn, tf), designantion, prix));
                        add(x);
                     Button affiche = new Button("Commandes");
                     c1.add(affiche);

                }
                }
            }
        }
        );
    }

}

//  public ListeArticle(Form previous) {
// ServiceArticle sa = new ServiceArticle
/*   String url = Article.getImg()+ "http://localhost/pidev/web/";
        try {
            ec = EncodedImage.create("/1.jpeg");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        iv = new ImageViewer(ec);

        img = URLImage.createToStorage(ec, url, url, URLImage.RESIZE_SCALE);
        iv = new ImageViewer(img);

        add(iv);



        
        setTitle("Liste dse articles ");
        
        SpanLabel sp = new SpanLabel();
        ServiceArticle.getInstance().getAllArticles();
        sp.setText(ServiceArticle.getInstance().getAllArticles().toString());
       
        
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
         
    }
}
 */

 /*public class ListeArticle extends SideMenuAdminForm {

    EncodedImage ec;
    String url;
    Image profilePic;
    Image mask;

    public ListeArticle(Resources res) {

        this.setLayout(BoxLayout.y());

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label("Users List", "Title")
        )
        );

        tb.setTitleComponent(titleCmp);
        setupSideMenu(res);


        Container cnAll = new Container(BoxLayout.y());
        Font lbl = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        ArrayList<Article> u = ServiceArticle.getInstance().getAllArticles();
        for (Article article : u) {
            Container cnone = new Container(BoxLayout.x());
        //    url = Statics + article.getImg();
            profilePic = URLImage.createToStorage(ec, url, url, URLImage.RESIZE_SCALE);
            mask = res.getImage("round-mask.png");
            profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
            Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
            profilePicLabel.setMask(mask.createMask());

            Container cnUser = new Container(BoxLayout.y());

           float prx = article.getPrix_vente();
                        Label designantion = new Label("designantion : " + article.getDesignation());
                        Label prix = new Label("prix de vente : " + article.getPrix_vente());

                        add(designantion, prix);
                        Button delete = new Button();


           /* email.getUnselectedStyle().setFgColor(BLACK);
            prenom.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 90));
            numtel.getUnselectedStyle().setFgColor(BLACK);
            delete.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 90));

            prenom.getUnselectedStyle().setFont(lbl);

            FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE_SWEEP);
            FontImage.setMaterialIcon(email, FontImage.MATERIAL_EMAIL);
            FontImage.setMaterialIcon(numtel, FontImage.MATERIAL_PHONE_IPHONE);

            cnUser.addAll(prenom, email, numtel);
            cnone.addAll(profilePicLabel, cnUser, delete);
            Label l = new Label("________________");
            cnAll.add(cnone,l);

            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    UserService.getInstance().deleteUser(user.getUsername());

                }
            });

            System.out.println(user.getPrenomUser() + " " + user.getNomUser());

        }
        //this.add(cnAll);

    }

    public ListeArticle() {
    }

    private void setLayout(BoxLayout y) {
    }

    private Toolbar getToolbar() {
        return null;
    }

    private void setupSideMenu(Resources res) {
    }

    public void show() {
    }

}*/
