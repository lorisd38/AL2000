package Modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
        Location loc;
        try{
            String query = "SELECT value(locs) FROM LesLocations l, TABLE(l.liste_location) locs WHERE l.clientCB ='" + id + "'";
            System.out.println(query);
            ResultSet result = this.connection.createStatement().executeQuery(query);
            while(result.next()){
                loc = new Location();
                loc.setIdClient(""+id);
                System.out.println("test");

                if(result.getObject("idDVD") != null){
                    System.out.println(result.getObject("idDVD").toString());
                    loc.setIdDVD(result.getObject("idDVD").toString());
                } else { loc.setIdDVD("");}

                if(result.getObject("dateLoc") != null){
                    System.out.println(result.getDate("dateLoc"));
                    loc.setDateLoc(result.getDate("dateLoc"));
                } else { loc.setDateLoc(null);}

                if(result.getObject("dateRet") != null){
                    System.out.println(result.getDate("dateRet").toString());
                    loc.setDateRen(result.getDate("dateRet"));
                } else { loc.setDateRen(null);}

                locations.add(loc);
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return locations;
    }

    public ArrayList<Location> readLocationActive(String id){
        System.out.println("Récupération des locations du client : " + id);
        ArrayList<Location> locations = new ArrayList<Location>();
        Location loc;
        try{
            ResultSet result = this.connection.createStatement().executeQuery(
                    "SELECT value(locs) FROM LesLocations l, TABLE(l.liste_location) locs WHERE locs.dateRet IS NULL AND l.clientCB =" + id);

            while(result.next()){
                loc = new Location();
                loc.setIdClient(""+id);

                if(result.getObject("idDVD") != null){
                    System.out.println(result.getObject("idDVD").toString());
                    loc.setIdDVD(result.getObject("idDVD").toString());
                } else { loc.setIdDVD("");}

                if(result.getObject("dateLoc") != null){
                    System.out.println(result.getDate("dateLoc"));
                    loc.setDateLoc(result.getDate("dateLoc"));
                } else { loc.setDateLoc(null);}

                if(result.getObject("dateRet") != null){
                    System.out.println(result.getDate("dateRet"));
                    loc.setDateRen(result.getDate("dateRet"));
                } else { loc.setDateRen(null);}

                locations.add(loc);
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return locations;
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

        String query = "INSERT INTO LesLocations values (?, ?)";
        PreparedStatement preparedStmt;
        try {
            String pattern = "YYYY-MM-DD";
            DateFormat df = new SimpleDateFormat(pattern);
            String date = df.format(LocalDateTime.now());

            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1,cli.getNoCB());
            String locStr = "ens_locations(";
            for (Iterator<Location> i = locations.iterator(); i.hasNext(); ) {
                locStr += "tlocationA((select REF(d) from lesDvds d where d.codeBarre = " + i.next().getIdDVD() + "),";
                locStr += "DATE '" + date + "', null), ";
            }
            locStr = locStr.substring(0, locStr.length()-2);
            locStr += ")";
            preparedStmt.setString (2, locStr);
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

    public boolean update(String codeBarre) {
        String query = "UPDATE LISTELOCATIONSCLIENT set dateRet = DATE '?' where DEREF(dvd).codeBarre = ? and dateRet IS NULL;";
        PreparedStatement preparedStmt;
        try {
            String pattern = "YYYY-MM-DD";
            DateFormat df = new SimpleDateFormat(pattern);
            String date = df.format(LocalDateTime.now());

            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1,date);
            preparedStmt.setString (2, codeBarre);

            //traiter retour dvd

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
