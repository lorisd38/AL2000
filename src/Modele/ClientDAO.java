package Modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO extends SqlDAO<Client> {
    public ClientDAO() throws SQLException {
    }

    @Override
    public Client read(Object noC) {
        Client client = new Client();
        try{
            ResultSet result = this.connection.createStatement().executeQuery("SELECT noCB FROM LesClientsA WHERE noCB = '" + noC + "'");

            while(result.next()){
                if(result.getObject("noCB") != null){
                    System.out.println(result.getObject("noCB").toString());
                    client.setNoCB(result.getObject("noCB").toString());
                } else {
                    client = null;
                }
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public boolean create(Client obj) {
        try {
            String query = "insert into LesClientsA values (";
            query += "tclient(" + obj.getNoCB() + "))";
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
    public boolean update(Client obj) {
        return false;
    }

    @Override
    public boolean delete(Client obj) {
        int nbMaJ = 0;
        String requete = "DELETE FROM LesClientsA WHERE noCB = '" + obj.getNoCB() + "'";
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
