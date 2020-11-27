package Modele;

import java.sql.SQLException;
import java.util.ArrayList;

public class Test {

    public static void main(String[] argv) throws SQLException {
        ReservationDAO fDAO = new ReservationDAO();
        fDAO.readTouteLocation("300");
        fDAO.connection.close();

    }
}
