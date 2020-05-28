/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author nannn
 */
public class Famille {
    private int codeFamille;
    private String nomFamille;

    public Famille(int codeFamille, String nomFamille) {
        this.codeFamille = codeFamille;
        this.nomFamille = nomFamille;
    }

    public Famille() {
    }

    public Famille(String nomFamille) {
        this.nomFamille = nomFamille;
    }

    
    
    

    public int getCodeFamille() {
        return codeFamille;
    }

    public void setCodeFamille(int codeFamille) {
        this.codeFamille = codeFamille;
    }

    public String getNomFamille() {
        return nomFamille;
    }

    public void setNomFamille(String nomFamille) {
        this.nomFamille = nomFamille;
    }

    @Override
    public String toString() {
        return "Famille{" + "codeFamille=" + codeFamille + ", nomFamille=" + nomFamille + '}';
    }

   
    
}
