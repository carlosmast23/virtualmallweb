/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.core;

import ec.com.codesoft.virtualmall.service.ProveedorService;
import ec.com.codesoft.virtualmall.service.UsuarioService;

/**
 * Clase que me permite agrupar todos los servicios en una sola tabla
 * @author CARLOS_CODESOFT
 */
public abstract class ServiceFactory {

    public static UsuarioService getUsuarioService() {return new UsuarioService();}
    public static ProveedorService getProveedorService() {return new ProveedorService();}
    
}
