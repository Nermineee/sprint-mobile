/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.entities;

/**
 *
 * @author Asus
 */
public class Goods {

    @Override
    public String toString() {
        return "Goods{" + "idG=" + idG + ", idH=" + idH + ", idUser=" + idUser + ", item=" + item + ", Qcollected=" + Qcollected + ", status=" + status + '}';
    }

    public int getIdG() {
        return idG;
    }

    public void setIdG(int idG) {
        this.idG = idG;
    }

    public Housing getIdH() {
        return idH;
    }

    public void setIdH(Housing idH) {
        this.idH = idH;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQcollected() {
        return Qcollected;
    }

    public void setQcollected(int Qcollected) {
        this.Qcollected = Qcollected;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Goods() {
    }
    
    public Goods(int idG, Housing idH, int idUser, Item item, int Qcollected, String status) {
        this.idG = idG;
        this.idH = idH;
        this.idUser = idUser;
        this.item = item;
        this.Qcollected = Qcollected;
        this.status = status;
    }

    public Goods(Housing idH, int idUser, Item item, int Qcollected, String status) {
        this.idH = idH;
        this.idUser = idUser;
        this.item = item;
        this.Qcollected = Qcollected;
        this.status = status;
    }

    public Goods(Item item, int Qcollected, String status) {
        this.item = item;
        this.Qcollected = Qcollected;
        this.status = status;
    }

   
    
    private int idG ;
    private Housing idH ;
    private int idUser ;
    private Item item ;
    private int Qcollected ;
    private String status ; 

    public Goods(Housing idH) {
        this.idH = idH;
    }

    

   
      
}





