/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.facade;

import ec.com.codesoft.virtualmall.entity.Usuario;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author CARLOS_CODESOFT
 */
public class UsuarioFacade extends AbstractFacade<Usuario>{

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario LoginFacade(String usuario,String clave)
    {
        /*Usuario u;
        u.getNick();
        String queryString = "SELECT u FROM USUARIO u WHERE u.nick=?1 and u.clave";
            Query query = getEntityManager().createQuery(queryString);
            query.setParameter(1,usuario);
            return (List<Perfil>) query.getResultList();
        }*/
        return null;
    }
    
}
