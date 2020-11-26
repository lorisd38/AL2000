package Modele;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationDAO extends SqlDAO<Location> {

    public LocationDAO() throws SQLException {
    }

    @Override
    public Location read(int id) {
        System.out.println("Récupération des locations du client : " + id);
        Location location = new Location();
        try{
            ResultSet result = this.connection.createStatement().executeQuery("SELECT * FROM locations WHERE numero = " + id);

            while(result.next()){
                if(result.getObject(1) != null){
                    location.setId(result.getObject(1).toString());
                } else { location.setId("");}

                if(result.getObject(2) != null){
                    location.setIdClient(result.getObject(2).toString());
                } else { location.setIdClient("");}

                if(result.getObject(3) != null){
                    location.setIdDVD(result.getObject(3).toString());
                } else { location.setIdDVD("");}

                if(result.getObject(4) != null){
                    location.setDateLoc(result.getDate(4));
                } else { location.setDateLoc(null);}

                if(result.getObject(5) != null){
                    location.setDateRen(result.getDate(5));
                } else { location.setDateRen(null);}
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return location;
    }

    @Override
    public boolean create(Location obj) {
        return false;
    }

    @Override
    public boolean update(Location obj) {
        return false;
    }

    @Override
    public boolean delete(Location obj) {
        return false;
    }
}
