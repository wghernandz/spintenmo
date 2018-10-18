/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.Descuentos;
import com.spintenmo.modelo.planillaMo;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface DescuentosFacadeLocal {

    void create(Descuentos descuentos);

    void edit(Descuentos descuentos);

    void remove(Descuentos descuentos);

    Descuentos find(Object id);

    List<Descuentos> findAll();

    List<Descuentos> findRange(int[] range);

    int count();
    
    List<Descuentos> descuentosaAplicar();
    
    planillaMo planillaPoroperario(int idpersona);
    
    BigDecimal sumDescuentooperario(int idempleadomo);
    
    List<Descuentos> descuentosUpdate();
    
    Descuentos descVerificado();
    
    void despuespagodesc();
}
