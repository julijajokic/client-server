/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import domen.Agent;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import kolekcije.KolekcijaUlogovanih;
import niti.NitKlijent;

/**
 *
 * @author Tami
 */
public class Server extends Thread {

    NitKlijent nit;
    private int clientNumber = 1;
    Socket socket;

    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    private ServerSocket serverSocket;

    public List<NitKlijent> klijenti = new LinkedList<>();
    public List<NitKlijent> odjavljeni = new LinkedList<>();

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("ServerSocket je kreiran.");
        } catch (IOException ex) {
            System.out.println("ServerSocket nije kreiran.");
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!(serverSocket.isClosed())) {

                this.socket = serverSocket.accept();

                nit = new NitKlijent(socket, clientNumber);

                nit.start();

                klijenti.add(nit);
                clientNumber++;

//             
                int i = klijenti.size();

                nit.setBroj(i);

            }

        } catch (IOException ex) {

        }
    }

    public void stopServ() throws IOException {

        serverSocket.close();
    }

    public void closeServerSocket() {

        for (int i = 0; i < nit.getOdjavljeniAgenti().size(); i++) {
            System.out.println(nit.getOdjavljeniAgenti().get(i));
            System.out.println(nit.getOdjavljeniAgenti().get(i).getBroj());
            for (int j = 0; j < klijenti.size(); j++) {

                if (nit.odjavljeniAgenti.get(i).getBroj() == klijenti.get(j).getBroj()) {
                    try {
                        klijenti.get(j).getSocket().close();
                        System.out.println("Klijent broj" + (nit.getOdjavljeniAgenti().get(i).getBroj()) + "je diskonektovan");

                    } catch (IOException ex) {
                        System.out.println("Klijent ne moze da se diskonektuje. " + nit.getOdjavljeniAgenti().get(i).getBroj());
                        ex.printStackTrace();
                    }
                }
            }
        }

        try {
            serverSocket.close();
        } catch (IOException ex) {
            System.out.println("Serverski soket ne moze da se zatvori.");
            ex.printStackTrace();
        }
    }

    public static void closeClient(NitKlijent nk) {
        //try {
            //nk.getSocket().close();
            nk.interrupt();
       
            System.out.println("Klijent je ugasen");
         
//        } catch (IOException ex) {
//            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
