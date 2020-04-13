/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.converter.selectItems;

import ec.com.codesoft.virtualmall.entity.Subcategoria;
import java.io.Serializable;
import javax.faces.model.SelectItem;

public class SubCategoriaSelectItems extends SelectItem implements Serializable {

    public SubCategoriaSelectItems(Subcategoria value, String label) {
        super(value, label);
    }
    
    
    public Subcategoria getValor()
    {
        return  (Subcategoria) getValue();
    }
}
