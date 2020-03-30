/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.service;

import ec.com.codesoft.virtualmall.exception.ExcepcionDataBaseEnum;
import ec.com.codesoft.virtualmall.exception.ServicioCodefacException;
import ec.com.codesoft.virtualmall.facade.AbstractFacade;
import ec.com.codesoft.virtualmall.core.MetodoInterfaceConsulta;
import ec.com.codesoft.virtualmall.core.MetodoInterfaceTransaccion;
import ec.com.codesoft.virtualmall.util.UtilidadesExcepciones;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import org.eclipse.persistence.exceptions.DatabaseException;

/**
 *
 * @author CARLOS_CODESOFT
 */
public abstract class ServiceAbstract<Entity,Facade>  implements Serializable
{

    private static final Logger LOG = Logger.getLogger(ServiceAbstract.class.getName());
    
    
    protected AbstractFacade<Entity> facade;
    protected EntityManager entityManager;
    
    /**
     * TODO: Variable temporal solo para hacer aritificios para las clases internas
     */
    public Object tmp;

    public ServiceAbstract(){
        
        this.entityManager=AbstractFacade.entityManager;
    }
    
    /**
     * Obtiene un transaccion para trabar con el entity manager
     */
    public EntityTransaction getTransaccion()
    {
        return entityManager.getTransaction();
    }
 
