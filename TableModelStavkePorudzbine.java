/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import domain.StavkaPorudzbine;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marija
 */
public class TableModelStavkePorudzbine extends AbstractTableModel {

    private ArrayList<StavkaPorudzbine> lista;
    private String[] kolone = {"Rb", "Auto oprema", "Kolicina", "Cena"};
    private int rb;

    public TableModelStavkePorudzbine() {
        lista = new ArrayList<>();
    }

    public TableModelStavkePorudzbine(ArrayList<StavkaPorudzbine> stavkePorudzbine) {
        lista = stavkePorudzbine;
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
        StavkaPorudzbine sp = lista.get(row);

        switch (column) {
            case 0:
                return sp.getRb();
            case 1:
                return sp.getAutoOprema().getNaziv();
            case 2:
                return sp.getKolicina();
            case 3:
                return sp.getIznos() + "din";

            default:
                return null;
        }
    }

    public void dodajStavku(StavkaPorudzbine sn) {

        for (StavkaPorudzbine stavkaPorudzbine : lista) {
            if (stavkaPorudzbine.getAutoOprema().getAutoOpremaID() == sn.getAutoOprema().getAutoOpremaID()) {
                stavkaPorudzbine.setKolicina(stavkaPorudzbine.getKolicina() + sn.getKolicina());
                stavkaPorudzbine.setIznos(stavkaPorudzbine.getIznos() + sn.getIznos());
                fireTableDataChanged();
                return;
            }
        }

        rb = lista.size();
        sn.setRb(++rb);
        lista.add(sn);
        fireTableDataChanged();
    }

    public void obrisiStavku(int row) {
        lista.remove(row);

        rb = 0;
        for (StavkaPorudzbine stavkaPorudzbine : lista) {
            stavkaPorudzbine.setRb(++rb);
        }

        fireTableDataChanged();
    }

    public double vratiUkupanIznos() {
        double ukupanIznos = 0;

        for (StavkaPorudzbine stavkaPorudzbine : lista) {
            ukupanIznos += stavkaPorudzbine.getIznos();
        }

        return ukupanIznos;
    }

    public ArrayList<StavkaPorudzbine> getLista() {
        return lista;
    }

}
