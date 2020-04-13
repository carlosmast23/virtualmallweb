/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.service;

import ec.com.codesoft.virtualmall.core.MetodoInterfaceTransaccion;
import ec.com.codesoft.virtualmall.entity.SolicitudBusqueda;
import ec.com.codesoft.virtualmall.entity.SubcategoriaBusqueda;
import ec.com.codesoft.virtualmall.exception.ServicioCodefacException;
import ec.com.codesoft.virtualmall.facade.SolicitudBusquedaFacade;
import ec.com.codesoft.virtualmall.util.UtilidadesFechas;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author CARLOS_CODESOFT
 */
public class SolicitudBusquedaService extends ServiceAbstract<SolicitudBusqueda, SolicitudBusquedaFacade> {

    public SolicitudBusquedaService() {
        super(SolicitudBusquedaFacade.class);
    }

    @Override
    public SolicitudBusqueda grabar(final SolicitudBusqueda entity) throws ServicioCodefacException {
        ejecutarTransaccion(new MetodoInterfaceTransaccion() {
            @Override
            public void transaccion() throws ServicioCodefacException, RemoteException {
                entity.setEstadoEnum(SolicitudBusqueda.EstadoEnum.GENERADA);
                entity.setFechaCreacionRegistro(UtilidadesFechas.getFechaHoyUtil());
                entity.setFechaCreacionRegistro(UtilidadesFechas.getFechaHoyUtil());
                entityManager.persist(entity);
            }
        });
        return entity;
    }

    @Override
    public void editar(final SolicitudBusqueda entity) throws ServicioCodefacException {
        ejecutarTransaccion(new MetodoInterfaceTransaccion() {
            @Override
            public void transaccion() throws ServicioCodefacException, RemoteException {
                entity.setEstadoEnum(SolicitudBusqueda.EstadoEnum.VERIFICADA);
                for (SubcategoriaBusqueda subcategoriaBusqueda : entity.getSubcategoriaBusquedas()) {
                    if (subcategoriaBusqueda.getId() == null) {
                        entityManager.persist(subcategoriaBusqueda);
                    } else {
                        entityManager.merge(subcategoriaBusqueda);
                    }
                }

                entityManager.merge(entity);

            }
        });
    }

    public void categorizar(final SolicitudBusqueda entity) throws ServicioCodefacException {
        categorizarValidar(entity);
        ejecutarTransaccion(new MetodoInterfaceTransaccion() {
            @Override
            public void transaccion() throws ServicioCodefacException, RemoteException {
                entity.setEstadoEnum(SolicitudBusqueda.EstadoEnum.VERIFICADA);
                for (SubcategoriaBusqueda subcategoriaBusqueda : entity.getSubcategoriaBusquedas()) {
                    if (subcategoriaBusqueda.getId() == null) {
                        entityManager.persist(subcategoriaBusqueda);
                    } else {
                        entityManager.merge(subcategoriaBusqueda);
                    }
                }

                entityManager.merge(entity);

            }
        });
    }
    
    private void categorizarValidar(final SolicitudBusqueda entity) throws ServicioCodefacException 
    {
        if(entity.getSubcategoriaBusquedas()==null || entity.getSubcategoriaBusquedas().size()==0)
        {
            throw new ServicioCodefacException("No se puede grabar sin categorias");
        }
    }

    public List<SolicitudBusqueda> buscarPorEstado(SolicitudBusqueda.EstadoEnum estadoEnum) {
        Map<String, Object> mapPatremos = new HashMap<>();
        mapPatremos.put("estado", estadoEnum.getLetra());
        return getFacade().findByMap(mapPatremos);
    }

}
