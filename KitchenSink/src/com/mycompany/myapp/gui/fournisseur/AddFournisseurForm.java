/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.fournisseur;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.myapp.entities.Fournisseur;
import com.mycompany.myapp.services.ServiceFournisseur;

/**
 *
 * @author Asus
 */
public class AddFournisseurForm extends Form {

    public AddFournisseurForm(Form previous) {
        setTitle("Ajouter un nouveau fournisseur");
        setLayout(BoxLayout.y());

        TextField tfNomSociete = new TextField("", "Nom de la societe");
        TextField tfCin = new TextField("", "Cin du fournisseur");
        TextField tfAdresse = new TextField("", "Adresse du fournisseur");
        TextField tfEmail = new TextField("", "Email du livreur");
        TextField tfTelephone = new TextField("", "Telephone du fournisseur");
        TextField tfFax = new TextField("", "Fax du fournisseur");

        Button btnValider = new Button("Ajouter fournisseur");
        btnValider.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
        btnValider.getAllStyles().setFgColor(0x189fA5);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              if ((tfNomSociete.getText().length() == 0) || (tfCin.getText().length()<7) || (tfAdresse.getText().length() == 0) || (tfEmail.getText().length() == 0)  || (tfTelephone.getText().length()<8) || (tfFax.getText().length()<8)) {
                        Dialog.show("Alert", "Veuillez remplir tous les champs","OK", null);
                    } else {
                    try {
                        Fournisseur l = new Fournisseur(Integer.parseInt(tfCin.getText()), tfEmail.getText(), tfAdresse.getText(), Integer.parseInt(tfTelephone.getText()), Integer.parseInt(tfFax.getText()), tfNomSociete.getText());
                        if (ServiceFournisseur.getInstance().addFournisseur(l)) {
                            Dialog.show("Success", "Fournisseur ajouté avec succés","OK",null);
                            new ListFournisseursForm(previous).show();
                        } else {
                           Dialog.show("ERREUR", "Echec à l'ajout", "OK", null);
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERREUR", "ça doit être un numero", "OK", null);
                    }

              }
            }
        });

        addAll(tfNomSociete, tfCin, tfAdresse, tfEmail, tfTelephone, tfFax, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
