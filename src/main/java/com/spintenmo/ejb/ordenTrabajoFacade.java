/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.operacionesOrdent;
import com.spintenmo.modelo.ordenTrabajo;
import static java.lang.String.format;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author willian
 */
@Stateless
public class ordenTrabajoFacade extends AbstractFacade<ordenTrabajo> implements ordenTrabajoFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenmo_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ordenTrabajoFacade() {
        super(ordenTrabajo.class);
    }
    
    //Obtener ordenes de trabajo ingresadas con estado costo no asignado
    @Override
    public List<ordenTrabajo> otIngresadas(){
      List<ordenTrabajo> lista = null;
      String consulta;
      try{
        consulta="SELECT ot FROM ordenTrabajo ot WHERE ot.estado = ?1";
        Query query=em.createQuery(consulta);
        query.setParameter(1, "Costo no asignado");
      
        lista = query.getResultList();
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
     //Obtener orden de trabajo estado costo no asignado
    @Override
    public ordenTrabajo obtenerOt(String codigo){
      List<ordenTrabajo> lista = null;
      ordenTrabajo orden=null;
      String consulta;
      try{
        consulta="SELECT ot FROM ordenTrabajo ot WHERE ot.codigo= ?1";
        Query query=em.createQuery(consulta);
        query.setParameter(1, codigo);
      
        lista = query.getResultList();
        
          if (!lista.isEmpty()){
          orden=lista.get(0);
        
      }
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return orden;
    }
    
    //Obtener OT segun codigo y estado;
    @Override
    public ordenTrabajo obtenerOt(String codigo,String estado){
      List<ordenTrabajo> lista = null;
      ordenTrabajo orden=null;
      String consulta;
      try{
        consulta="SELECT ot FROM ordenTrabajo ot WHERE ot.codigo= ?1 and ot.estado = ?2";
        Query query=em.createQuery(consulta);
        query.setParameter(1, codigo);
        query.setParameter(2,estado);
      
        lista = query.getResultList();
        
          if (!lista.isEmpty()){
            orden=lista.get(0);  
      }
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return orden;
    }
    
       //Obtener OT segun codigo y estado;
    @Override
    public List<ordenTrabajo> ultimasIngOt(String estado){
      List<ordenTrabajo> lista = null;
      ordenTrabajo orden=null;
      String consulta;
      try{
        consulta="SELECT ot FROM ordenTrabajo ot WHERE ot.estado = ?1 ORDER BY ot.id DESC ";
        Query query=em.createQuery(consulta);
        query.setParameter(1, estado);
      
        lista = query.setMaxResults(10).getResultList();
        
          if (!lista.isEmpty()){
            orden=lista.get(0);  
      }
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }  
    
     //Obtener OT por fecha ingreso
    @Override
    public List<ordenTrabajo> otXfecha(Date fechainicial,Date fechafinal){
        SimpleDateFormat parseFormat = new SimpleDateFormat("dd-MM-yyyy");
     
      List<ordenTrabajo> lista = null;
      ordenTrabajo orden=null;
      String consulta;
      try{
        consulta="SELECT ot FROM ordenTrabajo ot WHERE ot.fechaingreso>= ?1 AND ot.fechaingreso<= ?2 ORDER BY ot.fechaingreso DESC";

        Query query=em.createQuery(consulta);
        query.setParameter(1,fechainicial,TemporalType.DATE);
        query.setParameter(2,fechafinal,TemporalType.DATE);
      
        lista = query.getResultList();
        
          if (!lista.isEmpty()){
            orden=lista.get(0);
          
      }
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
    //Obtener OT segun codigo y estado;
    @Override
    public void actualizarEstado(int idot){
      String consulta;
      try{
        consulta="UPDATE ordenTrabajo ot SET ot.estado='Costo no asignado' WHERE ot.id = ?1";
        Query query=em.createQuery(consulta);
        query.setParameter(1, idot);
      
        query.executeUpdate();
      
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      } 
    }
    
    //Obtener OT segun codigo y ultima fecha de autorizado
    @Override
    public ordenTrabajo otPlacafechautoriz(String placa){
        ordenTrabajo ot = new ordenTrabajo();
        List<ordenTrabajo> lista = null;
        String consulta;
        
        try{
            consulta="select ot from ordenTrabajo ot " +
                        "where ot.placa = ?1 and " +
                        "ot.fechaautorizado=(select max(o.fechaautorizado) " +
                        "from ordenTrabajo o where o.placa = ?1) order by ot.id desc";
            Query query=em.createQuery(consulta);
            query.setParameter(1,placa);
            lista=query.getResultList();
            
            if(!lista.isEmpty()){
                ot=lista.get(0);
            }else{
                ot=null;
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return ot;
    }
    
          //Obtener OT segun placa
    @Override
    public List<ordenTrabajo> otSegunplaca(String placa){
      List<ordenTrabajo> lista = null;
      ordenTrabajo orden=null;
      String consulta;
      try{
        consulta="SELECT ot FROM ordenTrabajo ot WHERE ot.placa LIKE ?1 ORDER BY ot.id DESC ";
        Query query=em.createQuery(consulta);
        query.setParameter(1, "%"+placa+"%");
      
        lista = query.setMaxResults(10).getResultList();
        
          if (!lista.isEmpty()){
            orden=lista.get(0);  
      }
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }  
    
}
