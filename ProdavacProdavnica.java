/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Marija
 */
public class ProdavacProdavnica extends AbstractDomainObject {

    private Prodavnica prodavnica;
    private Prodavac prodavac;
    private Date datum;
    private double dnevnica;
    private int brojSati;

    public ProdavacProdavnica(Prodavnica prodavnica, Prodavac prodavac, Date datum, double dnevnica, int brojSati) {
        this.prodavnica = prodavnica;
        this.prodavac = prodavac;
        this.datum = datum;
        this.dnevnica = dnevnica;
        this.brojSati = brojSati;
    }

    public ProdavacProdavnica() {
    }

    @Override
    public String nazivTabele() {
        return " ProdavacProdavnica ";
    }

    @Override
    public String alijas() {
        return " pp ";
    }

    @Override
    public String join() {
        return "JOIN PRODAVNICA P ON (P.PRODAVNICAID = ZP.PRODAVNICAID)\n"
                + "JOIN PRODAVAC P ON (P.PRODAVACID = PP.PRODAVACID)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Prodavac p = new Prodavac(rs.getInt("prodavacID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("KorisnickoIme"), rs.getString("Lozinka"));
            
            Prodavnica prod = new Prodavnica(rs.getInt("ProdavnicaID"),
                    rs.getString("naziv"), rs.getDouble("satnica"));
            
            ProdavacProdavnica pp = new ProdavacProdavnica(prod, p, rs.getDate("datum"), 
                    rs.getDouble("dnevnica"), rs.getInt("BrojSati"));

            lista.add(pp);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (ProdavnicaID, prodavacID, datum, dnevnica, BrojSati) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + prodavnica.getProdavnicaID() + ", " + prodavac.getProdavacID() + ", "
                + " '" + new java.sql.Date(datum.getTime()) + "', " + dnevnica + ", " 
                + brojSati + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " prodavacID = " + prodavac.getProdavacID();
    }

    @Override
    public String dodatniUslov() {
        return "";
    }

    @Override
    public String orderBy() {
        return "";
    }

    public Prodavnica getProdavnica() {
        return prodavnica;
    }

    public void setProdavnica(Prodavnica prodavnica) {
        this.prodavnica = prodavnica;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public double getDnevnica() {
        return dnevnica;
    }

    public void setDnevnica(double dnevnica) {
        this.dnevnica = dnevnica;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getBrojSati() {
        return brojSati;
    }

    public void setBrojSati(int brojSati) {
        this.brojSati = brojSati;
    }


}
