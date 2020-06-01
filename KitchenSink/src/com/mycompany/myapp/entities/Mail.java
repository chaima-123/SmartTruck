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
public class Mail {
    private String mail, objet, sujet,frs;

    public String getFrs() {
        return frs;
    }

    public void setFrs(String frs) {
        this.frs = frs;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public Mail(String mail, String objet, String sujet, String frs) {
        this.mail = mail;
        this.objet = objet;
        this.sujet = sujet;
        this.frs = frs;
    }

    @Override
    public String toString() {
        return "Mail{" + "mail=" + mail + ", objet=" + objet + ", sujet=" + sujet + ", frs=" + frs + '}';
    }

    
    
    
    
}
