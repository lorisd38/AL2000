package Modele;

public class Membre extends Client{
    private CarteMembre carteMembre;
    private Personne personne;

    public Membre(){}

    public Membre(String noCB) {
        super(noCB);
    }

    public CarteMembre getCarteMembre() {
        return carteMembre;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setCarteMembre(CarteMembre carteMembre) {
        this.carteMembre = carteMembre;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }
}
