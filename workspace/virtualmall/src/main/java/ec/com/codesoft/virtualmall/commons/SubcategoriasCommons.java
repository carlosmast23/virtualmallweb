/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.commons;

import ec.com.codesoft.virtualmall.core.ServiceFactory;
import ec.com.codesoft.virtualmall.entity.Categoria;
import ec.com.codesoft.virtualmall.entity.Subcategoria;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

/**
 *
 * @author CARLOS_CODESOFT
 */
public abstract class SubcategoriasCommons {
    
        public static List<SelectItem> crearListaCategorias() {

        List<SelectItem> subcategoriasResultado=new ArrayList<SelectItem>();
        
        List<Categoria> categorias = ServiceFactory.getCategoriaService().obtenerTodos(); //Cambiar luego a obtener activos        
        for (Categoria categoria : categorias) {
            SelectItemGroup categoriaGroup = new SelectItemGroup(categoria.getNombre());
            
            List<Subcategoria> subcategorias=ServiceFactory.getSubCategoriaService().obtenerSubcategoriasActivasPorCategoria(categoria);
            
            List<SelectItem> items=new ArrayList<SelectItem>();
            for (Subcategoria subcategoria : subcategorias) 
            {
                items.add(new SelectItem(subcategoria.getId().toString(),subcategoria.getNombre()));
            }
            
            SelectItem[] arrayItems=new SelectItem[items.size()];
            categoriaGroup.setSelectItems(items.toArray(arrayItems));  
            subcategoriasResultado.add(categoriaGroup);
        }
        return subcategoriasResultado;

    }
}
