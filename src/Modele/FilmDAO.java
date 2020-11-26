package Modele;

import oracle.sql.STRUCT;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

public class FilmDAO extends SqlDAO<Film> {

    public FilmDAO() throws SQLException {
        super();
    }

    @Override
    public Film read(int id) {
        Film film = new Film();
        try{
            ResultSet result = this.connection.createStatement().executeQuery("SELECT titre, DEREF(producteur) as prod ,DEREF(realisateur) as rea, DEREF(acteursTab) as acts, date_de_sortie, resume, affiche_url, genres FROM (SELECT f.*, value(act) AS acteursTab from LeCatalogue f, table(f.acteurs) act)");

            while(result.next()){
                if(result.getObject(1) != null){
                    film.setNom(result.getObject(1).toString());
                } else {
                    film.setNom("INCONNU");
                }

                if(result.getObject("prod") != null){
                    Object[] att;
                    att = ((STRUCT) result.getObject(2)).getAttributes();
                    Personne p = new Personne(att[0].toString(), att[1].toString());
                    film.setProducteur(p);
                } else {
                    film.setProducteur(new Personne("INCONNU","INCONNU"));
                }

                if(result.getObject("rea") != null){
                    Object[] att;
                    att = ((STRUCT) result.getObject(3)).getAttributes();
                    Personne p = new Personne(att[0].toString(), att[1].toString());
                    film.setRealisateur(p);
                } else {
                    film.setRealisateur(new Personne("INCONNU","INCONNU"));
                }

                if(result.getObject("acts") != null){
                    Object[] att;
                    att = ((STRUCT) result.getObject("acts")).getAttributes();
                    for (Object o : att) {
                        System.out.println(o.getClass());
                        System.out.println(o.toString());
                    }
                }
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return film;
    }

    @Override
    public boolean create(Film obj) {
        String query = "INSERT INTO LeCatalogue values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, obj.getNom());
            preparedStmt.setString(2, "(select REF(p) from LesPersonnesA p where p.nom = '" + obj.getProducteur().getNom() + "')");
            preparedStmt.setString (3, "(select REF(p) from LesPersonnesA p where p.nom = '" + obj.getRealisateur().getNom() + "')");
            String pattern = "DD-MM-YYYY";
            DateFormat df = new SimpleDateFormat(pattern);
            String dateSortie = df.format(obj.getDateDeSortie());
            preparedStmt.setString (4, dateSortie);
            String acteurs = "";
            for(int i = 0 ; i < obj.getActeurs().size(); i++){
                if(i < obj.getActeurs().size()-1) {
                    acteurs += "(select REF(p) from LesPersonnesA p where p.nom = '" + obj.getActeurs().get(i).getNom() + "'), ";
                } else {
                    acteurs += "(select REF(p) from LesPersonnesA p where p.nom = '" + obj.getActeurs().get(i).getNom() + "')";
                }
            }
            preparedStmt.setString (5, "tacteurs(" + acteurs + ")");
            preparedStmt.setString (6, obj.getResume());
            preparedStmt.setString (7, obj.getAffiche());
            String genres = "";
            for(int i = 0 ; i < obj.getGenre().size(); i++){
                if(i < obj.getGenre().size()-1) {
                    acteurs += "'" + obj.getGenre().get(i) + "', ";
                } else {
                    acteurs += "'" + obj.getGenre().get(i) + "'";
                }
            }
            preparedStmt.setString (8, "tgenres(" + genres + ")");
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
    public boolean update(Film obj) {
        return false;
    }

    @Override
    public boolean delete(Film obj) {
        return false;
    }
}
