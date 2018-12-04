/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.Descuentos;
import com.spintenmo.modelo.anticipoMo;
import com.spintenmo.modelo.operacionesOrdent;
import com.spintenmo.modelo.ordenTrabajo;
import com.spintenmo.modelo.planillaMo;
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
public class planillaMoFacade extends AbstractFacade<planillaMo> implements planillaMoFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenmo_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public planillaMoFacade() {
        super(planillaMo.class);
    }
    //Llamar a funcion en BD para generar la planilla
    @Override
    public List<planillaMo> calculoPlanilla(String fechainicio,String fechafin){
      List<planillaMo> lista = null;
      Date fechai=null;
      Date fechaf=null;
      SimpleDateFormat formato= new SimpleDateFormat("dd-MM-yyyy");
        try {
            fechai=formato.parse(fechainicio);
            fechaf=formato.parse(fechafin);
        } catch (ParseException ex) {
            Logger.getLogger(planillaMoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
      String consulta;
      
      //EJECUTAR PROCEDIMIENTO ALMACENADO
      StoredProcedureQuery storedProcedure=em.createStoredProcedureQuery("calcularPlanilla");
      storedProcedure.registerStoredProcedureParameter("fechainicio", Date.class, ParameterMode.IN);
      storedProcedure.registerStoredProcedureParameter("fechafin", Date.class, ParameterMode.IN);
      storedProcedure.setParameter("fechainicio", fechai);
      storedProcedure.setParameter("fechafin",fechaf);
      storedProcedure.execute();
      //OBTENER DATOS DE PROCEDIMIENTO ALMACENADO
      try{
        consulta="SELECT p FROM planillaMo p WHERE p.finicio = ?1 and p.ffin = ?2 AND p.estado='Generada'";
        Query query=em.createQuery(consulta);
        query.setParameter(1, fechai);
        query.setParameter(2, fechaf);
        
        lista = query.getResultList();
    
      }catch (Exception e){
        System.out.println(e.getMessage());
      }
      return lista;
    }
    
    //Obtener planilla final segun estado puede ser Generada o Pagada;
    @Override
    public List<planillaMo> planillaSegunEstado(String estado){
      List<planillaMo> lista = null;
      String consulta;
      try{
        consulta="SELECT p FROM planillaMo p WHERE p.estado = ?1";
        Query query=em.createQuery(consulta);
        query.setParameter(1, estado);
      
        lista = query.getResultList();
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
      //Llamar a funcion en BD para Pagar Planilla y actualizar descuentos y anticipos de EMP.
    @Override
    public List<planillaMo> pagarPlanilla(String fechapago){
      List<planillaMo> lista = null;
      Date fechai=null;
      SimpleDateFormat formato= new SimpleDateFormat("dd-MM-yyyy");
        try {
            fechai=formato.parse(fechapago);
        } catch (ParseException ex) {
            Logger.getLogger(planillaMoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      String consulta;
      
      //EJECUTAR PROCEDIMIENTO ALMACENADO
      StoredProcedureQuery storedProcedure=em.createStoredProcedureQuery("pagarplanilla");
      storedProcedure.registerStoredProcedureParameter("fechapago", Date.class, ParameterMode.IN);
      storedProcedure.setParameter("fechapago", fechai);
      storedProcedure.execute();
      //OBTENER DATOS DE PROCEDIMIENTO ALMACENADO
      try{
        consulta="SELECT p FROM planillaMo p WHERE p.fpagado = ?1 ";
        Query query=em.createQuery(consulta);
        query.setParameter(1, fechai);

        lista = query.getResultList();
    
      }catch (Exception e){
        System.out.println(e.getMessage());
      }
      return lista;
    }
    
     //Obtener consolidado planilla segun estado
    @Override
    public List<List> consolidadoPlanilla(String estado){
      List lista = null;
      String consulta;
      try{
        consulta="SELECT p.fpagado,sum(p.montoapagar) FROM planillaMo p WHERE p.estado = ?1 GROUP BY p.fpagado";
        Query query=em.createQuery(consulta);
        query.setParameter(1, estado);
      
        lista = query.getResultList();
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
            //PARA IMPRIMIR PLANILLA
    @Override
    public List<planillaMo> imprimirPlanillagen(Date fechapago){
      List<planillaMo> lista = null;
      
   
      try{
        String consulta;
        consulta="SELECT pmo "
                + " FROM planillaMo pmo,empleadoMo em, Persona p "
                + " WHERE pmo.empleadomo.persona.id=em.persona.id and p.id=em.persona.id "
                + " and pmo.estado='Generada'";
        Query query=em.createQuery(consulta);
      
        lista = query.getResultList();
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    } 
    
    //PARA IMPRIMIR DESCUENTOS EN PLANILLA
    @Override
    public List<Descuentos> imprimirDescuentos(){
      List<Descuentos> lista = null;
     
      try{
        String consulta;
        consulta="SELECT d FROM Descuentos d, Persona p "
                + " WHERE d.empleadomo.persona.id=p.id and (d.montopend>'0.0' or d.deley=true)"
                + " AND (d.empleadomo.persona.id=p.id and (d.montopend>'0.0' or d.deley=true)) IS NOT NULL ";
                
        Query query=em.createQuery(consulta);
        lista = query.getResultList();
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
    //PARA IMPRIMIR DESCUENTOS EN PLANILLA PAGADA
    @Override
    public List<Descuentos> imprimirDescuentospagado(Date fechapago){
      List<Descuentos> lista = null;
     
      try{
        String consulta;
        consulta="SELECT a FROM Abonos a, Descuentos d, Persona p "
                + " WHERE d.empleadomo.persona.id=p.id AND d.id=a.descuentos.id and a.fechaabono = ?1";
                        
        Query query=em.createQuery(consulta);
        lista = query.getResultList();
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
      //ELIMINAR PLANILLA GENERADA
    @Override
    public void eliminarPsegunestado(String estado){ 
        List<planillaMo> lista=null;
      try{
        String consulta;
        consulta="DELETE FROM planillaMo p WHERE p.estado = ?1 ";
        Query query=em.createQuery(consulta);
        
        query.setParameter(1,estado);      
        query.executeUpdate();
        
      }catch (Exception e){
       System.out.println(e.getMessage());
      } 
    }
    
              //PARA IMPRIMIR PLANILLA PAGADA
    @Override
    public List<planillaMo> imprimirPlanillapag(Date fechapago){
      List<planillaMo> lista = null;
      
   
      try{
        String consulta;
        consulta="SELECT pmo "
                + " FROM planillaMo pmo,empleadoMo em, Persona p "
                + " WHERE pmo.empleadomo.persona.id=em.persona.id and p.id=em.persona.id "
                + " and pmo.estado='Pagada' and pmo.fpagado = ?1";
        Query query=em.createQuery(consulta);
        query.setParameter(1,fechapago);
        
        lista = query.getResultList();
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
    //Obtener anticipo pagados en planilla
    @Override
    public List<anticipoMo> anticipoPlanpag(Date fechapago){
      List<anticipoMo> lista = null;
      
      try{
        String consulta;
        consulta="SELECT an from anticipoMo an,empleadoMo em, operacionesOrdent o " +
                 "WHERE o.id=an.operacionesordent.id AND em.persona.id=o.empleadomo.persona.id" +
                 "AND an.fechaaplicacionplanilla = ?1";
        Query query=em.createQuery(consulta);
        query.setParameter(1,fechapago);
        
        lista = query.getResultList();
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
        //Obtener planilla segun estado y segun empleado.
    @Override
    public planillaMo planillaSegunEmpEstado(int idemp,String estado){
      planillaMo valor = null;
      String consulta;
      try{
        consulta="SELECT p FROM planillaMo p WHERE p.estado = ?1 AND p.empleadomo.persona.id = ?2";
        Query query=em.createQuery(consulta);
        query.setParameter(1, estado);
        query.setParameter(2, idemp);
      
        valor = (planillaMo)query.getSingleResult();
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return valor;
    } 
    
    
        //Obtener planillas segun fecha
    @Override
    public List<planillaMo> planillaFecha(Date finicio,Date ffin){
      List<planillaMo> list = null;
      String consulta;
      try{
        consulta="SELECT p FROM planillaMo p WHERE p.fpagado >= ?1 AND p.fpagado <= ?2";
        Query query=em.createQuery(consulta);
        query.setParameter(1, finicio);
        query.setParameter(2, ffin);
      
        list = query.getResultList();
    
      }catch (Exception e){
        System.out.println(e.getMessage());
      }
        return list;
    }   
}
