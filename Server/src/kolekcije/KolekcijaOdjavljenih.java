/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kolekcije;

import domen.Agent;
import domen.Klijent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tami
 */
public class KolekcijaOdjavljenih {
    private static KolekcijaOdjavljenih instance;
    List<Agent> lista;

    private KolekcijaOdjavljenih() {
        lista = new ArrayList<>();
    }

    public static KolekcijaOdjavljenih getInstance() {
        if (instance == null) {
            instance = new KolekcijaOdjavljenih();
        }
        return instance;
    }

    public List<Agent> getLista() {
        return lista;
    }

    public void dodaj(Agent a) {
        lista.add(a);
    }

    public void obrisi(Klijent a) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).equals(a)) {
                lista.remove(i);
            }
        }
    }

    @Override
    public String toString() {
        String string="";
        for( Agent agi : lista) {
            string+=agi.toString();
        }
        return string;
    }
}
