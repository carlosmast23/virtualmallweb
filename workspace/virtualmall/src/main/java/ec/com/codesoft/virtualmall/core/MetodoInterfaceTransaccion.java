/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.core;

import ec.com.codesoft.virtualmall.exception.ServicioCodefacException;
import java.rmi.RemoteException;

/**
 *
 * @author CARLOS_CODESOFT
 */
public interface MetodoInterfaceTransaccion {
    public void transaccion() throws ServicioCodefacException,RemoteException;
}
