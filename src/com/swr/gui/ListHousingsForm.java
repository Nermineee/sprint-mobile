/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.util.Resources;
import com.esprit.swr.entities.Housing;
import com.esprit.swr.services.ServiceHousing;
import com.esprit.swr.services.ServiceReview;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListHousingsForm extends BaseForm{

    private EncodedImage himg;
    
    
    
    public ListHousingsForm(Resources res) {
        Form current = this;
        this.setTitle("Housing List");
        this.setLayout(BoxLayout.y());
        ArrayList<Housing> LH= ServiceHousing.getInstance().getAllHousings();
        
        for (Housing h : LH) {
         
            Container c= new Container(BoxLayout.y());
            SpanLabel l= new SpanLabel(h.getName());
             // slider 
            
             //
             l.setUIID("CenterNoPadd");
             SpanLabel sp_desc = new SpanLabel("Description : "+h.getDescription());
           
             SpanLabel sp_adr = new SpanLabel("Address : "+h.getAddress());
               sp_desc.setUIID("RedLabel");
             sp_adr.setUIID("RedLabel");
            
             Button btdetails= new Button("View Details");
          //   btdetails.setUIID("InboxNumber2");
             
             btdetails.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new HousingForm(res,h).show();
                }
             });
             
             Button btitems= new Button("View Items");
             btitems.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                       new Listofitems(res, h, current).show();
                }
             });
             
        try {
            
            himg= EncodedImage.create("/"+h.getType()+".jpg");
            
            
            } 
        catch (IOException ex) {
            }
           
           Label imglabel= new Label();
           imglabel.setIcon(himg);
          imglabel.setUIID("CenterLabel");
            
        Container c2= new Container(new FlowLayout(Component.CENTER,Component.CENTER));
        
        
         Slider rating= new AddreviewForm().createStarRankSlider();
             rating.setMaxValue(5);
             int tt= (int) h.getRating();
             rating.setProgress(tt);
             rating.setEditable(false);
        c2.add(btdetails).add(btitems).add(rating);
        c.add(imglabel).add(l).add(sp_desc).add(sp_adr).add(c2);
        
        c.getAllStyles().setBgColor(0xCAE5E5);
        
        add(c);
        
        }
         
       
         // spacer
        getToolbar().addCommandToRightBar("Stats", null ,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new StatsForm(res).show();
            }
        });
        super.installSidemenu(res);
        
      
       // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    
}
