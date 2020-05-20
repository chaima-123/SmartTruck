/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import java.io.IOException;

import javafx.scene.paint.Color;

/**
 *
 * @author mac
 */
public class HomeForm extends Form {

    Form current;

    public HomeForm() {
        current = this; //Récupération de l'interface(Form) en cours

        setLayout(BoxLayout.y());

        add(new Label("Choisissez une option"));

        Button btnAddTask = new Button("Ajouter Allee");
        Button btnListTasks = new Button("Listes des Allees");
        Button btnListEmp = new Button("Listes des Emplacements");
        Button b = new Button("galery");

        Button f = new Button("Gestion de Famille");
        Button affFam = new Button("Affecter Famille");

        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> Form.previous.showBack());
        //b.getAllStyles().setBgImage(im);
        btnListTasks.addActionListener(e -> new ListAlleeForm(current).show());
        btnAddTask.addActionListener(e -> new AddAlleeForm(current).show());
        btnListEmp.addActionListener(e -> new ListEmpForm(current).show());
        b.addActionListener(e -> new apiCapture(current).show());
        //f.addActionListener(e -> new GestionFamille(current).show());
        affFam.addActionListener(e -> new AffecterFamille(current).show());

        btnListTasks.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0xe16702), focusScrolling);
        btnListTasks.getAllStyles().setFgColor(0xe16702);
        btnListTasks.getAllStyles().setBackgroundGradientEndColor(0xe16702);

        btnAddTask.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0xe16702), focusScrolling);
        btnAddTask.getAllStyles().setFgColor(0xe16702);
        btnAddTask.getAllStyles().setBackgroundGradientEndColor(0xe16702);

        btnListEmp.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0xe16702), focusScrolling);
        btnListEmp.getAllStyles().setFgColor(0xe16702);
       btnListEmp.getAllStyles().setBackgroundGradientEndColor(0xe16702);
       
       
        b.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0xe16702), focusScrolling);
        b.getAllStyles().setFgColor(0xe16702);
        b.getAllStyles().setBackgroundGradientEndColor(0xe16702);
       
        affFam.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0xe16702), focusScrolling);
        affFam.getAllStyles().setFgColor(0xe16702);
        affFam.getAllStyles().setBackgroundGradientEndColor(0xe16702);
        
        
        add(btnListEmp);
        add(btnAddTask);
        add(btnListTasks);
        //add(f);
        add(affFam);
        add(b);

        // addAll(btnAddTask, btnListTasks, btnListEmp, b,affFam);
    }

}
