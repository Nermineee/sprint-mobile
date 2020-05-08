/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.swr.entities.User;
import com.swr.services.ServiceUser;

/**
 *
 * @author Asus X550V
 */
public class ForgetForm extends Form {
    private Resources theme;
        public ForgetForm(Form previous) {
        this(com.codename1.ui.util.Resources.getGlobalResources(),previous);
    }
 public ForgetForm(com.codename1.ui.util.Resources resourceObjectInstance,Form previous) {
    setTitle("Forgot password");
    setLayout(BoxLayout.yCenter());
    ImageViewer  Im1= new ImageViewer(resourceObjectInstance.getImage("584856ade0bb315b0f7675ab.png"));
        TextField tfmail = new TextField("","Enter your email");
        Button btnValider = new Button("   Enter  ");
        btnValider.setUIID("InboxNumber3");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                     if( ServiceUser.getInstance().envoi(tfmail.getText()))
        {
        ServiceUser.getInstance().mailing(tfmail.getText());
        new IterForm(tfmail.getText()).show();
        }
        else{

       Dialog.show("ERROR", "User does not exist !", new Command("OK"));
        }                   
            }
        });
        
        addAll(Im1,tfmail,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack()); 
}   
}
