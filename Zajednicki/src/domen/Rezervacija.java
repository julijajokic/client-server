/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tami
 */
public class Rezervacija extends OpstiDomenskiObjekat {
 //SimpleDateFormat SDF = new SimpleDateFormat("dd-mm-yyyy");
    private int rezervacijaID;
    private double zaduzenje;
    private Date datum;
    private Date rokPlacanja;
    private Agent agent;
    private Klijent klijent;
    private Aranzman aranzman;
    private double avans;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public double getAvans() {
        return avans;
    }

    public void setAvans(double avans) {
        this.avans = avans;
    }

    public Aranzman getAranzman() {
        return aranzman;
    }

    public void setAranzman(Aranzman aranzman) {
        this.aranzman = aranzman;
    }

    public int getRezervacijaID() {
        return rezervacijaID;
    }

    public void setRezervacijaID(int rezervacijaID) {
        this.rezervacijaID = rezervacijaID;
    }

    public double getZaduzenje() {
        return zaduzenje;
    }

    public void setZaduzenje(double zaduzenje) {
        this.zaduzenje = zaduzenje;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getRokPlacanja() {
        return rokPlacanja;
    }

    public void setRokPlacanja(Date rokPlacanja) {
        this.rokPlacanja = rokPlacanja;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public Rezervacija() {
    }

    public Rezervacija(int rezervacijaID, Aranzman aranzman, Agent agent, Klijent klijent, Date datum, Date rokPlacanja, double avans, double zaduzenje) {
        this.rezervacijaID = rezervacijaID;
           this.aranzman = aranzman;
               this.agent = agent;
        this.klijent = klijent;
     
        this.datum = datum;
        this.rokPlacanja = rokPlacanja;
       this.avans = avans;
          this.zaduzenje = zaduzenje;
    }

    @Override
    public String getNazivTabele() {
        return "rezervacija";

    }

    @Override
    public String getParametre() {
        return String.format("%s,%s, %s, %s, '%s',' %s', %s, %s", rezervacijaID, aranzman.getAranzmanID(), agent.getAgentID(), klijent.getKlijentID(), datum, rokPlacanja, zaduzenje, avans);
    }

    @Override
    public String getNaziveParametara() {
       return "RezervacijaID,AranzmanID,AgentID,KlijentID,Datum,RokPlacanja,Zaduzenje,Avans";

    }

    @Override
    public String getNazivPrimarnogKljuca() {
        return "RezervacijaID";
    }

    @Override
    public Integer getVrednostPrimarnogKljuca() {
        return rezervacijaID;
    }

    @Override
    public List<OpstiDomenskiObjekat> konvertujRSUListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> rezervacije = new ArrayList<>();

        try {
            while (rs.next()) {
                Integer id = rs.getInt("RezervacijaID");
                Integer arid = rs.getInt("AranzmanID");
                Integer agentS = rs.getInt("AgentID");
                Integer kid = rs.getInt("KlijentID");
                Date datum = rs.getDate("Datum");
                Date rok = rs.getDate("RokPlacanja");
                Double dug = rs.getDouble("Zaduzenje");

                Double avansS = rs.getDouble("Avans");

                Aranzman ar = new Aranzman(arid);
                Klijent k = new Klijent();
                k.setKlijentID(kid);
  
                Rezervacija r = new Rezervacija(id, ar, new Agent(agentS), k, datum, rok, avansS, dug);
    
             
                rezervacije.add(r);
              
    }
               
          
                
            
          
          
        } catch (Exception e) {
            System.out.println("Greska u Rezervacije.Class ResultSet");
        }
        return rezervacije;
    }

    @Override
    public String getUpdateUpit() {
   
     
        // return " AranzmanID = "+aranzman.getAranzmanID()+"  ,  AgentID = "+agent.getAgentID()+"  ,KlijentID = "+klijent.getKlijentID()+ " , Datum= '"+datum+"'  ,  RokPlacanja = '" +rokPlacanja +"' ,  Zaduzenje = "+zaduzenje+ ", Avans = "+avans ;
       //  and
         //return String.format("%s"+"and"+"%s"+"and"+ "%s"+"and" +"'%s'"+"and"+" '%s'"+"and"+"%s"+"and"+"%s" +"and"+ " AranzmanID = " + aranzman.getAranzmanID() + "and" + "AgentID = " + agent.getAgentID() + "and" + "  KlijentID = " + klijent.getKlijentID() + "and" + " Datum = " + datum + "and" +"RokPlacanja = " + rokPlacanja + "and" + " Zaduzenje = " + zaduzenje + "and" + " Avans = " + avans);
         
      //return " AranzmanID = " + aranzman.getAranzmanID() +"and" + "AgentID = " + agent.getAgentID() + "and" + "  KlijentID = " + klijent.getKlijentID() + "and" + " Datum = " + datum + "and" +"RokPlacanja = " + rokPlacanja + "and" + " Zaduzenje = " + zaduzenje + "and" + " Avans = " + avans;
       return "  Datum= '"+datum+"'  ,  RokPlacanja = '" +rokPlacanja +"' ,  Zaduzenje = "+zaduzenje+ ", Avans = "+avans ;
      
      
     }
    
    
    
  

    @Override
    public String getSlozeniPrimarniKljuc() {
//        return " RezervacijaID=" + rezervacijaID + ",AranzmanID=" + aranzman.getAranzmanID() + " , AgentID=" + agent.getAgentID() + " , KlijentID=" + klijent.getKlijentID();
//    }
    return " RezervacijaID="+rezervacijaID+" AND AranzmanID="+aranzman.getAranzmanID()+" AND AgentID="+agent.getAgentID()+" AND KlijentID="+klijent.getKlijentID();
    }
    @Override
    public String toString() {
        return "Rezervacija{"+"rezervacijaID=" + rezervacijaID  +  ", aranzmanID=" + aranzman.getAranzmanID()+", agentID=" + agent.getAgentID() +",klijentID=" + klijent.getKlijentID()+", Datum = " + datum + ", RokPlacanja = " + rokPlacanja   +", Avans = " + avans + ", Zaduzenje = " + zaduzenje +'}' ; 

    // 
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.rezervacijaID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rezervacija other = (Rezervacija) obj;
        if (this.rezervacijaID != other.rezervacijaID) {
            return false;
        }
        return true;
    }

}
