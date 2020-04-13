/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.core;

import ec.com.codesoft.virtualmall.service.CategoriaService;
import ec.com.codesoft.virtualmall.service.ProveedorService;
import ec.com.codesoft.virtualmall.service.SolicitudBusquedaService;
import ec.com.codesoft.virtualmall.service.SubCategoriaService;
import ec.com.codesoft.virtualmall.service.UsuarioService;

/**
 * Clase que me permite agrupar todos los servicios en una sola tabla
 * @author CARLOS_CODESOFT
 */
public abstract class ServiceFactory {

    public static UsuarioService getUsuarioService() {return new UsuarioService();}
    public static ProveedorService getProveedorService() {return new ProveedorService();}
    public static SubCategoriaService getSubCategoriaService() {return new SubCategoriaService();}
    public static CategoriaService getCategoriaService() {return new CategoriaService();}
    public static SolicitudBusquedaService getSolicitudBusquedaService() {return new SolicitudBusquedaService();}
    
        /**
     * Ip del servidor del cual se va a consumir los servicios
     */
    //public String ipServidor;
    public static ServiceFactory serviceController;
    
    /**
     * Obtiene la instancia del controlador actual
     * @return 
     */
    public static ServiceFactory getFactory()
    {
        return serviceController;
    }
    
    /**
        
    
    
    /**
     * Permite saber si existe una conexion creada anteriormente
     * @return 
     */
    public static boolean isActiveController()
    {
        return serviceController !=null;
    }

    
}

