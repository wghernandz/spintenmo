/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.colorVehiculo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface colorVehiculoFacadeLocal {

    void create(colorVehiculo colorVehiculo);

    void edit(colorVehiculo colorVehiculo);

    void remove(colorVehiculo colorVehiculo);

    colorVehiculo find(Object id);

    List<colorVehiculo> findAll();

    List<colorVehiculo> findRange(int[] range);

    int count();
    
}
