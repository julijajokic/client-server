/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije.aranzmani;

import sistemske_operacije.partneri.*;

import domen.Aranzman;
import domen.Mesto;
import domen.OpstiDomenskiObjekat;
import domen.PoslovniPartner;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import sistemske_operacije.OpstaSO;

/**
 *
 * @author Tami
 */
public class SOPretraziAranzmane extends OpstaSO{
    
    private List<OpstiDomenskiObjekat> aranzmani;
    private String kriterijumPretrage;

    public List<OpstiDomenskiObjekat> getAranzmani() {
        return aranzmani;
    }

    public SOPretraziAranzmane(String kriterijumPretrage) {
        this.kriterijumPretrage = kriterijumPretrage;
    }

    @Override
    protected void executeSpecificOperation() throws SQLException {
        List<OpstiDomenskiObjekat> allAranzmani = REPOSITORY.getInstance().getAllOpstiDomenskiObjekats(new Aranzman());
        System.out.println("all aranamani size: " + allAranzmani.size());
        aranzmani = new LinkedList<>();

        for (OpstiDomenskiObjekat odo : allAranzmani) {
            Aranzman a = (Aranzman) odo;
            if (findStringMatches(a)) {
                System.out.println("Usao u uslov");
                PoslovniPartner poslovniPartner = (PoslovniPartner) REPOSITORY.getInstance().getOpstiDomenskiObjekatByPrimaryKey(new PoslovniPartner(),a.getPoslovniPartner().getPoslovniPartnerID());
                a.setPoslovniPartner(poslovniPartner);
                aranzmani.add(a);
            }
        }
    }

    private boolean findStringMatches(Aranzman a) {
        System.out.println(a);
        String[] trazeniKriterijumi = kriterijumPretrage.split(" ");
        for (String kriterijum : trazeniKriterijumi) {
            
            if (a.getNaziv().toLowerCase().matches("(.*)" + kriterijum.toLowerCase() + "(.*)")  ) {
                return true;
            }
        }
        return false;
    }
    
    
}
