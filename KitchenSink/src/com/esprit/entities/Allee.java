/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author mac
 */
public class Allee {
    
    public int id,niveau,nbTrav ;
    public String ligne ;

    public int getId() {
        return id;
    }

    public Allee( String ligne,int nbTrav ) {
        this.nbTrav = nbTrav;
        this.ligne = ligne;
    }

    public Allee( String ligne, int nbTrav,int niveau ) {
        this.niveau = niveau;
        this.nbTrav = nbTrav;
        this.ligne = ligne;
    }

    public Allee() {
       
    }
   

    public void setId(int id) {
        this.id = id;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getNbTrav() {
        return nbTrav;
    }

    public void setNbTrav(int nbTrav) {
        this.nbTrav = nbTrav;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }

    @Override
    public String toString() {
        return "Allee{" + "id=" + id + ", niveau=" + niveau + ", nbTrav=" + nbTrav + ", ligne=" + ligne + '}';
    }

    
    
    
    
    
    
}
