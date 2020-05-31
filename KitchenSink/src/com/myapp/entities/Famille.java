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
public class Famille {
    private int codeFamille;
    private String nomFamille;

    public Famille() {
    }

    public int getCodeFamille() {
        return codeFamille;
    }

    public String getNomFamille() {
        return nomFamille;
    }

    public void setCodeFamille(int codeFamille) {
        this.codeFamille = codeFamille;
    }

    public void setNomFamille(String nomFamille) {
        this.nomFamille = nomFamille;
    }

    @Override
    public String toString() {
        return "Famille{" + "codeFamille=" + codeFamille + ", nomFamille=" + nomFamille + '}';
    }
    
    
    
}
