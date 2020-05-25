/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.swr.entities;

/**
 *
 * @author Asus
 */
public class goods {

    public goods(int idG, int idH, int idUser, int item, int Qcollected, String status) {
        this.idG = idG;
        this.idH = idH;
        this.idUser = idUser;
        this.item = item;
        this.Qcollected = Qcollected;
        this.status = status;
    }

    public goods(int idH, int idUser, int item, int Qcollected, String status) {
        this.idH = idH;
        this.idUser = idUser;
        this.item = item;
        this.Qcollected = Qcollected;
        this.status = status;
    }
    private int idG ;
    private int idH ;
    private int idUser ;
    private int item ;
    private int Qcollected ;
    private String status ; 

    

    public int getIdG() {
        return idG;
    }

    public void setIdG(int idG) {
        this.idG = idG;
    }

    public int getIdH() {
        return idH;
    }

    public void setIdH(int idH) {
        this.idH = idH;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
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

    @Override
    public String toString() {
        return "goods{" + "idG=" + idG + ", idH=" + idH + ", idUser=" + idUser + ", item=" + item + ", Qcollected=" + Qcollected + ", status=" + status + '}';
    }
    
      
}





