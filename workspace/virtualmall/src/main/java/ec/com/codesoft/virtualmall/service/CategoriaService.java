/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.service;

import ec.com.codesoft.virtualmall.entity.Categoria;
import ec.com.codesoft.virtualmall.facade.CategoriaFacade;
import java.io.Serializable;


/**
 *
 * @author CARLOS_CODESOFT
 */

public class CategoriaService extends ServiceAbstract<Categoria,CategoriaFacade>{

    public CategoriaService() {
        super(CategoriaFacade.class);
    }
    

}
