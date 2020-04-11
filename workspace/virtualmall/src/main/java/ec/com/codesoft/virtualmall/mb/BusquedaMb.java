/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.mb;

import ec.com.codesoft.virtualmall.core.ConstantesMb;
import ec.com.codesoft.virtualmall.entity.SolicitudBusqueda;
import ec.com.codesoft.virtualmall.exception.ServicioCodefacException;
import ec.com.codesoft.virtualmall.service.SolicitudBusquedaService;
import ec.com.codesoft.virtualmall.util.UtilidadesMensajes;
import ec.com.codesoft.virtualmall.util.UtilidadesWeb;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author CARLOS_CODESOFT
 */
@ManagedBean
@ViewScoped
public class BusquedaMb extends AbstractMb implements Serializable{
    private SolicitudBusqueda busqueda;
    private SolicitudBusquedaService servicio;
    /**
     * Variable para obtener la prioridad del tiempo de la busqueda
     */
    private String prioridadMinutos;
    
    @PostConstruct
    public void init()
    {
        System.out.println("init ...");       
        busqueda=new SolicitudBusqueda();
        servicio=new SolicitudBusquedaService();
        
        String parametro=UtilidadesWeb.buscarParametroPeticion("busqueda");
        System.out.println("Parametro="+parametro);
        if(parametro!=null)
        {
            busqueda.setBusqueda(parametro);
        }
        
    }
    
    public String grabar()
    {
        
        setearDatosAdicionales();
        try {
            servicio.grabar(busqueda);
            UtilidadesMensajes.mensaje("Mensaje generado correctamente",FacesMessage.SEVERITY_INFO);
            /**
             * Generar el link para redirecionar a otra pantalla que muestra el resultado
             */
            Map<String,String> mapParametro=new HashMap<String,String>();
            mapParametro.put(RespuestaMb.PARAMETRO_TITULO,"Busqueda Generada Corretamente");
            mapParametro.put(RespuestaMb.PARAMETRO_LINK,ConstantesMb.INDEX_RUTA);
            mapParametro.put(RespuestaMb.PARAMETRO_CONTENIDO,"Su búsqueda se genero correctamente, vamos a buscar el producto o servicio solicitado en nuestra red de proveedores y nos comunicaremos por el numero de contacto proporcionado para mostrar las diferentes opciones, y finalmente que se pongan en contacto con el proveedor que le interesa su oferta.");            
            return UtilidadesWeb.agregarParametroGet(ConstantesMb.RESPUESTA_RUTA, mapParametro,true);
        } catch (ServicioCodefacException ex) {
            Logger.getLogger(BusquedaMb.class.getName()).log(Level.SEVERE, null, ex);
            UtilidadesMensajes.mensaje(ex.getMessage(),FacesMessage.SEVERITY_ERROR);
        }
        
        return "";
    }
    
    private void setearDatosAdicionales() {
        busqueda.setTiempoRespuestaMin(Integer.parseInt(prioridadMinutos)); 
    }


    public SolicitudBusqueda getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(SolicitudBusqueda busqueda) {
        this.busqueda = busqueda;
    }

    public String getPrioridadMinutos() {
        return prioridadMinutos;
    }

    public void setPrioridadMinutos(String prioridadMinutos) {
        this.prioridadMinutos = prioridadMinutos;
    }

    
    
}
