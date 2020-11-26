package Modele;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DvdDAO extends SqlDAO<DVD> {

    public DvdDAO() throws SQLException {
    }

    @Override
    public DVD read(Object titre) {
        return null;
    }

    @Override
    public boolean create(DVD obj) {
        String query = " insert into LesDVDs (film, estDispo, estReserve)"
                + " values (?, ?, ?)";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, "(select REF(c) from LeCatalogue c where c.titre = '" + obj.getFilm().getNom() + "')");
            preparedStmt.setString(2, Integer.toString(obj.isEstDispo()));
            preparedStmt.setString (3, Integer.toString(obj.isEstReservable()));
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
    public boolean update(DVD obj) {
        String query = " update LesDVDs set estDispo = ?, estReservable = ? where codeBarre = '"
                        + obj.getCodeBarre() + "'";

        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, Integer.toString(obj.isEstDispo()));
            preparedStmt.setString(2, Integer.toString(obj.isEstReservable()));
            preparedStmt.execute();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //TODO
    @Override
    public boolean delete(DVD obj) {
        return false;
    }
}
