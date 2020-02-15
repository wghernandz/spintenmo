/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;

import com.spintenmo.ejb.ordenTrabajoFacadeLocal;
import com.spintenmo.modelo.ordenTrabajo;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class eliminarOtController implements Serializable {
    @EJB
    private ordenTrabajoFacadeLocal ordentrabajoEJB;
    
    //Entidades
    private ordenTrabajo ordentrabajo;
    
    @PostConstruct
    public void init(){
        ordentrabajo=new ordenTrabajo();
    
    }

    public ordenTrabajoFacadeLocal getOrdentrabajoEJB() {
        return ordentrabajoEJB;
    }

    public void setOrdentrabajoEJB(ordenTrabajoFacadeLocal ordentrabajoEJB) {
        this.ordentrabajoEJB = ordentrabajoEJB;
    }

    public ordenTrabajo getOrdentrabajo() {
        return ordentrabajo;
    }

    public void setOrdentrabajo(ordenTrabajo ordentrabajo) {
        this.ordentrabajo = ordentrabajo;
    }
    
    public String eliminarOrden(){
    //verificar el estado de la orden
    
    if(ordentrabajoEJB.obtenerOt(ordentrabajo.getCodigo())==null){
         FacesContext.getCurrentInstance().addMessage(
        null, new FacesMessage("No existe Orden"));
 
    }else if(ordentrabajoEJB.obtenerOt(ordentrabajo.getCodigo(),"Costo no asignado")==null){
                FacesContext.getCurrentInstance().addMessage(
        null, new FacesMessage("Orden ya posee estado COSTO ASIGNADO"));
    
        } else{
           
         this.ordentrabajo=ordentrabajoEJB.obtenerOt(ordentrabajo.getCodigo(), "Costo no asignado");
         ordentrabajoEJB.remove(ordentrabajo);
           
        FacesContext.getCurrentInstance().addMessage(
        null, new FacesMessage("Orden Eliminada "+ordentrabajo.getCodigo()));
        }
    
     FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);
 
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        return view.getViewId() + "?faces-redirect=true&includeViewParams=true";
  }
    
       public String cancelar(){
        UIViewRoot view=FacesContext.getCurrentInstance().getViewRoot();
        return view.getViewId()+"?faces-redirect=true&includeViewParams=true";
    }
      
}
