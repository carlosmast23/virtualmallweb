/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.core;

import ec.com.codesoft.virtualmall.exception.PersistenciaDuplicadaException;
import ec.com.codesoft.virtualmall.facade.AbstractFacade;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.PersistenceException;

/**
 *
 * @author Carlos
 */
@ManagedBean(eager = true)
@ApplicationScoped
public class ApplicationCodefacMb  implements Serializable{
    
    private String ipServidor;
    
    @PostConstruct
    public void init()
    {
        System.out.println("=========> Creando Bean de Session <==============");
        try {
            AbstractFacade.cargarEntityManager();
        } catch (PersistenceException ex) {
            Logger.getLogger(ApplicationCodefacMb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PersistenciaDuplicadaException ex) {
            Logger.getLogger(ApplicationCodefacMb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Metodo que permite verificar si existe comunicacion con el servidor o lo realiza
     * para restablecer o establecer la comunicacion con el servidor
     */
    public void controladorConexionServidor()
    {       
        /*if(ServiceFactory.isActiveController())
        {
            return;
        }
        
        InputStream stream =null;
        try {
            String propertyHome = System.getenv("CATALINA_HOME");
            String pathProperty=propertyHome+"/../codefac.ini";
            stream = new FileInputStream(pathProperty);
            //try {
            //InputStreamReader input = new InputStreamReader(FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/WEB-INF/ejemplo.prop"));
            Properties propiedades=new Properties();
            propiedades.load(stream);
            System.out.println("Ip servidor archivo init: "+propiedades.get("servicio_web_ip_servidor").toString());
            ipServidor=propiedades.get("servicio_web_ip_servidor").toString();
            ServiceFactory.newController(ipServidor);   
            System.out.println("=====> CONEXION CON EL SERVIDOR EXITOSA ========>");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ApplicationCodefacMb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ApplicationCodefacMb.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stream.close();
            } catch (IOException ex) {
                Logger.getLogger(ApplicationCodefacMb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
    }
}
