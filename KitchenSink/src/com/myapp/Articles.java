/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.EventDispatcher;
import com.mycompany.myapp.gui.ListeArticle;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Webservice tutorial showing off a faux dog picture set and the ability to
 * browse thru images from the network using ImageViewer.
 *
 * @author Shai Almog
 */
public class Articles extends Demo {

    private static final String WEBSERVICE_URL = "https://www.codenameone.com/files/kitchensink/dogs/list2.json";
    private EncodedImage placeholder;

    public String getDisplayName() {
        return "Articles";
    }

    public Image getDemoIcon() {
        //return getResources().getImage("dog.jpg");
        Image imageArticles = null;
        try {
            imageArticles = Image.createImage("/articles.png");
            return imageArticles;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return imageArticles;
    }

    @Override
    public String getDescription() {
        return "Les articles de notre warehouse sont affichés dans cette rubrique.";
    }

    @Override
    public String getSourceCodeURL() {
        return "https://github.com/codenameone/KitchenSink/blob/master/src/com/codename1/demos/kitchen/WebServices.java";
    }

     private void resetMargin(Container c) {
        for (Component cc : c) {
            cc.setUIID(cc.getUIID());
        }
    }

    public Container createDemo() {
        Container cn = new Container();
        Button articles = new Button("Articles");
        articles.addActionListener(e -> new ListeArticle().show());
        cn.add(articles);
        return cn;
    }
    
     
}
