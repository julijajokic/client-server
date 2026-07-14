/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.RezI;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Sandra
 */
public class ModelTabeleI extends AbstractTableModel {
  private List<RezI> lista = new ArrayList<>();
    private String kolone[] = {"klijent", "broj rezervacija"};
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RezI z = lista.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return z.getKlijent().getIme();
            case 1: 
                return z.getBrojRezervacija();
            default:
                return "GRESKA";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<RezI> getLista() {
        return lista;
    }

    public void setLista(List<RezI> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }
    
}