    public ServiceAbstract(Class<Facade> clase) 
    {
        
        try {
            this.facade =(AbstractFacade<Entity>) clase.newInstance();
            this.entityManager=AbstractFacade.entityManager;
        } catch (InstantiationException ex) {
            Logger.getLogger(ServiceAbstract.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServiceAbstract.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //private T t;
    public List<Entity> obtenerTodos()
    {
        return this.facade.findAll();
    }
    
    public Entity grabar(final Entity entity) throws ServicioCodefacException
    {
        ejecutarTransaccion(new MetodoInterfaceTransaccion() {
            @Override
            public void transaccion() throws ServicioCodefacException, RemoteException {
                entityManager.persist(entity);
            }
        });
        return entity;
    }
   
    public Entity buscarPorId(Object primaryKey) 
    {
        return this.facade.find(primaryKey);
    }
    
    public void editar(final Entity entity) throws ServicioCodefacException
    {
        ejecutarTransaccion(new MetodoInterfaceTransaccion() {
            @Override
            public void transaccion() throws ServicioCodefacException, RemoteException {
                entityManager.merge(entity);
            }
        });
    }
    
    public void eliminar(Entity entity) throws ServicioCodefacException
    {
        this.facade.remove(entity);
    }
    
    public List<Entity> obtenerPorMap(Map<String,Object> parametros) throws ServicioCodefacException 
    {
        return this.facade.findByMap(parametros);
    }
    
    protected Object ejecutarConsulta(MetodoInterfaceConsulta interfaz) throws ServicioCodefacException
    {
        try {
            return interfaz.consulta();
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceAbstract.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new ServicioCodefacException("Error de conexión con el servidor");
        } catch (ServicioCodefacException ex) {
            ex.printStackTrace();
            throw ex;
        }catch (PersistenceException ex) { //Hacer un RoolBack cuando es un error relacionado con la persistencia
            ex.printStackTrace();
            
            ExcepcionDataBaseEnum excepcionEnum=UtilidadesExcepciones.analizarExcepcionDataBase(ex);
            //Logger.getLogger(PersonaService.class.getName()).log(Level.SEVERE, null, ex);
            if(excepcionEnum.equals(ExcepcionDataBaseEnum.CLAVE_DUPLICADO))
            {
                throw new ServicioCodefacException(ExcepcionDataBaseEnum.CLAVE_DUPLICADO.getMensaje());
            }
            else
            {
                String mensaje="sin mensaje";
                if(ex!=null)
                   mensaje=ex.getMessage();
                    
                throw new ServicioCodefacException(ExcepcionDataBaseEnum.DESCONOCIDO.getMensaje()+"\n Causa: "+ mensaje);
            }  
        }catch(Exception e)
        {
            e.printStackTrace();
            LOG.log(Level.SEVERE,e.getMessage()); //Todo: Mejorar esta parte porque deberia imprimir toda la pila de error y ademas deberia poder comunicar el error a la capa superior
            throw new ServicioCodefacException(e.getMessage());
        }
        
    }
    
    
    /**
     * Metodo Que me permite ejecutar un conjunto de procesos en el jpa como un proceso
     * @param interfaz
     * @throws PersistenceException 
     */
    protected void ejecutarTransaccion(MetodoInterfaceTransaccion interfaz) throws ServicioCodefacException
   {
        EntityTransaction transaccion = entityManager.getTransaction();
        try {            
            transaccion.begin();
            interfaz.transaccion();
            transaccion.commit();
            entityManager.clear();
        }catch (RemoteException ex) { //Hacer un RoolBack cuando no exista comunicacion con el servidor
            if (transaccion.isActive()) {
                transaccion.rollback();
            }
            ex.printStackTrace();
            throw new ServicioCodefacException("Error de conexión con el servidor");
        }catch (ServicioCodefacException ex) { //Hacer un RoolBack cuando sea un error personalizado
            if (transaccion.isActive()) {
                transaccion.rollback();
            }
            ex.printStackTrace();
            throw ex;
        }catch (PersistenceException ex) { //Hacer un RoolBack cuando es un error relacionado con la persistencia
            ex.printStackTrace();
            //verifica que la transaccion esta activa para hacer un rollback
            //Nota: Algunas veces el commit automaticamente hace un rollback es decir no es necesario hacer rollback y la sesion ya no esta activa
            if(transaccion.isActive())
            {
                transaccion.rollback();
            }
            
            ExcepcionDataBaseEnum excepcionEnum=UtilidadesExcepciones.analizarExcepcionDataBase(ex);
            //Logger.getLogger(PersonaService.class.getName()).log(Level.SEVERE, null, ex);
            if(excepcionEnum.equals(ExcepcionDataBaseEnum.CLAVE_DUPLICADO))
            {
                throw new ServicioCodefacException(ExcepcionDataBaseEnum.CLAVE_DUPLICADO.getMensaje());
            }
            else
            {
                String mensaje="sin mensaje";
                if(ex!=null)
                   mensaje=ex.getMessage();
                    
                throw new ServicioCodefacException(ExcepcionDataBaseEnum.DESCONOCIDO.getMensaje()+"\n Causa: "+ mensaje);
            }  
            
            
        }catch(NullPointerException e)
        {
            if (transaccion.isActive()) {
                transaccion.rollback();
            }
            throw new ServicioCodefacException("Problema con variable nula \n\n"+e.getMessage());
        }catch(Exception e) //Hacer un RollBack si se produce cualquier error
        {
            if (transaccion.isActive()) {
                transaccion.rollback();
            }
            e.printStackTrace();
            LOG.log(Level.SEVERE,e.getMessage()); //Todo: Mejorar esta parte porque deberia imprimir toda la pila de error y ademas deberia poder comunicar el error a la capa superior
            throw new ServicioCodefacException(e.getMessage());
            //throw e;
        }
    }

    protected Facade getFacade()
    {
        return (Facade) this.facade;
    }

    /**
     * Metodo que se encarga de desasoriar una entidad gestionada para poder hacer acciones
     * sobre el objecto pero que no se reflejen en la persistencia con la base de datoss
     */    
    public static void desasociarEntidadPersistencia(Object entidad)
    {
        AbstractFacade.detachEntity(entidad);
    }
    
    /**
     * Metodo recursivo que se encarga de desasoriar una entidad gestionada para poder
     * hacer acciones sobre el objecto pero que no se reflejen en la
     * persistencia con la base de datoss
     */   
    public static void desasociarEntidadRecursivo(Object entidad)
    {
        AbstractFacade.detachRecursive(entidad);
    }
    
    
}
