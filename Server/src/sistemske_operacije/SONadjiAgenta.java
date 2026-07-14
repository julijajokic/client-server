/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije;


import domen.Agent;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tami
 */
public class SONadjiAgenta extends OpstaSO{
    private OpstiDomenskiObjekat agent;
    public OpstiDomenskiObjekat ulogovan;

    public SONadjiAgenta(OpstiDomenskiObjekat agent) {
        this.agent = agent;
        ulogovan=null;
    }
    

    @Override
    protected void executeSpecificOperation() throws Exception {
        try {
            System.out.println("pokrenut je u bazi...");
            List<OpstiDomenskiObjekat> agenti = REPOSITORY.getInstance().getAllOpstiDomenskiObjekats(agent);
            Agent uneti = (Agent) agent;
          
            for (OpstiDomenskiObjekat odo : agenti) {
                  Agent a = (Agent) odo;
            
                if (a.getKorisnickoIme().equals(uneti.getKorisnickoIme())&& a.getSifra().equals(uneti.getSifra())) {
                    this.ulogovan =a;
                         System.out.println("Korisnicko ime ulogovanog:" +((Agent)(ulogovan)).getKorisnickoIme());
                System.out.println("Sifra ulogovanog:" +((Agent)(ulogovan)).getSifra());
//                System.out.println("Korisnicko ime unetog:" +uneti.getKorisnickoIme());
//                System.out.println("Sifra unetog:" +uneti.getSifra());
                  
                }
               
            }
             
        } catch (Exception e) {
            Logger.getLogger(SONadjiAgenta.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    public OpstiDomenskiObjekat getAgent() {
        return ulogovan;
    }

   
    
    
    
}
