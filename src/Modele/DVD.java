package Modele;

public class DVD {
    private int codeBarre;
    private Film film;
    private boolean estDispo;
    private boolean estReservable;

    public DVD(int codeBarre, Film film, boolean estDispo, boolean estReservable) {
        this.codeBarre = codeBarre;
        this.film = film;
        this.estDispo = estDispo;
        this.estReservable = estReservable;
    }

    @Override
    public String toString() {
        return "DVD{" +
                "codeBarre=" + codeBarre +
                ", estDispo=" + estDispo +
                ", estReservable=" + estReservable +
                '}';
    }

    public DVD() {
    }

    public int getCodeBarre() {
        return codeBarre;
    }

    public Film getFilm() {
        return film;
    }

    public int isEstDispo() {
        return estDispo ? 1 : 0;
    }

    public int isEstReservable() {
        return estReservable ? 1 : 0;
    }

    public void setCodeBarre(int codeBarre) {
        this.codeBarre = codeBarre;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public void setEstDispo(boolean estDispo) {
        this.estDispo = estDispo;
    }

    public void setEstReservable(boolean estReservable) {
        this.estReservable = estReservable;
    }
}
