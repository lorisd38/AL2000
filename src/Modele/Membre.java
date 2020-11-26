package Modele;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Date;


public class Membre extends Client{
	private String nom, prenom;
	private CarteMembre carte;
	private final Date dateInscription;
	private ArrayList<Location> historique;
	private ArrayList<Reservation> reservations;
	private ArrayList<Genre> preference;
	
	public Membre(String nom, String prenom) {
		super("3");
		dateInscription = new Date();
		this.nom = nom;
		this.prenom = prenom;
		historique = new ArrayList<>();
		reservations = new ArrayList<>();
		preference = new ArrayList<>();
	}
	
	public Membre() {
		super("3");
		dateInscription = new Date();
	}

}
