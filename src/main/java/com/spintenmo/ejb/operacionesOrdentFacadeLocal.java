/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.Descuentos;
import com.spintenmo.modelo.anticipoMo;
import com.spintenmo.modelo.empleadoMo;
import com.spintenmo.modelo.operacionesOrdent;
import com.spintenmo.modelo.ordenTrabajo;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface operacionesOrdentFacadeLocal {

    void create(operacionesOrdent operacionesOrdent);

    void edit(operacionesOrdent operacionesOrdent);

    void remove(operacionesOrdent operacionesOrdent);

    operacionesOrdent find(Object id);

    List<operacionesOrdent> findAll();

    List<operacionesOrdent> findRange(int[] range);

    int count();
    
    operacionesOrdent valoresMinMax(ordenTrabajo ot);
    
    operacionesOrdent valoresMinMaxp(ordenTrabajo ot);
    
    List<operacionesOrdent> otPorasignar(operacionesOrdent ot);
    
    List<empleadoMo> operarioSegunop(operacionesOrdent ot);
    
    List<operacionesOrdent> otAsignadas();
    
    List<operacionesOrdent> anexopagoOrden(operacionesOrdent ot);
    
    int ordenValorizada(operacionesOrdent ot);
    
    List<operacionesOrdent> otPreplanilla();
    
    List<anticipoMo> anticipoPreplanilla();
    
    long ordenesAsociadasEmp(int idempleado);
    
    List<operacionesOrdent> otXfecha(Date finicial, Date ffinal);
    
    List<operacionesOrdent> optSegunModelo(int idmodelo, String estado);
    
    List<operacionesOrdent> otEstado(String estado);
    
    List<operacionesOrdent> otEstadoOperacion(String estado, String operacion);
    
    void finalizarTodo();
    
    List<operacionesOrdent> otpagadaXplaca(String placa,String tipoop, String estado); 
   
    List<operacionesOrdent> otEstadosvarios();
}
