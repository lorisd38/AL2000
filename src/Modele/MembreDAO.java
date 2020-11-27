package Modele;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

public class MembreDAO extends SqlDAO<Membre> {

    public MembreDAO() throws SQLException {
        super();
    }

    @Override
    public Membre read(Object o) throws SQLException {
        Membre membre = new Membre();
        try{
            ResultSet result = this.connection.createStatement().executeQuery(
                    "SELECT TREAT(VALUE(m) AS tmembre).noCB as noCB, TREAT(VALUE(m) AS tmembre).carteMembre.montant as montant, TREAT(VALUE(m) AS tmembre).carteMembre.dateNeg as dateNeg, TREAT(VALUE(m) AS tmembre).membre.prenom as prenom, TREAT(VALUE(m) AS tmembre).membre.nom as nom FROM LesClientsAL m WHERE VALUE(m) IS OF (tmembre)");

            CarteMembre cm = new CarteMembre();
            Personne prop = new Personne();

            while(result.next()){
                if(result.getObject("noCB") != null){
                    System.out.println(result.getObject("noCB").toString());
                    membre.setNoCB(result.getObject("noCB").toString());
                } else {
                    membre = null;
                }
                if(result.getObject("montant") != null){
                    System.out.println(result.getObject("montant").toString());
                    cm.setCompteMontant(Integer.valueOf(result.getObject("montant").toString()));
                }
                if(result.getObject("dateNeg") != null){
                    System.out.println(result.getObject("dateNeg").toString());
                    cm.setDateNeg(result.getDate("dateNeg"));
                } else {
                    cm.setDateNeg(null) ;
                }
                if(result.getObject("prenom") != null){
                    System.out.println(result.getObject("prenom").toString());
                    prop.setPrenom(result.getObject("prenom").toString());
                } else {
                    prop = null;
                }
                if(result.getObject("nom") != null){
                    System.out.println(result.getObject("nom").toString());
                    prop.setNom(result.getObject("nom").toString());
                } else {
                    membre = null;
                }
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return membre;
    }

    @Override
    public boolean create(Membre obj) {
        try {
            String query = "insert into LesClientsAL values (tmembre('";
            query +=  obj.getNoCB() + "', ";
            query += "tcartemembreA(" + obj.getCarteMembre().getMontant() + ", ";
            String pattern = "yyyy-MM-dd";
            DateFormat df = new SimpleDateFormat(pattern);
            if(obj.getCarteMembre().getDateNeg() != null) {
                query += "DATE '" + df.format(obj.getCarteMembre().getDateNeg()) + "'), ";
            } else {
                query +="null ), ";
            }

            query += "tpersonne('" + obj.getPersonne().getNom() + "', '" + obj.getPersonne().getPrenom() + "')))";

            System.out.println(query);
            PreparedStatement preparedStmt;
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Membre obj) {
        String pattern = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(pattern);

        String query = "UPDATE LesClientsAL m SET value(m) = tmembre('" + obj.getNoCB() + "', tcartemembreA(";
        query += obj.getCarteMembre().getMontant() + ", ";
        if(obj.getCarteMembre().getDateNeg() != null) {
            query += "DATE '" + df.format(obj.getCarteMembre().getDateNeg()) + "'), tpersonne('";
        } else {
            query +="null ), tpersonne('";
        }

        query += obj.getPersonne().getNom()+"', '";
        query += obj.getPersonne().getPrenom() + "'))WHERE m.noCB =";
        query += obj.getNoCB();
        PreparedStatement preparedStmt;

        System.out.println(query);
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean delete(Membre obj) {
        return delete(obj.getNoCB());

    }

    public boolean delete(String noCB) {
        int nbMaJ = 0;
        String requete = "DELETE FROM LesClientsAL WHERE noCB = '" + noCB + "'";
        try {
            // Execution de la requete
            PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
            nbMaJ = preparedStatement.executeUpdate();
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return nbMaJ != 0;
    }
}
