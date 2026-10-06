/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.AutoOprema;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marija
 */
public class TableModelAutoOprema extends AbstractTableModel implements Runnable {

    private ArrayList<AutoOprema> lista;
    private String[] kolone = {"ID", "Naziv", "Cena"};
    private String parametarNaziv = "";
    private AutoOprema autoOprema = new AutoOprema(-1, "", "", 0);

    public TableModelAutoOprema() {
        try {
            lista = ClientController.getInstance()
                    .getAllAutoOprema(new AutoOprema(-1, "", "", 0));
        } catch (Exception ex) {
            Logger.getLogger(TableModelAutoOprema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        AutoOprema ao = lista.get(row);

        switch (column) {
            case 0:
                return ao.getAutoOpremaID();
            case 1:
                return ao.getNaziv();
            case 2:
                return ao.getCena() + "din";

            default:
                return null;
        }
    }

    public AutoOprema getSelectedAutoOprema(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelAutoOprema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametarNaziv(String naziv) {
        this.parametarNaziv = naziv;
        refreshTable();
    }

    public void refreshTable() {
        try {

            autoOprema.setNaziv(parametarNaziv.toLowerCase());

            lista = ClientController.getInstance().getAllAutoOprema(autoOprema);
            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<AutoOprema> getLista() {
        return lista;
    }

}
