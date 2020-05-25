/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.esprit.swr.entities.Rating;
import com.esprit.swr.services.ServiceReview;
import com.codename1.push.Push;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
/**
 *
 * @author bhk
 */
public class AddreviewForm {
    
     private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    public Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(5);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte)0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }

    public Container AddreviewForm(Resources current,Rating r,boolean exist) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        Container c= new Container(BoxLayout.y());
        
       
        
       
        Slider rtslider=createStarRankSlider();
        TextField tfStatus= new TextField("", "feedback");
         // Validations
        String isString = "^[a-zA-Z]+$";
        Validator v = new Validator();
        v.addConstraint(tfStatus,new RegexConstraint(isString, "Enter a valid feedback"));
        Button btnValider;
        
        if (exist==true) {btnValider=new Button("Update Your Review");
            tfStatus.setText(r.getFeedback());
        rtslider.setProgress(r.getRating());
        }
        else btnValider=new Button("Add Review");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfStatus.getText().length()==0 ))
                    Dialog.show("Alert", "Please fill the feedback field", new Command("OK"));
                else if ( rtslider.getProgress()==0)
                {Dialog.show("Alert", "Rating must be between 1 and 5 ", new Command("OK"));}
                else
                {
                    try {Rating t ;
                         t=new Rating(r.getIdR(),r.getidH(),r.getIduser(), tfStatus.getText(),rtslider.getProgress());
                        
                        if( ServiceReview.getInstance().addReview(t)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                            
                            new ListHousingsForm(current).showBack();
                        }
                        
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        btnValider.setUIID("InboxNumber");
        c.add(FlowLayout.encloseCenter(rtslider));
        c.addAll(tfStatus,btnValider);
        
        
        
             return c;   
    }
    
    
}
