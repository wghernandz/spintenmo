/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.aseguradoraCliente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface aseguradoraClienteFacadeLocal {

    void create(aseguradoraCliente aseguradoraCliente);

    void edit(aseguradoraCliente aseguradoraCliente);

    void remove(aseguradoraCliente aseguradoraCliente);

    aseguradoraCliente find(Object id);

    List<aseguradoraCliente> findAll();

    List<aseguradoraCliente> findRange(int[] range);

    int count();
    
    aseguradoraCliente clienteSegunNombre(String nombrecliente);
}
