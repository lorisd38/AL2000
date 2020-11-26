package Modele;

abstract class Carte {
	protected int compteMontant;
	protected int id;
	
	public int getNumeroCarte() {
		return id;
	}
	
	public int getMontant() {
		return compteMontant;
	}
	
	public void debit(int montant) throws Exception {
		if (montant > this.getMontant()) 
			throw new Exception("PAS ASSEZ D'ARGENT SUR LE COMPTE");
		this.compteMontant -= montant;
	}

	public void simule(int montant) throws Exception {
		if(montant > compteMontant) 
			throw new Exception("Fond insuffisant");
		
	}
}
