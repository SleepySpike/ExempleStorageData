package com.example.exemplestoragedata.entities;

public class Adherent {
    private String nom;
    private String prenom;

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

    public Adherent(){

    }

    public Adherent(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

}
