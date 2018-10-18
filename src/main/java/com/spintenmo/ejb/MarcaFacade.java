/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.Marca;
import com.spintenmo.modelo.Modelo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author willian
 */
@Stateless
public class MarcaFacade extends AbstractFacade<Marca> implements MarcaFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenmo_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MarcaFacade() {
        super(Marca.class);
    }
    
    @Override
    public List<Modelo> modelosXmarca(Marca marca){
        List<Modelo> modelos=null;
        String consulta;
        try{
            consulta= "SELECT m FROM Modelo m JOIN Marca mar "
                    + "WHERE m.marca.id=mar.id and mar.id= "+marca.getId();
            Query query=em.createQuery(consulta);
            //query.setParameter(1,depto.getId());
        
            modelos=query.getResultList();
              
            }
        catch(Exception e){
            throw e;
        }finally{
            //em.close();
        }
        return modelos;
    }
    
}
