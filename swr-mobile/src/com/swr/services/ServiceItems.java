/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.swr.entities.Housing;
import com.swr.entities.Item;
import com.swr.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceItems {

    public ArrayList<Item> Items;
    
    public static ServiceItems instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceItems() {
         req = new ConnectionRequest();
    }

    public static ServiceItems getInstance() {
        if (instance == null) {
            instance = new ServiceItems();
        }
        return instance;
    }

  
  

    public ArrayList<Item> parseItems(String jsonText){
        try {
            Items=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Item t = new Item();
                
                
                float idi = Float.parseFloat(obj.get("iditem").toString());
                t.setIdItem((int)idi);
                //t.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
                t.setNameItem(obj.get("name").toString());
                t.setDescription(obj.get("description").toString());
                t.setQuantity((int)Float.parseFloat(obj.get("quantity").toString()));
                
                t.setStatusItem(obj.get("status").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                Items.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return Items;
    }
    
    
    
    public ArrayList<Item> getAllItems(){
        String url = Statics.BASE_URL+"/delivery/housing/items/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Items = parseItems(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Items;
    }
}
