/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.CommandeE;
import com.mycompany.myapp.services.ServiceCommande;
import java.util.ArrayList;


public class CommandeForm  extends Form{
    Form current;
    public CommandeForm(Form previous) {
        current=this;
        ArrayList<CommandeE> cmd = ServiceCommande.getInstance().getAllCommandeEncours();
         Form hi = new Form();
             Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        for (CommandeE p : cmd)
        {              
                        Label lR= new Label("Reference Article: "+p.getRef());
                        Label lQ= new Label("Quantite: "+Integer.toString(p.getQte()));
                        Label lD= new Label("Designation: "+p.getDesignation());
                        
                        Label lC= new Label("Code barre: "+p.getCode());


                        c.add(lR);
                        c.add(lQ);
                        c.add(lD);
                        c.add(lC);
                       
                        
        }
          hi.add(c);
            hi.show();
               getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
  
        
    }
}
