/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.mb;

import ec.com.codesoft.virtualmall.core.ServiceFactory;
import ec.com.codesoft.virtualmall.entity.Proveedor;
import ec.com.codesoft.virtualmall.entity.Usuario;
import ec.com.codesoft.virtualmall.exception.PersistenciaDuplicadaException;
import ec.com.codesoft.virtualmall.exception.ServicioCodefacException;
import ec.com.codesoft.virtualmall.facade.AbstractFacade;
import ec.com.codesoft.virtualmall.util.UtilidadesMensajes;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;

/**
 *
 * @author CARLOS_CODESOFT
 */
@ManagedBean
@ViewScoped
public class RegistroProveedorMb implements Serializable{
    
    //private Usuario usuario;
    private Proveedor proveedor;
    
    private String claveDuplicada;
    
    @PostConstruct
    public void init()
    {
        try {
            AbstractFacade.cargarEntityManager();
        } catch (PersistenceException ex) {
            Logger.getLogger(RegistroProveedorMb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PersistenciaDuplicadaException ex) {
            Logger.getLogger(RegistroProveedorMb.class.getName()).log(Level.SEVERE, null, ex);
        }
        proveedor=new Proveedor();
        proveedor.setUsuario(new Usuario());
    }
    
    public String grabar()
    {
        try {
            validar();
            ServiceFactory.getProveedorService().grabar(proveedor);
            UtilidadesMensajes.mensaje("Proveedor grabado correctamente",FacesMessage.SEVERITY_INFO);
            return "index.xhtml?faces-redirect=true";
        } catch (ServicioCodefacException ex) {
            UtilidadesMensajes.mensaje(ex.getMessage(),FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(RegistroProveedorMb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public void validar() throws ServicioCodefacException
    {
        if(!proveedor.getUsuario().getClave().equals(claveDuplicada))
        {
            throw new ServicioCodefacException("Las claves ingresadas son distintas");
        }
    }
    
    
    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getClaveDuplicada() {
        return claveDuplicada;
    }

    public void setClaveDuplicada(String claveDuplicada) {
        this.claveDuplicada = claveDuplicada;
    }
    
    
    
}
