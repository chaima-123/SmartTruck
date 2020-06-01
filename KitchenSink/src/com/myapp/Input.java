/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.ToastBar;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import static com.codename1.ui.CN.*;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;

/**
 * Demonstrates basic usage of input facilities, device orientation behavior as well as adapting the UI to tablets.
 * This demo shows off a typical form with user information, different keyboard types, ability to capture an 
 * avatar image and style it etc.
 *
 * @author Shai Almog
 */
public class Input  extends Demo {

    public String getDisplayName() {
        return "Formulaires";
    }

    public Image getDemoIcon() {
        return getResources().getImage("input.png"); 
    }

    @Override
    public String getDescription() {
        return "Demonstrates basic usage of input facilities, device orientation behavior as well as adapting the UI to tablets." +
                "This demo shows off a typical form with user information, different keyboard types, ability to capture an " +
                "avatar image and style it etc.";
    }

    @Override
    public String getSourceCodeURL() {
        return "https://github.com/codenameone/KitchenSink/blob/master/src/com/codename1/demos/kitchen/Input.java";
    }

    private void addComps(Form parent, Container cnt, Component... cmps) {
        if(isTablet() || !isPortrait()) {
            TableLayout tl = new TableLayout(cmps.length / 2, 2);
            cnt.setLayout(tl);
            tl.setGrowHorizontally(true);
            for(Component c : cmps) {
                if(c instanceof Container) {
                    cnt.add(tl.createConstraint().horizontalSpan(2), c);
                } else {
                    cnt.add(c);
                }
            }
        } else {
            cnt.setLayout(BoxLayout.y());
            for(Component c : cmps) {
                cnt.add(c);
            }
        }
        if(cnt.getClientProperty("bound") == null) {
            cnt.putClientProperty("bound", "true");
            if(!isTablet()) {
                parent.addOrientationListener((e) -> {
                    callSerially(() -> {
                        cnt.removeAll();
                        addComps(parent, cnt, cmps);
                        cnt.animateLayout(800);
                    });
                });
            }
        }
    }
    
    @Override
    public Container createDemo(Form parent) {
        TextField name = new TextField("", "Name", 20, TextField.ANY);
        FontImage.setMaterialIcon(name.getHintLabel(), FontImage.MATERIAL_PERSON);
        TextField email = new TextField("", "E-mail", 20, TextField.EMAILADDR);
        FontImage.setMaterialIcon(email.getHintLabel(), FontImage.MATERIAL_EMAIL);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        FontImage.setMaterialIcon(password.getHintLabel(), FontImage.MATERIAL_LOCK);
        TextField bio = new TextField("", "Bio", 2, 20);
        FontImage.setMaterialIcon(bio.getHintLabel(), FontImage.MATERIAL_LIBRARY_BOOKS);
        Picker birthday = new Picker();
        birthday.setType(PICKER_TYPE_DATE);
        OnOffSwitch joinMailingList = new OnOffSwitch();
        bio.setSingleLineTextArea(false);
        
        Validator val = new Validator();
        val.setValidationFailureHighlightMode(Validator.HighlightMode.UIID);
        val.addConstraint(name, new LengthConstraint(2, "Name must have at least 2 characters")).
                addConstraint(email, RegexConstraint.validEmail("E-Mail must be a valid email address")).
                addConstraint(password, new LengthConstraint(6, "Password must have at least 6 characters"));
        
        Container comps = new Container();
        addComps(parent, comps, 
                new Label("Name", "InputContainerLabel"), 
                name,
                new Label("E-Mail", "InputContainerLabel"),
                email,
                new Label("Password", "InputContainerLabel"),
                password,
                BorderLayout.center(new Label("Birthday", "InputContainerLabel")).
                        add(EAST, birthday),
                new Label("Bio", "InputContainerLabel"),
                bio,
                BorderLayout.center(new Label("Join Mailing List", "InputContainerLabel")).
                        add(EAST, joinMailingList));
        
        comps.setScrollableY(true);
        comps.setUIID("PaddedContainer");
        
        Container content = BorderLayout.center(comps);
        
        Button save = new Button("Save");
        save.setUIID("InputAvatarImage");
        content.add(SOUTH, save);
        save.addActionListener(e -> {
            ToastBar.showMessage("Save pressed...", FontImage.MATERIAL_INFO);
        });
        
        content.setUIID("InputContainerForeground");
        
        Button avatar = new Button("");
        avatar.setUIID("InputAvatar");
        Image defaultAvatar = FontImage.createMaterial(FontImage.MATERIAL_CAMERA, "InputAvatarImage", 8);
        Image circleMaskImage = getResources().getImage("circle.png");
        defaultAvatar = defaultAvatar.scaled(circleMaskImage.getWidth(), circleMaskImage.getHeight());
        defaultAvatar = ((FontImage)defaultAvatar).toEncodedImage();
        Object circleMask = circleMaskImage.createMask();
        defaultAvatar = defaultAvatar.applyMask(circleMask);
        avatar.setIcon(defaultAvatar);
        
        avatar.addActionListener(e -> {
            if(Dialog.show("Camera or Gallery", "Would you like to use the camera or the gallery for the picture?", "Camera", "Gallery")) {
                String pic = Capture.capturePhoto();
                if(pic != null) {
                    try {
                        Image img = Image.createImage(pic).fill(circleMaskImage.getWidth(), circleMaskImage.getHeight());
                        avatar.setIcon(img.applyMask(circleMask));
                    } catch(IOException err) {
                        ToastBar.showErrorMessage("An error occured while loading the image: " + err);
                        Log.e(err);
                    }
                }
            } else {
                openGallery(ee -> {
                    if(ee.getSource() != null) {
                        try {
                            Image img = Image.createImage((String)ee.getSource()).fill(circleMaskImage.getWidth(), circleMaskImage.getHeight());
                            avatar.setIcon(img.applyMask(circleMask));
                        } catch(IOException err) {
                            ToastBar.showErrorMessage("An error occured while loading the image: " + err);
                            Log.e(err);
                        }
                    }
                }, GALLERY_IMAGE);
            }
        });
        
        Container actualContent = LayeredLayout.encloseIn(content, 
                        FlowLayout.encloseCenter(avatar));
        
        Container input;
        if(!isTablet()) {
            Label placeholder = new Label(" ");

            Component.setSameHeight(actualContent, placeholder);
            Component.setSameWidth(actualContent, placeholder);

            input = BorderLayout.center(placeholder);

            parent.addShowListener(e -> {
                if(placeholder.getParent() != null) {
                    input.replace(placeholder, actualContent, CommonTransitions.createFade(1500));
                }
            });
        } else {
            input = BorderLayout.center(actualContent);
        }
        input.setUIID("InputContainerBackground");
        
        return input;
    }
    
}