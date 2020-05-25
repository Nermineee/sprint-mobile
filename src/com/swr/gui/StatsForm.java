/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author moham
 */
public class StatsForm extends Form {

    public StatsForm(Resources res) {
        setLayout(new FlowLayout(Component.CENTER,Component.CENTER));
        setTitle("Stats");
       add(new SalesBarChart().execute());
       getToolbar().addCommandToLeftBar("Return", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new ListHousingsForm(res).showBack();
            }
       });
    }
    
}
