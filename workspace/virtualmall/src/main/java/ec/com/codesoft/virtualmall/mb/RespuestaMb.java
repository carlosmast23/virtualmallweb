/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.mb;

import com.sun.faces.context.flash.ELFlash;
import ec.com.codesoft.virtualmall.util.UtilidadesWeb;
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
public class RespuestaMb extends AbstractMb implements Serializable{
    
    public static final String PARAMETRO_MENSAJE="MENSAJE";
    
    private Mensaje mensaje;
    
    
    @PostConstruct
    public void init()
    {
        mensaje=(Mensaje) ELFlash.getFlash().get(PARAMETRO_MENSAJE);
        if(mensaje.titulo==null)
        {
            mensaje.titulo="index";
        }
        //linkRedirigir="index"; 
        //linkRedirigir=UtilidadesWeb.buscarParametroPeticion(PARAMETRO_LINK);
        //titulo=UtilidadesWeb.buscarParametroPeticion(PARAMETRO_TITULO);
        //mensaje=UtilidadesWeb.buscarParametroPeticion(PARAMETRO_CONTENIDO);
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    
    
    public static class Mensaje
    {
        public String titulo;
        public String mensaje;
        public String linkRedirigir;    

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getLinkRedirigir() {
            return linkRedirigir;
        }

        public void setLinkRedirigir(String linkRedirigir) {
            this.linkRedirigir = linkRedirigir;
        }
        
        
    }
    
    
}
