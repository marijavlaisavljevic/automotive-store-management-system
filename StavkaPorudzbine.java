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
public class StavkaPorudzbine extends AbstractDomainObject {

    private Porudzbina porudzbina;
    private int rb;
    private int kolicina;
    private double cena;
    private double iznos;
    private AutoOprema autoOprema;

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public AutoOprema getAutoOprema() {
        return autoOprema;
    }

    public void setAutoOprema(AutoOprema autoOprema) {
        this.autoOprema = autoOprema;
    }

    public StavkaPorudzbine(Porudzbina porudzbina, int rb, int kolicina, double cena, double iznos, AutoOprema autoOprema) {
        this.porudzbina = porudzbina;
        this.rb = rb;
        this.kolicina = kolicina;
        this.cena = cena;
        this.iznos = iznos;
        this.autoOprema = autoOprema;
    }

    public StavkaPorudzbine() {
    }

    @Override
    public String nazivTabele() {
        return " StavkaPorudzbine ";
    }

    @Override
    public String alijas() {
        return " sp ";
    }

    @Override
    public String join() {
        return " JOIN PORUDZBINA P ON (P.PORUDZBINAID = SP.PORUDZBINAID)\n"
                + "JOIN KUPAC K ON (K.KUPACID = P.KUPACID)\n"
                + "JOIN GRAD G ON (G.GRADID = K.GRADID)\n"
                + "JOIN PRODAVAC PR ON (PR.PRODAVACID = P.PRODAVACID)\n"
                + "JOIN AUTOOPREMA AO ON (AO.AUTOOPREMAID = SP.AUTOOPREMAID)";
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
                    rs.getDouble("iznos"), k, pr, null);

            AutoOprema ao = new AutoOprema(rs.getInt("ao.AutoOpremaID"),
                    rs.getString("ao.naziv"),
                    rs.getString("ao.opis"),
                    rs.getDouble("ao.cena"));

            StavkaPorudzbine sp = new StavkaPorudzbine(p, rs.getInt("rb"),
                    rs.getInt("kolicina"), rs.getDouble("sp.cena"),
                    rs.getDouble("sp.iznos"), ao);

            lista.add(sp);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (porudzbinaID, rb, kolicina, cena, iznos, AutoOpremaID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + porudzbina.getPorudzbinaID() + ", " + rb + ", "
                + kolicina + ", " + cena + ", " + iznos + ", "
                + autoOprema.getAutoOpremaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Kolicina = " + kolicina + ", "
                + "cena = " + cena + ", "
                + "iznos = " + iznos + ", "
                + "AutoOpremaID = " + autoOprema.getAutoOpremaID();
    }

    @Override
    public String uslov() {
        return " porudzbinaID = " + porudzbina.getPorudzbinaID() + " AND RB = " + rb;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE P.PORUDZBINAID = " + porudzbina.getPorudzbinaID();
    }

    @Override
    public String orderBy() {
        return " ORDER BY RB ASC ";
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

}
