/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.Abonos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface AbonosFacadeLocal {

    void create(Abonos abonos);

    void edit(Abonos abonos);

    void remove(Abonos abonos);

    Abonos find(Object id);

    List<Abonos> findAll();

    List<Abonos> findRange(int[] range);

    int count();
    
}
