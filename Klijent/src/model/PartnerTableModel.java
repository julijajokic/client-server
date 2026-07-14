/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.Klijent;
import domen.Mesto;
import domen.PoslovniPartner;
import java.io.IOException;
import java.util.ArrayList;
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
public class PartnerTableModel extends AbstractTableModel {

    List<PoslovniPartner> listapartnera = new ArrayList<>();
    String[]kolone = new String[]{"Naziv","Tip Poslovnog Partnera", "Ziro racun", "Mesto"};

    public PartnerTableModel(List<PoslovniPartner> listapartnera) {
        this.listapartnera = listapartnera;
    }

    public PartnerTableModel() {
//          try {
//                
//          
//       
//            KlijentTransfer kt = new KlijentTransfer();
//            kt.setOperacija(Konstante.VRATI_PARTNERE);
//            Komunikacija.getInstance().sendRequest(kt);
//            ServerTransfer st = Komunikacija.getInstance().readResponse();
//            listapartnera = (List<PoslovniPartner>) st.getPodaci();
//             
//                
//      
//              
//          } catch (IOException ex) {
//                   Logger.getLogger(KlijentiTableModel.class.getName()).log(Level.SEVERE, null, ex);
//               } catch (ClassNotFoundException ex) {
//                   Logger.getLogger(KlijentiTableModel.class.getName()).log(Level.SEVERE, null, ex);
//               }
//      
  }
        
    public boolean validirajCeliju(int rowIndex, String unos){
        if (unos.isEmpty()) {
            prikaziGresku("Polje je prazno!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public List<PoslovniPartner> getListapartnera() {
        return listapartnera;
    }

    public void setListapartnera(List<PoslovniPartner> listapartnera) {
        this.listapartnera = listapartnera;
        fireTableDataChanged();
    }
       private void prikaziGresku(String poruka, int tip) {
        JOptionPane.showMessageDialog(null, poruka, "Greska", tip);

    } 
    
    @Override
    public int getRowCount() {
        return listapartnera.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PoslovniPartner pp = listapartnera.get(rowIndex);
        switch(columnIndex){
            case 0: return pp.getNaziv();
//            case 1: return vratiNazivTipa(pp);
            case 1:return pp.getTipPoslovnogPartnera();
            case 2: return pp.getZiroRacun();
            case 3: return pp.getMesto().getNaziv();
                default: return "n/a";
        }
                
    }
        @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        PoslovniPartner pp = listapartnera.get(rowIndex);
        switch(columnIndex){
            case 0: if (validirajCeliju(rowIndex, (String) aValue)) {
                pp.setNaziv((String) aValue);
                fireTableDataChanged();
                }
                break;
            case 1: if (validirajCeliju(rowIndex, (String) aValue)) {
                    pp.setTipPoslovnogPartnera((String) aValue);
                    fireTableDataChanged();
                }
                break;
            case 2: if (validirajCeliju(rowIndex, (String) aValue)) {
                    pp.setZiroRacun((String) aValue);
                    fireTableDataChanged();
                }
                break;
          
            case 3: validirajMesto(rowIndex, (Mesto) aValue);
                    fireTableDataChanged();
            
                break;
        }   
        }
  private void validirajMesto(int rowIndex, Mesto mesto) {
        PoslovniPartner pp = listapartnera.get(rowIndex);
        if (mesto == null) {
            prikaziGresku("Polje za mesto nije popunjeno!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        pp.setMesto(mesto);
    }
 

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
//    public String vratiNazivTipa(PoslovniPartner pp){
//        if (pp.getTipPoslovnogPartnera()==1) {
//            return "Smestaj";
//        }else{
//            return "Prevoz";
//        }
//    }
//    @Override
//    public void run() {
//    while (true) {
//         try {
//                
//          
//       
//            KlijentTransfer kt = new KlijentTransfer();
//            kt.setOperacija(Konstante.VRATI_PARTNERE);
//            Komunikacija.getInstance().sendRequest(kt);
//            ServerTransfer st = Komunikacija.getInstance().readResponse();
//            listapartnera = (List<PoslovniPartner>) st.getPodaci();
//             fireTableDataChanged();
//                
//      
//                
//                Thread.sleep(10000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(KlijentiTableModel.class.getName()).log(Level.SEVERE, null, ex);
//            }  catch (IOException ex) {
//                   Logger.getLogger(KlijentiTableModel.class.getName()).log(Level.SEVERE, null, ex);
//               } catch (ClassNotFoundException ex) {
//                   Logger.getLogger(KlijentiTableModel.class.getName()).log(Level.SEVERE, null, ex);
//               }
//}
//}
}


