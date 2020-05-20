/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
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
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.esprit.entities.Allee;
import com.esprit.services.ServiceAllee;
import java.io.IOException;

/**
 *
 * @author mac
 */
public class AddAlleeForm extends Form {

    public AddAlleeForm(Form previous) {
            /*
            Le paramètre previous définit l'interface(Form) précédente.
            Quelque soit l'interface faisant appel à AddTask, on peut y revenir
            en utilisant le bouton back
             */
            setTitle("Ajouter une Allée");
            setLayout(BoxLayout.y());

            SpanLabel info = new SpanLabel("Cet assistant vous permet de créer automatiquement "
                    + "les emplacements en fonction des informations que vous allez indiquer");

            TextField tfLigne = new TextField("", "Ligne");
            TextField tfNbTrav = new TextField("", "Nombre Travee");
            TextField tfNiv = new TextField("", "Niveau");

            Button btnValider = new Button("Ajouter Allée");

            btnValider.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0xe16702), focusScrolling);
            btnValider.getAllStyles().setFgColor(0xe16702);
            btnValider.getAllStyles().setBackgroundGradientEndColor(0xe16702);

            btnValider.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if ((tfLigne.getText().length() == 0) || (tfNbTrav.getText().length() == 0)) {
                        Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                    } else {
                        try {
                            Allee t = new Allee(tfLigne.getText(), Integer.parseInt(tfNbTrav.getText()),
                                    Integer.parseInt(tfNiv.getText()));

                            if (ServiceAllee.getInstance().addAllee(t)) {
                                Dialog.show("Success", "Allée ajoutée avec succèes", new Command("OK"));
                            } else {
                                Dialog.show("ERROR", "Server error", new Command("OK"));
                            }

                        } catch (NumberFormatException e) {
                            System.out.println(e.getMessage());
                            Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                        }

                    }

                }
            });

            Font mediumPlainProportionalFont = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);

            
            
              Style s = UIManager.getInstance().getComponentStyle("Title");

            FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_INFO, s);

            info.setIcon(icon);
            info.setIconPosition(BorderLayout.WEST);

            
            Font smallItalicProportionalFont = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_ITALIC, Font.SIZE_SMALL);
            add(createForFont(smallItalicProportionalFont, info));
            addAll(tfLigne, tfNbTrav, tfNiv, btnValider);
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                    e -> previous.showBack()); // Revenir vers l'interface précédente
        

    }

    private SpanLabel createForFont(Font fnt, SpanLabel s) {
        s.getUnselectedStyle().setFont(fnt);
        return s;
    }

}
