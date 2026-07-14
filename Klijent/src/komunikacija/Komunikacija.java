/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.KlijentTransfer;
import transfer.ServerTransfer;

/**
 *
 * @author Tami
 */
public class Komunikacija {

    private Socket socket;
    private static Komunikacija instance;

    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public void setSocket(Socket soket) throws IOException {
        this.socket = soket;
    }

    public void sendRequest(KlijentTransfer ct) throws SocketException, IOException {
  try{
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(ct);
        out.flush();
          }catch(EOFException|SocketException e) {
               if(socket!=null &&!socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
                }
               }
        }catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ServerTransfer readResponse() throws SocketException, ClassNotFoundException, IOException {
        try{
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ServerTransfer sto = (ServerTransfer) in.readObject();

        return sto;
               }catch(EOFException|SocketException e) {
               if(socket!=null &&!socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        } 
    }
           catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }

    public Socket getSocket() {
        return socket;
    }

  
    

}
