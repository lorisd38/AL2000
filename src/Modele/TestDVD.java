package Modele;

import oracle.sql.ARRAY;

import java.sql.SQLException;


public class TestDVD {
    public static void main(String[] argv) throws SQLException {
        Film f = new Film("avator", "Disney",new Personne("comeron", "james"),"bonjour", null,null,null,0,null,null);
        DVD dvd = new DVD(3026, f, true,false);
        DvdDAO fDAO = new DvdDAO();
        fDAO.create(dvd);
        dvd.setEstDispo(false);
        dvd.setEstReservable(true);
        fDAO.update(dvd);
        fDAO.read(3026);
        fDAO.connection.close();
    }
}
