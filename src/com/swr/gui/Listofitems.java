/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.swr.entities.Housing;
import com.esprit.swr.entities.Item;
import com.esprit.swr.services.ServiceItems;


/**
 *
 * @author moham
 */
public class Listofitems extends BaseForm{

    public Listofitems(Resources res,Housing h,Form previous) {
        installSidemenu(res);
        
        for (Item it : ServiceItems.getInstance().getAllItems(h.getIdh())) {
            
            Container c= new Container(BoxLayout.y());
            Container c1= new Container(BoxLayout.x());
             Label l= new Label(it.getNameItem());
             l.setUIID("BoldLabel");
             SpanLabel sp_desc = new SpanLabel("Description : "+it.getDescription());
           
             SpanLabel sp_quant = new SpanLabel("Quantity : "+it.getQuantity());
             Label sp_stat = new Label("Status: ");
             Label l_stat = new Label(it.getStatusItem());
             
            
             
             sp_desc.setUIID("RedLabel");
             sp_quant.setUIID("RedLabel");
             sp_stat.setUIID("RedLabel");
             l_stat.setUIID("RedLabel");
             c1.addAll(sp_stat,l_stat);
              c.addAll(l,sp_desc,sp_quant,c1);
              add(c);
             
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        }
        
    }
    
    
    
    

