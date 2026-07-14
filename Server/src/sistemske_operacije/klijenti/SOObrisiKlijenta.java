/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije.klijenti;


import domen.Klijent;
import domen.OpstiDomenskiObjekat;
import domen.Rezervacija;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import kontroler.Kontroler;

import sistemske_operacije.OpstaSO;

/**
 *
 * @author Tami
 */
public class SOObrisiKlijenta extends OpstaSO {

    private OpstiDomenskiObjekat klijent;

    private boolean uspesno = false;

    public boolean isUspesno() {
        return uspesno;
    }

    public SOObrisiKlijenta(OpstiDomenskiObjekat klijent) {
        this.klijent = klijent;
    }

    @Override
    protected void executeSpecificOperation() throws SQLException {

        uspesno = REPOSITORY.getInstance().deleteOpstiDomenskiObjekat(klijent);

    }

}


