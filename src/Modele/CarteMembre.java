package Modele;

import java.sql.Date;

public class CarteMembre {
    private double montant;
    private Date dateNeg;

    public double getMontant() {
        return montant;
    }

    public Date getDateNeg() {
        return dateNeg;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setDateNeg(Date dateNeg) {
        this.dateNeg = dateNeg;
    }
}
