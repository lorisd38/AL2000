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
    }

    @Override
    public Membre read(Object o) throws SQLException {
        Membre membre = new Membre();
        try{
            ResultSet result = this.connection.createStatement().executeQuery("SELECT TREAT(VALUE(m) AS tmembre) FROM LesClientsAL m WHERE VALUE(m) IS OF (tmembre);");

            while(result.next()){
                if(result.getObject("noCB") != null){
                    System.out.println(result.getObject("noCB").toString());
                    membre.setNoCB(result.getObject("noCB").toString());
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
            String query = "insert into LesClientsA values (tmembre(";
            query += "tcartemembreA(" + obj.getCarteMembre().getMontant() + ", ";
            String pattern = "yyyy-MM-dd";
            DateFormat df = new SimpleDateFormat(pattern);
            query += "DATE '" + df.format(obj.getCarteMembre().getDateNeg()) + "'), ";
            query += "tpersonne('" + obj.getPersonne().getNom() + "', '" + obj.getPersonne().getPrenom() + "')))";
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
        /*String query = " update lesmembres set titulaire = ?, noCarte = ? WHERE numero='";
        query += obj.getIdCarteMembre();
        query +="'";

        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, "tpersonne(" + obj.getNom() + ", " + obj.getPrenom() + ")");
            preparedStmt.setString(2, obj.getNoCB());
            preparedStmt.execute();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return false;

    }

    @Override
    public boolean delete(Membre obj) {
        /*int nbMaJ = 0;

        // Requete SQL de la suppression du membre obj
        String requete = "DELETE FROM lesmembres WHERE idCarteMembre = " + obj.getIdCarteMembre();

        try {
            // Execution de la requete
            PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
            nbMaJ = preparedStatement.executeUpdate();
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return nbMaJ != 0;*/
        return false;

    }
}
