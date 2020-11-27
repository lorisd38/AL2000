package Modele;

import java.sql.*;

public class Connexion {
    private final static String URL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";
    private final static String USER = "davilori";
    private final static String PASSWD = "Lorisdavid1";
    private static Connection connect;

    private Connexion() throws SQLException {
        System.out.println("Tentative de Connexion au serveur");
        try {
            // Enregistrement du driver Oracle
            System.out.print("Loading Oracle driver... ");
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());  	    System.out.println("loaded");

            // Etablissement de la connection
            System.out.print("Connecting to the database... ");
            connect = DriverManager.getConnection(URL,USER,PASSWD);
            System.out.println("connected");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connexion getInstance() throws SQLException {
        return new Connexion();
    }

    public PreparedStatement prepareStatement(String requete) throws ClassNotFoundException, SQLException {
        return connect.prepareStatement(requete);
    }

    public Statement createStatement() throws ClassNotFoundException, SQLException {
        return connect.createStatement();
    }

    public void close() throws SQLException {
        connect.close();
    }

}
