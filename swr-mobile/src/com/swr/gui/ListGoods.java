/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.swr.entities.Goods;
import com.swr.services.ServiceGoods;
import java.awt.FlowLayout;

/**
 *
 * @author Asus
 */
public class ListGoods extends BaseForm{

    public ListGoods(Resources res,Form prev) {
        installSidemenu(res);
        Form current= this;
        
        for (Goods gd : ServiceGoods.getInstance().getAllGoods()) {
            
            Container c= new Container(BoxLayout.y());
            Container c1= new Container(BoxLayout.y());
           
            SpanLabel spl_quant = new SpanLabel();
               FontImage.setMaterialIcon(spl_quant, FontImage.MATERIAL_LAYERS);
          Font Flike = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
         spl_quant.getTextAllStyles().setFont(Flike);
         spl_quant.setText("Quantity collected : "+gd.getQcollected());
         
         
            SpanLabel spl_item = new SpanLabel();
              FontImage.setMaterialIcon(spl_item, FontImage.MATERIAL_SHOPPING_BASKET);
         spl_item.getTextAllStyles().setFont(Flike);
         spl_item.setText("Item : " +gd.getItem().getNameItem());
         
         
         
            SpanLabel spl_address = new SpanLabel();
            FontImage.setMaterialIcon(spl_address, FontImage.MATERIAL_LOCATION_SEARCHING);
         spl_address.getTextAllStyles().setFont(Flike);
         spl_address.setText("Address : " +gd.getIdH().getAddress());
            
            
            
            
            SpanLabel spl_stat = new SpanLabel();
            FontImage.setMaterialIcon(spl_stat, FontImage.MATERIAL_HOURGLASS_EMPTY);
         spl_stat.getTextAllStyles().setFont(Flike);
         spl_stat.setText("Status : "+gd.getStatus());
           
            
            
     Label whiteLine = new Label(); 
    whiteLine.setShowEvenIfBlank(true);
    whiteLine.getUnselectedStyle().setBgColor(0xd9d5d3);
    whiteLine.getUnselectedStyle().setBgTransparency(255);
    whiteLine.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_DIPS);
    whiteLine.getUnselectedStyle().setPadding(1, 1,1, 1);
             
            
             
             spl_quant.setUIID("RedLabel");
             spl_item.setUIID("RedLabel");
             spl_stat.setUIID("RedLabel");
             spl_address.setUIID("RedLabel");
              c1.addAll(spl_quant,spl_item,spl_stat,spl_address);
              c1.add(whiteLine);
              //c.addAll(l,c1);
              c.addAll(c1);
              add(c);
             
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> prev.showBack());
        getToolbar().addCommandToRightBar("Contribute", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                        new AddgoodsForm(current).show();
            }
        });
        }

      
        
    }
    

    
    


