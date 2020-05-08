/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.swr.services.ServiceUser;

/**
 *
 * @author Asus X550V
 */
public class IterForm extends Form {
private Resources theme;
        public IterForm(String mail) {
        this(com.codename1.ui.util.Resources.getGlobalResources(),mail);
    }
 public IterForm(com.codename1.ui.util.Resources resourceObjectInstance,String mail) {
    setTitle("Forgot password");
    setLayout(BoxLayout.yCenter());
    ImageViewer  Im1= new ImageViewer(resourceObjectInstance.getImage("logoswr.png"));
    Container cx = new Container(BoxLayout.xCenter());
        TextField tfmail = new TextField("","Enter the code");
        Label lb=new Label("Please check your email");
        lb.setUIID("BoldLabel");
        Label lb1=new Label("you have ");
        Label lb2 = new Label(""+ServiceUser.getInstance().affichuser(mail).get(0).getIteration());
        Label lb3 = new Label(" attempts");
        lb1.setUIID("BoldLabel");
        lb3.setUIID("BoldLabel");
        lb2.setUIID("BoldLabel");
        lb2.getAllStyles().setFgColor(0xFF0000);       
        cx.add(lb1).add(lb2).add(lb3);
        Button btnValider = new Button("   Enter  ");
        btnValider.setUIID("InboxNumber3");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                     if(tfmail.getText().equals(String.valueOf(ServiceUser.getInstance().affichuser(mail).get(0).getRadom())))
        {
        new PassForm(mail).show();
        }
                     else if (ServiceUser.getInstance().affichuser(mail).get(0).getIteration()==0){
        ServiceUser.getInstance().deleteu(ServiceUser.getInstance().affichuser(mail).get(0).getId());
       Dialog.show("ERROR", "your account has been deleted !", new Command("OK"));
       new SignInForm().show();
        }
                                          else{
        ServiceUser.getInstance().upuser(mail);
       Dialog.show("ERROR", "Code invalid !", new Command("OK"));
       lb2.setText(""+ServiceUser.getInstance().affichuser(mail).get(0).getIteration());
        }
            }
        });
        
        this.add(Im1).add(lb).add(tfmail).add(cx).add(btnValider);
   
}  
}
