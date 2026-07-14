package repository;

import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Aleksa
 * @param <OpstDomenskiObjekat>
 */
public interface Repository<OpstiDomenskiObjekat> {

    public List<OpstiDomenskiObjekat> getAllOpstiDomenskiObjekats(OpstiDomenskiObjekat o)throws SQLException;
     public OpstiDomenskiObjekat getOpstiDomenskiObjekatByPrimaryKey(OpstiDomenskiObjekat o, int id) throws SQLException;
    public boolean deleteOpstiDomenskiObjekat(OpstiDomenskiObjekat o) throws SQLException;
 public boolean deleteOpstiDomenskiObjekats(List<OpstiDomenskiObjekat> list) throws SQLException;
   public boolean saveOpstiDomenskiObjekat(OpstiDomenskiObjekat o) throws SQLException ;
 public void saveOpstiDomenskiObjekats(List<OpstiDomenskiObjekat> list) throws SQLException;
   public boolean updateOpstiDomenskiObjekat(OpstiDomenskiObjekat o) throws SQLException;  
}
