/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Marija
 */
public class Prodavac extends AbstractDomainObject {

    private int prodavacID;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;

    public Prodavac() {
    }

    public Prodavac(int prodavacID, String ime, String prezime, String username, String password) {
        this.prodavacID = prodavacID;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = username;
        this.lozinka = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prodavac other = (Prodavac) obj;
        if (!Objects.equals(this.prodavacID, other.prodavacID)) {
            return false;
        }
        return true;
    }

    public int getProdavacID() {
        return prodavacID;
    }

    public void setProdavacID(int prodavacID) {
        this.prodavacID = prodavacID;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String nazivTabele() {
        return " prodavac ";
    }

    @Override
    public String alijas() {
        return " p ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Prodavac p = new Prodavac(rs.getInt("prodavacID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("KorisnickoIme"), rs.getString("Lozinka"));

            lista.add(p);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, KorisnickoIme, Lozinka) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + korisnickoIme + "', '" + lozinka + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Ime = '" + ime + "', Prezime = '" + prezime + "', "
                + "korisnickoIme = '" + korisnickoIme + "', lozinka = '" + lozinka + "' ";
    }

    @Override
    public String uslov() {
        return " prodavacID = " + prodavacID;
    }

    @Override
    public String dodatniUslov() {
        if (prodavacID != -1) {
            return " WHERE prodavacID = " + prodavacID;
        }
        return " WHERE LOWER(IME) LIKE '%" + ime + "%' "
                + "AND LOWER(PREZIME) LIKE '%" + prezime + "%' "
                + "AND LOWER(KORISNICKOIME) LIKE '%" + korisnickoIme + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY PRODAVACID ASC ";
    }

}
