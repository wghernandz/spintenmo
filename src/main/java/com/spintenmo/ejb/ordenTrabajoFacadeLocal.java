/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.ejb;

import com.spintenmo.modelo.ordenTrabajo;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author willian
 */
@Local
public interface ordenTrabajoFacadeLocal {

    void create(ordenTrabajo ordenTrabajo);

    void edit(ordenTrabajo ordenTrabajo);

    void remove(ordenTrabajo ordenTrabajo);

    ordenTrabajo find(Object id);

    List<ordenTrabajo> findAll();

    List<ordenTrabajo> findRange(int[] range);

    int count();
    
    List<ordenTrabajo> otIngresadas();
    
    ordenTrabajo obtenerOt(String codigo);
    
    ordenTrabajo obtenerOt(String codigo, String estado);
    
    List<ordenTrabajo> ultimasIngOt(String estado);
    
    List<ordenTrabajo> otXfecha(Date fechainicial,Date fechafinal);
    
    void actualizarEstado(int idot);
}
