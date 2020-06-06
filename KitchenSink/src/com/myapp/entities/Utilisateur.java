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
public class Utilisateur {
    private int id;
    private int cin;
    private String nom,prenom,adresse,role,login,mdp,gender,email,username,password;
    private int telephone;

    public Utilisateur() {
    }
 public Utilisateur(int id) {
        this.id = id;
    }
    public Utilisateur(int id, String prenom) {
        this.id = id;
        this.prenom = prenom;
    }

    public Utilisateur(int id, int cin, String nom, String prenom, String adresse, String role, String login, String mdp, String gender, String email, String username, String password, int telephone) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.role = role;
        this.login = login;
        this.mdp = mdp;
        this.gender = gender;
        this.email = email;
        this.username = username;
        this.password = password;
        this.telephone = telephone;
    }

    public Utilisateur(int cin, String nom, String prenom, String adresse, String role, String login, String mdp, String gender, String email, String username, String password, int telephone) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.role = role;
        this.login = login;
        this.mdp = mdp;
        this.gender = gender;
        this.email = email;
        this.username = username;
        this.password = password;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public int getCin() {
        return cin;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getRole() {
        return role;
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", role=" + role + ", login=" + login + ", mdp=" + mdp + ", gender=" + gender + ", email=" + email + ", username=" + username + ", password=" + password + ", telephone=" + telephone + '}';
    }

    
    
    
}