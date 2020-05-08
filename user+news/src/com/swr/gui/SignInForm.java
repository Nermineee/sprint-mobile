/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.swr.gui;
import com.codename1.facebook.FaceBookAccess;
import static com.codename1.facebook.FaceBookAccess.getInstance;
import com.codename1.facebook.User;
import com.codename1.io.Storage;
import com.codename1.messaging.Message;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.swr.services.ServiceUser;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.swr.services.ServiceNews;
import com.swr.services.servicebcrypt;
import com.swr.utils.staticvar;
import java.io.IOException;



/**
 * GUI builder created Form
 *
 * @author Shai Almog
 */
public class SignInForm extends com.codename1.ui.Form {

    public SignInForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public SignInForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SigninTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        getContentPane().setUIID("SignInForm");
        
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField gui_Text_Field_2 = new com.codename1.ui.TextField("","Email");
    private com.codename1.ui.TextField gui_Text_Field_1 = new com.codename1.ui.TextField("","Password",0,com.codename1.ui.TextField.PASSWORD);
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_fb = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    

// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_Button_2.addActionListener(callback);
        gui_Button_1.addActionListener(callback);
        gui_Button_3.addActionListener(callback);
        gui_Button_fb.addActionListener(callback);
    }

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {
        private com.codename1.ui.Component cmp;
        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();
            if(sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if(sourceComponent == gui_Button_2) {
                onButton_2ActionEvent(ev);
            }
                        if(sourceComponent == gui_Button_1) {
                onButton_1ActionEvent(ev);
            }
                                                if(sourceComponent == gui_Button_3) {
                onButton_3ActionEvent(ev);
            
                                                } 
                                                                                                if(sourceComponent == gui_Button_fb) {
                onButton_fbActionEvent(ev);
            }
        }

        public void dataChanged(int type, int index) {
        }
    }
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Sign In");
        setName("SignInForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(gui_Text_Field_2);
        gui_Component_Group_1.addComponent(gui_Text_Field_1);
        gui_Text_Field_2.setName("Text_Field_2");
        gui_Text_Field_1.setName("Text_Field_1");
        gui_Container_1.addComponent(gui_Button_2);
        gui_Container_1.addComponent(gui_Button_fb);
        gui_Container_1.addComponent(gui_Button_3);
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        gui_Label_1.setIcon(resourceObjectInstance.getImage("logosignin.png"));
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Sign In");
        gui_Button_fb.setText("Connect with Facebook");
        gui_Button_fb.setUIID("InboxNumber");
        gui_Button_2.setName("Button_2");
        gui_Button_3.setText("Forgot Your Password");
        gui_Button_3.setUIID("CenterLabelSmall");
        gui_Button_3.setName("Button_3");
         gui_Button_2.setUIID("SideCommandNumber");
        addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Button_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Button_1.setText("Create New Account");
        gui_Button_1.setUIID("CenterLabel");
        gui_Button_1.setName("Button_1");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
        String crypted = servicebcrypt.hashpw(gui_Text_Field_1.getText(), staticvar.SALT);
        //new NewsForm("aminee",44).show();
        if( ServiceUser.getInstance().verifUser(gui_Text_Field_2.getText(),crypted))
        {
        new InboxForm(gui_Text_Field_2.getText(),"").show();
        }
        else{

       Dialog.show("ERROR", "Account does not exist !", new Command("OK"));
        }
        
    }
        public void onButton_1ActionEvent(com.codename1.ui.events.ActionEvent ev) {
        new SignUpForm(this).show();
    }
                public void onButton_3ActionEvent(com.codename1.ui.events.ActionEvent ev) {
        new ForgetForm(this).show();
    }
                                public void onButton_fbActionEvent(com.codename1.ui.events.ActionEvent ev) {
                String clientId = "3566482806711767";
                String redirectURI = "http://localhost/";
                String clientSecret = "ddf3472b716ce707f729f5cfe9bf3beb";
                Login fb = FacebookConnect.getInstance();
                fb.setClientId(clientId);
                fb.setRedirectURI(redirectURI);
                fb.setClientSecret(clientSecret);
                /*if(Storage.getInstance().readObject("token")=="")
                {
                fb.doLogin();
                }*/
                
                //Storage.getInstance().writeObject("token",String.valueOf(fb.getAccessToken().getToken()));
                //String token = (String) Storage.getInstance().readObject("token");
                if(!fb.isUserLoggedIn())
                {
                fb.doLogin();
                }
                if(fb.isUserLoggedIn())
                {
                FaceBookAccess.setToken(fb.getAccessToken().getToken());
                }
               // System.out.println(fb.getAccessToken().getToken());
                final User me = new User();
                
                
        try {
            FaceBookAccess.getInstance().getUser("me", me, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if(fb.isUserLoggedIn())
                            {
               new InboxForm(fb.getAccessToken().getToken(),me.getName()).show();     
                            }
                }
            });
        } catch (IOException ex) {
            
        }
                                }}
