/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.entities;

/**
 *
 * @author mohamed
 */
public class Item {

    public Item() {
    }
    
    private int idItem;
    private int idhouse;
    private String nameItem;
    private String description;
    private int Quantity;
    private String expDate;
    private String statusItem;
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    public Item(int idItem, int idhouse, String nameItem, String description, int Quantity ,String expDate, String statusItem) {
        this.idItem = idItem;
        this.idhouse = idhouse;
        this.nameItem = nameItem;
        this.description=description;
        this.expDate = expDate;
        this.Quantity = Quantity;
        this.statusItem = statusItem;
    }

    public Item(int idhouse, String nameItem,String description, int Quantity ,String expDate, String statusItem) {
        this.idhouse = idhouse;
        this.nameItem = nameItem;
        this.description=description;
        this.expDate = expDate;
        this.Quantity = Quantity;
        this.statusItem = statusItem;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getIdhouse() {
        return idhouse;
    }

    public void setIdhouse(int idhouse) {
        this.idhouse = idhouse;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public String getStatusItem() {
        return statusItem;
    }

    public void setStatusItem(String statusItem) {
        this.statusItem = statusItem;
    }

    @Override
    public String toString() {
        return "Item{" + "idItem=" + idItem + ", idhouse=" + idhouse + ", nameItem=" + nameItem + ", expDate=" + expDate + ", Quantity=" + Quantity + ", statusItem=" + statusItem + '}';
    }
    
}
