/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.Persona;
import com.spintenmo.modelo.ordenTrabajo;
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
public class PersonaFacade extends AbstractFacade<Persona> implements PersonaFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenmo_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }
        
    //Obtener lista de personas segun cargo
    @Override
    public List<Persona> personaCargo(String cargo){
      List<Persona> lista = null;
      String consulta;
      try{
        consulta="SELECT p FROM Persona p,empleadoMo em WHERE p.id=em.persona.id and em.cargo= ?1";
        Query query=em.createQuery(consulta);
        query.setParameter(1, cargo);
      
        lista = query.getResultList();
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
}
