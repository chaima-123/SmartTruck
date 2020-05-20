/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextComponent;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Style;


/**
 *
 * @author mac
 */
public class apiCapture extends Form{
     public apiCapture(Form previous) {
        
             setTitle("Gallerie");
             
             
             Style s = UIManager.getInstance().getComponentStyle("Title");
             FontImage icon =FontImage.createMaterial(FontImage.MATERIAL_CAMERA, s);
                     Container cn = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                     SpanLabel l=new SpanLabel("Choissisez une photo qui définit la structure de l'entrepôt");
             cn.add(l);
             ImageViewer iv = new ImageViewer(icon);
             
           //  Image im =Image.createImage("/test.jpg");
             Image im = Image.createImage(1200, 1900, 0xfffffff);
iv.setHeight(30);
            

             ImageViewer iv2 = new ImageViewer(im);
                          cn.add(iv2);


             
             getToolbar().addCommandToRightBar("", icon, (ev) -> {
                 Display.getInstance().openGallery((e) -> {
                     if (e != null && e.getSource() != null) {
                         try {
                             DefaultListModel <Image> m = (DefaultListModel<Image>) iv2.getImageList();
                             Image  img = Image.createImage((String) e.getSource());

                             if (m == null) {
                                 m = new DefaultListModel <>(img);
                                 iv2.setImageList(m);
                                 iv2.setImage(img);
                             } else {
                                 m.addItem(img);
                             }
                             m.setSelectedIndex(m.getSize() - 1);
                         } catch (IOException  err) {
                             Log.e(err);
                         }
                     }
                 }, Display.GALLERY_IMAGE);
             });
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

             addAll(iv,cn);
         
         
         
        
        
     }
    
    
}
