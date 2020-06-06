/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class fos_user {

    private int id, enabled, telephone;
    //private tinyint enabled;
    private String username, username_canonical, email, email_canonical, salt, password, confiramtion_token, roles, nom, prenom, adresse, grade;
    private Date last_login, password_requested_at;

    public fos_user() {
    }

    public fos_user(int id, int enabled, int telephone, String username, String username_canonical, String email, String email_canonical, String salt, String password, String confiramtion_token, String roles, String nom, String prenom, String adresse, String grade, Date last_login, Date password_requested_at) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.last_login = last_login;
        this.confiramtion_token = confiramtion_token;
        this.password_requested_at = password_requested_at;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.grade = grade;
    }

    public fos_user(int enabled, int telephone, String username, String username_canonical, String email, String email_canonical, String salt, String password, String confiramtion_token, String roles, String nom, String prenom, String adresse, String grade, Date last_login, Date password_requested_at) {
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.last_login = last_login;
        this.confiramtion_token = confiramtion_token;
        this.password_requested_at = password_requested_at;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.grade = grade;
    }

    public fos_user(int id, int telephone, String nom, String prenom, String adresse, String email, String grade, String username, String password) {
        this.id = id;
        this.telephone = telephone;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.grade = grade;
        this.username = username;
        this.password = password;
    }

    public fos_user(String nom, String prenom, String adresse, int telephone, String email, String grade, String username, String password) {
        this.telephone = telephone;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.grade = grade;
        this.username = username;
        this.password = password;
    }

    public fos_user(String nom, String prenom, String adresse, int telephone, String email, String username, String password) {
        this.telephone = telephone;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public fos_user(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfiramtion_token() {
        return confiramtion_token;
    }

    public void setConfiramtion_token(String confiramtion_token) {
        this.confiramtion_token = confiramtion_token;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public Date getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(Date password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    @Override
    public String toString() {
        return "fos_user{" + "id=" + id + ", enabled=" + enabled + ", telephone=" + telephone + ", username=" + username + ", username_canonical=" + username_canonical + ", email=" + email + ", email_canonical=" + email_canonical + ", salt=" + salt + ", password=" + password + ", confiramtion_token=" + confiramtion_token + ", roles=" + roles + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", grade=" + grade + ", last_login=" + last_login + ", password_requested_at=" + password_requested_at + '}';
    }

}
