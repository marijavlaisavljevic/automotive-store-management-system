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
public class Kupac extends AbstractDomainObject {

    private int kupacID;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private Grad grad;

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    public Kupac(int kupacID, String ime, String prezime, String email, String telefon, Grad grad) {
        this.kupacID = kupacID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
        this.grad = grad;
    }

    public Kupac() {
    }

    @Override
    public String nazivTabele() {
        return " kupac ";
    }

    @Override
    public String alijas() {
        return " k ";
    }

    @Override
    public String join() {
        return " JOIN GRAD G ON (G.GRADID = K.GRADID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            Grad g = new Grad(rs.getInt("GradID"),
                    rs.getString("g.naziv"));

            Kupac k = new Kupac(rs.getInt("kupacID"), rs.getString("k.ime"),
                    rs.getString("k.prezime"), rs.getString("email"),
                    rs.getString("telefon"), g);

            lista.add(k);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, email, telefon, GradID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + email + "', '" + telefon + "', " + grad.getGradID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " email = '" + email + "', telefon = '" + telefon + "', gradID ="
                + grad.getGradID();
    }

    @Override
    public String uslov() {
        return " kupacID = " + kupacID;
    }

    @Override
    public String dodatniUslov() {
        if (kupacID != -1) {
            return " WHERE kupacID = " + kupacID;
        }
        return " WHERE LOWER(IME) LIKE '%" + ime + "%' "
                + "AND LOWER(PREZIME) LIKE '%" + prezime + "%' "
                + "AND LOWER(EMAIL) LIKE '%" + email + "%' "
                + "AND LOWER(G.NAZIV) LIKE '%" + grad.getNaziv() + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY KUPACID ASC ";
    }

    public int getKupacID() {
        return kupacID;
    }

    public void setKupacID(int kupacID) {
        this.kupacID = kupacID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }

}
