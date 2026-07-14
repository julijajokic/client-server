/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kolekcije;

import domen.Agent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tami
 */
public class KolekcijaUlogovanih {

    private static KolekcijaUlogovanih instance;
    List<Agent> listaUlogovanih;

    private KolekcijaUlogovanih() {
        listaUlogovanih = new ArrayList<>();
    }

    public static KolekcijaUlogovanih getInstance() {
        if (instance == null) {
            instance = new KolekcijaUlogovanih();
        }
        return instance;
    }

    public List<Agent> getListaUlogovanih() {
        return listaUlogovanih;
    }

    public void dodaj(Agent a) {
        listaUlogovanih.add(a);
    }

    public boolean obrisi(Agent a) {
        for (int i = 0; i < listaUlogovanih.size(); i++) {
            if (listaUlogovanih.get(i).equals(a)) {
                    listaUlogovanih.remove(listaUlogovanih.get(i));
               // listaUlogovanih.remove(i);
                
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String string="";
        for (Agent agent : listaUlogovanih) {
            string+=agent.toString();
        }
        return string;
    }
    
    

}
