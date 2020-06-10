/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

////import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.swr.entities.Housing;
import com.swr.entities.Item;
import com.swr.entities.Goods;
import com.swr.services.ServiceGoods;
import com.swr.services.ServiceHousing;
import com.swr.services.ServiceItems;
import java.util.ArrayList;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author asus
 */
public class AddgoodsForm extends Form{
    //public static final String ACCOUNT_SID ="AC87415bc270674dccbc3300c28fc22bce";
   // public static final String AUTH_TOKEN ="b8200cb5885219eb575a4a51666c361b";

    public AddgoodsForm(Form previous) {
        setTitle("Add a new good");
        setLayout(BoxLayout.y());
         
        TextField tfItem = new TextField("","Item");
        
        TextField tfQcollected = new TextField("","Quantity Collected");
        
        ArrayList<Housing> LH= ServiceHousing.getInstance().getAllHousings();
        ComboBox comboitems=new ComboBox();
        ComboBox combohousings = new ComboBox();
        
        for (Housing h : LH) {
            combohousings.addItem(h.getName());
        }
        for (Item it : ServiceItems.getInstance().getAllItems()) {
            comboitems.addItem(it.getNameItem());
        }
                                
        //ComboBox comboitems= new ComboBox("Food","Water");
        
        Button btnValider = new Button("Add goods");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfQcollected.getText().length()==0)
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Housing h = new Housing();
                        h.setName(combohousings.getSelectedItem().toString());
                        Item i= new Item();
                        i.setNameItem(comboitems.getSelectedItem().toString());
                        Goods t = new Goods(h,4,i,Integer.parseInt(tfQcollected.getText()),"");
                         
                        if( ServiceGoods.getInstance().addGoods(t))
                            Dialog.show("Success","Good added",new Command("OK"));
                        
                     
                        else
                            Dialog.show("ERROR", "Please fill all the fields", new Command("OK"));
                       // sms();
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfQcollected,combohousings,comboitems,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
//    public static void sms() {
//            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//            
//            Message message = Message.creator(new com.twilio.type.PhoneNumber("+21655882630"),
//                    new com.twilio.type.PhoneNumber("+18312288506"),
//                    "The goods were sent").create();
//            System.out.println(message.getSid());
//        }
//    
    
    
}
