/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;

import com.spintenmo.ejb.operacionesOrdentFacadeLocal;
import com.spintenmo.ejb.ordenTrabajoFacadeLocal;
import com.spintenmo.modelo.operacionesOrdent;
import com.spintenmo.modelo.ordenTrabajo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.spintemo.utilieria.desactivarControles;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import static org.eclipse.persistence.jpa.rs.util.JPARSLogger.exception;
import org.primefaces.component.inputtext.InputText;


@Named
@ViewScoped
public class asignarMinmaxController implements Serializable {
@EJB
private operacionesOrdentFacadeLocal operacionesordentEJB;
@EJB
private ordenTrabajoFacadeLocal ordentrabajoEJB;
@Inject
private asignarcostoController asignarcostoManaged;
    //LISTAS
    private List<ordenTrabajo> ordenesnoasignadas;
    //variables
    private ordenTrabajo ordentrabajo;
    private operacionesOrdent operacionesordentend;
    private operacionesOrdent operacionesordentp;
    private operacionesOrdent operacionesordent;
    private operacionesOrdent operacionesordentpin;
    

   
    @PostConstruct
    public void init(){
        //inicializar operaciones de ot de acuerdo a orden de trabajo pasada desde el 
        //controlador asignarcostoController
       this.ordentrabajo=asignarcostoManaged.getOrdentrabajo();
       operacionesordentend=operacionesordentEJB.valoresMinMax(this.ordentrabajo);
       operacionesordentp=operacionesordentEJB.valoresMinMaxp(this.ordentrabajo);
       //si ya existen valores establecidos desactivar controles del formulario
       
       if (operacionesordentend!=null){
           
       desactivarControles.disableUIComponent(":form");
       operacionesordent=operacionesordentend;
       }else{
        operacionesordent=new operacionesOrdent();
        
       }
       if (operacionesordentp!=null){
       desactivarControles.disableUIComponent(":form2");
       operacionesordentpin=operacionesordentp;
       }else{
           operacionesordentpin=new operacionesOrdent(); 
       }
       
       //crear objeto operaciones de ot para guardar registro nuevo
    }

    public operacionesOrdentFacadeLocal getOperacionesordentEJB() {
        return operacionesordentEJB;
    }

    public void setOperacionesordentEJB(operacionesOrdentFacadeLocal operacionesordentEJB) {
        this.operacionesordentEJB = operacionesordentEJB;
    }

    public ordenTrabajoFacadeLocal getOrdentrabajoEJB() {
        return ordentrabajoEJB;
    }

    public void setOrdentrabajoEJB(ordenTrabajoFacadeLocal ordentrabajoEJB) {
        this.ordentrabajoEJB = ordentrabajoEJB;
    }

    public List<ordenTrabajo> getOrdenesnoasignadas() {
        return ordenesnoasignadas;
    }

    public void setOrdenesnoasignadas(List<ordenTrabajo> ordenesnoasignadas) {
        this.ordenesnoasignadas = ordenesnoasignadas;
    }

    public asignarcostoController getAsignarcostoManaged() {
        return asignarcostoManaged;
    }

    public void setAsignarcostoManaged(asignarcostoController asignarcostoManaged) {
        this.asignarcostoManaged = asignarcostoManaged;
    }

    public ordenTrabajo getOrdentrabajo() {
        return ordentrabajo;
    }

    public void setOrdentrabajo(ordenTrabajo ordentrabajo) {
        this.ordentrabajo = ordentrabajo;
    }

    public operacionesOrdent getOperacionesordent() {
        return operacionesordent;
    }

    public void setOperacionesordent(operacionesOrdent operacionesordent) {
        this.operacionesordent = operacionesordent;
    }

    public operacionesOrdent getOperacionesordentp() {
        return operacionesordentp;
    }

    public void setOperacionesordentp(operacionesOrdent operacionesordentp) {
        this.operacionesordentp = operacionesordentp;
    }

    public operacionesOrdent getOperacionesordentend() {
        return operacionesordentend;
    }

    public void setOperacionesordentend(operacionesOrdent operacionesordentend) {
        this.operacionesordentend = operacionesordentend;
    }

    public operacionesOrdent getOperacionesordentpin() {
        return operacionesordentpin;
    }

    public void setOperacionesordentpin(operacionesOrdent operacionesordentpin) {
        this.operacionesordentpin = operacionesordentpin;
    }
    
    
    //guardar valores min y max para enderezado
    public void guardarMinmaxend(){
        
        try{
        this.operacionesordent.setOrdentrabajo(ordentrabajo);
        this.operacionesordent.setEmpleadomo(null);
        this.operacionesordent.setTipooperaciones("enderezado");
        this.operacionesordent.setEstado("Costo asignado");
        this.operacionesordent.setFechavalorizado(new Date());
        operacionesordentEJB.create(operacionesordent);
        
        //Determinar si ya se asigno el costo de los dos tipo de operacion, proceder a cambiar de estado de OT
             
        desactivarControles.disableUIComponent(":form");
        FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage("Se Actualizo Orden de Trabajo: " + ordentrabajo.getCodigo()));         
        } catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage("SE PRODUJO UN ERROR EN " + ordentrabajo.getCodigo()));         
        }
        
     
        //UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        //return view.getViewId() + "?faces-redirect=true&includeViewParams=true";
           
    }
    
    //guardar valores min y max para pintura
    public void guardarMinmaxpin(){
        try{
        this.operacionesordentpin.setOrdentrabajo(ordentrabajo);
        this.operacionesordentpin.setEmpleadomo(null);
        this.operacionesordentpin.setTipooperaciones("pintura");
        this.operacionesordentpin.setEstado("Costo asignado");
        this.operacionesordentpin.setFechavalorizado(new Date());
        operacionesordentEJB.create(operacionesordentpin); 
       
        desactivarControles.disableUIComponent(":form2");
         FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage("Se Actualizo Orden de Trabajo: " + ordentrabajo.getCodigo()));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage("SE PRODUJO UN ERROR " + ordentrabajo.getCodigo()));
        }
    }
    
    public String cancelar(){
       return "/gerente/establecimientoCostosmo.xhtml?faces-redirect=true";
    
    }
    
     //validar monto minimo contra monto maximo ENDEREZADO
   public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Object otherValue = component.getAttributes().get("montomin");
        
        if(otherValue!=null){
        BigDecimal monto=((BigDecimal)value);
        BigDecimal montopendiente=(BigDecimal)otherValue;
        if (monto.compareTo(montopendiente) < 0) {
            throw new ValidatorException(new FacesMessage("Anticipo No puede ser mayor a "+montopendiente));
        }
    }
   }
   
       //validar monto minimo contra monto maximo PINTURA
   public void validatepintura(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Object otherValue = component.getAttributes().get("montominp");
        
        if(otherValue!=null){
        BigDecimal monto=((BigDecimal)value);
        BigDecimal montopendiente=(BigDecimal)otherValue;
        if (monto.compareTo(montopendiente) < 0) {
            throw new ValidatorException(new FacesMessage("Anticipo No puede ser mayor a "+montopendiente));
        }
    }
   }
   //Hacer montomax igual a monto min de enderezado para mejor interaccion con el usuario
   public void handleKeyEvent(){
       this.operacionesordent.setMontomax(operacionesordent.getMontomin());
   }
    
   //Hacer montomax igual a monto min de pintura para mejor interaccion con el usuario
   public void handleKeyEventPin(){
       this.operacionesordentpin.setMontomaxp(operacionesordentpin.getMontominp());
       
   }
   
}
