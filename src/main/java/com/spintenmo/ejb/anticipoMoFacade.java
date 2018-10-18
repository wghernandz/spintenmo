/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.anticipoMo;
import java.math.BigDecimal;
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
public class anticipoMoFacade extends AbstractFacade<anticipoMo> implements anticipoMoFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenmo_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public anticipoMoFacade() {
        super(anticipoMo.class);
    }
    
    //obtener sumatoria de anticipos dados a una orden.
    @Override
    public BigDecimal sumAnticipoOrden(int idoperacionesordent){
      BigDecimal suma = null;
      String consulta;
      try{
        consulta="SELECT SUM(an.montoanticipo) FROM anticipoMo an WHERE an.operacionesordent.id = ?1 ";
        Query query=em.createQuery(consulta);
        query.setParameter(1, idoperacionesordent);
      
        suma = (BigDecimal)query.getSingleResult();
    
       }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return suma;
    }
    
    //Conocer si una operacionorden tiene anticipos aplicados en planilla.  
    @Override
    public List<anticipoMo> anticipoPorOrden(int idoperacionot){
      List<anticipoMo> lista = null;
      //select * from anticipomo an,operacionesordent op where op.id=an.idoperacionesot and an.idoperacionesot=943 and fechaaplicacionplanilla is not null limit 1
     
      try{
        String consulta;
        consulta="SELECT an FROM anticipoMo an, operacionesordent op WHERE op.id=an.operacionesordent.id AND "
                + "an.operacionesordent.id = ?1 AND an.fechaaplicacion!=NULL";
        Query query=em.createQuery(consulta);
        
        lista = query.getResultList();
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
}
