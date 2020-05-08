/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.components.ImageViewer;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.swr.services.ServiceUser;
import com.swr.services.servicebcrypt;
import com.swr.utils.staticvar;

/**
 *
 * @author Asus X550V
 */
public class PassForm extends Form{
 private Resources theme;
        public PassForm(String mail) {
        this(com.codename1.ui.util.Resources.getGlobalResources(),mail);
    }
 public PassForm(com.codename1.ui.util.Resources resourceObjectInstance,String mail) {
    setTitle("New password");
    setLayout(BoxLayout.yCenter());
    ImageViewer  Im1= new ImageViewer(resourceObjectInstance.getImage("logoswr.png"));
    
        TextField tfmail = new TextField("","Enter a new password");
        Label lb=new Label("Choose a new password");
        lb.setUIID("BoldLabel");
        Button btnValider = new Button("   Enter  ");
        btnValider.setUIID("InboxNumber3");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                     if(tfmail.getText().length()>=8)
        {
        String crypted = servicebcrypt.hashpw(tfmail.getText(), staticvar.SALT);
        ServiceUser.getInstance().modifpass(mail,crypted);
        new SignInForm().show();
        }
                                          else{
Dialog.show("ERROR", "Please the length of the password must be more then 8 characters ! ", new Command("OK"));
}
            }
        });
        
        addAll(Im1,lb,tfmail,btnValider);
   
}      
}
