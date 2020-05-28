/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import com.codename1.ui.Image;



public class Stock {
     private int num_lot;
    private String ref_article;
    private int quantiteTotale;
    private int seuil_min;
    private int seuil_max;
    private String designation;
    private String Image;

    public Stock(int num_lot, String ref_article, int quantiteTotale, int seuil_min, int seuil_max, String designation) {
        this.num_lot = num_lot;
        this.ref_article = ref_article;
        this.quantiteTotale = quantiteTotale;
        this.seuil_min = seuil_min;
        this.seuil_max = seuil_max;
        this.designation = designation;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
 public Stock(String Image) {
this.Image= Image;
    }
    public Stock() {

    }

    @Override
    public String toString() {
        return "Stock{" + "num_lot=" + num_lot + ", ref_article=" + ref_article + ", quantiteTotale=" + quantiteTotale + ", seuil_min=" + seuil_min + ", seuil_max=" + seuil_max + ", designation=" + designation + '}';
    }

    
    
    
    
    public int getNum_lot() {
        return num_lot;
    }

    public void setNum_lot(int num_lot) {
        this.num_lot = num_lot;
    }

    public String getRef_article() {
        return ref_article;
    }

    public void setRef_article(String ref_article) {
        this.ref_article = ref_article;
    }

    public int getQuantiteTotale() {
        return quantiteTotale;
    }

    public void setQuantiteTotale(int quantiteTotale) {
        this.quantiteTotale = quantiteTotale;
    }

    public int getSeuil_min() {
        return seuil_min;
    }

    public void setSeuil_min(int seuil_min) {
        this.seuil_min = seuil_min;
    }

    public int getSeuil_max() {
        return seuil_max;
    }

    public void setSeuil_max(int seuil_max) {
        this.seuil_max = seuil_max;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    
    
    
    
}
