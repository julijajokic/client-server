/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;



import config.Config;
import domen.Klijent;
import domen.Mesto;
import domen.OpstiDomenskiObjekat;
import domen.RezI;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.Repository;
import repository.db.DbConnectionFactory;
import repository.db.DbRepository;

/**
 *
 * @author Aleksa
 */
public class RepositoryDbGeneric implements DbRepository<OpstiDomenskiObjekat> {
 private Connection connection;
    private static RepositoryDbGeneric instance;

    public RepositoryDbGeneric() {
    }

    public static RepositoryDbGeneric getInstance() {
        if (instance == null) {
            instance = new RepositoryDbGeneric();
        }
        return instance;
    }

    public boolean connectToMySqlDatabase() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drajver ucitan");
//            String url = Config.getInstance().getDbUrl();
//            String user = Config.getInstance().getUsername();
//            String pass = Config.getInstance().getPassword();
//            connection = DriverManager.getConnection(url, user, pass);
//            connection.setAutoCommit(false);
        connection= DbConnectionFactory.getInstance().getConnection();
            System.out.println("Konektovanje na bazu uspesno!");
            return true;
        } catch (ClassNotFoundException ex) {
            System.out.println("Drajver nije nadjen");
            return false;
        } catch (SQLException ex) {
            System.out.println("Neuspesno konektovanje na bazu!");
            return false;
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Konekcija ne moze da se zatvori!");
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException ex) {
            System.out.println("Commit ne moze da se uradi!");
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            System.out.println("Rollback ne moze da se uradi!");
        }
    }

    public  List<OpstiDomenskiObjekat> getAllOpstiDomenskiObjekats(OpstiDomenskiObjekat o) throws SQLException {
        try {
            
            String query = "SELECT * FROM " + o.getNazivTabele();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
             List<OpstiDomenskiObjekat> list=new LinkedList<>();
           list = o.konvertujRSUListu(rs);
             s.close();
            System.out.println("ResultSet uspesno postavljen!");
            
               return list;
          
            
           
        } catch (SQLException ex) {
            System.out.println("Greska u postavljanju ResultSet-a na klasu " + o.getNazivTabele());
            ex.printStackTrace();
            throw ex;
        }
       
    }

    public synchronized OpstiDomenskiObjekat getOpstiDomenskiObjekatByPrimaryKey(OpstiDomenskiObjekat o, int id) throws SQLException {
        String query;

        if (o.getSlozeniPrimarniKljuc() == null) {
            query = "SELECT * FROM " + o.getNazivTabele() + " WHERE " + o.getNazivPrimarnogKljuca() + "=" + id;
        } else {
            query = "SELECT * FROM " + o.getNazivTabele() + " WHERE " + o.getSlozeniPrimarniKljuc();
        }
        Statement s = (Statement) connection.createStatement();
        ResultSet rs = s.executeQuery(query);
        List<OpstiDomenskiObjekat> list = o.konvertujRSUListu(rs);
        s.close();
        return list.get(0);
       
    }

    public synchronized boolean deleteOpstiDomenskiObjekat(OpstiDomenskiObjekat o) throws SQLException {
        try {
            String query;

            if (o.getSlozeniPrimarniKljuc() == null) {
                query = "DELETE FROM " + o.getNazivTabele() + " WHERE " + o.getNazivPrimarnogKljuca() + "=" + o.getVrednostPrimarnogKljuca();
            } else {
                query = "DELETE FROM " + o.getNazivTabele() + " WHERE " +o.getSlozeniPrimarniKljuc();
            }
             // query = "DELETE FROM " + o.getNazivTabele() + " WHERE " + o.getNazivPrimarnogKljuca() + "=" + o.getVrednostPrimarnogKljuca();
            System.out.println(query);
            Statement s = (Statement) connection.createStatement();
            s.executeUpdate(query);
            commit();
            s.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Ne moze da se obrise objekat: " + o.getNazivTabele());
            throw ex;
        }
    }

    public synchronized boolean deleteOpstiDomenskiObjekats(List<OpstiDomenskiObjekat> list) throws SQLException {
        for (OpstiDomenskiObjekat o : list) {
            deleteOpstiDomenskiObjekat(o);
        }
        return true;
    }

    public synchronized boolean saveOpstiDomenskiObjekat(OpstiDomenskiObjekat o) throws SQLException {
        try {
         //  int id=1;

            String query = "";
            query = "INSERT INTO " + o.getNazivTabele() + "(" + o.getNaziveParametara() + ")" + " VALUES (" + o.getParametre() + ")";
            System.out.println(query);
          Statement s = (Statement) connection.createStatement();
             // PreparedStatement s = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int i = s.executeUpdate(query);
                 // ResultSet rs = s.getGeneratedKeys();
            
//            while(rs.next()){
//                id = rs.getInt(1);
//            }
            s.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Ne moze da se sacuva objekat: " + o.getNazivTabele() + "\n" + ex.getMessage());
            throw ex;
        }
    }

    public synchronized void saveOpstiDomenskiObjekats(List<OpstiDomenskiObjekat> list) throws SQLException {
        for (OpstiDomenskiObjekat o : list) {
            saveOpstiDomenskiObjekat(o);
        }
    }

    public synchronized boolean updateOpstiDomenskiObjekat(OpstiDomenskiObjekat o) throws SQLException {
        try {
            String query = "";
            if (o.getSlozeniPrimarniKljuc() == null) {
                query = "UPDATE " + o.getNazivTabele() + " SET " + o.getUpdateUpit() + " WHERE " + o.getNazivPrimarnogKljuca() + "=" + o.getVrednostPrimarnogKljuca();
            } else {
                query = "UPDATE " + o.getNazivTabele() + " SET " + o.getUpdateUpit() + " WHERE " + o.getSlozeniPrimarniKljuc();
            }
                //  query = "UPDATE " + o.getNazivTabele()  +  " SET " + o.getUpdateUpit()+" WHERE " + o.getNazivPrimarnogKljuca() + "=" + o.getVrednostPrimarnogKljuca();
            System.out.println(query);
            Statement s = (Statement) connection.createStatement();
            int i = s.executeUpdate(query);
            s.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Ne moze da se azurira objekat: " + o.getNazivTabele());
            throw ex;
        }
    
}

    public synchronized List<RezI> vratiI() throws Exception {
       List<RezI> izvestaji = new ArrayList<>();
        String upit = "select * from klijent as k join rezervacija r on k.KlijentID=r.KlijentID";
        try{
            connectToMySqlDatabase();
       Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            
            while (rs.next()) {
                boolean postoji = false;
              Klijent k= new Klijent();
                k.setKlijentID(rs.getInt("KlijentID"));
                k.setIme(rs.getString("Prezime"));
                k.setPrezime(rs.getString("Ime"));
                k.setEmail(rs.getString("EmailAdresa"));
                k.setTelefon(rs.getString("BrojTelefona"));
                
                int mesto=rs.getInt("MestoID");
                Mesto mestom=vartiM(mesto);
            k.setMesto(mestom);
                
                for (RezI pi : izvestaji) {
                    if(pi.getKlijent().getKlijentID()==k.getKlijentID() ){
                       pi.setBrojRezervacija(pi.getBrojRezervacija()+1);
                        postoji = true;
                        break;
                    }
                }
                
                if(!postoji){
                 RezI r= new RezI();
                 r.setKlijent(k);
                 r.setBrojRezervacija(1);
                
                   izvestaji.add(r); 
                }               
            }
           
        } catch (SQLException ex) {
            System.out.println(">>>Broker: " + ex.getMessage());
        }
        
        return izvestaji;
    }

    private synchronized Mesto vartiM(int mesto) {
        String upit = "SELECT * from mesto where MestoID= ?";
        try {
            PreparedStatement ps =  connection.prepareStatement(upit);
            ps.setInt(1, mesto);
            
            
            
            ResultSet rs =ps.executeQuery();
            
            if(rs.next()){
            
            
                Mesto m = new Mesto (mesto,rs.getInt("Ptt"), rs.getString("Naziv"));
                return m;
            
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryDbGeneric.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;


    }
    }
    


 


   

