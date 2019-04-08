/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.aseguradoraCliente;
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
public class aseguradoraClienteFacade extends AbstractFacade<aseguradoraCliente> implements aseguradoraClienteFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenmo_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public aseguradoraClienteFacade() {
        super(aseguradoraCliente.class);
    }
    

    @Override
    public aseguradoraCliente clienteSegunNombre(String nombrecliente){
      aseguradoraCliente cli=null;
      
      String consulta;
      try{
        consulta="SELECT c FROM aseguradoraCliente c WHERE c.nombrecliente = ?1";
        Query query=em.createQuery(consulta);
        query.setParameter(1, nombrecliente);
      
        List<aseguradoraCliente> lista= query.getResultList();
      if (!lista.isEmpty()){
          cli=lista.get(0);
      }
      
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return cli;
    }
    
}
