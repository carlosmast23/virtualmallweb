/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.mb;

import com.sun.faces.context.flash.ELFlash;
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

        subcategorias=new ArrayList<SelectItem>();
        
        List<Categoria> categorias = ServiceFactory.getCategoriaService().obtenerTodos(); //Cambiar luego a obtener activos        
        for (Categoria categoria : categorias) {
            SelectItemGroup categoriaGroup = new SelectItemGroup(categoria.getNombre());
            
            List<Subcategoria> subcategorias=ServiceFactory.getSubCategoriaService().obtenerSubcategoriasActivasPorCategoria(categoria);
            
            List<SelectItem> items=new ArrayList<SelectItem>();
            for (Subcategoria subcategoria : subcategorias) 
            {
                items.add(new SelectItem(subcategoria,subcategoria.getNombre()));
            }
            
            SelectItem[] arrayItems=new SelectItem[items.size()];
            categoriaGroup.setSelectItems(items.toArray(arrayItems)); 
            this.subcategorias.add(categoriaGroup);
        }
       

    }

    private void obtenerParametrosFlash() {
        proveedor.setNombres(ELFlash.getFlash().get(PARAMETRO_NOMBRES).toString());
        proveedor.setApellidos(ELFlash.getFlash().get(PARAMETRO_APELLIDOS).toString());
        proveedor.setNombreNegocio(ELFlash.getFlash().get(PARAMETRO_NOMBRE_EMPRESA).toString());
        proveedor.setWhatsapp(ELFlash.getFlash().get(PARAMETRO_CELULAR).toString());
        proveedor.setCorreo(ELFlash.getFlash().get(PARAMETRO_CORREO).toString());
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
            validar();
            ServiceFactory.getProveedorService().grabar(proveedor);
            UtilidadesMensajes.mensaje("Proveedor grabado correctamente", FacesMessage.SEVERITY_INFO);
            return "index.xhtml?faces-redirect=true";
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
