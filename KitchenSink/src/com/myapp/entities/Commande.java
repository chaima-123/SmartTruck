/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author user
 */
import java.util.Date;

/**
 *
 * @author aa
 */
public class Commande {
   private int id_commande;
   private String num_commande ;
   private  Date  date_commande ;
   private float montant ;
   private String type;
   private Date date_livraison;
   private String etat;
      private Utilisateur u;

    public Commande() {
    }


    public String getEtat() {
        return etat;
    }

    public Commande(String num_commande, Date date_commande, String type, Date date_livraison, Utilisateur u) {
        this.num_commande = num_commande;
        this.date_commande = date_commande;
        this.type = type;
        this.date_livraison = date_livraison;
        this.u = u;
    }
    
     public Commande(String num_commande, Date date_commande, String type, Date date_livraison) {
        this.num_commande = num_commande;
        this.date_commande = date_commande;
        this.type = type;
        this.date_livraison = date_livraison;
    }
   

    public Commande(int id_commande, String num_commande, Date date_commande, float montant, String type, String etat,Utilisateur u) {
        this.id_commande = id_commande;
        this.num_commande = num_commande;
        this.date_commande = date_commande;
        this.montant = montant;
        this.type = type;
        this.etat=etat;
        this.u=u;

    }

    public Commande(String num_commande, Date date_commande, float montant, String type, Date date_livraison, Utilisateur u) {
        this.num_commande = num_commande;
        this.date_commande = date_commande;
        this.montant = montant;
        this.type = type;
        this.date_livraison = date_livraison;
        this.u = u;
    }

 




    public int getId_commande() {
        return id_commande;
    }

    public String getNum_commande() {
        return num_commande;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public float getMontant() {
        return montant;
    }

    public String getType() {
        return type;
    }

    public Date getDate_livraison() {
        return date_livraison;
    }

    public Utilisateur getU() {
        return u;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public void setNum_commande(String num_commande) {
        this.num_commande = num_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }

    public void setU(Utilisateur u) {
        this.u = u;
    }

    @Override
    public String toString() {
        return "Commande{" + "id_commande=" + id_commande + ", num_commande=" + num_commande + ", date_commande=" + date_commande + ", montant=" + montant + ", type=" + type + ", date_livraison=" + date_livraison + ", u=" + u + '}';
    }
   
   
   
   
   
   
}


