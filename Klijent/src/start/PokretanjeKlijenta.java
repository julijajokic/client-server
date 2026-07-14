/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author Tami
 */
public class PokretanjeKlijenta extends Thread{
    private int port;
    private static Socket socket;
  
 

    public PokretanjeKlijenta(int port) {
        this.port = port;
    }

    @Override
    public void run() {
      
        try {
           
            socket = new Socket("localhost", port);
         
          
            System.out.println("Socket kreiran " + socket.getInetAddress()+" " ); 
             //+ socket.getLocalAddress());
            Komunikacija.getInstance().setSocket(socket);
         
        } catch (SocketException ex) {
            JOptionPane.showMessageDialog(null, "Server je zatvorio konekciju!\n Program se gasi!", "Error!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("ClientStart.class IOException!");
        }
        
    }

    public static Socket getSocket() {
        return socket;
    }
    public void ugasi() {
        this.interrupt();
    }

   

   

 

 
    
    
}
