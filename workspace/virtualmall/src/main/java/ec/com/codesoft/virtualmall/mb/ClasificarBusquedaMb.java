/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.mb;

import ec.com.codesoft.virtualmall.commons.SubcategoriasCommons;
import ec.com.codesoft.virtualmall.core.ServiceFactory;
import ec.com.codesoft.virtualmall.entity.SolicitudBusqueda;
import ec.com.codesoft.virtualmall.entity.Subcategoria;
import ec.com.codesoft.virtualmall.entity.SubcategoriaBusqueda;
import ec.com.codesoft.virtualmall.exception.ServicioCodefacException;
import ec.com.codesoft.virtualmall.util.UtilidadesMensajes;
import java.io.Serializable;
import java.lang.reflect.Array;
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

/**
 *
 * @author CARLOS_CODESOFT
 */
@ManagedBean
@ViewScoped
public class ClasificarBusquedaMb implements Serializable{
    //private List<SolicitudBusqueda> busquedas;
    private List<BusquedaModel> busquedasModel;
    private List<SelectItem> subcategorias;    
    
    @PostConstruct
    public void init()
    {        
        crearListaCategorias();
        crearBusquedaModel();
    }
    
    public void crearBusquedaModel()
    {
        busquedasModel=new ArrayList<BusquedaModel>();
        List<SolicitudBusqueda> busquedas=ServiceFactory.getSolicitudBusquedaService().buscarPorEstado(SolicitudBusqueda.EstadoEnum.GENERADA);
        for (SolicitudBusqueda busqueda : busquedas) 
        {
            busquedasModel.add(new BusquedaModel(busqueda));
        }
    
    }
    
    
    public void autorizarBusqueda(BusquedaModel busquedaModel)
    { 
        System.out.println("actualizando  datos ..."); 
        try { 
            //busqueda.setEstadoEnum(SolicitudBusqueda.EstadoEnum.VERIFICADA);
            ServiceFactory.getSolicitudBusquedaService().categorizar(busquedaModel.getSolicitudBusquedaResult()); 
            UtilidadesMensajes.mensaje("Autorizada Busqueda Correctamente",FacesMessage.SEVERITY_INFO);
            init();
        } catch (ServicioCodefacException ex) {
            Logger.getLogger(ClasificarBusquedaMb.class.getName()).log(Level.SEVERE, null, ex);
            UtilidadesMensajes.mensaje(ex.getMessage(),FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void crearListaCategorias() 
    {   
        subcategorias=SubcategoriasCommons.crearListaCategorias();
    }
    
    public void ejemplo()
    {
        System.out.println("ejemplo ...");
    }

    public List<BusquedaModel> getBusquedasModel() {
        return busquedasModel;
    }

    public void setBusquedasModel(List<BusquedaModel> busquedasModel) {
        this.busquedasModel = busquedasModel;
    }

    
    

    public List<SelectItem> getSubcategorias() {
        return subcategorias;
    }

    public void setSubcategorias(List<SelectItem> subcategorias) {
        this.subcategorias = subcategorias;
    }

    
    public static class BusquedaModel
    {
        private SolicitudBusqueda solicitudBusqueda;
        private Subcategoria[] subCategoriaSeleccionadas;

        public BusquedaModel(SolicitudBusqueda solicitudBusqueda) {
            this.solicitudBusqueda = solicitudBusqueda;
            crearSubCategoriasBusqueda();
            
        }
        
        public void crearSubCategoriasBusqueda()
        {
            if(solicitudBusqueda.obtenerSubCategoriaActivos()!=null)
            {
                subCategoriaSeleccionadas=new Subcategoria[solicitudBusqueda.obtenerSubCategoriaActivos().size()];
                this.subCategoriaSeleccionadas=solicitudBusqueda.obtenerSubCategoriaActivos().toArray(subCategoriaSeleccionadas);
            }
        }
        
        public SolicitudBusqueda getSolicitudBusquedaResult()
        {
            if(subCategoriaSeleccionadas!=null)
            {                
                solicitudBusqueda.addAllCategoria(new ArrayList<Subcategoria>(Arrays.asList(subCategoriaSeleccionadas)));
            }
            return solicitudBusqueda;
        }
        

        public SolicitudBusqueda getSolicitudBusqueda() {
            return solicitudBusqueda;
        }

        public void setSolicitudBusqueda(SolicitudBusqueda solicitudBusqueda) {
            this.solicitudBusqueda = solicitudBusqueda;
        }

        public Subcategoria[] getSubCategoriaSeleccionadas() {
            return subCategoriaSeleccionadas;
        }

        public void setSubCategoriaSeleccionadas(Subcategoria[] subCategoriaSeleccionadas) {
            this.subCategoriaSeleccionadas = subCategoriaSeleccionadas;
        }
        
        
        
    }
    
    
}
