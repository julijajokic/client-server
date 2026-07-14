/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije.rezervacije;

import domen.OpstiDomenskiObjekat;
import domen.Rezervacija;
import java.sql.SQLException;
import java.util.List;
import sistemske_operacije.OpstaSO;

/**
 *
 * @author Tami
 */
public class SOObrisiRezervaciju extends OpstaSO{
    private  OpstiDomenskiObjekat rezervacija;
    private boolean uspesno = false;
    
    public boolean isUspesno(){
        return uspesno;
    }
    
    public SOObrisiRezervaciju(OpstiDomenskiObjekat rezervacija){
        this.rezervacija = rezervacija;
    }
    @Override
    protected void executeSpecificOperation() throws SQLException {
        uspesno = REPOSITORY.getInstance().deleteOpstiDomenskiObjekat(rezervacija);
                
    }
}
