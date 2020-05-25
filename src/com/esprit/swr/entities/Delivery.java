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
public class Delivery {
    private int IdDe ;
    private int idDman ;
    private String DateD ;
    private int idItem ;

    public Delivery(int IdDe, int idDman, String DateD, int idItem) {
        this.IdDe = IdDe;
        this.idDman = idDman;
        this.DateD = DateD;
        this.idItem = idItem;
    }

    public Delivery(int idDman, String DateD, int idItem) {
        this.idDman = idDman;
        this.DateD = DateD;
        this.idItem = idItem;
    }

    public Delivery(String DateD, int idItem) {
        this.DateD = DateD;
        this.idItem = idItem;
    }

    public int getIdDe() {
        return IdDe;
    }

    public void setIdDe(int IdDe) {
        this.IdDe = IdDe;
    }

    public int getIdDman() {
        return idDman;
    }

    public void setIdDman(int idDman) {
        this.idDman = idDman;
    }

    public String getDateD() {
        return DateD;
    }

    public void setDateD(String DateD) {
        this.DateD = DateD;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    @Override
    public String toString() {
        return "delivery{" + "IdDe=" + IdDe + ", idDman=" + idDman + ", DateD=" + DateD + ", idItem=" + idItem + '}';
    }
    
    
}
