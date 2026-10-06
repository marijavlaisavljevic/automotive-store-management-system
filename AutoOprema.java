/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Marija
 */
public class AutoOprema extends AbstractDomainObject {

    private int autoOpremaID;
    private String naziv;
    private String opis;
    private double cena;

    @Override
    public String toString() {
        return naziv + " (Cena: " + cena + "din)";
    }

    public AutoOprema(int autoOpremaID, String naziv, String opis, double cena) {
        this.autoOpremaID = autoOpremaID;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
    }

    public AutoOprema() {
    }

    @Override
    public String nazivTabele() {
        return " autoOprema ";
    }

    @Override
    public String alijas() {
        return " ao ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            AutoOprema ao = new AutoOprema(rs.getInt("AutoOpremaID"),
                    rs.getString("naziv"),
                    rs.getString("opis"),
                    rs.getDouble("cena"));

            lista.add(ao);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (naziv, opis, cena) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', '" + opis + "', " + cena + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " opis = '" + opis + "', cena = " + cena;
    }

    @Override
    public String uslov() {
        return " AutoOpremaID = " + autoOpremaID;
    }

    @Override
    public String dodatniUslov() {
        if (autoOpremaID != -1) {
            return " WHERE AutoOpremaID = " + autoOpremaID;
        }
        return " WHERE LOWER(NAZIV) LIKE '%" + naziv + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY AutoOpremaID ASC ";
    }

    public int getAutoOpremaID() {
        return autoOpremaID;
    }

    public void setAutoOpremaID(int autoOpremaID) {
        this.autoOpremaID = autoOpremaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

}
