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
public class Prodavnica extends AbstractDomainObject {
    
    private int prodavnicaID;
    private String naziv;
    private double satnica;

    public Prodavnica(int prodavnicaID, String naziv, double satnica) {
        this.prodavnicaID = prodavnicaID;
        this.naziv = naziv;
        this.satnica = satnica;
    }

    public Prodavnica() {
    }
    
    @Override
    public String nazivTabele() {
        return " Prodavnica ";
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
            Prodavnica p = new Prodavnica(rs.getInt("ProdavnicaID"),
                    rs.getString("naziv"), rs.getDouble("satnica"));

            lista.add(p);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (naziv, satnica) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', " + satnica + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " naziv = '" + naziv + "', satnica = " + satnica + " ";
    }

    @Override
    public String uslov() {
        return " ProdavnicaID = " + prodavnicaID;
    }

    @Override
    public String dodatniUslov() {
        if (prodavnicaID != -1) {
            return " WHERE prodavnicaID = " + prodavnicaID;
        }
        return " WHERE LOWER(NAZIV) LIKE '%" + naziv + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY PRODAVNICAID ASC ";
    }

    public int getProdavnicaID() {
        return prodavnicaID;
    }

    public void setProdavnicaID(int prodavnicaID) {
        this.prodavnicaID = prodavnicaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getSatnica() {
        return satnica;
    }

    public void setSatnica(double satnica) {
        this.satnica = satnica;
    }
    
}
