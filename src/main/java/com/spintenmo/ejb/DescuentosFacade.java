/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.Descuentos;
import com.spintenmo.modelo.operacionesOrdent;
import com.spintenmo.modelo.ordenTrabajo;
import com.spintenmo.modelo.planillaMo;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author willian
 */
@Stateless
public class DescuentosFacade extends AbstractFacade<Descuentos> implements DescuentosFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenmo_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DescuentosFacade() {
        super(Descuentos.class);
    }
    
         //obtener descuentos a aplicar
    @Override
    public List<Descuentos> descuentosaAplicar(){
      List<Descuentos> lista=null;
      //System.out.println("VALOR INICIAL "+ordentrabajo.getCodigo());
      String consulta;
      try{
        consulta="SELECT d FROM Descuentos d WHERE d.montopend>'0.0' or d.deley=true";
        Query query=em.createQuery(consulta);
 
        lista= query.getResultList();
      
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
     //Obtener objeto planillaMo de acuerdo al IDPERSONA
    @Override
    public planillaMo planillaPoroperario(int idpersona){
      planillaMo planillaoperario = null;
      List<planillaMo> lista=null;
      String consulta;
      try{
        consulta="SELECT p FROM planillaMo p WHERE p.empleadomo.persona.id = ?1";
        Query query=em.createQuery(consulta);
        query.setParameter(1, idpersona);
      
        lista = query.getResultList();
        
          if (!lista.isEmpty()){
          planillaoperario=lista.get(0);
        
      }
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return planillaoperario;
    }
    
        //Obtener SUMATORIA de DESCUENTOS para un operario especifico
    @Override
    public BigDecimal sumDescuentooperario(int idempleadomo){
      BigDecimal sumdescuentos = null;
      String consulta;
      try{
        consulta="SELECT sum(d.descquincenal) FROM Descuentos d WHERE d.empleadomo.persona.id = ?1 and (d.montopend>0 or d.deley=true)";
        Query query=em.createQuery(consulta);
        query.setParameter(1,idempleadomo);
      
        sumdescuentos = (BigDecimal)query.getSingleResult();
        
         // if (!lista.isEmpty()){
         // sumdescuentos=lista.get(0);
      //}
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return sumdescuentos;
    }
    
     //Llamar a funcion en BD para Pagar Planilla y actualizar descuentos y anticipos de EMP.
    @Override
    public List<Descuentos> descuentosUpdate(){
      List<Descuentos> lista = null;
      String consulta;
      
      //EJECUTAR PROCEDIMIENTO ALMACENADO
      StoredProcedureQuery storedProcedure=em.createStoredProcedureQuery("actualizardescuento");
      storedProcedure.execute();
      //OBTENER DATOS DE PROCEDIMIENTO ALMACENADO
      try{
        consulta="SELECT d FROM Descuentos d WHERE d.montopend>'0.0' or d.deley=true ";
        Query query=em.createQuery(consulta);

        lista = query.getResultList();
    
      }catch (Exception e){
        System.out.println(e.getMessage());
      }
      return lista;
    }
    
    //conocer si la tabla descuentos ya fue verificada
    @Override
    public Descuentos descVerificado(){
      Descuentos descuento = null;
      List<Descuentos> lista=null;
      String consulta;
      try{
        consulta="SELECT d FROM Descuentos d WHERE d.verificada='SI' ";
        Query query=em.createQuery(consulta);
      
        lista = query.getResultList();
        
          if (!lista.isEmpty()){
          descuento=lista.get(0); 
      }
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return descuento;
    }
    
   //Despues de pagar planilla actualizar descuentos a verificada igual NO
    @Override
    public void despuespagodesc(){

      String consulta;
      

      try{
        consulta="UPDATE Descuentos d SET d.verificada='NO' ";
        Query query=em.createQuery(consulta);

        query.executeUpdate();
    
      }catch (Exception e){
        System.out.println(e.getMessage());
      }
      
    }
    
}
