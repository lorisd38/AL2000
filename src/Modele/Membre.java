package Modele;

public class Membre {
    private String idCarteMembre;
    private String nom;
    private String prenom;
    private String noCB;

    public Membre() {
    }

    public Membre(String idCarteMembre, String nom, String prenom, String noCB) {
        this.idCarteMembre = idCarteMembre;
        this.nom = nom;
        this.prenom = prenom;
        this.noCB = noCB;
    }

    public String getIdCarteMembre() {
        return idCarteMembre;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNoCB() {
        return noCB;
    }

    public void setIdCarteMembre(String idCarteMembre) {
        this.idCarteMembre = idCarteMembre;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNoCB(String noCB) {
        this.noCB = noCB;
    }
}
