package Modele;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Test {

    public static void main(String[] argv) throws SQLException {
        ArrayList<Location> locs = new ArrayList<Location>();
        locs.add(new Location("45", 1227, new Date(), null));

        LocationDAO fDAO = new LocationDAO();
        try {
            //fDAO.create(locs, new Client("45"));
            fDAO.update(1205);
            fDAO.readLocationActive("65");
            fDAO.readTouteLocation("65");
            System.out.println("Closing connections...");
            fDAO.connection.close();
        }catch (Exception e){
            System.err.println(e.getMessage());
            System.out.println("Closing connections...");
            fDAO.connection.close();
        }
    }
}
