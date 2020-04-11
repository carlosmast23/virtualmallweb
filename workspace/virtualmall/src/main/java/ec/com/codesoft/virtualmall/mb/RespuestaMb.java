/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.mb;

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
    
    public static final String PARAMETRO_TITULO="titulo";
    public static final String PARAMETRO_CONTENIDO="contenido";
    public static final String PARAMETRO_LINK="link";
    
    private String titulo;
    private String mensaje;
    private String linkRedirigir;
    
    @PostConstruct
    public void init()
    {
        linkRedirigir="index"; 
        linkRedirigir=UtilidadesWeb.buscarParametroPeticion(PARAMETRO_LINK);
        titulo=UtilidadesWeb.buscarParametroPeticion(PARAMETRO_TITULO);
        mensaje=UtilidadesWeb.buscarParametroPeticion(PARAMETRO_CONTENIDO);
    }

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
