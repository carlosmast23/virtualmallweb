/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.core;

import ec.com.codesoft.virtualmall.util.UtilidadesWeb;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;



/**
 *
 * @author CARLOS_CODESOFT
 */

@ManagedBean
@ApplicationScoped
public class ConstantesMb implements Serializable{
    public static final String REGISTRO_PROVEEDOR_RUTA="registro_proveedor.xhtml";
    public static final String BUSQUEDA_RUTA="busqueda";
    public static final String RESPUESTA_RUTA="respuesta";
    public static final String INDEX_RUTA="index.xhtml";
    public static final String INDEX_NOSOTROS_RUTA="index.xhtml#section_que_es";
    public static final String INDEX_CONTACTANOS_RUTA="index.xhtml#section_contactanos"; 
     
    private String busquedaRuta=BUSQUEDA_RUTA;
    private String respuestaRuta=RESPUESTA_RUTA;
    private String indexRuta=INDEX_RUTA;
    private String indexNosotrosRuta=INDEX_NOSOTROS_RUTA;
    private String registroProveedorRuta=REGISTRO_PROVEEDOR_RUTA;
    private String indexContactanosRuta=INDEX_CONTACTANOS_RUTA;
    
    private String temaActual="flick";
    private String paginaInicial="busqueda.xhtml";

    public ConstantesMb() {
    }
    
    public String redirigir(String ruta)
    {
        Map<String,String> map=new HashMap<String,String>();
        String[] rutaTemporal=ruta.split("\\#");
        map.put("nombre","otro");
        String rutaFinal=UtilidadesWeb.agregarParametroGet(rutaTemporal[0], map,true);
        if(rutaTemporal.length>1)
        {
            rutaFinal+= "#"+rutaTemporal[1];
        }
        /*try {
            rutaFinal=URLEncoder.encode(rutaFinal, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ConstantesMb.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        //System.out.println("rutaFinal: "+rutaFinal);
        return rutaFinal;
    }
    

    public String getBusquedaRuta() {
        return busquedaRuta;
    }

    public void setBusquedaRuta(String busquedaRuta) {
        this.busquedaRuta = busquedaRuta;
    }

    public String getRespuestaRuta() {
        return respuestaRuta;
    }

    public void setRespuestaRuta(String respuestaRuta) {
        this.respuestaRuta = respuestaRuta;
    }

    public String getIndexRuta() {
        return indexRuta; 
    }

    public void setIndexRuta(String indexRuta) {
        this.indexRuta = indexRuta;
    }

    public String getRegistroProveedorRuta() {
        return registroProveedorRuta;
    }

    public void setRegistroProveedorRuta(String registroProveedorRuta) {
        this.registroProveedorRuta = registroProveedorRuta;
    }

    public String getIndexNosotrosRuta() { 
        return indexNosotrosRuta;
    }

    public void setIndexNosotrosRuta(String indexNosotrosRuta) {
        this.indexNosotrosRuta = indexNosotrosRuta;
    }

    public String getTemaActual() {
        return temaActual;
    }

    public void setTemaActual(String temaActual) {
        this.temaActual = temaActual;
    }

    public String getPaginaInicial() {
        return paginaInicial;
    }

    public void setPaginaInicial(String paginaInicial) {
        this.paginaInicial = paginaInicial;
    }

    public String getIndexContactanosRuta() {
        return indexContactanosRuta;
    }

    public void setIndexContactanosRuta(String indexContactanosRuta) {
        this.indexContactanosRuta = indexContactanosRuta;
    }

    
}
