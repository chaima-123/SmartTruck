/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;


public class Article {

    
    private String ref_article;
    private String designation;
    private String code;
    private float prix_achat;
    private float prix_vente;
    private String unité;
    private int seuil_min;
    private int seuil_max;
    private int fournisseur;
    private int famille;

    public Article(String ref_article, String designation, String code, float prix_achat, float prix_vente, String unité, int seuil_min, int seuil_max, int fournisseur, int famille) {
        this.ref_article = ref_article;
        this.designation = designation;
        this.code = code;
        this.prix_achat = prix_achat;
        this.prix_vente = prix_vente;
        this.unité = unité;
        this.seuil_min = seuil_min;
        this.seuil_max = seuil_max;
        this.fournisseur = fournisseur;
        this.famille = famille;
    }

    public Article() {
    }

    public String getRef_article() {
        return ref_article;
    }

    public void setRef_article(String ref_article) {
        this.ref_article = ref_article;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getPrix_achat() {
        return prix_achat;
    }

    public void setPrix_achat(float prix_achat) {
        this.prix_achat = prix_achat;
    }

    public float getPrix_vente() {
        return prix_vente;
    }

    public void setPrix_vente(float prix_vente) {
        this.prix_vente = prix_vente;
    }

    public String getUnité() {
        return unité;
    }

    public void setUnité(String unité) {
        this.unité = unité;
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

    public int getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(int fournisseur) {
        this.fournisseur = fournisseur;
    }

    public int getFamille() {
        return famille;
    }

    public void setFamille(int famille) {
        this.famille = famille;
    }

    @Override
    public String toString() {
        return "Article{" + "ref_article=" + ref_article + ", designation=" + designation + ", code=" + code + ", prix_achat=" + prix_achat + ", prix_vente=" + prix_vente + ", unit\u00e9=" + unité + ", seuil_min=" + seuil_min + ", seuil_max=" + seuil_max + ", fournisseur=" + fournisseur + ", famille=" + famille + '}';
    }
  

   
}
