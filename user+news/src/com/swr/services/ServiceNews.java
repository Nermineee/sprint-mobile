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
import com.swr.entities.News;
import com.swr.utils.Statics;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus X550V
 */
public class ServiceNews {
        public ArrayList<News> News;
    String s;
    public static ServiceNews instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
     private ServiceNews() {
         req = new ConnectionRequest();
    }

    public static ServiceNews getInstance() {
        if (instance == null) {
            instance = new ServiceNews();
        }
        return instance;
    }
    public ArrayList<News> parseTasks(String jsonText){
        try {
            News=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                News t = new News();
                float id = Float.parseFloat(obj.get("idn").toString());
                t.setIdn((int)id);
                float view = Float.parseFloat(obj.get("view").toString());
                t.setView((int)view);
                t.setTitre(obj.get("titre").toString());
                t.setDatepub(obj.get("datepub").toString());
                t.setDesc(obj.get("description").toString());
                t.setNomcat(obj.get("nomcat").toString());
                t.setImage(obj.get("image").toString());
                News.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return News;
    }

                    public ArrayList<News> affichnews() {
        String url = Statics.BASE_URL + "/newsm";
        req.setUrl(url);
        req.setPost(false);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                News = parseTasks(new String(req.getResponseData()));
                System.out.println(req.getResponseData());
                System.out.println(News);
                req.removeResponseListener(this);
                System.out.println(News);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(News);
        return News;
    }
                  public void viewm(int id) {
        String url = Statics.BASE_URL + "/viewm?idn="+id;
        req.setUrl(url);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public ArrayList<News> affichnewsd(int id) {
        String url = Statics.BASE_URL + "/newsmd?idn="+id;
        req.setUrl(url);
        req.setPost(false);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(req.getResponseData());
                News = parseTasks(new String(req.getResponseData()));
                System.out.println(req.getResponseData());
                System.out.println(News);
                req.removeResponseListener(this);
                System.out.println(News);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(News);
        return News;
   }
    
        public ArrayList<News> affichnewscat(String cat,int id) {
        String url = Statics.BASE_URL + "/newscatm?nomcat="+cat+"&idn="+id;
        req.setUrl(url);
        req.setPost(false);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(req.getResponseData());
                News = parseTasks(new String(req.getResponseData()));
                System.out.println(req.getResponseData());
                System.out.println(News);
                req.removeResponseListener(this);
                System.out.println(News);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(News);
        return News;
   }
                public ArrayList<News> pdfnews(int id) {
        String url = Statics.BASE_URL + "/pdfnewsm/"+id;
        req.setUrl(url);
        req.setPost(false);
        System.out.println(url);
        return News;
   }
                        public String nblikes(int id) {
        String url = Statics.BASE_URL + "/newsnb/"+id;
        req.setUrl(url);
        req.setPost(false);
       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String response="";
               try {
		                            response = new String(req.getResponseData(), "utf-8");
		                        } catch (UnsupportedEncodingException ex) {
		                            System.out.println(ex.getMessage());
		                        } 
              s=response;
              
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
     return s;
    }
                                public boolean verifLike(int idn,int id) {
        String url = Statics.BASE_URL + "/searlike/"+idn+"/"+id;
        req.setUrl(url);
        req.setPost(false);
       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String response="";
               try {
		                            response = new String(req.getResponseData(), "utf-8");
		                        } catch (UnsupportedEncodingException ex) {
		                            System.out.println(ex.getMessage());
		                        } 
              s=response;
              
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
  if(   "true".equals(s))
  {
  return true;
  }
  return false;
    }
                                        public void deletelike(int id,int idn) {
        String url = Statics.BASE_URL + "/deletelike/"+idn+"/"+id;
        req.setUrl(url);
        req.setPost(false);
       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String response="";
               try {
		                            response = new String(req.getResponseData(), "utf-8");
		                        } catch (UnsupportedEncodingException ex) {
		                            System.out.println(ex.getMessage());
		                        } 
              s=response;
              
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
        public void addlike(int id,int idn) {
        String url = Statics.BASE_URL + "/addlike/"+idn+"/"+id;
        req.setUrl(url);
        req.setPost(false);
       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String response="";
               try {
		                            response = new String(req.getResponseData(), "utf-8");
		                        } catch (UnsupportedEncodingException ex) {
		                            System.out.println(ex.getMessage());
		                        } 
              s=response;
              
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
}

