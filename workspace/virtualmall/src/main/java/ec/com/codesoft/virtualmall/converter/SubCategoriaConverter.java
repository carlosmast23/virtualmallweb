/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.converter;

import ec.com.codesoft.virtualmall.service.SubCategoriaService;
import java.io.Serializable;
import java.rmi.RemoteException;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("subCategoriaConverter")
public class SubCategoriaConverter  extends AbstractConverter implements Converter {

    @Override
    public Object buscarObjetoPorId(String valor) throws RemoteException {
        //System.out.println("casting : "+valor);
        SubCategoriaService servicio=new SubCategoriaService();
        return servicio.buscarPorId(Long.parseLong(valor));
    }

}
