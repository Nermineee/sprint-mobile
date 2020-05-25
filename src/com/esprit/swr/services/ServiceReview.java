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
import com.esprit.swr.entities.User;
import com.esprit.swr.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceReview {

    public ArrayList<Rating> Reviews;
    public ArrayList<Rating> AvgReview;
    public Map<Rating,User> ratings;
    
    public static ServiceReview instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReview() {
         req = new ConnectionRequest();
    }

    public static ServiceReview getInstance() {
        if (instance == null) {
            instance = new ServiceReview();
        }
        return instance;
    }

public boolean addReview(Rating t) {
        String url = Statics.BASE_URL + "/housing/rating/new?idr="+ t.getIdR()+ "&idhouse="+ t.getidH()+ "&iduser=" + t.getIduser()+ "&rating=" + t.getRating()+ "&feedback=" + t.getFeedback(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

 public Map<Rating,User> parseRatings(String jsonText,int id){
        try {
            Reviews=new ArrayList<>();
            ratings= new HashMap<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            
            for(Map<String,Object> obj : list){
               
                Rating t = new Rating();
                User u=new User();
                float idr = Float.parseFloat(obj.get("idr").toString());
                t.setIdR((int)idr);
                t.setidH(id);
                float idu = Float.parseFloat(obj.get("id").toString());
                System.out.println(idu);
                t.setIduser((int)idu);
                //t.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
                t.setFeedback(obj.get("feedback").toString());
                t.setRating((int)Float.parseFloat(obj.get("rating").toString()));
                u.setUsername(obj.get("username").toString());
                u.setId((int)idu);
                u.setImage(obj.get("image").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                
               // Reviews.add(t);
                
               ratings.put(t,u);
              
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
          
        return ratings;
    }
    
    
    public ArrayList<Rating> parseavgRatings(String jsonText){
        try {
           AvgReview=new ArrayList<>();
            
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

           
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
             
              Rating t = new Rating();
                
                float idr = Float.parseFloat(obj.get("idr").toString());
                t.setIdR((int)idr);
                float idh = Float.parseFloat(obj.get("idh").toString());
                t.setidH((int)idh);
                t.setFeedback(obj.get("feedback").toString());
                t.setRating((int)Float.parseFloat(obj.get("rating").toString()));
                
                AvgReview.add(t);
              
            }
            
            
        } catch (IOException ex) {
            System.out.println("Lmochkel lena");
        }
        
        return AvgReview;
    }
    
    
    public ArrayList<Rating> getavgReviews(){
        String url = Statics.BASE_URL+"/housing/ratings/avg";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AvgReview = parseavgRatings(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return AvgReview;
    }
    
    public Map<Rating,User> getAllReviews(int id){
        String url = Statics.BASE_URL+"/housing/"+id+"/ratings";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ratings = parseRatings(new String(req.getResponseData()),id);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ratings;
    }
}
