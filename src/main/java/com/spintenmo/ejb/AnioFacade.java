/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.Anio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author willian
 */
@Stateless
public class AnioFacade extends AbstractFacade<Anio> implements AnioFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenmo_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnioFacade() {
        super(Anio.class);
    }
    
}
