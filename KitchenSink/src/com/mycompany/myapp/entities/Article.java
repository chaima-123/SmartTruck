/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author aa
 */
public class Article {
    private String refArticle;
     private String code;
      private String designation;
    private float prix;
    private String marque;
    private int seuil_max;
    private int sueil_min;
    private int id_personne;
    private int id_famille;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
   

    public Article(String refArticle) {
        this.refArticle = refArticle;
    }

    public String getRefArticle() {
        return refArticle;
    }

    public Article() {
    }

    public void setRefArticle(String refArticle) {
        this.refArticle = refArticle;
    }
    
    
    
}
