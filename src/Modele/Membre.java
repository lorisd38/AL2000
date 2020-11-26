package Modele;

public class Membre {
    private CarteMembre carteMembre;
    private String nom;
    private String prenom;
    private String noCB;

    public Membre() {
    }

    public CarteMembre getCarteMembre() {
        return carteMembre;
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

    public void setCarteMembre(CarteMembre carteMembre) {
        this.carteMembre = carteMembre;
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
