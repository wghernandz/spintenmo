/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.Persona;
import com.spintenmo.modelo.empleadoMo;
import com.spintenmo.modelo.operacionesOrdent;
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
public class empleadoMoFacade extends AbstractFacade<empleadoMo> implements empleadoMoFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenmo_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public empleadoMoFacade() {
        super(empleadoMo.class);
    }
    
    //Obtener Operario segun Su DUI
    @Override
    public Persona personaSegunDui(String dui){
      Persona persona=null;
      
      //System.out.println("VALOR INICIAL "+ordentrabajo.getCodigo());
      String consulta;
      try{
        consulta="SELECT p FROM empleadoMo em, Persona p WHERE em.persona.id=p.id and p.pdui = ?1";
        Query query=em.createQuery(consulta);
        query.setParameter(1, dui);
      
        List<Persona> lista= query.getResultList();
      if (!lista.isEmpty()){
          persona=lista.get(0);
      }
      
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return persona;
    }
    
    //Obtener Operario segun Su DUI
    @Override
    public empleadoMo operarioSegunDui(String dui){
      empleadoMo empleadomo=null;
      
      //System.out.println("VALOR INICIAL "+ordentrabajo.getCodigo());
      String consulta;
      try{
        consulta="SELECT em FROM empleadoMo em, Persona p WHERE em.persona.id=p.id and p.pdui = ?1";
        Query query=em.createQuery(consulta);
        query.setParameter(1, dui);
      
        List<empleadoMo> lista= query.getResultList();
      if (!lista.isEmpty()){
          empleadomo=lista.get(0);
      }
      
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return empleadomo;
    }
  
     //Obtener Operario segun ID
    @Override
    public empleadoMo operarioSegunId(int id){
      empleadoMo empleadomo=null;
      
      //System.out.println("VALOR INICIAL "+ordentrabajo.getCodigo());
      String consulta;
      try{
        consulta="SELECT em FROM empleadoMo em WHERE em.persona.id = ?1";
        Query query=em.createQuery(consulta);
        query.setParameter(1, id);
      
        List<empleadoMo> lista= query.getResultList();
      if (!lista.isEmpty()){
          empleadomo=lista.get(0);
      }
      
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return empleadomo;
    }
    
    
}
