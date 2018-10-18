/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.colorVehiculo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author willian
 */
@Stateless
public class colorVehiculoFacade extends AbstractFacade<colorVehiculo> implements colorVehiculoFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenmo_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public colorVehiculoFacade() {
        super(colorVehiculo.class);
    }
    
}
