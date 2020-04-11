/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.validator;

import ec.com.codesoft.virtualmall.util.UtilidadesJuridicas;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author CARLOS_CODESOFT
 */
@FacesValidator("cedulaValidador")
public class CedulaValidador implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        if (!(o instanceof String)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Validación","Debe ser un cadena"));
        }
        
        if(!UtilidadesJuridicas.validarIdentificaciones((String) o))
        {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Validación","Identificación Incorrecta"));
        }
        
        System.out.println("Cedula Validada ...");
        
    }
    
}
