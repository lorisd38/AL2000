package Modele;

import java.sql.SQLException;
import java.util.Date;

public class TestMembre {
    public static void main(String[] argv) throws SQLException {
        MembreDAO fDAO = new MembreDAO();
        CarteMembre caarte = new CarteMembre();
        caarte.setCompteMontant(-10);
        caarte.setDateNeg(new Date());
        Membre test = new Membre("73", caarte, new Personne("Nowak","Theo"));
        try {
            //fDAO.create(locs, new Client("65"))
            fDAO.delete("60");
            fDAO.read('e');
            System.out.println("Closing connections...");
            fDAO.connection.close();
        }catch (Exception e){
            System.err.println(e);
            System.out.println("Closing connections...");
            fDAO.connection.close();
        }
    }
}
