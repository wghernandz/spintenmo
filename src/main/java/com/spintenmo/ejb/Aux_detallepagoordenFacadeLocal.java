/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.Aux_detallepagoorden;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface Aux_detallepagoordenFacadeLocal {

    void create(Aux_detallepagoorden aux_detallepagoorden);

    void edit(Aux_detallepagoorden aux_detallepagoorden);

    void remove(Aux_detallepagoorden aux_detallepagoorden);

    Aux_detallepagoorden find(Object id);

    List<Aux_detallepagoorden> findAll();

    List<Aux_detallepagoorden> findRange(int[] range);

    int count();
    
}
