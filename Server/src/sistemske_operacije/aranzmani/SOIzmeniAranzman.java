/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije.aranzmani;

import sistemske_operacije.aranzmani.*;

import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;
import sistemske_operacije.OpstaSO;

/**
 *
 * @author Tami
 */
public class SOIzmeniAranzman extends OpstaSO{
    private OpstiDomenskiObjekat aranzman;
    private boolean success = false;

    public boolean isUspesno() {
        return success;
    }

    public SOIzmeniAranzman(OpstiDomenskiObjekat aranzman) {
        this.aranzman = aranzman;
    }

    @Override
    protected void executeSpecificOperation() throws SQLException {
        success = REPOSITORY.getInstance().updateOpstiDomenskiObjekat(aranzman);
    }
}
