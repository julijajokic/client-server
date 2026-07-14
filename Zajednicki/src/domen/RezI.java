/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author Sandra
 */
public class RezI implements Serializable{
    private Klijent klijent;
    private int brojRezervacija;

    public RezI() {
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public int getBrojRezervacija() {
        return brojRezervacija;
    }

    public void setBrojRezervacija(int brojRezervacija) {
        this.brojRezervacija = brojRezervacija;
    }
    


    
    
}
