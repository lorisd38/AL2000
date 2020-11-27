package Modele;

public class GestionnaireBanque {
	Carte carte;
	
	public GestionnaireBanque() {
		carte = new CarteBancaire();
	}
	
	public void remplirCarteMembre(CarteMembre c, int montant) throws Exception {
		try {
			carte.simule(montant);
			//c.credit(montant);
			carte.debit(montant);	
		}
		catch (Exception e) {
			System.out.println(carte.compteMontant + " " + c.compteMontant);
			throw e;
		}		
	}
	
	public void setCarte(Carte carte) {
		this.carte = carte;
	}
	
	public void payer(int d) throws Exception {
		carte.debit(d);
	}
}
