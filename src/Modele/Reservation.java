package Modele;

import java.util.Date;

public class Reservation {
    private String idClient;
    private String titre;
    private Date dateRes;
    private String idDvd;

    public String getIdDvd() {
        return idDvd;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDateRes(Date dateLoc) {
        this.dateRes = dateLoc;
    }

    public void setIdDvd(String idDvd) {
        this.idDvd = idDvd;
    }

    public Reservation(String idClient, String titre, Date dateRes) {
        this.idClient = idClient;
        this.titre = titre;
        this.dateRes = dateRes;
    }
    public Reservation() {
        this.idClient = null;
        this.titre = null;
        this.dateRes = null;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getIdClient() {
        return idClient;
    }

    public String getTitre() {
        return titre;
    }

    public Date getDateRes() {
        return dateRes;
    }
}
