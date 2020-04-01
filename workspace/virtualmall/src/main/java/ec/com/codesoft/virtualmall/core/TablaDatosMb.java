/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.core;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author CARLOS_CODESOFT
 */
@ManagedBean
@SessionScoped
public class TablaDatosMb implements Serializable{
    
    private List<Object> datosConsultados;
    
    private Object datoSeleccionado;

    public TablaDatosMb() 
    {
    
    }

    public List<Object> getDatosConsultados() {
        return datosConsultados;
    }

    public void setDatosConsultados(List<Object> datosConsultados) {
        this.datosConsultados = datosConsultados;
    }
    
    static public class ColumnModel implements Serializable {
 
        private String header;
        private String property;
        
 
        public ColumnModel(String header, String property) {
            this.header = header;
            this.property = property;
        }
 
        public String getHeader() {
            return header;
        }
 
        public String getProperty() {
            return property;
        }
    }
}
