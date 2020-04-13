/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.mb;

import com.sun.faces.context.flash.ELFlash;
import ec.com.codesoft.virtualmall.commons.SubcategoriasCommons;
import ec.com.codesoft.virtualmall.core.NavigationMb;
import ec.com.codesoft.virtualmall.core.ServiceFactory;
import ec.com.codesoft.virtualmall.entity.Categoria;
import ec.com.codesoft.virtualmall.entity.Proveedor;
import ec.com.codesoft.virtualmall.entity.Subcategoria;
import ec.com.codesoft.virtualmall.entity.Usuario;
import ec.com.codesoft.virtualmall.exception.PersistenciaDuplicadaException;
import ec.com.codesoft.virtualmall.exception.ServicioCodefacException;
import ec.com.codesoft.virtualmall.facade.AbstractFacade;
import ec.com.codesoft.virtualmall.util.UtilidadesMensajes;
import ec.com.codesoft.virtualmall.util.UtilidadesWeb;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.persistence.PersistenceException;

/**
 *
 * @author CARLOS_CODESOFT
 */
@ManagedBean
@ViewScoped
public class RegistroProveedorMb implements Serializable {

    public static final String PARAMETRO_NOMBRES = "NOMBRES";
    public static final String PARAMETRO_APELLIDOS = "APELLIDOS";
    public static final String PARAMETRO_NOMBRE_EMPRESA = "NOMBRE_EMPRESA";
    public static final String PARAMETRO_CELULAR = "CELULAR";
    public static final String PARAMETRO_CORREO = "CORREO";

    //private Usuario usuario;
    private Proveedor proveedor;

    private String claveDuplicada;

    private List<SelectItem> subcategorias;
    private Subcategoria[] subCategoriaSeleccionadas;

    @PostConstruct
    public void init() {
        try {
            AbstractFacade.cargarEntityManager();
        } catch (PersistenceException ex) {
            Logger.getLogger(RegistroProveedorMb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PersistenciaDuplicadaException ex) {
            Logger.getLogger(RegistroProveedorMb.class.getName()).log(Level.SEVERE, null, ex);
        }
        proveedor = new Proveedor();
        proveedor.setUsuario(new Usuario());

        obtenerParametrosGet();
        obtenerParametrosFlash(); //TODO: Optimizar para si obtengo los parametros por get no obtenga por flash
        crearListaCategorias();

    }

    public void crearListaCategorias() {
        
        subcategorias=SubcategoriasCommons.crearListaCategorias();
    }

    private void obtenerParametrosFlash() {
        if(!ELFlash.getFlash().isEmpty())
        {
            proveedor.setNombres(ELFlash.getFlash().get(PARAMETRO_NOMBRES).toString());
            proveedor.setApellidos(ELFlash.getFlash().get(PARAMETRO_APELLIDOS).toString());
            proveedor.setNombreNegocio(ELFlash.getFlash().get(PARAMETRO_NOMBRE_EMPRESA).toString());
            proveedor.setWhatsapp(ELFlash.getFlash().get(PARAMETRO_CELULAR).toString());
            proveedor.setCorreo(ELFlash.getFlash().get(PARAMETRO_CORREO).toString()); 
        }
    }

    private void obtenerParametrosGet() {
        System.out.println("NOMBRE: " + UtilidadesWeb.buscarParametroPeticion(PARAMETRO_NOMBRES));
        proveedor.setNombres((UtilidadesWeb.buscarParametroPeticion(PARAMETRO_NOMBRES) != null) ? UtilidadesWeb.buscarParametroPeticion(PARAMETRO_NOMBRES) : "");
        proveedor.setApellidos((UtilidadesWeb.buscarParametroPeticion(PARAMETRO_APELLIDOS) != null) ? UtilidadesWeb.buscarParametroPeticion(PARAMETRO_APELLIDOS) : "");
        proveedor.setNombreNegocio((UtilidadesWeb.buscarParametroPeticion(PARAMETRO_NOMBRE_EMPRESA) != null) ? UtilidadesWeb.buscarParametroPeticion(PARAMETRO_NOMBRE_EMPRESA) : "");
        proveedor.setWhatsapp((UtilidadesWeb.buscarParametroPeticion(PARAMETRO_CELULAR) != null) ? UtilidadesWeb.buscarParametroPeticion(PARAMETRO_CELULAR) : "");
        proveedor.setCorreo((UtilidadesWeb.buscarParametroPeticion(PARAMETRO_CORREO) != null) ? UtilidadesWeb.buscarParametroPeticion(PARAMETRO_CORREO) : "");
    }

    public String grabar() {
        try {
            System.out.println("grabando lo datos");
            setearValores(); 
            validar();
            ServiceFactory.getProveedorService().grabar(proveedor);
            UtilidadesMensajes.mensaje("Proveedor grabado correctamente", FacesMessage.SEVERITY_INFO);
            
            //Crear mennsaje para mostrar al cliente
            RespuestaMb.Mensaje mensaje=new RespuestaMb.Mensaje();
            mensaje.titulo="Registro Proveedor Correto";
            mensaje.mensaje="El registro fue realizado correctamente , si algún cliente necesita algún producto por su sector Virtual Mall se pondrá en contacto atreves del numero proporcionado para que pueda generar propuestas ";
            mensaje.linkRedirigir=NavigationMb.INDEX.getRutaJsf();
            ELFlash.getFlash().put(RespuestaMb.PARAMETRO_MENSAJE,mensaje);
            
            return NavigationMb.RESPUESTA.getRutaJsf();
        } catch (ServicioCodefacException ex) {
            UtilidadesMensajes.mensaje(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(RegistroProveedorMb.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void validar() throws ServicioCodefacException {
        
        
        if (!proveedor.getUsuario().getClave().equals(claveDuplicada)) {
            throw new ServicioCodefacException("Las claves ingresadas son distintas");
        }
    }
    
    private void setearValores() {        
        System.out.println("Datos de las subcategorias seleccionadas -> ");
        for (Subcategoria subCategoriaSeleccionada : subCategoriaSeleccionadas) {
            System.out.println(">"+subCategoriaSeleccionada.getNombre());
        }
        
        proveedor.addAllCategoria(subCategoriaSeleccionadas);
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

    public List<SelectItem> getSubcategorias() {
        return subcategorias;  
    }

    public void setSubcategorias(List<SelectItem> subcategorias) {
        this.subcategorias = subcategorias;
    }

    public Subcategoria[] getSubCategoriaSeleccionadas() {
        return subCategoriaSeleccionadas; 
    }

    public void setSubCategoriaSeleccionadas(Subcategoria[] subCategoriaSeleccionadas) {
        this.subCategoriaSeleccionadas = subCategoriaSeleccionadas;  
    }

  
}
