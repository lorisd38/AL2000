package Modele;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Test {

    public static void main(String[] argv) throws SQLException {
        ArrayList<Reservation> locs = new ArrayList<Reservation>();
        locs.add(new Reservation("65", "Cendrillon", new Date(), 0));
        ReservationDAO fDAO = new ReservationDAO();
        try {
            fDAO.create(locs, new Client("65"));
            fDAO.readTouteLocation("65");
            System.out.println(fDAO.dvdDemandeReservation(1205));
            System.out.println("Closing connections...");
            fDAO.connection.close();
        }catch (Exception e){
            System.err.println(e.getMessage());
            System.out.println("Closing connections...");
            fDAO.connection.close();
        }
    }
}
