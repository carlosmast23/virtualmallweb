/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.service;

import ec.com.codesoft.virtualmall.core.MetodoInterfaceTransaccion;
import ec.com.codesoft.virtualmall.entity.Proveedor;
import ec.com.codesoft.virtualmall.entity.SubcategoriaProveedor;
import ec.com.codesoft.virtualmall.entity.Usuario;
import ec.com.codesoft.virtualmall.exception.ServicioCodefacException;
import ec.com.codesoft.virtualmall.facade.ProveedorFacade;
import ec.com.codesoft.virtualmall.facade.UsuarioFacade;
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
        
        validarGrabar(entity);
        
        ejecutarTransaccion(new MetodoInterfaceTransaccion() {
            @Override
            public void transaccion() throws ServicioCodefacException, RemoteException {
                
                /**
                 * ============================================================
                 *              GRABANDO CATEGORIAS
                 * ============================================================
                 */
                for (SubcategoriaProveedor subcategoriaProveedor : entity.getSubcategoriaProveedorList()) 
                {
                    entityManager.persist(subcategoriaProveedor);
                }
                /**
                 * ============================================================
                 *              GRABANDO USUARIOS
                 * ============================================================
                 */
                entityManager.persist(entity.getUsuario());
                /**
                 * ============================================================
                 *              GRABANDO PROVEEDOR
                 * ============================================================
                 */
                entityManager.persist(entity);
            }
        });
        return entity;
    }
    
    private void validarGrabar(Proveedor entity) throws ServicioCodefacException
    {
        if(entity.getSubcategoriaProveedorList()==null || entity.getSubcategoriaProveedorList().size()==0)
        {
            throw new ServicioCodefacException("Las categorias son requeridas para grabar");
        }
        
        UsuarioFacade usuarioFacade=new UsuarioFacade();
        Usuario usuarioResultado = usuarioFacade.buscarPorNick(entity.getUsuario().getNick());
        if(usuarioResultado!=null)
        {
            throw new ServicioCodefacException("No se puede registrar el nombre de usuario porque ya se encuentra registrado");
        }
        
    }
    
    
    
    
    
}
