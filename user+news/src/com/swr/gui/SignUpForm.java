/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.capture.Capture;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.github.sarxos.webcam.Webcam;
import com.swr.entities.User;
import com.swr.services.ServiceUser;
import com.swr.services.servicebcrypt;
import com.swr.utils.staticvar;
import java.io.IOException;
import java.util.Random;

import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author Asus X550V
 */
public class SignUpForm extends com.codename1.ui.Form {
   FileUploader file;
    String fileNameInServer;
    String ipath;
 public SignUpForm(Form previous) {
    setTitle("Sign Up");
        setLayout(BoxLayout.y());
        
        TextField tfmail = new TextField("","E-mail");
        TextField tfusername= new TextField("", "Username");
        TextField tfnom= new TextField("", "First Name");
        TextField tfprenom= new TextField("", "Last Name");
        TextField tftel= new TextField("", "Telephone");
        TextField tfpass= new TextField("", "Password",0,TextField.PASSWORD);
        TextField tfcountry= new TextField("", "Country");
        Button picture = new Button("");
        picture.setUIID("InboxNumber4");
        picture.setMaterialIcon(FontImage.MATERIAL_CLOUD_UPLOAD);
        picture.addPointerReleasedListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
            ipath = Capture.capturePhoto();
            String link= ipath.toString();
            System.out.println(link);
            int pod=link.indexOf("/",2);
            String p = link.substring(pod+2,link.length());
          FileUploader fu = new FileUploader("http://localhost/swr/web/");
          
                fileNameInServer=fu.upload(p);
               
            } catch (Exception ex) {
                
            }
            
        }
    });
        Button btnValider = new Button("Sign Up");
        btnValider.setUIID("InboxNumber3");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfmail.getText().length()==0)||(tfpass.getText().length()==0)||(tfusername.getText().length()==0)||(tftel.getText().length()==0)||(tfnom.getText().length()==0)||(tfprenom.getText().length()==0)||(tfcountry.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                     if(ServiceUser.getInstance().envoi(tfmail.getText()))
                     {
                     Dialog.show("Success","Mail exists",new Command("OK"));
                     }
                     else{
                         try {
                         int a=Integer.parseInt(tftel.getText());
                        User u;
                        String crypted = servicebcrypt.hashpw(tfpass.getText(), staticvar.SALT);
                        u = new User(tfnom.getText(),tfprenom.getText(),tfcountry.getText(),tfmail.getText(),Integer.parseInt(tftel.getText()),crypted,tfusername.getText(),fileNameInServer);
                        if( ServiceUser.getInstance().addUser(u))
                            Dialog.show("Success","User added",new Command("OK"));
                         }catch (NumberFormatException ex) { Dialog.show("Success","Please enter a phone number ",new Command("OK")); }
                     }
                    }
                    
                }
                
                
            
        });
        
        addAll(tfnom,tfprenom,tftel,tfcountry,tfmail,tfusername,tfpass,picture,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack()); 
}
}

