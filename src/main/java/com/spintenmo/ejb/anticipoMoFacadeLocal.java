/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.anticipoMo;
import com.spintenmo.modelo.operacionesOrdent;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface anticipoMoFacadeLocal {

    void create(anticipoMo anticipoMo);

    void edit(anticipoMo anticipoMo);

    void remove(anticipoMo anticipoMo);

    anticipoMo find(Object id);

    List<anticipoMo> findAll();

    List<anticipoMo> findRange(int[] range);

    int count();
    
    BigDecimal sumAnticipoOrden(int idoperacionesordent);
    
    List<anticipoMo> anticipoPorOrden(operacionesOrdent operacionesordent);
}
