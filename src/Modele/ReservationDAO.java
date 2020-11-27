package Modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

public class ReservationDAO extends SqlDAO<Reservation>{
    public ReservationDAO() throws SQLException {
        super();
    }

    @Override
    public Reservation read(Object o) throws SQLException {
        return null;
    }

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
                    "SELECT DEREF(film).titre as titre, dateRes , DEREF(dvdRetire).codeBarre as idDvd FROM LesReservationsA WHERE clientCB = '" + id + "'");

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
                    System.out.println(result.getObject("idDvd"));
                    res.setIdDvd(Integer.valueOf(result.getObject("idDvd").toString()));
                } else { res.setIdDvd(0);}

                reservation.add(res);
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return reservation;
    }

    public boolean create(ArrayList<Reservation> reservation, Client cli) {
        if(reservation.isEmpty()) return true;

        String start = "INSERT INTO LesReservationsA values ('"+ reservation.get(0).getIdClient()+"', ";
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String end = "DATE '" + date + "', null)";
        PreparedStatement preparedStmt;
        try {
            String locStr = "ens_reservations(";
            for (Iterator<Reservation> i = reservation.iterator(); i.hasNext(); ) {
                System.out.println(start + "(select REF(c) from LeCatalogue c where c.titre = '" + i.next().getTitre() + "'),"+ end);
                preparedStmt = connection.prepareStatement(start + "(select REF(c) from LeCatalogue c where c.titre = '" + i.next().getTitre() + "'),"+ end);
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

    public boolean dvdDemandeReservation(int codeBarre){
        try{
            ResultSet result = this.connection.createStatement().executeQuery(
                    "SELECT dateRes FROM LesReservationsA WHERE DEREF(film).titre = (select DEREF(d.film).titre from lesDvdsA d where d.codeBarre = "+ codeBarre + ")");
            while(result.next()){
                return true;
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateRes(int codeBarre){
        try{
            ResultSet result = this.connection.createStatement().executeQuery(
                    "UPDATE LesReservationsA a SET dvdRetire = (select REF(d) from lesDvdsA d where d.codeBarre = " + codeBarre + ") WHERE ROWNUM <=1 AND DEREF(film).titre = (select DEREF(d.film).titre from lesDvdsA d where d.codeBarre = "+ codeBarre + ")");
            return true;
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Reservation obj) {
        return false;
    }

    public boolean delete(int codeBarre) {

        String query = "DELETE FROM LesReservationsA WHERE DEREF(dvdRetire).codeBarre = " + codeBarre;
        PreparedStatement preparedStmt;
        try {
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
    public boolean delete(Reservation obj) {
        return false;
    }
}
