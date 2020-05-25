/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.Validator;
import com.esprit.swr.entities.Housing;
import com.esprit.swr.entities.Rating;
import com.esprit.swr.entities.User;
import com.esprit.swr.services.ServiceHousing;
import com.esprit.swr.services.ServiceReview;
import java.io.IOException;

import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author bhk
 */
public class HousingForm extends Form{

    private EncodedImage himg;
    private EncodedImage marker;
    private boolean exists;
    private Rating upr;
    
    public HousingForm(Resources res,Housing h) {
        exists=false;
        upr=new Rating();
        setTitle("Housing List");
        Label listlb= new Label();
        listlb.setUIID("List");
        
        
        
            Container c1=new Container(new FlowLayout(Component.CENTER,Component.CENTER));
            Container c= new Container(BoxLayout.y());
             SpanLabel l= new SpanLabel(h.getName());
             l.setUIID("BoldLabel");
             SpanLabel sp_desc = new SpanLabel("Description : "+h.getDescription());
             SpanLabel sp_adr = new SpanLabel("Address : "+h.getAddress());
             SpanLabel sp_cap = new SpanLabel("Capacity : "+h.getCapacity());
             
             SpanLabel sp_res = new SpanLabel("Address : "+h.getNbresidents());
             
             Label sep = new Label("Reviews");
            sep.setUIID("Separator");
        try {
            
            himg= EncodedImage.create("/"+h.getType()+".jpg");
                        marker= EncodedImage.create("/marker.png");
 
            } 
        catch (IOException ex) {
            }
        
           ImageViewer housingimg= new ImageViewer(himg);
            
        
        
        c.add(housingimg).add(l).add(sp_desc).add(sp_adr).add(sp_cap).add(sp_res);
        
        c.getAllStyles().setBgColor(0xCAE5E5);
        c1.add(c).add(sep);
        AddreviewForm a= new AddreviewForm();
        add(c1);
        
       
         Label all = new Label("Reviews");
            all.setUIID("Separator");
            
            Map<Rating,User> Mapof= ServiceReview.getInstance().getAllReviews(h.getIdh());
           
         for (Map.Entry<Rating,User> entry : Mapof.entrySet())  {
            
            Container ctr= new Container(BoxLayout.y());
            Container ctrating= new Container(BoxLayout.x());
            Container ctrlkol= new Container(new FlowLayout(CENTER,CENTER));
          
            Rating r= entry.getKey();
            User u= entry.getValue();
             
            if (u.getIdu()==4) {exists=true; 
                
            upr=r;}
            Label rv= new Label(u.getUsername());
            rv.setUIID("RedLabel");
            String path="http://localhost/swr-web/web/Front/assets/images/avatar/"+u.getImage();
        Image img= URLImage.createToStorage(himg, path, path,URLImage.RESIZE_SCALE);
        
            
            ImageViewer imgv= new ImageViewer(img);
            
            SpanLabel span_fd= new SpanLabel("Feedback:" +r.getFeedback());
            Slider sldr= a.createStarRankSlider();
            sldr.setEditable(false);
            sldr.setProgress(r.getRating());
            ctrating.add(new Label("Rating:")).add(sldr);
            ctr.add(rv);
            ctr.add(ctrating);
            ctr.add(span_fd);
            ctrlkol.add("***********************************").add(imgv).add(ctr).add(new Label("***********************************"));
        add(ctrlkol);    
        
        }
            
        
         double lat=0;
         double lng=0;
        String locc= h.getLocation().substring(1, (h.getLocation().length()-1));
        
        StringTokenizer locat=new StringTokenizer(locc,",");
         while (locat.hasMoreTokens()) {
             lat=Double.parseDouble(locat.nextToken());
         lng=Double.parseDouble(locat.nextToken());
         
    }    
         
         if(!exists)     {
             upr.setIduser(4);
             upr.setidH(h.getIdh());
             add(a.AddreviewForm(res, upr, exists)) ;
         }
         else
         add(a.AddreviewForm(res,upr,exists));
        
        Label lbloc = new Label("Location On The Map ");
        Label space= new Label("");
        space.setUIID("Label");
          
        add(space);
        Container mapct= new Container(new FlowLayout(Component.BOTTOM,Component.CENTER));
        mapct.add(lbloc);
        //
        MapContainer cnt = new MapContainer();
        //"AIzaSyALPYyxrjY4KClo41xfDVr7xexBWNB2jqk"
        
        Coord cord= new Coord(lat, lng);
        cnt.setCameraPosition(cord);
       
        cnt.addMarker(
                    EncodedImage.createFromImage(marker, false),
                    cnt.getCameraPosition(),
                    "Hi marker",
                    "Optional long description",
                     evt -> {
                             ToastBar.showMessage("You clicked the marker", FontImage.MATERIAL_PLACE);
                     }
            );
        
       
        mapct.add(cnt);
        add(listlb).add(mapct);
        
        getToolbar().addMaterialCommandToLeftBar("Return", FontImage.MATERIAL_ARROW_BACK, e->new ListHousingsForm(res).showBack());
    }
    
    
}
