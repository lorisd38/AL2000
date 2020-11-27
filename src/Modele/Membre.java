package Modele;

public class Membre extends Client{
    private CarteMembre carteMembre;
    private String nom;
    private String prenom;

    public Membre(String noCB) {
        super(noCB);
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

    public void setCarteMembre(CarteMembre carteMembre) {
        this.carteMembre = carteMembre;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
