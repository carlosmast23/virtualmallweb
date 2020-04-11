/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.util;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author CARLOS_CODESOFT
 */
@ManagedBean
@ApplicationScoped
public class ExpresionesRegularesMb {
    public static final String SOLO_NUMEROS="^[0][0-9]{9}$";
    public static final String TEXTO_SIN_NUMEROS = "^[A-Za-zÀ-ÿ\\u00f1\\u00d1\\s.\\_\\-\\,\\ ]*$";

    
    private String soloNumeros=SOLO_NUMEROS;
    
    private String textoSinNumerosMensaje="Ingrese caracteres sin números";
    private String textoSinNumeros=TEXTO_SIN_NUMEROS;

    public String getSoloNumeros() {
        return soloNumeros; 
    }

    public void setSoloNumeros(String soloNumeros) {
        this.soloNumeros = soloNumeros;
    }

    public String getTextoSinNumeros() {
        return textoSinNumeros;
    }

    public void setTextoSinNumeros(String textoSinNumeros) {
        this.textoSinNumeros = textoSinNumeros;
    }

    public String getTextoSinNumerosMensaje() {
        return textoSinNumerosMensaje;
    }

    public void setTextoSinNumerosMensaje(String textoSinNumerosMensaje) {
        this.textoSinNumerosMensaje = textoSinNumerosMensaje;
    }

    
    
    
    
    
}
