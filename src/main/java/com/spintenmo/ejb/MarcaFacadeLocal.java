/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.Marca;
import com.spintenmo.modelo.Modelo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface MarcaFacadeLocal {

    void create(Marca marca);

    void edit(Marca marca);

    void remove(Marca marca);

    Marca find(Object id);

    List<Marca> findAll();

    List<Marca> findRange(int[] range);

    int count();
    
    List<Modelo> modelosXmarca(Marca marca);
    
}
