/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Tami
 */
public class KlijentTransfer implements Serializable {

    private int operacija;
    private Object parametar;
    private List<Object>parametri;

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public Object getParametar() {
        return parametar;
    }

    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }

    public List<Object> getParametri() {
        return parametri;
    }

    public void setParametri(List<Object> parametri) {
        this.parametri = parametri;
    }
    

}
