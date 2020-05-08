/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.Storage;
import com.codename1.io.URL;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.swr.services.ServiceNews;
import com.swr.services.ServiceUser;
import java.io.IOException;
import java.net.URI;


/**
 *
 * @author Asus X550V
 */
public class NewsForm extends BaseForm{
Container c = new Container(BoxLayout.x());
Container cxx = new Container(BoxLayout.xCenter());
public NewsForm(String mail,int id) {
        this(com.codename1.ui.util.Resources.getGlobalResources(),mail,id);
    }

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }
    
    
    
    public NewsForm(com.codename1.ui.util.Resources resourceObjectInstance,String mail,int id) {
       // initGuiBuilderComponents(resourceObjectInstance);
        
        getToolbar().setTitleComponent(
              /*  FlowLayout.encloseCenterMiddle(
                        new Label("          Home", "Title"),
                        new Label("                                  "+ServiceUser.getInstance().affichuser(mail).get(0).getUsername(), "Title")
                ),*/
                                FlowLayout.encloseRight(
                        new Label("                             "+ServiceUser.getInstance().affichuser(mail).get(0).getUsername(), "Title")
                ));
                        setLayout(BoxLayout.y());
                        
                        
        //setLayout(BoxLayout.y());
       EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(1100, 500), false);
        Image img1 = URLImage.createToStorage(placeholder,"http://localhost/swr/web/uploads/"+ServiceNews.getInstance().affichnewsd(id).get(0).getImage(), "http://localhost/swr/web/uploads/"+ServiceNews.getInstance().affichnewsd(id).get(0).getImage(),URLImage.RESIZE_SCALE);
   
        Label titre = new Label(ServiceNews.getInstance().affichnewsd(id).get(0).getTitre());
        titre.setUIID("CENTER");
        titre.setAlignment(CENTER);
        titre.getAllStyles().setFgColor(0xff5866d3);
        SpanLabel desc = new SpanLabel(ServiceNews.getInstance().affichnewsd(id).get(0).getDesc());
        Label t = new Label("Title : ");
        Label d = new Label("Description : ");
        Label cat = new Label("Category : ");
        Label cat1 = new Label(ServiceNews.getInstance().affichnewsd(id).get(0).getNomcat());
        d.setUIID("BoldLabel");
        d.getAllStyles().setFgColor(0xffb76653);
                cat.setUIID("BoldLabel");
        cat.getAllStyles().setFgColor(0xffb76653);
        Label ta = new Label(""+ServiceNews.getInstance().nblikes(id));
        Label p = new Label("");
        FontImage.setMaterialIcon(p, FontImage.MATERIAL_PRINT);
                Label like = new Label(" ");
                if(ServiceNews.getInstance().verifLike(id,ServiceUser.getInstance().affichuser(mail).get(0).getId()))
                {
          FontImage.setMaterialIcon(like, FontImage.MATERIAL_FAVORITE_BORDER);
                  int idc=ServiceUser.getInstance().affichuser(mail).get(0).getId();
        like.addPointerReleasedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(ServiceNews.getInstance().verifLike(id,ServiceUser.getInstance().affichuser(mail).get(0).getId()))
                {
                ServiceNews.getInstance().addlike(idc, id);
                FontImage.setMaterialIcon(like, FontImage.MATERIAL_FAVORITE);
                ta.setText(""+ServiceNews.getInstance().nblikes(id));
              
                }
                else{
                ServiceNews.getInstance().deletelike(idc, id);
                FontImage.setMaterialIcon(like, FontImage.MATERIAL_FAVORITE_BORDER);
                ta.setText(""+ServiceNews.getInstance().nblikes(id));
                }
            }
        });
                }
                else{
                FontImage.setMaterialIcon(like, FontImage.MATERIAL_FAVORITE);
                        int idc=ServiceUser.getInstance().affichuser(mail).get(0).getId();
        like.addPointerReleasedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(!ServiceNews.getInstance().verifLike(id,ServiceUser.getInstance().affichuser(mail).get(0).getId()))
                {ServiceNews.getInstance().deletelike(idc, id);
                FontImage.setMaterialIcon(like, FontImage.MATERIAL_FAVORITE_BORDER);
                ta.setText(""+ServiceNews.getInstance().nblikes(id));
              
                }
                else{
                              ServiceNews.getInstance().addlike(idc, id);
                FontImage.setMaterialIcon(like, FontImage.MATERIAL_FAVORITE);
                ta.setText(""+ServiceNews.getInstance().nblikes(id));  
                }
            }
        });
                }
        

        p.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().execute("http://localhost/swr-web/web/app_dev.php/pdfnewsm/"+id);
            }
        });

        cxx.add(ta).add(like).add(p);
        like.setUIID("MultiLine2");
        
                p.setAlignment(CENTER);
        like.setAlignment(CENTER);
        cxx.setScrollableY(true);
        if(ServiceNews.getInstance().affichnewscat(ServiceNews.getInstance().affichnewsd(id).get(0).getNomcat(),ServiceNews.getInstance().affichnewsd(id).get(0).getIdn()).size()!=0)
        {
        Label l = new Label("Same Category");
        l.setUIID("BoldLabel");
        l.getAllStyles().setFgColor(0xffb76653);
        l.setAlignment(CENTER);
        this.add(new ImageViewer(img1)).add(titre).add(d).add(desc).add(cat).add(cat1).add(cxx).add(l);
                //this.setScrollableX(true);   
            for(int i=0;i<ServiceNews.getInstance().affichnewscat(ServiceNews.getInstance().affichnewsd(id).get(0).getNomcat(),ServiceNews.getInstance().affichnewsd(id).get(0).getIdn()).size();i++)
            {
                
                Container cy = new Container(BoxLayout.y());
                placeholder = EncodedImage.createFromImage(Image.createImage(1100, 500), false);
        Image img = URLImage.createToStorage(placeholder,"http://localhost/swr/web/uploads/"+ServiceNews.getInstance().affichnewscat(ServiceNews.getInstance().affichnewsd(id).get(0).getNomcat(),ServiceNews.getInstance().affichnewsd(id).get(0).getIdn()).get(i).getImage(), "http://localhost/swr/web/uploads/"+ServiceNews.getInstance().affichnewscat(ServiceNews.getInstance().affichnewsd(id).get(0).getNomcat(),ServiceNews.getInstance().affichnewsd(id).get(0).getIdn()).get(i).getImage(),URLImage.RESIZE_SCALE);
          Label k = new Label(ServiceNews.getInstance().affichnewscat(ServiceNews.getInstance().affichnewsd(id).get(0).getNomcat(),ServiceNews.getInstance().affichnewsd(id).get(0).getIdn()).get(i).getTitre());  
          int idn=ServiceNews.getInstance().affichnewscat(ServiceNews.getInstance().affichnewsd(id).get(0).getNomcat(),ServiceNews.getInstance().affichnewsd(id).get(0).getIdn()).get(i).getIdn();
          k.addPointerPressedListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                    new NewsForm(mail,idn).show();                        
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
          k.setUIID("MultiLine2");
          k.setAlignment(CENTER);
          cy.add(img).add(k);
          c.add(cy);
                    
            
            
            }
            c.setScrollableX(true);
        this.add(c);
        }
        else{      
        this.add(new ImageViewer(img1)).add(titre).add(d).add(desc).add(cat).add(cat1).add(cxx);}
        
                        
                
             
        
        
        installSidemenu(resourceObjectInstance,mail);
        placeholder = (EncodedImage) resourceObjectInstance.getImage("toolbar-profile-pic.png");
        Image img = URLImage.createToStorage(placeholder,"http://localhost/swr/web/uploads/"+ServiceUser.getInstance().affichuser(mail).get(0).getImage(), "http://localhost/swr/web/uploads/"+ServiceUser.getInstance().affichuser(mail).get(0).getImage(), URLImage.RESIZE_SCALE);
        getToolbar().addCommandToRightBar("",img, e -> {});
        
        gui_Label_5.setShowEvenIfBlank(true);
        gui_Label_6.setShowEvenIfBlank(true);
        gui_Label_7.setShowEvenIfBlank(true);
        gui_Label_8.setShowEvenIfBlank(true);
        gui_Label_9.setShowEvenIfBlank(true);
        
        gui_Text_Area_1.setRows(2);
        gui_Text_Area_1.setColumns(100);
        gui_Text_Area_1.setEditable(false);
        gui_Text_Area_1_1.setRows(2);
        gui_Text_Area_1_1.setColumns(100);
        gui_Text_Area_1_1.setEditable(false);
        gui_Text_Area_1_2.setRows(2);
        gui_Text_Area_1_2.setColumns(100);
        gui_Text_Area_1_2.setEditable(false);
        gui_Text_Area_1_3.setRows(2);
        gui_Text_Area_1_3.setColumns(100);
        gui_Text_Area_1_3.setEditable(false);
        gui_Text_Area_1_4.setRows(2);
        gui_Text_Area_1_4.setColumns(100);
        gui_Text_Area_1_4.setEditable(false);
        
        
     
      /*  FloatingActionButton fab  = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder)fab.getUnselectedStyle().getBorder();
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
        fab.addActionListener(e -> {
            fab.setUIID("FloatingActionButtonClose");
            Image oldImage = fab.getIcon();
            FontImage image = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "FloatingActionButton", 3.8f);
            fab.setIcon(image);
            Dialog popup = new Dialog();
            popup.setDialogUIID("Container");
            popup.setLayout(new LayeredLayout());
            
            Button c1 = new Button(resourceObjectInstance.getImage("contact-a.png"));
            Button c2 = new Button(resourceObjectInstance.getImage("contact-b.png"));
            Button c3 = new Button(resourceObjectInstance.getImage("contact-c.png"));
            Button trans = new Button(" ");
            trans.setUIID("Container");
            c1.setUIID("Container");
            c2.setUIID("Container");
            c3.setUIID("Container");
            Style c1s = c1.getAllStyles();
            Style c2s = c2.getAllStyles();
            Style c3s = c3.getAllStyles();
            
            c1s.setMarginUnit(Style.UNIT_TYPE_DIPS);
            c2s.setMarginUnit(Style.UNIT_TYPE_DIPS);
            c3s.setMarginUnit(Style.UNIT_TYPE_DIPS);

            c1s.setMarginBottom(16);
            c1s.setMarginLeft(12);
            c1s.setMarginRight(3);

            c2s.setMarginLeft(4);
            c2s.setMarginTop(5);
            c2s.setMarginBottom(10);
            c3s.setMarginRight(14);
            
            c3s.setMarginTop(12);
            c3s.setMarginRight(16);

            popup.add(trans).
                    add(FlowLayout.encloseIn(c1)).
                    add(FlowLayout.encloseIn(c2)).
                    add(FlowLayout.encloseIn(c3));
            
            ActionListener a = ee -> popup.dispose();
            
            trans.addActionListener(a);
            c1.addActionListener(a);
            c2.addActionListener(a);
            c3.addActionListener(a);
            
            popup.setTransitionInAnimator(CommonTransitions.createEmpty());
            popup.setTransitionOutAnimator(CommonTransitions.createEmpty());
            popup.setDisposeWhenPointerOutOfBounds(true);
            int t = InboxForm.this.getTintColor();
            InboxForm.this.setTintColor(0);
            popup.showPopupDialog(new Rectangle(InboxForm.this.getWidth() - 10, InboxForm.this.getHeight() - 10, 10, 10));
            InboxForm.this.setTintColor(t);
            fab.setUIID("FloatingActionButton");
            fab.setIcon(oldImage);
        });*/
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_4 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_3 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_2 = new com.codename1.ui.Label();
    private com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Label gui_Label_6 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_1_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_2_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_1_1 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_4_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_4_1 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_3_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_3_1 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_2_1 = new com.codename1.ui.Label();
    private com.codename1.ui.TextArea gui_Text_Area_1_1 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Label gui_Label_7 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_1_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_2_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_1_2 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_4_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_4_2 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_3_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_3_2 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_2_2 = new com.codename1.ui.Label();
    private com.codename1.ui.TextArea gui_Text_Area_1_2 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Label gui_Label_8 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_1_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_2_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_1_3 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_4_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_4_3 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_3_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_3_3 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_2_3 = new com.codename1.ui.Label();
    private com.codename1.ui.TextArea gui_Text_Area_1_3 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Label gui_Label_9 = new com.codename1.ui.Label();
     private com.codename1.ui.Label gui_Label_10 = new com.codename1.ui.Label();
      private com.codename1.ui.Label gui_Label_11 = new com.codename1.ui.Label();
       private com.codename1.ui.Label gui_Label_12 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_1_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_2_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_1_4 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_4_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_4_4 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_3_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_3_4 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_2_4 = new com.codename1.ui.Label();
    private com.codename1.ui.TextArea gui_Text_Area_1_4 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Label gui_Label_5 = new com.codename1.ui.Label();

     private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
       private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
        private com.codename1.ui.Button gui_Button_4 = new com.codename1.ui.Button();
       private com.codename1.ui.Button gui_Button_5 = new com.codename1.ui.Button();
// <editor-fold defaultstate="collapsed" desc="Generated Code">     
}
