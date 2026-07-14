/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Agent;
import domen.Aranzman;
import domen.Klijent;
import domen.OpstiDomenskiObjekat;
import domen.PoslovniPartner;
import domen.Rezervacija;
import domen.StavkaAranzmana;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kolekcije.KolekcijaKlijenata;
import kolekcije.KolekcijaOdjavljenih;
import kolekcije.KolekcijaUlogovanih;
import konstante.Konstante;
import kontroler.Kontroler;
import server.Server;
import transfer.KlijentTransfer;
import transfer.ServerTransfer;

/**
 *
 * @author Tami
 */
public class NitKlijent extends Thread{
    KlijentTransfer kt;
    private int clientNumber;
   private static Socket socket;
    private boolean aktivan;
    public List<Agent> odjavljeniAgenti =new ArrayList<>();
    Agent odjavljeniAgent;
    public List<Agent> prijavljeniAgenti =new ArrayList<>();
Agent prijavljeniAgent;
int broj;

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public void setSocket(Socket socket ) {
        this.socket = socket;
       
    }




    public List<Agent> getPrijavljeniAgenti() {
        return prijavljeniAgenti;
    }

    public void setPrijavljeniAgenti(List<Agent> prijavljeniAgenti) {
        this.prijavljeniAgenti = prijavljeniAgenti;
    }

    public Agent getPrijavljeniAgent() {
        return prijavljeniAgent;
    }

    public void setPrijavljeniAgent(Agent prijavljeniAgent) {
        this.prijavljeniAgent = prijavljeniAgent;
    }

    public List<Agent> getOdjavljeniAgenti() {
        return odjavljeniAgenti;
    }

    public void setOdjavljeniAgenti(List<Agent> odjavljeniAgenti) {
        this.odjavljeniAgenti = odjavljeniAgenti;
    }

    public  Agent getOdjavljeniAgent() {
        return odjavljeniAgent;
    }

    public void setOdjavljeniAgent(Agent odjavljeniAgent) {
        this.odjavljeniAgent = odjavljeniAgent;
    }
     

    public NitKlijent(Socket socket, int clientNumber) {
        this.socket = socket;
      this.clientNumber=clientNumber;
       this.aktivan = true;
        
    }

    public static Socket getSocket() {
        return socket;
    }
    
