/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;

/**
 *
 * @author bhk
 */
public class HomePalette extends Form{
Form current;
    public HomePalette(Form previous) throws ParseException {
        current=this;
        setTitle("Gestion Palette");
        setLayout(BoxLayout.y());
        
        add(new Label("Choisir une option"));
        Button btnAddTask = new Button("Ajouter palette");
        Button btnListTasks = new Button("Liste palette");
        Label l= new Label();
                Label l1= new Label();
         
btnAddTask.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
        btnAddTask.getAllStyles().setFgColor(0x189fA5);
        
        btnListTasks.getAllStyles().setBorder(Border.createGrooveBorder(CENTER, 0x189fA5), focusScrolling);
        btnListTasks.getAllStyles().setFgColor(0x189fA5);
        
        btnAddTask.addActionListener(e-> new AddPaletteForm(current).show());
        btnListTasks.addActionListener(e-> {
            try {
                new ListTasksForm(current).show();
            } catch (ParseException ex) {
            }
        }); 

        addAll(btnAddTask,l,l1,btnListTasks);
        
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

        
    }
    
    
}
