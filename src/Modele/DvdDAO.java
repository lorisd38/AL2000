package Modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DvdDAO extends SqlDAO<DVD> {

    public DvdDAO() throws SQLException {
    }

    @Override
    public DVD read(Object codebarre) throws SQLException{
        DVD dvd = new DVD();
        FilmDAO fDAO = new FilmDAO();
        try{
            ResultSet result = this.connection.createStatement().executeQuery("SELECT codeBarre, DEREF(film).titre as titre, estDispo, estReserve from LesDVDs where codeBarre = " + codebarre.toString());

            while(result.next()){

                if(result.getObject("codeBarre") != null){
                    dvd.setCodeBarre((int) result.getObject("codeBarre"));
                } else { dvd.setCodeBarre(0);}

                if(result.getObject("titre") != null){
                    dvd.setFilm(fDAO.read(result.getObject("titre")));
                }

                if(result.getObject("estDispo") != null){
                    dvd.setEstDispo(((int) result.getObject("estDispo")) == 1 ? true : false );
                } else { dvd.setEstDispo(false);}

                if(result.getObject("estReserve") != null){
                    dvd.setEstReservable(((int) result.getObject("estReserve")) == 1 ? true : false );
                } else { dvd.setEstReservable(false);}
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dvd;
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
