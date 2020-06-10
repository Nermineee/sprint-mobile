///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.swr.gui;
//
//import com.codename1.components.SpanLabel;
//import com.codename1.ui.Component;
//import com.codename1.ui.Container;
//import com.codename1.ui.EncodedImage;
//import com.codename1.ui.Form;
//import com.codename1.ui.Label;
//import com.codename1.ui.layouts.BoxLayout;
//import com.codename1.ui.layouts.FlowLayout;
//import com.codename1.ui.util.Resources;
//import com.swr.entities.Goods;
//import java.util.Map;
//
///**
// *
// * @author Asus
// */
//
//
//public class GoodsForm extends Form {
//
//    private EncodedImage himg;
//    private EncodedImage marker;
//    private boolean exists;
//    
//    
//    public GoodsForm(Resources res,Goods g) {
//        exists=false;
//        
//        setTitle("Goods List");
//        Label listgoods= new Label();
//        listgoods.setUIID("The list");
//        
//        
//        
//             Container c1=new Container(new FlowLayout(Component.CENTER,Component.CENTER));
//             Container c= new Container(BoxLayout.y());            
//             SpanLabel sp_item = new SpanLabel("Item : "+g.getItem());
//             SpanLabel sp_qcollected = new SpanLabel("Quantity Collected : "+g.getQcollected());
//             SpanLabel sp_status = new SpanLabel("Status : "+g.getStatus());
//             
//            // SpanLabel sp_res = new SpanLabel("Address : "+g.getNbresidents());
//             
//           //  Label sep = new Label("Reviews");
//            //sep.setUIID("Separator");
////        try {
////            
////            himg= EncodedImage.create("/"+g.getType()+".jpg");
////                        marker= EncodedImage.create("/marker.png");
//// 
////            } 
////        catch (IOException ex) {
////            }
//        
////           ImageViewer housingimg= new ImageViewer(himg);
//            
//        
//        
//        c.add(sp_item).add(sp_qcollected).add(sp_status);
//        
//        c.getAllStyles().setBgColor(0xCAE5E5);
//        c1.add(c);
//        AddgoodsForm a= new AddgoodsForm();
//        add(c1);
//        
//       
//         Label all = new Label("Reviews");
//            all.setUIID("Separator");
//            
//            Map<Rating,User> Mapof= ServiceReview.getInstance().getAllReviews(h.getIdh());
//           
//         for (Map.Entry<Rating,User> entry : Mapof.entrySet())  {
//            
//            Container ctr= new Container(BoxLayout.y());
//            Container ctrating= new Container(BoxLayout.x());
//            Container ctrlkol= new Container(new FlowLayout(CENTER,CENTER));
//          
//            Rating r= entry.getKey();
//            User u= entry.getValue();
//             
//            if (u.getIdu()==4) {exists=true; 
//                
//            upr=r;}
//            Label rv= new Label(u.getUsername());
//            rv.setUIID("RedLabel");
//            String path="http://localhost/swr-web/web/Front/assets/images/avatar/"+u.getImage();
//        Image img= URLImage.createToStorage(himg, path, path,URLImage.RESIZE_SCALE);
//        
//            
//            ImageViewer imgv= new ImageViewer(img);
//            
//            SpanLabel span_fd= new SpanLabel("Feedback:" +r.getFeedback());
//            Slider sldr= a.createStarRankSlider();
//            sldr.setEditable(false);
//            sldr.setProgress(r.getRating());
//            ctrating.add(new Label("Rating:")).add(sldr);
//            ctr.add(rv);
//            ctr.add(ctrating);
//            ctr.add(span_fd);
//            ctrlkol.add("***********************************").add(imgv).add(ctr).add(new Label("***********************************"));
//        add(ctrlkol);    
//        
//        }
//            
//        
//         double lat=0;
//         double lng=0;
//        String locc= h.getLocation().substring(1, (h.getLocation().length()-1));
//        
//        StringTokenizer locat=new StringTokenizer(locc,",");
//         while (locat.hasMoreTokens()) {
//             lat=Double.parseDouble(locat.nextToken());
//         lng=Double.parseDouble(locat.nextToken());
//         
//    }    
//         
//         if(!exists)     {
//             upr.setIduser(4);
//             upr.setidH(h.getIdh());
//             add(a.AddreviewForm(res, upr, exists)) ;
//         }
//         else
//         add(a.AddreviewForm(res,upr,exists));
//        
//        Label lbloc = new Label("Location On The Map ");
//        Label space= new Label("");
//        space.setUIID("Label");
//          
//        add(space);
//        Container mapct= new Container(new FlowLayout(Component.BOTTOM,Component.CENTER));
//        mapct.add(lbloc);
//        //
//        MapContainer cnt = new MapContainer();
//        //"AIzaSyALPYyxrjY4KClo41xfDVr7xexBWNB2jqk"
//        
//        Coord cord= new Coord(lat, lng);
//        cnt.setCameraPosition(cord);
//       
//        cnt.addMarker(
//                    EncodedImage.createFromImage(marker, false),
//                    cnt.getCameraPosition(),
//                    "Hi marker",
//                    "Optional long description",
//                     evt -> {
//                             ToastBar.showMessage("You clicked the marker", FontImage.MATERIAL_PLACE);
//                     }
//            );
//        
//       
//        mapct.add(cnt);
//        add(listlb).add(mapct);
//        
//        getToolbar().addMaterialCommandToLeftBar("Return", FontImage.MATERIAL_ARROW_BACK, e->new ListHousingsForm(res).showBack());
//    }
//    
//    
//}
