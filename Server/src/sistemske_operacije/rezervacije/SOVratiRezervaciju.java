/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije.rezervacije;


import domen.Agent;
import domen.Aranzman;
import domen.Klijent;
import domen.OpstiDomenskiObjekat;
import domen.Rezervacija;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import sistemske_operacije.OpstaSO;

/**
 *
 * @author Tami
 */
public class SOVratiRezervaciju extends OpstaSO{
    private List<OpstiDomenskiObjekat> rezervacije;
    

    public SOVratiRezervaciju() {
        rezervacije = new LinkedList<>();
        
    }

    public List<OpstiDomenskiObjekat> getRezervacije() {
        return rezervacije;
    }
    
    @Override
    protected void executeSpecificOperation() throws Exception {
        rezervacije = REPOSITORY.getInstance().getAllOpstiDomenskiObjekats(new Rezervacija());
        for (OpstiDomenskiObjekat odo : rezervacije) {
            Rezervacija r = (Rezervacija) odo;
            Klijent k =(Klijent) REPOSITORY.getInstance().getOpstiDomenskiObjekatByPrimaryKey(new Klijent(), r.getKlijent().getKlijentID());
            Aranzman ar = (Aranzman)REPOSITORY.getInstance().getOpstiDomenskiObjekatByPrimaryKey(new Aranzman(), r.getAranzman().getAranzmanID());
            Agent ag = (Agent) REPOSITORY.getInstance().getOpstiDomenskiObjekatByPrimaryKey(new Agent(), r.getAgent().getAgentID());
            r.setKlijent(k);
            r.setAgent(ag);
            r.setAranzman(ar);
            
            
        }
    }
}
