/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Marija
 */
public class Porudzbina extends AbstractDomainObject {

    private int porudzbinaID;
    private Date datumVreme;
    private Date datumVremeIsporuke;
    private double iznos;
    private Kupac kupac;
    private Prodavac prodavac;
    private ArrayList<StavkaPorudzbine> stavkePorudzbine;

    public Porudzbina(int porudzbinaID, Date datumVreme, Date datumVremeIsporuke, double iznos, Kupac kupac, Prodavac prodavac, ArrayList<StavkaPorudzbine> stavkePorudzbine) {
        this.porudzbinaID = porudzbinaID;
        this.datumVreme = datumVreme;
        this.datumVremeIsporuke = datumVremeIsporuke;
        this.iznos = iznos;
        this.kupac = kupac;
        this.prodavac = prodavac;
        this.stavkePorudzbine = stavkePorudzbine;
    }

    public Porudzbina() {
    }

    @Override
    public String nazivTabele() {
        return " Porudzbina ";
    }

    @Override
    public String alijas() {
        return " p ";
    }

    @Override
    public String join() {
        return " JOIN KUPAC K ON (K.KUPACID = P.KUPACID)\n"
                + "JOIN GRAD G ON (G.GRADID = K.GRADID)\n"
                + "JOIN PRODAVAC PR ON (PR.PRODAVACID = P.PRODAVACID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Prodavac pr = new Prodavac(rs.getInt("prodavacID"),
                    rs.getString("pr.Ime"), rs.getString("pr.Prezime"),
                    rs.getString("KorisnickoIme"), rs.getString("Lozinka"));

            Grad g = new Grad(rs.getInt("GradID"),
                    rs.getString("g.naziv"));

            Kupac k = new Kupac(rs.getInt("kupacID"), rs.getString("k.ime"),
                    rs.getString("k.prezime"), rs.getString("email"),
                    rs.getString("telefon"), g);

            Porudzbina p = new Porudzbina(rs.getInt("porudzbinaID"),
                    rs.getTimestamp("datumVreme"), rs.getTimestamp("datumVremeIsporuke"),
                    rs.getDouble("iznos"), k, pr, new ArrayList<>());
            
            lista.add(p);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumVreme, datumVremeIsporuke, iznos, kupacID, prodavacID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new Timestamp(datumVreme.getTime()) + "', "
                + "'" + new Timestamp(datumVremeIsporuke.getTime()) + "', "
                + " " + iznos + ", " + kupac.getKupacID() + ", "
                + prodavac.getProdavacID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " iznos = " + iznos + ", "
                + "datumVremeIsporuke = '"
                + new Timestamp(datumVremeIsporuke.getTime()) + "' ";
    }

    @Override
    public String uslov() {
        return " porudzbinaID = " + porudzbinaID;
    }

    @Override
    public String dodatniUslov() {
        if (porudzbinaID != -1) {
            return " WHERE porudzbinaID = " + porudzbinaID;
        }
        return " WHERE LOWER(K.IME) LIKE '%" + kupac.getIme() + "%' "
                + "AND LOWER(K.PREZIME) LIKE '%" + kupac.getPrezime() + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY P.PORUDZBINAID ASC ";
    }

    public int getPorudzbinaID() {
        return porudzbinaID;
    }

    public void setPorudzbinaID(int porudzbinaID) {
        this.porudzbinaID = porudzbinaID;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public Date getDatumVremeIsporuke() {
        return datumVremeIsporuke;
    }

    public void setDatumVremeIsporuke(Date datumVremeIsporuke) {
        this.datumVremeIsporuke = datumVremeIsporuke;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public ArrayList<StavkaPorudzbine> getStavkePorudzbine() {
        return stavkePorudzbine;
    }

    public void setStavkePorudzbine(ArrayList<StavkaPorudzbine> stavkePorudzbine) {
        this.stavkePorudzbine = stavkePorudzbine;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

}
