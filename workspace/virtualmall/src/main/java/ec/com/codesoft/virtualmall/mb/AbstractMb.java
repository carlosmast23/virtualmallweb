/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.mb;

import ec.com.codesoft.virtualmall.exception.PersistenciaDuplicadaException;
import ec.com.codesoft.virtualmall.facade.AbstractFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.persistence.PersistenceException;

/**
 *
 * @author CARLOS_CODESOFT
 */
public abstract class AbstractMb {
    
    @PostConstruct
    private void init()
    {
        System.out.println("Iniciando init Abstract");
        try {
            AbstractFacade.cargarEntityManager();
        } catch (PersistenceException ex) {
            Logger.getLogger(AbstractMb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PersistenciaDuplicadaException ex) {
            Logger.getLogger(AbstractMb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
