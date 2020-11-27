package Modele;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class Film {
    private String nom = "Inconnu";
    private String producteur;
    private Personne realisateur;
    private String resume = "Inconnu";
    private ArrayList<Personne> acteurs;
    private Date dateDeSortie;
    private String affiche;
    private int ageLimite;
    private Support support;
    private ArrayList<Genre> genre;

    public Film(){

    }

    public Film(String nom, String producteur, Personne realisateur, String resume, ArrayList<Personne> acteurs, Date dateDeSortie, String affiche, int ageLimite, Support support, ArrayList<Genre> genre) {
        this.nom = nom;
        this.producteur = producteur;
        this.realisateur = realisateur;
        this.resume = resume;
        this.acteurs = acteurs;
        this.dateDeSortie = dateDeSortie;
        this.affiche = affiche;
        this.ageLimite = ageLimite;
        this.support = support;
        this.genre = genre;
    }

    public String getNom() {
        return nom;
    }

    public String getProducteur() {
        return producteur;
    }

    public Personne getRealisateur() {
        return realisateur;
    }

    public String getResume() {
        return resume;
    }

    public ArrayList<Personne> getActeurs() {
        return acteurs;
    }

    public Date getDateDeSortie() {
        return dateDeSortie;
    }

    public String getAffiche() {
        return affiche;
    }

    public int getAgeLimite() {
        return ageLimite;
    }

    public Support getSupport() {
        return support;
    }

    public ArrayList<Genre> getGenre() {
        return genre;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setProducteur(String producteur) {
        this.producteur = producteur;
    }

    public void setRealisateur(Personne realisateur) {
        this.realisateur = realisateur;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setActeurs(ArrayList<Personne> acteurs) {
        this.acteurs = acteurs;
    }

    public void setDateDeSortie(Date dateDeSortie) {
        this.dateDeSortie = dateDeSortie;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public void setAgeLimite(int ageLimite) {
        this.ageLimite = ageLimite;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    public void setGenre(ArrayList<Genre> genre) {
        this.genre = genre;
    }
}
