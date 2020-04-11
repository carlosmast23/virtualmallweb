/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.mb;

import com.sun.faces.context.flash.ELFlash;
import ec.com.codesoft.virtualmall.core.ConstantesMb;
import ec.com.codesoft.virtualmall.util.UtilidadesWeb;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author CARLOS_CODESOFT
 */
@ManagedBean(name = "indexMb")
@ViewScoped
public class IndexMb extends AbstractMb implements Serializable {
    
    private String nombres;
    private String apellidos;
    private String nombreEmpresa;
    private String whatsapp;
    private String correo;
    
    private String busquedaTexto;  
     
    @PostConstruct
    public void init()   
    {
        nombres="";
        busquedaTexto="";
        System.out.println("iniciando indexMb ...");
    }

    public String getBusquedaTexto() {
        return busquedaTexto; 
    }

    public void setBusquedaTexto(String busquedaTexto) {
        this.busquedaTexto = busquedaTexto;
    }   
    
    public String ejemplo()
    {
        System.out.println("presionando boton: "+busquedaTexto);
        Map<String,String> mapParametros=new HashMap<String,String>();
        mapParametros.put("busqueda",busquedaTexto);
        mapParametros.put("faces-redirect","true");
        String rutaFinal=UtilidadesWeb.agregarParametroGet(ConstantesMb.BUSQUEDA_RUTA,mapParametros);        
        System.out.println(rutaFinal);
        return rutaFinal;
    }
    
    public String registroProveedorRedirigir() 
    {
        ELFlash.getFlash().put(RegistroProveedorMb.PARAMETRO_NOMBRES,nombres);
        ELFlash.getFlash().put(RegistroProveedorMb.PARAMETRO_APELLIDOS,apellidos);
        ELFlash.getFlash().put(RegistroProveedorMb.PARAMETRO_CELULAR,whatsapp);
        ELFlash.getFlash().put(RegistroProveedorMb.PARAMETRO_CORREO,correo);
        ELFlash.getFlash().put(RegistroProveedorMb.PARAMETRO_NOMBRE_EMPRESA,nombreEmpresa);
        System.out.println("redireccionado");
        
        return "registroProveedor"; 
        
        
        //return ""; 
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
    
}
