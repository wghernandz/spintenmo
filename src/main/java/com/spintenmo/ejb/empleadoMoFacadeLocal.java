/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.Persona;
import com.spintenmo.modelo.empleadoMo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface empleadoMoFacadeLocal {

    void create(empleadoMo empleadoMo);

    void edit(empleadoMo empleadoMo);

    void remove(empleadoMo empleadoMo);

    empleadoMo find(Object id);

    List<empleadoMo> findAll();

    List<empleadoMo> findRange(int[] range);

    int count();
    
    Persona personaSegunDui(String dui);
    
    empleadoMo operarioSegunDui(String dui);
    
    empleadoMo operarioSegunId(int id);
    
}
