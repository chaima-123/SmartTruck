/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.CommandeE;
import com.mycompany.myapp.services.ServiceCommandeClient;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class commandesclientsForm extends Form {

    Form current;

    public commandesclientsForm(Form current) {

        ArrayList<CommandeE> cmd = ServiceCommandeClient.getInstance().getAllCommandesclients();

        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        for (CommandeE p : cmd) {
            // Label lR= new Label("Num commande: "+p.getNumCommande());
            Label L1 = new Label("Article: " + p.getRef());

            Label lQ = new Label("Quantite: " + Integer.toString(p.getQte()));
            Label lH = new Label("Client: " + p.getNom());

            //  c.add(lR);
            c.add(L1);
            c.add(lQ);
            c.add(lH);

        }
        add(c);

        // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }

}
