package Modele;

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
            ResultSet result = this.connection.createStatement().executeQuery("SELECT titre, producteur ,DEREF(realisateur) as rea, DEREF(acteursTab) as acts, date_de_sortie, resume, affiche_url, genres, ageLimite FROM (SELECT f.*, value(act) AS acteursTab from LeCatalogue f, table(f.acteurs) act) where titre = '" + titre.toString() + "'");

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
                }

                if(result.getObject("date_de_sortie") != null){
                    DateFormat format = new SimpleDateFormat("DD-MM-YYYY", Locale.FRANCE);
                    Date date = (Date) format.parse(result.getObject("date_de_sortie").toString());
                    film.setDateDeSortie(date);
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
                    Object[] att;
                    att = ((STRUCT) result.getObject("genres")).getAttributes();
                    ArrayList<Genre> genres = new ArrayList<Genre>();
                    for (Object o : att) {
                        switch(o.toString()){
                            case "Action" :
                                genres.add(Genre.Action);
                                break;
                            case "Thriller" :
                                genres.add(Genre.Thriller);
                                break;
                            case "Documentaire" :
                                genres.add(Genre.Documentaire);
                        }
                    }
                    film.setGenre(genres);
                }

                if(result.getObject("ageLimite") != null){
                    film.setAgeLimite((int)result.getObject("ageLimite"));
                } else {
                    film.setAgeLimite(0);
                }

            }
        } catch(SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return film;
    }

    @Override
    public boolean create(Film obj) {
        String query = "INSERT INTO LeCatalogue values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, obj.getNom());
            preparedStmt.setString(2, obj.getProducteur());
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
            preparedStmt.setString (6, Integer.toString(obj.getAgeLimite()));
            preparedStmt.setString (7, obj.getResume());
            preparedStmt.setString (8, obj.getAffiche());
            String genres = "";
            for(int i = 0 ; i < obj.getGenre().size(); i++){
                if(i < obj.getGenre().size()-1) {
                    acteurs += "'" + obj.getGenre().get(i) + "', ";
                } else {
                    acteurs += "'" + obj.getGenre().get(i) + "'";
                }
            }
            preparedStmt.setString (9, "tgenres(" + genres + ")");
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