    @Override
    public void run() {
//        if(kt==null) {
//          System.out.println("Client " + clientNumber + "je diskonektovan");
//        }
      
        try {
            while (aktivan  && !isInterrupted()&&!(socket.isClosed())) {                
                KlijentTransfer ct = primi();
                if(ct==null){
                    return;
                }
               
                ServerTransfer st = new ServerTransfer();
                if(st==null) {
                return;
                }
              
                System.out.println("O:" +ct.getOperacija());
                boolean success;
                switch(ct.getOperacija()){
            
                  
                    case Konstante.VRATI_AGENTE:
                            List<OpstiDomenskiObjekat> agenti = Kontroler.getInstance().getAgente();
                            st.setUspesnost(Konstante.USPEH);
                            st.setPodaci(agenti);
                        break;
                    case Konstante.VRATI_PARTNERE: 
                            List<OpstiDomenskiObjekat> partneri = Kontroler.getInstance().getPoslovnePartnere();
                            st.setUspesnost(Konstante.USPEH);
                            st.setPodaci(partneri);
                        break;
                    case Konstante.VRATI_KLIJENTE:
                            List<OpstiDomenskiObjekat> klijenti = Kontroler.getInstance().getKlijente();
                            st.setUspesnost(Konstante.USPEH);
                            st.setPodaci(klijenti);
                        break;
                    case Konstante.VRATI_ARANZMANE:
                            List<OpstiDomenskiObjekat> aranzmani = Kontroler.getInstance().getAranzmane();
                            st.setUspesnost(Konstante.USPEH);
                            st.setPodaci(aranzmani);
                        break;
                    case Konstante.VRATI_MESTA:
                            List<OpstiDomenskiObjekat> mesta = Kontroler.getInstance().getMesta();
                            st.setUspesnost(Konstante.USPEH);
                            st.setPodaci(mesta);
                        break;
                    case Konstante.VRATI_STAVKE:
                            List<OpstiDomenskiObjekat> stavke = Kontroler.getInstance().getSveStavke();
                            st.setUspesnost(Konstante.USPEH);
                            st.setPodaci(stavke);
                        break;
                        case Konstante.VRATI_KLIJENTSKE_REZERVACIJE:
                        
                            List<OpstiDomenskiObjekat> Krezervacije = Kontroler.getInstance().getKlijentskeRezervacije((Klijent) ct.getParametar());
                            
                            st.setUspesnost(Konstante.USPEH);
                            st.setPodaci(Krezervacije);
                                                   
                          
                              
                           
                        
                        break;
                    case Konstante.VRATI_PARTNERSKE_ARANZMANE:
                        
                            List<OpstiDomenskiObjekat> partnerAranzmani = Kontroler.getInstance().getPartnerskeAranzmane((PoslovniPartner) ct.getParametar());
                            st.setUspesnost(Konstante.USPEH);
                            st.setPodaci(partnerAranzmani);
                        
                        break;
                    case Konstante.VRATI_STAVKE_ARANZMANA:
                        
                            List<OpstiDomenskiObjekat> stavkeAr = Kontroler.getInstance().getStavkeAranzmana((Aranzman) ct.getParametar());
                            st.setUspesnost(Konstante.USPEH);
                            st.setPodaci(stavkeAr);
                       
                        break;
                    case Konstante.VRATI_REZERVACIJE:
                        List<OpstiDomenskiObjekat> rezervacije = Kontroler.getInstance().getRezervacije();
                        st.setUspesnost(Konstante.USPEH);
                      
                        st.setPodaci(rezervacije);
                        break;
                    case Konstante.KREIRAJ_PARTNERA:
                        success = Kontroler.getInstance().kreirajPartnera((PoslovniPartner) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }
                        
                        break;
                    case Konstante.KREIRAJ_KLIJENTA:
                        success = Kontroler.getInstance().kreirajKlijenta((Klijent) ct.getParametar());
                        //System.out.println(""+ct.getParametar().toString());
                        if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }
                        
                        break;
                    case Konstante.KREIRAJ_ARANZMAN:
                        success = Kontroler.getInstance().kreirajAranzman((Aranzman) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }
                        
                        break;
                    case Konstante.KREIRAJ_REZERVACIJU:
                        success = Kontroler.getInstance().kreirajRezervaciju((Rezervacija) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }
                        
                        break;
                    case Konstante.KREIRAJ_STAVKU_ARANZMANA:
                        
                            success = Kontroler.getInstance().kreirajStavkuAranzmana((StavkaAranzmana) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }
                        
                        break;
                    case Konstante.IZMENI_KLIJENTA:
                        
                            success = Kontroler.getInstance().izmeniKlijenta((Klijent) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }
                            break;
                           case Konstante.IZMENI_REZERVACIJU:
                        
                            success = Kontroler.getInstance().izmeniRezervaciju((Rezervacija) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }  
                            
                        
                        break;
                    case Konstante.IZMENI_PARTNERA:
                        success = Kontroler.getInstance().izmeniPartnera((PoslovniPartner) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }

                        break;
                           case Konstante.IZMENI_ARANZMAN:
                        success = Kontroler.getInstance().izmeniAranzman((Aranzman) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }

                        break;
                        
                    case Konstante.OBRISI_PARTNERA:
                        success = Kontroler.getInstance().obrisiPartnera((PoslovniPartner) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }

                        break;
                    case Konstante.OBRISI_KLIJENTA:
                        success = Kontroler.getInstance().obrisiKlijenta((Klijent) ct.getParametar());//&& Kontroler.getInstance().obrisiRezervaciju((Rezervacija) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }

                        break;
                    case Konstante.OBRISI_REZERVACIJU:
                        success = Kontroler.getInstance().obrisiRezervaciju((Rezervacija) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }

                        break;
                    case Konstante.OBRISI_ARANZMAN:
                        success = Kontroler.getInstance().obrisiAranzman((Aranzman) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }

                        break;
                    case Konstante.OBRISI_STAVKU_ARANZMANA:
                        success = Kontroler.getInstance().obrisiStavkuAranzmana((StavkaAranzmana) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }

                        break;
                    case Konstante.PRETRAZI_KLIJENTE:
                    
                        List<OpstiDomenskiObjekat> klijentiPretraga = Kontroler.getInstance().pretraziKlijente((String) ct.getParametar());
                        st.setUspesnost(Konstante.USPEH);
                        st.setPodaci(klijentiPretraga);
                        break;
                    case Konstante.PRETRAZI_PARTNERE:
                    
                        List<OpstiDomenskiObjekat> partneriPretraga = Kontroler.getInstance().pretraziPartnere((String) ct.getParametar());
                        st.setUspesnost(Konstante.USPEH);
                        st.setPodaci(partneriPretraga);
                        break;
                        case Konstante.PRETRAZI_ARANZMANE:
                    
                        List<OpstiDomenskiObjekat> aranzmaniPretraga = Kontroler.getInstance().pretraziAranzmane((String) ct.getParametar());
                        st.setUspesnost(Konstante.USPEH);
                        st.setPodaci(aranzmaniPretraga);
                        break;
                    case Konstante.NADJI_AGENTA:
                        
                       
                      
                   
                         OpstiDomenskiObjekat ulogovaniAgent = Kontroler.getInstance().nadjiAgenta((Agent) ct.getParametar());
                   
                        if (ulogovaniAgent!=null) {
                             System.out.println("Client " + clientNumber + " has connected"); 
                            if (!KolekcijaUlogovanih.getInstance().getListaUlogovanih().contains(ulogovaniAgent)) {
                                KolekcijaUlogovanih.getInstance().dodaj((Agent)ulogovaniAgent);
                                System.out.print("Agent u kolekciji" +KolekcijaUlogovanih.getInstance().getListaUlogovanih().toString());
                                prijavljeniAgenti=KolekcijaUlogovanih.getInstance().getListaUlogovanih();
                                for (int i =0 ; i<prijavljeniAgenti.size(); i++) {
                                   prijavljeniAgenti.get(i).setBroj(i+1);
                                  
                                  
                            
                               
                                }
                                
                                st.setUspesnost(Konstante.USPEH);
                                st.setPodaci(ulogovaniAgent);
                                
                                }else{
                                st.setUspesnost(Konstante.GRESKA);
                                st.setPodaci(ulogovaniAgent);
                            }
                             
                         }else{
                            st.setUspesnost(Konstante.GRESKA);
                        }
                        break;
                    case Konstante.LOGOUT:
                        OpstiDomenskiObjekat ulogovaniAg = (Agent) ct.getParametar();
                        if (ulogovaniAg!=null) {
                              if (!KolekcijaOdjavljenih.getInstance().getLista().contains(ulogovaniAg)) {
                                KolekcijaOdjavljenih.getInstance().dodaj((Agent)ulogovaniAg);
                              
                          odjavljeniAgenti= KolekcijaOdjavljenih.getInstance().getLista();
                                  for (int i=0; i<odjavljeniAgenti.size();i++) {
                                     for (int j =0 ; j<prijavljeniAgenti.size(); j++) {
                                         if(odjavljeniAgenti.get(i).getImePrezime().equals(prijavljeniAgenti.get(j).getImePrezime())) {
                                             odjavljeniAgenti.get(i).setBroj(prijavljeniAgenti.get(j).getBroj());
                                   
                                      
                                   
                                  
                            KolekcijaUlogovanih.getInstance().obrisi((Agent)ulogovaniAg);
                             System.out.println("Client " + clientNumber + " has disconnected"); 
                                             for (Agent ulogovani : KolekcijaUlogovanih.getInstance().getListaUlogovanih()) {
                                                 System.out.println(ulogovani);
                                             }
                                          
                            st.setUspesnost(Konstante.USPEH);
                             Server.closeClient(this);   
                         
                           
                          
                                  } 
                              }
                                  }
                              }
                        }else{
                            st.setUspesnost(Konstante.GRESKA);
                        }
                        break;
                }
                posalji(st);
            }
            
        } catch (Exception e) {
             System.out.println("Client " + clientNumber + " has disconnected");
        }
            
                       
                   
                                
                            
                       
                        
  
                          
                           
        }                      
                       
                       
                            //server.Server.closeClient(this);
                           //}
                      
                            
             
    

    public void posalji(ServerTransfer st) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(st);
            out.flush();
         
        }catch(EOFException|SocketException e) {
               if(socket!=null &&!socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(NitKlijent.class.getName()).log(Level.SEVERE, null, ex);
                }
               }
        }catch (IOException ex) {
            Logger.getLogger(NitKlijent.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    

    public KlijentTransfer primi() {
        KlijentTransfer kt = new KlijentTransfer();
        try {
           
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
           
            kt = (KlijentTransfer) in.readObject();
             return kt;
             }catch(EOFException|SocketException e) {
               if(socket!=null &&!socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(NitKlijent.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        } 
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(NitKlijent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
}
