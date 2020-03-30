/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.service;

import ec.com.codesoft.virtualmall.core.MetodoInterfaceTransaccion;
import ec.com.codesoft.virtualmall.entity.Proveedor;
import ec.com.codesoft.virtualmall.exception.ServicioCodefacException;
import ec.com.codesoft.virtualmall.facade.ProveedorFacade;
import java.rmi.RemoteException;

/**
 *
 * @author CARLOS_CODESOFT
 */
public class ProveedorService extends ServiceAbstract<Proveedor,ProveedorFacade>{

    public ProveedorService() {
        super(ProveedorFacade.class);
    }

    @Override
    public Proveedor grabar(final Proveedor entity) throws ServicioCodefacException {
        ejecutarTransaccion(new MetodoInterfaceTransaccion() {
            @Override
            public void transaccion() throws ServicioCodefacException, RemoteException {
                entityManager.persist(entity.getUsuario());
                entityManager.persist(entity);
            }
        });
        return entity;
    }
    
    
}
