package Modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class ReservationDAO extends SqlDAO<Reservation>{
    public ReservationDAO() throws SQLException {
        super();
    }

    @Override
    public Reservation read(int id) {
        return null;
    }

    @Override
    public boolean create(Reservation obj) {
        return false;
    }

    public ArrayList<Reservation> readTouteLocation(String id){
        System.out.println("Récupération des Reservation du client : " + id);
        ArrayList<Reservation> reservation = new ArrayList<Reservation>();
        Reservation res;
        try{
            ResultSet result = this.connection.createStatement().executeQuery(
                    "SELECT DEREF(value(locs).film).titre as titre, value(locs).dateRes as dateRes, DEREF(value(locs).dvdRetire).codeBarre as idDvd FROM LesReservations l, TABLE(l.liste_reservation) locs WHERE clientCB = '" + id + "'");

            while(result.next()){
                res = new Reservation();
                res.setIdClient(""+id);

                if(result.getObject("titre") != null){
                    System.out.println(result.getObject("titre").toString());
                    res.setTitre(result.getObject("titre").toString());
                } else { res.setTitre("");}

                if(result.getObject("dateRes") != null){
                    System.out.println(result.getDate("dateRes"));
                    res.setDateRes(result.getDate("dateRes"));
                } else { res.setDateRes(null);}

                if(result.getObject("idDvd") != null){
                    System.out.println(result.getDate("idDvd"));
                    res.setIdDvd(result.getObject("idDvd").toString());
                } else { res.setIdDvd(null);}

                reservation.add(res);
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return reservation;
    }

    public boolean create(ArrayList<Reservation> locations, Client cli) {

        String query = "INSERT INTO LesReservations values (?, ?)";
        PreparedStatement preparedStmt;
        try {
            String pattern = "YYYY-MM-DD";
            DateFormat df = new SimpleDateFormat(pattern);
            String date = df.format(LocalDateTime.now());

            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1,cli.getNoCB());
            String locStr = "ens_reservations(";
            for (Iterator<Reservation> i = locations.iterator(); i.hasNext(); ) {
                locStr += "treservation((select REF(c) from LeCatalogue c where c.titre = '" + i.next().getTitre() + "'),";
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

    @Override
    public boolean update(Reservation obj) {
        //SELECT dateRes FROM LISTERESERVATIONSMEMBRE WHERE ROWNUM <=1 ORDER BY dateRes DESC;
        return false;
    }

    public boolean delete(String idDvd) {
        return false;
    }

    @Override
    public boolean delete(Reservation obj) {
        return false;
    }
}
