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
public class Deliveryman {
    private int idDman ;
    private String Fname ;
    private String Lname ;
    private int Pnum ;
    private String mail ;
    private int numD ;

    public Deliveryman(int idDman, String Fname, String Lname, int Pnum, String mail) {
        this.idDman = idDman;
        this.Fname = Fname;
        this.Lname = Lname;
        this.Pnum = Pnum;
        this.mail = mail;
    }

    public Deliveryman(String Fname, String Lname, int Pnum, String mail) {
        this.Fname = Fname;
        this.Lname = Lname;
        this.Pnum = Pnum;
        this.mail = mail;
    }

    public Deliveryman(String mail) {
        this.mail = mail;
    }


   

    public int getidDman() {
        return idDman;
    }

    public void setidDman(int idDman) {
        this.idDman = idDman;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    public int getPnum() {
        return Pnum;
    }

    public void setPnum(int Pnum) {
        this.Pnum = Pnum;
    }

    public String getmail() {
        return mail;
    }

    public void setmail(String mail) {
        this.mail = mail;
    }

    public int getnumD() {
        return numD;
    }

    public void setnumD(int numD) {
        this.numD = numD;
    }

    @Override
    public String toString() {
        return "Deliveryman{" + "idDman=" + idDman + ", Fname=" + Fname + ", Lname=" + Lname + ", Pnum=" + Pnum + ", mail=" + mail + ", numD=" + numD + '}';
    }

  
    
    
}
