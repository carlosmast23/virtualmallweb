/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.service;

import ec.com.codesoft.virtualmall.entity.Usuario;
import ec.com.codesoft.virtualmall.facade.UsuarioFacade;

/**
 *
 * @author CARLOS_CODESOFT
 */
public class UsuarioService extends ServiceAbstract<Usuario,UsuarioFacade>{

    public UsuarioService() {
        super(UsuarioFacade.class);
    }
    
    public Usuario Login(String usuario,String clave)
    {
        return null;
    }
}
