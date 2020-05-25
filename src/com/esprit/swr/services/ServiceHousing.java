/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.swr.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.swr.entities.Housing;
import com.esprit.swr.entities.Rating;
import com.esprit.swr.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceHousing {

    public ArrayList<Housing> Housings;
    
    public static ServiceHousing instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceHousing() {
         req = new ConnectionRequest();
    }

    public static ServiceHousing getInstance() {
        if (instance == null) {
            instance = new ServiceHousing();
        }
        return instance;
    }

    public ArrayList<Housing> parseHousings(String jsonText){
        try {
            Housings=new ArrayList<>();
            double avgrating = 0;
            int totalr=0;
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

           
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            ArrayList<Rating> LR=ServiceReview.getInstance().getavgReviews();
            int i=0;
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Housing t = new Housing();
                
                float id = Float.parseFloat(obj.get("idh").toString());
                t.setIdh((int)id);
                //t.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
                t.setName(obj.get("name").toString());
                t.setDescription(obj.get("description").toString());
                t.setAddress(obj.get("address").toString());
                t.setLocation(obj.get("location").toString());
                t.setCapacity((int)Float.parseFloat(obj.get("capacity").toString()));
                t.setNbresidents((int)Float.parseFloat(obj.get("nbresidents").toString()));
                t.setType(obj.get("type").toString());
                for(Rating R : LR){
                if(R.getidH()==t.getIdh())
                {avgrating +=(double)R.getRating();
                totalr++;
                }
                }
                avgrating=avgrating/totalr; 
                t.setRating(Math.round(avgrating));
               
                //Ajouter la tâche extraite de la réponse Json à la liste
                Housings.add(t);
            }
           
            
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return Housings;
    }

    
      public ArrayList<Housing> getHousing(int idh){
        String url = Statics.BASE_URL+"/housing/find/"+idh;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Housings = parseHousings(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Housings;
    }
    
    
    public ArrayList<Housing> getAllHousings(){
        String url = Statics.BASE_URL+"/housing/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Housings = parseHousings(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Housings;
    }
}
