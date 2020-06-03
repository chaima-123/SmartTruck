/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;
import java.util.Objects;

public class Palette {
    private int num_lot;
    private int qte;
    private Date date_expiration;
   private String  ref,codeEmp;


    public Date getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(Date date_expiration) {
        this.date_expiration = date_expiration;
    }

    

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
    private Article article;

    public void setArticle(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }

    public Palette( int qte, Date date_expiration, String ref, String codeEmp) {
        //this.num_lot = num_lot;
        this.qte = qte;
        this.codeEmp = codeEmp;
        this.date_expiration= date_expiration;
        this.ref= ref; 
        
    }
    public Palette( int num_lot,int qte, Date date_expiration, String ref, String codeEmp) {
        this.num_lot = num_lot;
        this.qte = qte;
        this.codeEmp = codeEmp;
        this.date_expiration= date_expiration;
        this.ref= ref; 
      
        
    }
     public Palette( int qte, String ref, String codeEmp) {
        //this.num_lot = num_lot;
        this.qte = qte;
        this.codeEmp = codeEmp;
        this.ref= ref; 
        
    }

    public Palette(int qte) {
        this.qte = qte;
    }

    

    public String getCodeEmp() {
        return codeEmp;
    }

    public void setCodeEmp(String codeEmp) {
        this.codeEmp = codeEmp;
    }

    
   
    public int getNum_lot() {
        return num_lot;
    }

    public void setNum_lot(int num_lot) {
        this.num_lot = num_lot;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

   

    @Override
    public String toString() {
        return "Palette{" + "num_lot=" + num_lot + ", qte=" + qte + ", date_expiration=" + date_expiration + ", codeEmp=" + codeEmp + '}';
    }

    public Palette() {
    }

   



    
    
    
}
