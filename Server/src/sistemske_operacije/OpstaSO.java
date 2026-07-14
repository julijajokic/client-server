/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import repository.Repository;
import repository.db.impl.RepositoryDbGeneric;

/**
 *
 * @author Tami
 */
public abstract class OpstaSO {
   protected final RepositoryDbGeneric REPOSITORY = new RepositoryDbGeneric();

    public final void executeOperation() throws Exception {
        try {
         REPOSITORY.getInstance().connectToMySqlDatabase();
            executeSpecificOperation();
          REPOSITORY.getInstance().commit();
        REPOSITORY.getInstance().closeConnection();
        } catch (Exception e) {
            System.out.println(">>>>" + e.getMessage());
            REPOSITORY.getInstance().rollback();
         REPOSITORY.getInstance().closeConnection();
        }
    }

    protected abstract void executeSpecificOperation() throws Exception;
}
