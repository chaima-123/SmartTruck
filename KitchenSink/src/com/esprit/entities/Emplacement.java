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
public class Emplacement {
    int id,idAllee;
    String intitule ,codeEmp,etat ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAllee() {
        return idAllee;
    }

    public void setIdAllee(int idAllee) {
        this.idAllee = idAllee;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getCodeEmp() {
        return codeEmp;
    }

    public void setCodeEmp(String codeEmp) {
        this.codeEmp = codeEmp;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Emplacement{" + "id=" + id + ", idAllee=" + idAllee + ", intitule=" + intitule + ", codeEmp=" + codeEmp + ", etat=" + etat + '}';
    }
    
    
    
    
    
}
