/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.Aux_detallepagoorden;
import com.spintenmo.modelo.Usuario;
import com.spintenmo.modelo.anticipoMo;
import com.spintenmo.modelo.empleadoMo;
import com.spintenmo.modelo.operacionesOrdent;
import com.spintenmo.modelo.ordenTrabajo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TemporalType;
/**
 *
 * @author willian
 */
@Stateless
public class operacionesOrdentFacade extends AbstractFacade<operacionesOrdent> implements operacionesOrdentFacadeLocal {

    @PersistenceContext(unitName = "com.servipinten_spintenmo_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public operacionesOrdentFacade() {
        super(operacionesOrdent.class);
    }
    
     //obtener valores min y max ENDEREZADO
    @Override
    public operacionesOrdent valoresMinMax(ordenTrabajo ordentrabajo){
      operacionesOrdent opordent=null;
      
      //System.out.println("VALOR INICIAL "+ordentrabajo.getCodigo());
      String consulta;
      try{
        consulta="SELECT op FROM operacionesOrdent op WHERE op.ordentrabajo.id = ?1 and op.tipooperaciones = ?2";
        Query query=em.createQuery(consulta);
        query.setParameter(1, ordentrabajo.getId());
        query.setParameter(2, "enderezado");
      
        List<operacionesOrdent> lista= query.getResultList();
      if (!lista.isEmpty()){
          opordent=lista.get(0);
      }
      
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return opordent;
    }
    
       //obtener valores min y max PINTURA
    @Override
    public operacionesOrdent valoresMinMaxp(ordenTrabajo ordentrabajo){
      operacionesOrdent opordent=null;
      
      //System.out.println("VALOR INICIAL "+ordentrabajo.getCodigo());
      String consulta;
      try{
        consulta="SELECT op FROM operacionesOrdent op WHERE op.ordentrabajo.id = ?1 and op.tipooperaciones = ?2";
        Query query=em.createQuery(consulta);
        query.setParameter(1, ordentrabajo.getId());
        query.setParameter(2, "pintura");
      
        List<operacionesOrdent> lista= query.getResultList();
      if (!lista.isEmpty()){
          opordent=lista.get(0);
      }
      
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return opordent;
    }
    
      //obtener ordenes segun estado COSTO ASIGNADO
    @Override
    public List<operacionesOrdent> otPorasignar(operacionesOrdent ot){
      List<operacionesOrdent> lista = null;
      String consulta;
      try{
        consulta="SELECT op FROM operacionesOrdent op WHERE op.estado = ?1";
        Query query=em.createQuery(consulta);
        query.setParameter(1, "Costo asignado");
      
        lista = query.getResultList();
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
    //obtener ordenes segun estado OPERARIO NO ASIGNADO
    @Override
    public List<operacionesOrdent> otAsignadas(){
      List<operacionesOrdent> lista = null;
      String consulta;
      try{
        consulta="SELECT op FROM operacionesOrdent op WHERE op.estado = ?1 or op.estado = ?2";
        Query query=em.createQuery(consulta);
        query.setParameter(1, "Operario Asignado");
        query.setParameter(2, "Complemento");
      
        lista = query.getResultList();
        System.out.println("AQUI"+lista.get(0).getMontomo().toString());
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    //obtener empleados de acuerdo al tipo de operacion a realizar   
    @Override
    public List<empleadoMo> operarioSegunop(operacionesOrdent ot){
      List<empleadoMo> lista = null;
      String operacion=ot.getTipooperaciones();
     
      try{
        String consulta;
        consulta="SELECT em FROM empleadoMo em WHERE em.cargo = ?1";
        Query query=em.createQuery(consulta);
        
        if ("pintura".equals(operacion)){
            query.setParameter(1, "Pintor de vehiculos");
        }else{
            query.setParameter(1, "Enderezador de vehiculos");
        } 
        lista = query.getResultList();
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
      //determinar si ya se asignaron costo a los dos tipos de operaciones para una orden   
    @Override
    public int ordenValorizada(operacionesOrdent ot){
      int registros=0;
      String operacion=ot.getTipooperaciones();
     
      try{
        String consulta;
        consulta="SELECT COUNT(o.id) FROM operacionesOrdent o WHERE o.id = ?1";
        Query query=em.createQuery(consulta);
       
        registros =(int)query.getSingleResult();
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return registros;
    }
  
    //PARA RECIBO
    @Override
    public List<operacionesOrdent> anexopagoOrden(operacionesOrdent ot){
      List<operacionesOrdent> lista = null;
      
      String operacion=ot.getTipooperaciones();
      try{
        String consulta;
        consulta="SELECT o FROM operacionesOrdent o,ordenTrabajo myorden,empleadoMo em, Persona p, "
                + " Marca ma, aseguradoraCliente cli "
                + " WHERE myorden.id=o.ordentrabajo.id and o.empleadomo.persona.id=p.id "
                + " and myorden.modelot.marca.id=ma.id and myorden.aseguradoracliente.id=cli.id and o.id = ?1";
        Query query=em.createQuery(consulta);
        
        query.setParameter(1,ot.getId());
      
        lista = query.getResultList();
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    } 
    
       //obtener ordenes segun estado FINALIZADO 
    @Override
    public List<operacionesOrdent> otPreplanilla(){
      List<operacionesOrdent> lista = null;
      String consulta;
      try{
        consulta="SELECT op FROM operacionesOrdent op WHERE op.estado = ?1 ORDER BY op.empleadomo.persona.id ";
        Query query=em.createQuery(consulta);
        query.setParameter(1, "Finalizado");
      
        lista = query.getResultList();
        System.out.println("AQUI"+lista.get(0));
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
    //obtener ordenes segun estado ANTICIPO
    @Override
    public List<anticipoMo> anticipoPreplanilla(){
      List<anticipoMo> lista = null;
      String consulta;
      try{
        consulta="SELECT an FROM anticipoMo an ,operacionesOrdent op WHERE an.operacionesordent.id=op.id and op.estado = ?1 and an.fechaaplicacionplanilla=null";
        Query query=em.createQuery(consulta);
        query.setParameter(1, "Anticipo");
      
        lista = query.getResultList();
        System.out.println("AQUI"+lista.get(0));
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
     //Saber si un empleado tiene ordenes asociadas
    @Override
    public long ordenesAsociadasEmp(int idempleadomo){
      long x=0;
      String consulta;
      
     
      //try{ 
        consulta="SELECT COUNT(o.id) FROM operacionesOrdent o, empleadoMo em WHERE o.empleadomo.persona.id=em.persona.id AND em.persona.id = ?1";
        Query query=em.createQuery(consulta);
        query.setParameter(1,idempleadomo);
        x = (long) query.getSingleResult();
        
      //}catch (Exception e){
       //System.out.println(e.getMessage());
      //}
      return x;
    }
    
     //Obtener OT por fecha ingreso
    @Override
    public List<operacionesOrdent> otXfecha(Date fechainicial,Date fechafinal){
     
      List<operacionesOrdent> lista = null;
      operacionesOrdent operacionesordent=null;
      String consulta;
      try{
        consulta="SELECT op FROM operacionesOrdent op WHERE op.fechavalorizado>= ?1 AND op.fechavalorizado<= ?2 ORDER BY op.fechavalorizado DESC";

        Query query=em.createQuery(consulta);
        query.setParameter(1,fechainicial,TemporalType.DATE);
        query.setParameter(2,fechafinal,TemporalType.DATE);
      
        lista = query.getResultList();
        
          if (!lista.isEmpty()){
            operacionesordent=lista.get(0);   
      }
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
     //operacionesordent segun estado y modelo
    @Override
    public List<operacionesOrdent> optSegunModelo(int idmodelo, String estado){
     
      List<operacionesOrdent> lista = null;
      operacionesOrdent operacionesordent=null;
      String consulta;
      try{
        consulta="SELECT op FROM operacionesOrdent op, ordenTrabajo ot, Modelo m WHERE op.ordentrabajo.id=ot.id AND ot.modelot.Id=m.Id AND op.estado = ?2 AND m.Id = ?1";

        Query query=em.createQuery(consulta);
        query.setParameter(1,idmodelo);
        query.setParameter(2,estado);
      
        lista = query.getResultList();
        
          if (!lista.isEmpty()){
            operacionesordent=lista.get(0);   
      }
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
          //obtener ordenes segun cualquier estado, no importando la fecha mostrar agrupado por empleado y sumarizado
    @Override
    public List<operacionesOrdent> otEstado(String estado){
      List<operacionesOrdent> lista = null;
      String consulta;
      String consulta2;
      try{
        System.out.println("RESULTADO DE COMPARAR"+estado.equals("Preplanilla"));
        
        if(estado.equals("Preplanilla")==true){
           System.out.println("PREPLANILLA");
           consulta="SELECT op FROM operacionesOrdent op WHERE op.estado = 'Finalizado' OR op.estado='Anticipo' ORDER BY op.empleadomo.persona.id,op.fechaasignado DESC";
           Query query=em.createQuery(consulta);
           lista = query.getResultList();
           
        }else {
           System.out.println("NO PREPLANILLA");
           consulta2="SELECT op FROM operacionesOrdent op WHERE op.estado = ?1 ORDER BY op.empleadomo.persona.id,op.fechaasignado DESC";
           Query query2=em.createQuery(consulta2);
           query2.setParameter(1,estado);
           lista = query2.getResultList();
        }
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
    @Override
    public void finalizarTodo(){
         String consulta;
      try{
        consulta="UPDATE operacionesOrdent op SET op.fechaterminado = ?1,op.montoplanilla=op.montopendiente,op.estado = 'Finalizado' WHERE op.estado = 'Operario Asignado' OR op.estado='Complemento'";
        Query query=em.createQuery(consulta);
        query.setParameter(1,new Date(),TemporalType.DATE);
        query.executeUpdate();
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      } 
    }
    
    //obtener lista de ordenes pagadas o anticipos de acuerdo a una placa dada.
    @Override
    public List<operacionesOrdent> otpagadaXplaca(String placa,String tipoop,String estado){
     
      if(tipoop==null){
          tipoop="";
      }
      if (estado==null){
          estado="";
      }
      List<operacionesOrdent> lista = null;
      operacionesOrdent operacionesordent=null;
      String consulta;
      try{
        consulta="SELECT op FROM operacionesOrdent op WHERE op.ordentrabajo.placa LIKE ?1 AND (op.estado = ?3 AND op.tipooperaciones = ?2) ORDER BY op.fechapago DESC";
        Query query=em.createQuery(consulta);
        query.setParameter(1,"%"+placa+"%");
        query.setParameter(2,tipoop);
        query.setParameter(3,estado);
      
        lista = query.getResultList();
        
          if (!lista.isEmpty()){
            operacionesordent=lista.get(0);   
        }
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
    //obtener ordenes segun cualquier estado, no importando la fecha mostrar agrupado por empleado y sumarizado ademas por tipo de operacion
    @Override
    public List<operacionesOrdent> otEstadoOperacion(String estado,String operacion){
      List<operacionesOrdent> lista = null;
      String consulta;
      String consulta2;
      String parametro;
      try{
      if ("todas".equals(operacion)){
          parametro=" ";
      }else{
          parametro=" AND op.tipooperaciones = ?1"; 
      }
      
        if(estado.equals("Preplanilla")==true){
           System.out.println("PREPLANILLA");
           consulta="SELECT op FROM operacionesOrdent op WHERE (op.estado = 'Finalizado' OR op.estado='Anticipo')" +parametro+ " ORDER BY op.empleadomo.persona.id,op.fechaasignado DESC";
           Query query=em.createQuery(consulta);
           if(!"todas".equals(operacion)){
            query.setParameter(1, operacion);
           }
           lista = query.getResultList();
           
        }else{
           System.out.println("NO PREPLANILLA");
           consulta2="SELECT op FROM operacionesOrdent op WHERE op.estado = ?2 "+parametro+" ORDER BY op.empleadomo.persona.id,op.fechaasignado DESC";
           Query query2=em.createQuery(consulta2);
           if(!"todas".equals(operacion)){
                query2.setParameter(1,operacion);
           }
           query2.setParameter(2,estado);
           
           lista = query2.getResultList();
        }
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
    
     //obtener operaciones segun ESTADO -Costo Asignado,Operario Asignado,Complemento,Anticipo,Finalizado,Reasignada.
    @Override
    public List<operacionesOrdent> otEstadosvarios(){
      List<operacionesOrdent> lista = null;
      String consulta;
      try{
        consulta="SELECT op FROM operacionesOrdent op WHERE op.estado = ?1 or op.estado = ?2 or op.estado = ?3 or op.estado = ?4 or op.estado = ?5 or op.estado = ?6";
        Query query=em.createQuery(consulta);
        query.setParameter(1, "Operario Asignado");
        query.setParameter(2, "Complemento");
        query.setParameter(3, "Costo asignado");
        query.setParameter(4,"Anticipo");
        query.setParameter(5, "Finalizado");
        query.setParameter(6,"Reasignada");
      
        lista = query.getResultList();
        System.out.println("AQUI"+lista.get(0).getMontomo().toString());
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      return lista;
    }
    
        //obtener ot pagadas, tambien si como si ha tenido anticipos o fue reasignada
    @Override
    public List<Aux_detallepagoorden> otPagadasdetalle(int ordenabuscar, String toperacion){
     
      List<Aux_detallepagoorden> lista=null;
      String consulta;
      
      //EJECUTAR PROCEDIMIENTO ALMACENADO
      StoredProcedureQuery storedProcedure=em.createStoredProcedureQuery("detallepagosordent");
      storedProcedure.registerStoredProcedureParameter("ordenabuscar", Integer.class, ParameterMode.IN);
      storedProcedure.registerStoredProcedureParameter("tipoop", String.class, ParameterMode.IN);
      storedProcedure.setParameter("ordenabuscar", ordenabuscar);
      storedProcedure.setParameter("tipoop",toperacion);
      storedProcedure.execute();
      //OBTENER DATOS DE TABLA GENERADA POR PROCEDIMIENTO ALMACENADO.
      try{
        consulta="SELECT a FROM Aux_detallepagoorden a";
        Query query=em.createQuery(consulta);
        
        lista = query.getResultList();
    
      }catch (Exception e){
        System.out.println(e.getMessage());
      }
      return lista;
    }
    
    
        
    //obtener orden pagada por placa.
    @Override
    public operacionesOrdent otpagadaPlaca(String placa,String tipoop,String estado){
     
      if(tipoop==null){
          tipoop="";
      }
      if (estado==null){
          estado="";
      }
      List<operacionesOrdent> lista = null;
      operacionesOrdent operacionesordent=null;
      String consulta;
      
      System.out.println("VALORES "+placa+" "+tipoop+" "+estado);
      try{
        consulta="SELECT op FROM operacionesOrdent op WHERE op.ordentrabajo.placa LIKE ?1 AND (op.estado = ?3 AND op.tipooperaciones = ?2) ORDER BY op.fechapago DESC";
        Query query=em.createQuery(consulta);
        query.setParameter(1,"%"+placa+"%");
        query.setParameter(2,tipoop);
        query.setParameter(3,estado);
      
        lista = query.getResultList();
        
        System.out.println("VALORES "+lista.size());
        System.out.println("VALORES "+!lista.isEmpty());
        if (!lista.isEmpty()){
            operacionesordent=lista.get(0);
            System.out.println("VALORES dentro IF "+lista.size()+" " +lista.get(0).getOrdentrabajo());
        }
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      //System.out.println("VALORES a retornar "+operacionesordent.getId());
      return operacionesordent;
    }
    
    //obtener orden pagada por id de orden
    @Override
    public operacionesOrdent otpagadaIdorden(int id,String tipoop,String estado){
     
      if(tipoop==null){
          tipoop="";
      }
      if (estado==null){
          estado="";
      }
      List<operacionesOrdent> lista = null;
      operacionesOrdent operacionesordent=null;
      String consulta;
      
      System.out.println("VALORES "+id+" "+tipoop+" "+estado);
      try{
        consulta="SELECT op FROM operacionesOrdent op WHERE op.ordentrabajo.id = ?1 AND (op.estado = ?3 AND op.tipooperaciones = ?2) ORDER BY op.fechapago DESC";
        Query query=em.createQuery(consulta);
        query.setParameter(1,id);
        query.setParameter(2,tipoop);
        query.setParameter(3,estado);
      
        lista = query.getResultList();
        
        System.out.println("VALORES "+lista.size());
        System.out.println("VALORES "+!lista.isEmpty());
        if (!lista.isEmpty()){
            operacionesordent=lista.get(0);
            System.out.println("VALORES dentro IF "+lista.size()+" " +lista.get(0).getOrdentrabajo());
        }
    
      }catch (Exception e){
       System.out.println(e.getMessage());
      }
      //System.out.println("VALORES a retornar "+operacionesordent.getId());
      return operacionesordent;
    }
    
}
