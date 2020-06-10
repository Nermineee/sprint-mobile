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
import com.swr.entities.Goods;
import com.swr.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class ServiceGoods {
 
    public ArrayList<Goods> Goods;
    
    public static ServiceGoods instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceGoods() {
         req = new ConnectionRequest();
    }

    public static ServiceGoods getInstance() {
        if (instance == null) {
            instance = new ServiceGoods();
        }
        return instance;
    }

   
    public ArrayList<Goods> parseGoods(String jsonText){
        try {
            Goods=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Goods o = new Goods();
                Item i=new Item();
                Housing h=new Housing();
                i.setNameItem(obj.get("name").toString());
                h.setAddress(obj.get("address").toString());
                //float idH = Float.parseFloat(obj.get("idh").toString());
                
                //float idG = Float.parseFloat(obj.get("idg").toString());
                //float idUser = Float.parseFloat(obj.get("iduser").toString());
                
                o.setStatus(obj.get("status").toString());
                o.setQcollected((int)Float.parseFloat(obj.get("qcollected").toString()));                
                o.setIdH(h);
                o.setItem(i);
                //o.setIdH((Housing)obj.get("address"));
                  //o.setItem((Item)obj.get("name"));
               
                
                Goods.add(o);
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return Goods;
    }
    public boolean addGoods(Goods g) {
        String url = Statics.BASE_URL + "/delivery/goodss/new?item=" + g.getItem().getNameItem() + "&qc="+ g.getQcollected()+"&iduser=" +g.getIdUser()+"&idh="+ g.getIdH().getName();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public ArrayList<Goods> getAllGoods(){
        String url = Statics.BASE_URL+"/delivery/goodss/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Goods = parseGoods(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Goods;
    }
}

