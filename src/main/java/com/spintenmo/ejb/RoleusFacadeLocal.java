/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.Roleus;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface RoleusFacadeLocal {

    void create(Roleus roleus);

    void edit(Roleus roleus);

    void remove(Roleus roleus);

    Roleus find(Object id);

    List<Roleus> findAll();

    List<Roleus> findRange(int[] range);

    int count();
    
}
