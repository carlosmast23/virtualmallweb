/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.mb;

import ec.com.codesoft.virtualmall.entity.SolicitudBusqueda;
import ec.com.codesoft.virtualmall.exception.ServicioCodefacException;
import ec.com.codesoft.virtualmall.service.SolicitudBusquedaService;
import ec.com.codesoft.virtualmall.util.UtilidadesMensajes;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
    }
    
    public void grabar()
    {
        
        setearDatosAdicionales();
        try {
            servicio.grabar(busqueda);
            UtilidadesMensajes.mensaje("Mensaje generado correctamente",FacesMessage.SEVERITY_INFO);
        } catch (ServicioCodefacException ex) {
            Logger.getLogger(BusquedaMb.class.getName()).log(Level.SEVERE, null, ex);
            UtilidadesMensajes.mensaje(ex.getMessage(),FacesMessage.SEVERITY_ERROR);
        }
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
