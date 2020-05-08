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
import com.swr.entities.User;
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
public class ServiceUser {
        public ArrayList<User> users;
    String s;
    public static ServiceUser instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    
        private ServiceUser() {
         req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }
       public boolean addUser(User u) {
        String url = Statics.BASE_URL + "/addus?nom=" + u.getNom() + "&prenom=" + u.getPrenom()+ "&email=" + u.getMail()+ "&password=" + u.getPwd()+ "&country=" + u.getCountry()+ "&username=" + u.getUsername()+ "&tel=" + u.getTel()+ "&image=" + u.getImage();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
              public void mailing(String mail) {
        String url = Statics.BASE_URL + "/forget?mail="+mail;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
                            public void upuser(String mail) {
        String url = Statics.BASE_URL + "/ituser?mail="+mail;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
              public void deleteu(int id) {
        String url = Statics.BASE_URL + "/deleteu?id="+id;
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
                      public boolean envoi(String mail) {
        String url = Statics.BASE_URL + "/envoi?mail="+mail;
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
                              public boolean verifUser(String mail,String pwd) {
        String url = Statics.BASE_URL + "/verifu?email="+mail+"&password="+pwd;
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
        public boolean pass(String pwd) {
        String url = Statics.BASE_URL + "/modifierpass?pass="+pwd;
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
        public void modifyUser(String nom,String prenom,String country,String password,int tel,String mail,String username,String image,int id) {
        String url = Statics.BASE_URL + "/muser?nom=" + nom + "&prenom=" + prenom+ "&email=" +mail+ "&password=" + password+ "&country=" +country+ "&username=" +username+ "&tel=" +tel+ "&image=" +image+ "&id=" +id;
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
                public void modifpass(String mail,String password) {
        String url = Statics.BASE_URL + "/modu?mail=" + mail +"&password=" +password;
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
            public ArrayList<User> parseTasks(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                User t = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                float it = Float.parseFloat(obj.get("iteration").toString());
                t.setIteration((int)it);
                float tel = Float.parseFloat(obj.get("tel").toString());
                t.setTel((int)tel);
                float ra = Float.parseFloat(obj.get("radom").toString());
                t.setRadom((int)ra);
                t.setPrenom(obj.get("prenom").toString());
                t.setNom(obj.get("nom").toString());
                t.setUsername(obj.get("username").toString());
                t.setMail(obj.get("email").toString());
                t.setPwd(obj.get("password").toString());
                t.setImage(obj.get("image").toString());
                t.setCountry(obj.get("country").toString());
                t.setImage(obj.get("image").toString());
                
                users.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return users;
    }
                public ArrayList<User> affichuser(String mail) {
        String url = Statics.BASE_URL + "/userr/"+mail;
        req.setUrl(url);
        req.setPost(false);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseTasks(new String(req.getResponseData()));
                System.out.println(req.getResponseData());
                System.out.println(users);
                req.removeResponseListener(this);
                System.out.println(users);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(users);
        return users;
    } 
                public ArrayList<User> userit(String mail) {
        String url = Statics.BASE_URL + "/userit?mail="+mail;
        req.setUrl(url);
        req.setPost(false);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseTasks(new String(req.getResponseData()));
                System.out.println(req.getResponseData());
                System.out.println(users);
                req.removeResponseListener(this);
                System.out.println(users);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(users);
        return users;
    }
}
