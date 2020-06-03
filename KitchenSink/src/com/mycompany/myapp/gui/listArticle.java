package com.mycompany.myapp.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.codename1.components.SpanLabel;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
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
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.CommandeE;
import com.mycompany.myapp.entities.LigneCommande;
import com.mycompany.myapp.entities.Palette;
import com.mycompany.myapp.services.ServiceCommande;
import com.mycompany.myapp.services.ServicePalette;
import java.util.ArrayList;
import java.util.Date;


public class listArticle extends Form{
    Form current;
    public listArticle(Form previous) {
        current=this;
        setTitle("List des articles");
        

ArrayList<Article> articles= ServicePalette.getInstance().getAllArticle();
        for (Article p : articles)
        {
Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        Label l= new Label( (p.getRefArticle()));
                        Label l2= new Label("Code barre: "+p.getCode());  
                        Label l1= new Label("Designation: "+ p.getDesignation());
                        Label l3= new Label("Prix: "+Float.toString(p.getPrix()));

                        Label l4= new Label("Quantite:");
                        TextField tqte= new TextField("","saisir la quantite");
                        Button ajouter= new Button ("Ajouter");
                        
                        ajouter.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
        ajouter.getAllStyles().setFgColor(0x189fA5);
                       ajouter.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
         if ((tqte.getText().length()==0))
                    Dialog.show("Alert", "Il faut saisir une quantite", new Command("OK"));
                else
             
        {
                 
                     //java.sql.Date.valueOf( dateP.getValue()),
                        LigneCommande t = new LigneCommande(Integer.parseInt( tqte.getText()),l.getText() );
                        if( ServiceCommande.getInstance().addArticle(t))
                           // Dialog.show("succès","Article ajouté avec succès");
                            System.out.println("ok");
                       
                    
                    
                }
    }
});
                        getToolbar().addMaterialCommandToRightBar("Commande",FontImage.MATERIAL_SHOP, new ActionListener() {
         @Override
           public void actionPerformed(ActionEvent evt) {
              // new CommandeForm(current).show() ;
              Form hi = new Form();
         setTitle("Commande Entree");
             Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
              ArrayList<CommandeE> cmd = ServiceCommande.getInstance().getAllCommandeEncours();
                            ArrayList<Commande> cmdL = ServiceCommande.getInstance().getlastCommande();
                            
                for (Commande cm : cmdL)
                {
                    Label LN= new Label("Numero commande");
                    TextField txtn= new TextField();
                    txtn.setText("C_"+Integer.toString(cm.getId_commande()));
                     Label LM= new Label("Montant");
                     TextField txtm= new TextField();
                    Label LDC= new Label("Date commande");
                     Picker DC= new Picker();
                     
                     Label LDL= new Label("Date livraison");
                      Picker DL= new Picker();
                      
                      Date DDC = (Date) DC.getValue();
                       Date DDL = (Date) DL.getValue();

                       
                      Button btnA= new Button("Ajouter commande");
                      btnA.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
        btnA.getAllStyles().setFgColor(0x189fA5);
                      
                      Label sep= new Label("-----------------Liste des articles choisis--------------");
                      
                      
                        btnA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((txtm.getText().length()==0))
                    Dialog.show("Alert", "Vous devez choisir une quantite", new Command("OK"));
                else
                {
                    
                     //java.sql.Date.valueOf( dateP.getValue()),
                Commande c= new Commande(cm.getId_commande(),txtn.getText(),Float.parseFloat(txtm.getText()),DDC,DDL);     
                         ServiceCommande.getInstance().addCmd(c);
                          //  Dialog.show("succès","Commande ajoutee avec succès",new Command("OK"));
                        
                     
                }
            }
        });
                     
                    c.addAll(LN,txtn,LM,txtm,LDC,DC,LDL,DL,btnA,sep);
                }
                            
                            

        for (CommandeE p : cmd)
        {              
                        Label lR= new Label("Reference Article: "+p.getRef());
                        Label lQ= new Label("Quantite: ");
                        TextField tq= new TextField(Integer.toString(p.getQte()));
                        Label lD= new Label("Designation: "+p.getDesignation());
                        
                        Label lC= new Label("Code barre: "+p.getCode());
                         Button btn= new Button("supprimer");
                         Button btn1= new Button("modifier");
                         
                        btn.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
        btn.getAllStyles().setFgColor(0x189fA5);
        
        
        btn1.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
        btn1.getAllStyles().setFgColor(0x189fA5);
                        Label s= new Label ("=====================================");


                        btn1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            LigneCommande L = new LigneCommande(Integer.parseInt( tq.getText()),p.getRef() );

                             if ((tq.getText().length()==0))
                    Dialog.show("Erreur", "Il faut choisir une quantite!", new Command("OK"));
                             else {
                                 
                                if (ServiceCommande.getInstance().UpdateLigne(L)) {
                                    if (Dialog.show("modification", "Ligne de commande modifie avec succès", "ok", null)) {
                                          new listArticle(current).show();
                                    }
                                    else 
                                      Dialog.show("ERROR", "Server error", new Command("OK")); 

                            }
                                 
                             }
                        }
                    });

                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                        LigneCommande L = new LigneCommande(Integer.parseInt( tq.getText()),p.getRef() );

                            if (Dialog.show("Supression", "Voulez-vous vraiment supprimer cette palette?", "Oui", "Annuler")) {
                                if (ServiceCommande.getInstance().DeleteLigne(L)) {
                                    new listArticle(current).show();
                                } else {
                                    Dialog.show("ERROR", "Server error", new Command("OK"));
                                }

                            } else {
                                hi.show();
                            }

                        }
                    });
                        
                        
                        c.add(lR);
                        c.add(lQ);
                        c.add(tq);

                        c.add(lD);
                        c.add(lC);
                        c.add(btn);
                        c.add(btn1);
                        c.add(s);
                       
                        
        }
         hi.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent evt) {
                        show();
                  }
              });
          hi.add(c);
            hi.show();
            
               
        }
    });
                       
                       c.addAll(l,l2,l1,l3,l4,tqte,ajouter);
                       add(c);
                     

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    
}
}
