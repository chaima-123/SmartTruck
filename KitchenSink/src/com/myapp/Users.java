/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Form;
import com.mycompany.myapp.gui.utilisateur.UtilisateurForm;
import java.io.IOException;

/**
 * Demonstrates some of the basic layout types available in Codename One with
 * explanation and a smooth animation
 *
 * @author Shai Almog
 */
public class Users extends Demo {

    public String getDisplayName() {
        return "Utilisateurs";
    }

    public Image getDemoIcon() {
        Image imageUsers = null;
        try {
            imageUsers = Image.createImage("/users.jpg");
            return imageUsers;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return imageUsers;
    }

    @Override
    public String getSourceCodeURL() {
        return "https://github.com/codenameone/KitchenSink/blob/master/src/com/codename1/demos/kitchen/Layouts.java";
    }

    private void resetMargin(Container c) {
        for (Component cc : c) {
            cc.setUIID(cc.getUIID());
        }
    }

    public Container createDemo() {
        Container cn = new Container();
        Button utilisateurs = new Button("Utilisateurs");
        utilisateurs.addActionListener(e -> new UtilisateurForm().show());
        cn.add(utilisateurs);
        return cn;
    }

    @Override
    public String getDescription() {
        return "La gestion des utilisateurs: cette rubrique permet d'ajouter afficher modifier et supprimer un utilisateur";
    }

    
}
