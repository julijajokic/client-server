/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.Aranzman;
import domen.Klijent;
import domen.Mesto;
import domen.Rezervacija;
import forme.klijent.KlijentForma;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import komunikacija.Komunikacija;
import konstante.Konstante;
import transfer.KlijentTransfer;
import transfer.ServerTransfer;

/**
 *
 * @author Tami
 */
public class KlijentiTableModel extends AbstractTableModel     {

  

    List<Klijent> klijenti=new ArrayList<>();
    String [] kolone= new String[]{"Ime","Prezime","Telefon","Email","Mesto"};
    boolean menjaj=false;

    public KlijentiTableModel() {
//         
//            try {
//                
//          
//       
//            KlijentTransfer kt = new KlijentTransfer();
//            kt.setOperacija(Konstante.VRATI_KLIJENTE);
//            Komunikacija.getInstance().sendRequest(kt);
//            ServerTransfer st = Komunikacija.getInstance().readResponse();
//            klijenti = (List<Klijent>) st.getPodaci();
//             
//                
//      
//                
//               
//            } catch (IOException ex) {
//                   Logger.getLogger(KlijentiTableModel.class.getName()).log(Level.SEVERE, null, ex);
//               } catch (ClassNotFoundException ex) {
//                   Logger.getLogger(KlijentiTableModel.class.getName()).log(Level.SEVERE, null, ex);
//               }
//       
 
        
    }

    public boolean isMenjaj() {
        return menjaj;
    }
    

    public void setMenjaj(boolean menjaj) {
        this.menjaj = menjaj;
    }
    

    public KlijentiTableModel(List<Klijent> klijenti) {
        this.klijenti = klijenti;
    }

    public List<Klijent> getKlijenti() {
        return klijenti;
    }
    
    
    
    @Override
    public int getRowCount() {
        return klijenti.size();
                
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Klijent k = klijenti.get(rowIndex);
        switch(columnIndex){
            case 0: return k.getIme();
            case 1: return k.getPrezime();
            case 2: return k.getTelefon();
            case 3: return k.getEmail();
            case 4: return k.getMesto().getNaziv();
            default : return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (menjaj==true) {
           return true;
        }
       return false;
    }
    

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Klijent k = klijenti.get(rowIndex);
        switch(columnIndex){
            case 0: if (validirajCeliju(rowIndex, (String) aValue)) {
                k.setIme((String) aValue);
                fireTableDataChanged();
                }
                break;
            case 1: if (validirajCeliju(rowIndex, (String) aValue)) {
                    k.setPrezime((String) aValue);
                    fireTableDataChanged();
                }
                break;
            case 2: if (validirajCeliju(rowIndex, (String) aValue)) {
                    k.setTelefon((String) aValue);
                    fireTableDataChanged();
                }
                break;
            case 3: if (validirajCeliju(rowIndex, (String) aValue)) {
                    k.setEmail((String) aValue);
                    fireTableDataChanged();
                }
                break;
            case 4: validirajMesto(rowIndex, (Mesto) aValue);
                    fireTableDataChanged();
                break;
                
        }
    }
    
    public boolean validirajCeliju(int rowIndex, String unos){
        if (unos.isEmpty()) {
            prikaziGresku("Polje je prazno!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    private void validirajMesto(int rowIndex, Mesto mesto) {
        Klijent k = klijenti.get(rowIndex);
        if (mesto == null) {
            prikaziGresku("Polje za mesto nije popunjeno!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        k.setMesto(mesto);
    }
    private void prikaziGresku(String poruka, int tip) {
        JOptionPane.showMessageDialog(null, poruka, "Greska", tip);

    }
    
      public Klijent getRow(int row) {
        return klijenti.get(row);
    }
         public void setLista(List<Klijent> lista) {
        this.klijenti = lista;
        fireTableDataChanged();
    }

    
//   @Override
//    public void run() {
//       while(true) {
//            try {
//                
//          
//       
//            KlijentTransfer kt = new KlijentTransfer();
//            kt.setOperacija(Konstante.VRATI_KLIJENTE);
//            Komunikacija.getInstance().sendRequest(kt);
//            ServerTransfer st = Komunikacija.getInstance().readResponse();
//            klijenti = (List<Klijent>) st.getPodaci();
//             
//                fireTableDataChanged();
//      
//                
//                Thread.sleep(8000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(KlijentiTableModel.class.getName()).log(Level.SEVERE, null, ex);
//            }  catch (IOException ex) {
//                   Logger.getLogger(KlijentiTableModel.class.getName()).log(Level.SEVERE, null, ex);
//               } catch (ClassNotFoundException ex) {
//                   Logger.getLogger(KlijentiTableModel.class.getName()).log(Level.SEVERE, null, ex);
//               }
//       
//    }
//    }
  

    public void setKlijenti(List<Klijent> klijenti) {
        this.klijenti = klijenti;
    }
    
//    public List<Klijent> vratiKlijente() {
//         
//            try {
//                
//          
//       
//            KlijentTransfer kt = new KlijentTransfer();
//            kt.setOperacija(Konstante.VRATI_KLIJENTE);
//            Komunikacija.getInstance().sendRequest(kt);
//            ServerTransfer st = Komunikacija.getInstance().readResponse();
//            klijenti = (List<Klijent>) st.getPodaci();
//             
//                
//      
//                
//               
//            } catch (IOException ex) {
//                   Logger.getLogger(KlijentiTableModel.class.getName()).log(Level.SEVERE, null, ex);
//               } catch (ClassNotFoundException ex) {
//                   Logger.getLogger(KlijentiTableModel.class.getName()).log(Level.SEVERE, null, ex);
//               }
//        return klijenti;
//    }
}
