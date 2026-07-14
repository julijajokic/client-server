/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domen.RezI;
import java.util.List;
import javax.swing.JTable;
import kontroler.Kontroler;
import modeli.ModelTabeleI;



/**
 *
 * @author pavle
 */
public class OsveziTabeluZvanje extends Thread{

    private JTable tabela;

    public OsveziTabeluZvanje(JTable tabela) {
        this.tabela = tabela;
    }
    
    
    @Override
    public void run() {
        while (true) {            
            try {
                Thread.sleep(3000);
                
                List<RezI> lista = Kontroler.getInstance().vratiIzvestaj();
                
                ModelTabeleI mti = (ModelTabeleI) tabela.getModel();
                mti.setLista(lista);
            } catch (InterruptedException ex) {
                System.out.println(">>>OsveziTabelu: " + ex.getMessage());
            }
        }
    }
    
}
