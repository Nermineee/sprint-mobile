/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.swr.entities;

/**
 *
 * @author mohamed
 */
public class Rating {

    public Rating() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.idR;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rating other = (Rating) obj;
        if (this.idR != other.idR) {
            return false;
        }
        return true;
    }

    private int idR;
    private int idH;
    private int iduser;
    private String feedback;
    private int rating;
    
    public Rating(int idR, int idH, int iduser, String feedback, int rating) {
        this.idR = idR;
        this.idH = idH;
        this.iduser = iduser;
        this.feedback = feedback;
        this.rating = rating;
    }

    public Rating(int idH, int iduser, String feedback, int rating) {
        this.idH = idH;
        this.iduser = iduser;
        this.feedback = feedback;
        this.rating = rating;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public int getidH() {
        return idH;
    }

    public void setidH(int idH) {
        this.idH = idH;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating{" + "idR=" + idR + ", idH=" + idH + ", iduser=" + iduser + ", feedback=" + feedback + ", rating=" + rating + '}';
    }
    
    
}
