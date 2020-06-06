/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author aa
 */
public class CommandeE {
    private int qte,id;
    private String ref, designation,code,numCommande,nomSociete,etat ;
    private Date dateCommande,dateLivraison  ;
    private float montant; 

    public CommandeE(int qte, String ref, String numCommande, String nomSociete, String etat, Date dateCommande, Date dateLivraison, float montant) {
        this.qte = qte;
        this.ref = ref;
        this.numCommande = numCommande;
        this.nomSociete = nomSociete;
        this.etat = etat;
        this.dateCommande = dateCommande;
        this.dateLivraison = dateLivraison;
        this.montant = montant;
    }
    public CommandeE(int id,int qte, String ref, String numCommande,  String etat, Date dateCommande, float montant) {
        this.id=id;
        this.qte = qte;
        this.ref = ref;
        this.numCommande = numCommande;
        this.etat = etat;
        this.dateCommande = dateCommande;
        this.montant = montant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    

    public String getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(String numCommande) {
        this.numCommande = numCommande;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }
    
     

    public CommandeE() {
    }

    
    
    
    
    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
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

    @Override
    public String toString() {
        return "CommandeE{" + "qte=" + qte + ", ref=" + ref + ", numCommande=" + numCommande + ", nomSociete=" + nomSociete + ", etat=" + etat + ", montant=" + montant + ", datelivraison=" + dateLivraison +   ", date commande =" + dateCommande + '}';
    }
    
    
    
}
