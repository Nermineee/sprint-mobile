/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.entities;

/**
 *
 * @author Asus X550V
 */
public class User {
    private int id;
    private String nom;
    private String prenom;
    private String username;
    private String roles;
    private String dateins;
    private String country;
    private String mail;
    private String pwd;
    private int tel;
    private int randoom;
    private int iteration;
    private String image;

    public User(int id, String nom, String prenom, String country, String mail, String pwd, int tel,String username,String roles,String dateins) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.country = country;
        this.mail = mail;
        this.pwd = pwd;
        this.tel = tel;
        this.username = username;
        this.roles = roles;
        this.dateins = dateins;
    }
    public User(String nom, String prenom, String country, String mail,int tel, String pwd,String username,String roles,String dateins,String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.country = country;
        this.mail = mail;
        this.pwd = pwd;
        this.tel = tel;
        this.username = username;
        this.roles = roles;
        this.dateins = dateins;
    this.image = image;
    }
    public User(String nom, String prenom, String country, String mail,int tel, String pwd,String username,String roles,String dateins,int randoom,String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.country = country;
        this.mail = mail;
        this.pwd = pwd;
        this.tel = tel;
        this.username = username;
        this.roles = roles;
        this.dateins = dateins;
        this.randoom = randoom;
        this.image = image;
    }
    public User(String nom, String prenom, String country, String mail, int tel, String pwd,String username,String roles,String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.country = country;
        this.mail = mail;
        this.pwd = pwd;
        this.tel = tel;
        this.username = username;
        this.roles = roles;
        this.image = image;
        
        
        
    }
    public User(String nom, String prenom, String country, String mail, int tel, String pwd,String username,String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.country = country;
        this.mail = mail;
        this.pwd = pwd;
        this.tel = tel;
        this.username = username;
        this.image = image;
        
    }
    public User(String nom, String prenom, String country,String pwd, int tel, String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.country = country;
        this.pwd = pwd;
        this.tel = tel;
        this.image = image;
        
        
        
    }

    public User() {
         //To change body of generated methods, choose Tools | Templates.
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        public void setRadom(int randoom) {
        this.randoom = randoom;
    }

    public String getNom() {
        return nom;
    }
        public int getRadom() {
        return randoom;
    }
    public String getImage() {
        return image;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getRoles() {
        return roles;
    }
    
    public String getDateins() {
        return dateins;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
        public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public String getCountry() {
        return country;
    }
    public String getMail() {
        return mail;
    }
    public String getPwd() {
        return pwd;
    }
    public int getTel() {
        return tel;
    }
        public void setMail(String mail) {
        this.mail = mail;
    }
                public void setCountry(String co) {
        this.country = co;
    }
                                public void setImage(String co) {
        this.image = co;
    }
                                                                public void setPwd(String co) {
        this.pwd = co;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom +", image=" + image+ ", Country=" + country + ",Mail:"+mail+",Username:"+username+",password:"+pwd+",Tel:"+tel+ '}';
    }   
}
