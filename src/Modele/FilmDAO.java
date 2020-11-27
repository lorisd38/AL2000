package Modele;

import oracle.sql.ARRAY;
import oracle.sql.STRUCT;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class FilmDAO extends SqlDAO<Film> {

    public FilmDAO() throws SQLException {
        super();
    }

    @Override
    public Film read(Object titre) {
        Film film = new Film();
        try{
            ResultSet result = this.connection.createStatement().executeQuery("SELECT titre, producteur ,DEREF(realisateur) as rea, DEREF(acteursTab) as acts, TO_CHAR(date_de_sortie, 'YYYY-MM-DD') as date_sortie, resume, affiche_url, genres, ageLimite FROM (SELECT f.*, value(act) AS acteursTab from LeCatalogue f, table(f.acteurs) act) where titre = '" + titre.toString() + "'");

            while(result.next()){
                if(result.getObject("titre") != null){
                    film.setNom(result.getObject("titre").toString());
                } else {
                    film.setNom("INCONNU");
                }

                if(result.getObject("producteur") != null){
                    film.setProducteur(result.getObject("producteur").toString());
                } else {
                    film.setProducteur("INCONNU");
                }

                if(result.getObject("rea") != null){
                    Object[] att;
                    att = ((STRUCT) result.getObject(3)).getAttributes();
                    Personne p = new Personne(att[0].toString(), att[1].toString());
                    System.out.println(p.getNom() + " " + p.getPrenom());
                    film.setRealisateur(p);
                } else {
                    film.setRealisateur(new Personne("INCONNU","INCONNU"));
                }

                if(result.getObject("acts") != null){
                    Object[] att;
                    att = ((STRUCT) result.getObject("acts")).getAttributes();
                    ArrayList<Personne> acteurs = new ArrayList<Personne>();
                    int i = 0;
                    String nom = "";
                    for (Object o : att) {
                        if(i%2 == 0){
                            nom = o.toString();
                        } else {
                            Personne p = new Personne(nom, o.toString());
                            acteurs.add(p);
                        }
                        i++;
                    }
                    film.setActeurs(acteurs);
                } else {
                    ArrayList<Personne> acteurs = new ArrayList<Personne>();
                    film.setActeurs(acteurs);
                }

                if(result.getDate("date_sortie") != null){
                    //DateFormat format = new SimpleDateFormat("YYYY-MM-DD", Locale.FRANCE);
                    //Date date = (Date) format.parse(result.getObject("date_sortie").toString());
                    film.setDateDeSortie(result.getDate("date_sortie"));
                }

                if(result.getObject("resume") != null){
                    film.setResume(result.getObject("resume").toString());
                } else {
                    film.setResume("Pas de résumé");
                }

                if(result.getObject("affiche_url") != null){
                    film.setAffiche(result.getObject("affiche_url").toString());
                } else {
                    film.setAffiche("Pas d'affiche");
                }

                if(result.getObject("genres") != null) {
                    ARRAY att;
                    att = ((ARRAY) result.getObject("genres"));
                    String[] tab = (String[])att.getArray();
                    ArrayList<Genre> genres = new ArrayList<Genre>();
                    for (String s : tab) {
                        switch(s){
                            case "Action" :
                                genres.add(Genre.Action);
                                break;
                            case "Thriller" :
                                genres.add(Genre.Thriller);
                                break;
                            case "Documentaire" :
                                genres.add(Genre.Documentaire);
                            case "Fantastique" :
                                genres.add(Genre.Fantastique);
                            default:
                                genres.add(Genre.Inconnu);
                        }
                    }
                    film.setGenre(genres);
                }

                if(result.getObject("ageLimite") != null){
                    System.out.println(result.getObject("ageLimite").toString());
                    film.setAgeLimite(Integer.valueOf(result.getObject("ageLimite").toString()));
                } else {
                    film.setAgeLimite(0);
                }

            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return film;
    }

    public ArrayList<Film> readAllFilm(){
        ArrayList<Film> films = new ArrayList<Film>();
        try {
            ResultSet result = this.connection.createStatement().executeQuery("SELECT titre FROM LeCatalogue");
            while (result.next()) {
                films.add(read(result.getObject("titre")));
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return films;
    }

    @Override
    public boolean create(Film obj) {
        PreparedStatement preparedStmt;
        try {
            String query = "INSERT INTO LeCatalogue values (";
            query += "'" + obj.getNom() + "', ";
            query += "'" + obj.getProducteur() + "', ";
            query += "(select REF(p) from LesPersonnesA p where p.nom = '" + obj.getRealisateur().getNom() + "'), ";
            String pattern = "yyyy-MM-dd";
            DateFormat df = new SimpleDateFormat(pattern);
            query += "DATE '" + df.format(obj.getDateDeSortie()) + "', ";
            String acteurs = "";
            for(int i = 0 ; i < obj.getActeurs().size(); i++){
                if(i < obj.getActeurs().size()-1) {
                    acteurs += "(select REF(p) from LesPersonnesA p where p.nom = '" + obj.getActeurs().get(i).getNom() + "'), ";
                } else {
                    acteurs += "(select REF(p) from LesPersonnesA p where p.nom = '" + obj.getActeurs().get(i).getNom() + "')";
                }
            }
            query += "tacteurs(" + acteurs + "), ";
            query += Integer.toString(obj.getAgeLimite()) + ", ";
            query += "'" + obj.getResume() + "', ";
            query += "'" + obj.getAffiche() + "', ";
            String genres = "";
            for(int i = 0 ; i < obj.getGenre().size(); i++){
                if(i < obj.getGenre().size()-1) {
                    genres += "'" + obj.getGenre().get(i) + "', ";
                } else {
                    genres += "'" + obj.getGenre().get(i) + "'";
                }
            }
            query += "tgenres(" + genres + "))";
            System.out.println(query);
            preparedStmt = connection.prepareStatement(query);
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
        int nbMaJ = 0;
        String requete = "DELETE FROM LeCatalogue WHERE titre = '" + obj.getNom() + "'";

        try {
            // Execution de la requete
            PreparedStatement preparedStatement = this.connection.prepareStatement(requete);
            nbMaJ = preparedStatement.executeUpdate();
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return nbMaJ != 0;
    }
}
