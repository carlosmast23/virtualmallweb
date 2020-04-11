/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.service;

import ec.com.codesoft.virtualmall.entity.Categoria;
import ec.com.codesoft.virtualmall.entity.Subcategoria;
import ec.com.codesoft.virtualmall.facade.SubCategoriaFacade;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author CARLOS_CODESOFT
 */
public class SubCategoriaService extends ServiceAbstract<Subcategoria,SubCategoriaFacade>{

    public SubCategoriaService() {
        super(SubCategoriaFacade.class);
    }
    
    public List<Subcategoria> obtenerSubcategoriasActivasPorCategoria(Categoria categoria)
    {

        Map<String,Object> mapParametros=new HashMap<String, Object>();
        mapParametros.put("categoria",categoria);
        mapParametros.put("estado","A"); //Todo: Remplazar por un enum
        
        return getFacade().findByMap(mapParametros);
    }
    
}
