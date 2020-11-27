package Modele;

import java.util.Date;

public class Location {
    public Location(String idClient, int idDVD, Date dateLoc, Date dateRen) {
        this.idClient = idClient;
        this.idDVD = idDVD;
        this.dateLoc = dateLoc;
        this.dateRen = dateRen;
    }

    private String idClient;
    private int idDVD;
    private Date dateLoc;
    private Date dateRen;

    public Location() {
    }

    public String getIdClient() {
        return idClient;
    }

    public int getIdDVD() {
        return idDVD;
    }

    public Date getDateLoc() {
        return dateLoc;
    }

    public Date getDateRen() {
        return dateRen;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public void setIdDVD(int idDVD) {
        this.idDVD = idDVD;
    }

    public void setDateLoc(Date dateLoc) {
        this.dateLoc = dateLoc;
    }

    public void setDateRen(Date dateRen) {
        this.dateRen = dateRen;
    }
}
