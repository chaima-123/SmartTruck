/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


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
import com.mycompany.myapp.entities.Palette;
import com.mycompany.myapp.services.ServicePalette;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.control.DatePicker;


/**
 *
 * @author bhk
 */
public class AddPaletteForm extends Form{

    public AddPaletteForm(Form previous) {
        setTitle("Ajouter palette");
                                //Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));

             //Form hi = new Form("", BoxLayout.y());
        setLayout(BoxLayout.y());

        Label lqte = new Label ("Quantite: ");
        TextField tfName = new TextField("","Quantité");
        Label ldate = new Label ("Date d'expiration: ");
     Picker dateP = new Picker();
        Date value = (Date) dateP.getValue();
        Label larticle = new Label ("Article: ");

        ComboBox cmbArticle = new ComboBox();
        Label lcode = new Label ("Emplacement: ");

        ComboBox cmbCode = new ComboBox();

                    
        ArrayList<Article> articles= ServicePalette.getInstance().getAllArticle();
        for (Article p : articles)
        {
            cmbArticle.addItem(p.getRefArticle());
        }
        
        ArrayList<Emplacement> emps= ServicePalette.getInstance().getAllEmp();
        for (Emplacement p : emps)
        {
            cmbCode.addItem(p.getCodeEmp());
            //System.out.println(p.getCodeEmp());
        }
       // DatePicker date= new DatePicker();
       // TextField tfStatus= new TextField("", "Status: 0 - 1");
        Button btnValider = new Button("ajouter ");
         btnValider.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
        btnValider.getAllStyles().setFgColor(0x189fA5);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0))
                    Dialog.show("Alert", "Tous les champs sont obligatoires", new Command("OK"));
                else
                {
                    try {
                     //java.sql.Date.valueOf( dateP.getValue()),
                        Palette t = new Palette(Integer.parseInt( tfName.getText()), value,cmbArticle.getSelectedItem().toString(),cmbCode.getSelectedItem().toString());
                        if( ServicePalette.getInstance().addPalette(t))
                            Dialog.show("Succes","Palette ajoutee avec succes",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
            }
        });
        
        addAll(lqte,tfName,larticle,cmbArticle,lcode,cmbCode,ldate,dateP,btnValider);
      
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
