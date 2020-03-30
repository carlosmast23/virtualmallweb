/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import static org.primefaces.behavior.confirm.ConfirmBehavior.PropertyKeys.message;

/**
 *
 * @author CARLOS_CODESOFT
 */
public abstract class UtilidadesMensajes {
    
    public static void mensaje(String mensaje,FacesMessage.Severity tipoMensaje)
    {
        
        FacesContext context = FacesContext.getCurrentInstance();
        
        String tituloMensaje="";
        if(FacesMessage.SEVERITY_INFO.equals(tipoMensaje))
        {
            tituloMensaje="Informativo";
        
        }else if(FacesMessage.SEVERITY_ERROR.equals(tipoMensaje))
        {
            tituloMensaje="Error";
        }else if(FacesMessage.SEVERITY_FATAL.equals(tipoMensaje))
        {
            tituloMensaje="Grave";
        }else if(FacesMessage.SEVERITY_WARN.equals(tipoMensaje))
        {
            tituloMensaje="Alerta";
        }
        
        context.addMessage(null, new FacesMessage(tipoMensaje,tituloMensaje,mensaje) );
        //context.addMessage(titulo, fm);
        //context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));
    }
    
}
