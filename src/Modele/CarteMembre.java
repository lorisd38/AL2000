package Modele;

public class CarteMembre extends Carte {
	private final int montantMinimalCarte = 15;
	Membre proprietaire;
	
	public CarteMembre(int numeroMembre, Membre proprietaire) {
		id = numeroMembre;
		compteMontant = 0;
		this.proprietaire = proprietaire;
	}
}
