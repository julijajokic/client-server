/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import domen.Rezervacija;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tami
 */
public class PrikaziRezTableModel extends AbstractTableModel{
    List<Rezervacija> rezervacije= new ArrayList<>();
    private String[] kolone = new String[]{"Aranzman","Datum ", "Rok placanja","Avans","Zaduzenje"};

    public PrikaziRezTableModel(List<Rezervacija> lista) {
        this.rezervacije = lista;
    }

//    public PrikaziRezTableModel() {
//      
//    }

    
    @Override
    public int getRowCount() {
        return rezervacije.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Rezervacija rez = rezervacije.get(rowIndex);
        
        switch(columnIndex){
            
            case 0: return rez.getAranzman().getNaziv();
         
            case 1: return rez.getDatum();
            case 2: return rez.getRokPlacanja();
            case 3: return rez.getAvans();
            case 4: return rez.getZaduzenje();
            default: return "n/a";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
           public void setLista(List<Rezervacija> lista) {
        this.rezervacije = lista;
        fireTableDataChanged();
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }
       public Rezervacija getRow(int row) {
        return rezervacije.get(row);
    }

 
    
           
    
}
