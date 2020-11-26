package Modele;

import java.sql.SQLException;
import java.util.ArrayList;

public class Test {

    public static void main(String[] argv) throws SQLException {
        FilmDAO fDAO = new FilmDAO();
        fDAO.read(0);
        fDAO.connection.close();

    }
}
