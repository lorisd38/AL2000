package Modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DvdDAO extends SqlDAO<DVD> {

    public DvdDAO() throws SQLException {
    }

    @Override
    public DVD read(Object codebarre) throws SQLException{
        DVD dvd = new DVD();
        FilmDAO fDAO = new FilmDAO();
        try{
            System.out.println("Reading dvd: " + codebarre.toString());
            ResultSet result = this.connection.createStatement().executeQuery("SELECT codeBarre, DEREF(film).titre as titre, estDispo, estReserve from LesDVDs where codeBarre = " + codebarre.toString());

            while(result.next()){

                if(result.getObject("codeBarre") != null){
                    dvd.setCodeBarre(Integer.valueOf( result.getObject("codeBarre").toString()));
                } else { dvd.setCodeBarre(0);}

                if(result.getObject("titre") != null){
                    dvd.setFilm(fDAO.read(result.getObject("titre")));
                } else { dvd.setFilm(null);}

                if(result.getObject("estDispo") != null){
                    dvd.setEstDispo(Integer.valueOf( result.getObject("estDispo").toString()) == 1 ? true : false);
                } else { dvd.setEstDispo(false);}

                if(result.getObject("estReserve") != null){
                    dvd.setEstReservable(Integer.valueOf( result.getObject("estReserve").toString()) == 1 ? true : false);
                } else { dvd.setEstReservable(false);}
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(dvd.toString());
        return dvd;
    }

    @Override
    public boolean create(DVD obj) {
        PreparedStatement preparedStmt;
        String query = "insert into LesDVDs values (";
        try {
            query += obj.getCodeBarre() + ", ";
            query += "(select REF(c) from LeCatalogue c where c.titre = '" + obj.getFilm().getNom() + "'), ";
            query += Integer.toString(obj.isEstDispo()) + ", ";
            query += Integer.toString(obj.isEstReservable()) + ")";
            System.out.println(query);
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
    public boolean update(DVD obj) {
        String query = " update LesDVDs set estDispo = ?, estReserve = ? where codeBarre = "
                        + obj.getCodeBarre() + "";

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

    public ArrayList<String> filmDispoLoc() throws SQLException {
        ArrayList<String> lesTitres = new ArrayList<String>();
        try{
            System.out.println("Reading dvd dispo " );
            ResultSet result = this.connection.createStatement().executeQuery("Select DEREF(film).titre as titre FROM LesDVDs where estDispo = 1 AND estReserve = 0");

            while(result.next()) {
                if(result.getObject("titre") != null){
                    lesTitres.add(result.getObject("titre").toString());
                }
            }

            lesTitres.forEach(t -> System.out.println(t));

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lesTitres;
    }

    //TODO
    @Override
    public boolean delete(DVD obj) {
        return false;
    }
}
