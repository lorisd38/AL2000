package Modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonneDAO extends SqlDAO<Personne> {

    public PersonneDAO() throws SQLException {
    }

    @Override
    public Personne read(Object personne) throws SQLException {
        Personne p = (Personne) personne;
        try{
            ResultSet result = this.connection.createStatement().executeQuery("SELECT nom, prenom FROM LesPersonnesA WHERE nom = '" + p.getNom() + "' AND prenom = '" + p.getPrenom() + "'");

            if(!result.next()){
                p = null;
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public boolean create(Personne obj) {
        try {
            String query = "insert into LesPersonnesA values (";
            query += "'" + obj.getNom() + "', ";
            query += "'" + obj.getPrenom() + "')";
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
    public boolean update(Personne obj) {
        return false;
    }

    @Override
    public boolean delete(Personne obj) {
        return false;
    }
}
