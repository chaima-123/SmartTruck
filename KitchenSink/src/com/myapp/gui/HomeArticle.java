/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeArticle extends Form {

    Form current;
    /*Garder traÃ§e de la Form en cours pour la passer en paramÃ¨tres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la mÃ©thode showBack*/
    
    public HomeArticle() {
        current = this; //RÃ©cupÃ©ration de l'interface(Form) en cours
        setTitle("SmartTruck");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Ajouter Article");
        Button btnListTasks = new Button("catalogue des articles ");

//      btnAddTask.addActionListener(e -> new AddArticleForm(current).show());
       // btnListTasks.addActionListener(e -> new ListeArticle(current).show());
        addAll(btnAddTask, btnListTasks);

    }

}
