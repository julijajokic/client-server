/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije.rezervacije;

import sistemske_operacije.klijenti.*;

import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;
import sistemske_operacije.OpstaSO;

/**
 *
 * @author Tami
 */
public class SOIzmeniRezervaciju extends OpstaSO{
    private OpstiDomenskiObjekat rezervacija;
    private boolean success = false;

    public boolean isUspesno() {
        return success;
    }

    public SOIzmeniRezervaciju(OpstiDomenskiObjekat rezervacija) {
        this.rezervacija = rezervacija;
    }

    @Override
    protected void executeSpecificOperation() throws SQLException {
        success = REPOSITORY.getInstance().updateOpstiDomenskiObjekat(rezervacija);
    }
}
