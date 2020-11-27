package Modele;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class TestFilm {
    public static void main(String[] argv) throws SQLException {
        ArrayList<Personne> acts = new ArrayList<Personne>();
        acts.add(new Personne("Pitt", "brad"));
        acts.add(new Personne("Dicaprio", "Ronamdo"));

        ArrayList<Genre> genres = new ArrayList<Genre>();
        genres.add(Genre.Documentaire);
        Film f = new Film("avatir", "Disney",new Personne("DiCaprio", "Leonardo"),"bonjour", acts ,new Date(),"ezfqzef",0,null,genres);

        FilmDAO sDAO = new FilmDAO();

        try {
            sDAO.create(f);
            System.out.println(sDAO.read("avatir").toString());
            System.out.println("NBacteurs :"+sDAO.read("Cendrillon").getActeurs().size());
            ArrayList<Film> films = sDAO.readAllFilm();
            films.forEach(az -> System.out.println(az.toString()));
            sDAO.connection.close();
        }catch (Exception e){
            System.err.println(e.getMessage());
            System.out.println("Closing connections...");
            sDAO.connection.close();

        }
    }
}
