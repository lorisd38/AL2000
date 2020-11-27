package Modele;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Test {

    public static void main(String[] argv) throws SQLException {
        ArrayList<Location> locs = new ArrayList<Location>();
        locs.add(new Location("45", 1227, new Date(),null));
        locs.add(new Location("45", 1228, new Date(),null));

        LocationDAO fDAO = new LocationDAO();
        fDAO.create(locs, new Client("45"));
        fDAO.update(1227);
        fDAO.readLocationActive("45");
        fDAO.connection.close();

    }
}
