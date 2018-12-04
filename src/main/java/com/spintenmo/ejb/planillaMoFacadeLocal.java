/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.Descuentos;
import com.spintenmo.modelo.anticipoMo;
import com.spintenmo.modelo.planillaMo;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface planillaMoFacadeLocal {

    void create(planillaMo planillaMo);

    void edit(planillaMo planillaMo);

    void remove(planillaMo planillaMo);

    planillaMo find(Object id);

    List<planillaMo> findAll();

    List<planillaMo> findRange(int[] range);

    int count();
    
    List<planillaMo> calculoPlanilla(String fechainicio, String fechafin);
    
    List<planillaMo> planillaSegunEstado(String estado);
    
    List<planillaMo> pagarPlanilla(String fpago);
    
    List<List> consolidadoPlanilla(String estado);
    
    List<planillaMo> imprimirPlanillagen(Date fechapago);
    
    List<Descuentos> imprimirDescuentos();
    
    void eliminarPsegunestado(String estado);
    
    List<planillaMo> imprimirPlanillapag(Date fechapago);
    
    List<anticipoMo> anticipoPlanpag(Date fechapago);
    
    planillaMo planillaSegunEmpEstado(int emp,String estado);
    
    List<Descuentos> imprimirDescuentospagado(Date fechapago);
    
    List<planillaMo> planillaFecha(Date fechainicio,Date fechafin);
}
