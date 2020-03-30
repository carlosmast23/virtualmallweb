/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.mb;

import ec.com.codesoft.virtualmall.core.DatosSession;
import ec.com.codesoft.virtualmall.core.ServiceFactory;
import ec.com.codesoft.virtualmall.entity.Usuario;
import ec.com.codesoft.virtualmall.facade.UsuarioFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author CARLOS_CODESOFT
 */
@ManagedBean
@ViewScoped
public class LoginMb implements Serializable{
    private String nick;
    private String clave;
    
    /**
     * IU
     */
    private DatosSession sesion;
        
    @PostConstruct
    public void init()
    {
        
    }
    
    public void ingresar()
    {
        //ServiceFactory.getUsuarioService()
        System.out.println("Ingresando Login");
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    
    
}
