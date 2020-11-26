package Modele;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class MembreDAO extends SqlDAO<Membre> {

    public MembreDAO() throws SQLException {
    }

    @Override
    public Membre read(int id) {
        Membre membre = new Membre();
        try{
            ResultSet result = this.connection.createStatement().executeQuery("SELECT idCarteMembre, t.nom, t.prenom, noCB FROM lesmembres m, TABLE(m.titulaire) t WHERE numero = " + id);

            while(result.next()){
                /*if(result.getObject(1) != null){
                    membre.setIdCarteMembre(result.getObject(1).getClass());
                } else { membre.setIdCarteMembre("");}*/

                if(result.getObject(2) != null){
                    membre.setNom(result.getObject(2).toString());
                } else { membre.setNom("");}

                if(result.getObject(3) != null){
                    membre.setPrenom(result.getObject(3).toString());
                } else { membre.setPrenom("");}

                if(result.getObject(4) != null){
                    membre.setNoCB(result.getObject(4).toString());
                } else { membre.setNoCB("");}
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return membre;
    }

    @Override
    public boolean create(Membre obj) {
        String query = " insert into LesClientsA values (?, ?)";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, "tcartemembre(" + obj.getCarteMembre().getMontant() + ", " + obj.getCarteMembre().getDateNeg() + ")");
            preparedStmt.setString (2, "tpersonne(" + obj.getNom() + ", " + obj.getPrenom() + ")");
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
