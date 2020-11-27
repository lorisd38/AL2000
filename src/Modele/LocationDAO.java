package Modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class LocationDAO extends SqlDAO<Location> {

    public LocationDAO() throws SQLException {
    }

    @Override
    public Location read(Object o) throws SQLException {
        return null;
    }

    public ArrayList<Location> readTouteLocation(String id){
        System.out.println("Récupération des locations du client : " + id);
        ArrayList<Location> locations = new ArrayList<Location>();
        try{
            String query = "SELECT DEREF(dvd).codeBarre as dvd, dateLoc as dateL, dateRet as dateR  FROM LesLocationsA WHERE clientCB ='" + id + "'";
            System.out.println(query);
            ResultSet result = this.connection.createStatement().executeQuery(query);
            parseLocations(id, locations, result);

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return locations;
    }



    public ArrayList<Location> readLocationActive(String id){
        System.out.println("Récupération des locations du client : " + id);
        ArrayList<Location> locations = new ArrayList<Location>();
        try{
            ResultSet result = this.connection.createStatement().executeQuery(
                    "SELECT DEREF(dvd).codeBarre as dvd, dateLoc as dateL, dateRet as dateR  FROM LesLocationsA WHERE dateRet IS NULL AND clientCB =" + id);

            parseLocations(id, locations, result);
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return locations;
    }

    private void parseLocations(String id, ArrayList<Location> locations, ResultSet result) throws SQLException {
        Location loc;
        while(result.next()){
            loc = new Location();
            loc.setIdClient(""+id);

            if(result.getObject("dvd") != null){
                System.out.println(result.getObject("dvd").toString());
                loc.setIdDVD(Integer.valueOf( result.getObject("dvd").toString()));
            } else { loc.setIdDVD(-1);}

            if(result.getObject("dateL") != null){
                System.out.println(result.getDate("dateL"));
                loc.setDateLoc(result.getDate("dateL"));
            } else { loc.setDateLoc(null);}

            if(result.getObject("dateR") != null){
                System.out.println(result.getDate("dateR"));
                loc.setDateRen(result.getDate("dateR"));
            } else { loc.setDateRen(null);}

            locations.add(loc);
        }
    }


    @Override
    public boolean create(Location obj) {
        return false;
    }

    @Override
    public boolean update(Location obj) {
        return false;
    }

    public boolean create(ArrayList<Location> locations, Client cli) {

        PreparedStatement preparedStmt;
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String start = "INSERT INTO LesLocationsA values ('" + cli.getNoCB()+"', ";
        String end = "DATE '" + date + "', null)";
        try {

            for (Iterator<Location> i = locations.iterator(); i.hasNext(); ) {
                System.out.println(start + "(select REF(d) from lesDvds d where d.codeBarre = " + i.next().getIdDVD() + "),"+ end);
                preparedStmt = connection.prepareStatement(start + "(select REF(d) from lesDvdsA d where d.codeBarre = " + i.next().getIdDVD() + "),"+ end);
                preparedStmt.execute();
            }

            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(int codeBarre) {
        //Select locs FROM LesLocations l, TABLE(l.liste_location) locs where l.clientCB = '45';

        String query = "update LesLocationsA set dateRet = DATE '";
        PreparedStatement preparedStmt;
        try {
            query +=  LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +"' where dateRet IS NULL and DEREF(dvd).codeBarre = ";
            query += codeBarre ;

            System.out.println(query);
            preparedStmt = connection.prepareStatement(query);

            System.out.println(preparedStmt.toString());
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
    public boolean delete(Location obj) {
        return false;
    }
}
