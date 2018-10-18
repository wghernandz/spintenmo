/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;

import com.spintenmo.ejb.DescuentosFacadeLocal;
import com.spintenmo.ejb.PersonaFacadeLocal;
import com.spintenmo.ejb.empleadoMoFacadeLocal;
import com.spintenmo.modelo.Descuentos;
import com.spintenmo.modelo.Persona;
import com.spintenmo.modelo.empleadoMo;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ingresoDescuentosController implements Serializable {
    //EJB
    @EJB
    private DescuentosFacadeLocal descuentosEJB;
    @EJB
    private empleadoMoFacadeLocal empleadomoEJB;
    @EJB
    private PersonaFacadeLocal personaEJB;
    //entidades
    private Descuentos descuento;
    private empleadoMo empleadomo;
    private Persona persona;
    //Listas
    private List<empleadoMo> empleadosmo;
    private List<Descuentos> descuentos;
    //Variables
    private boolean desactivar;
    private boolean desactivarboton;
    @PostConstruct
    public void init(){
        descuento=new Descuentos();
        empleadomo=new empleadoMo();
        empleadosmo=empleadomoEJB.findAll();
        persona=new Persona();
        descuentos=descuentosEJB.descuentosaAplicar();
    }

    public DescuentosFacadeLocal getDescuentosEJB() {
        return descuentosEJB;
    }

    public void setDescuentosEJB(DescuentosFacadeLocal descuentosEJB) {
        this.descuentosEJB = descuentosEJB;
    }

    public empleadoMoFacadeLocal getEmpleadomoEJB() {
        return empleadomoEJB;
    }

    public void setEmpleadomoEJB(empleadoMoFacadeLocal empleadomoEJB) {
        this.empleadomoEJB = empleadomoEJB;
    }

    public Descuentos getDescuento() {
        return descuento;
    }

    public void setDescuento(Descuentos descuento) {
        this.descuento = descuento;
        //Establecer tambien datos del empleado
        this.empleadomo=descuento.getEmpleadomo();
        this.persona=empleadomo.getPersona();
    }

    public empleadoMo getEmpleadomo() {
        return empleadomo;
    }

    public void setEmpleadomo(empleadoMo empleadomo) {
        this.empleadomo = empleadomo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<empleadoMo> getEmpleadosmo() {
        return empleadosmo;
    }

    public void setEmpleadosmo(List<empleadoMo> empleadosmo) {
        this.empleadosmo = empleadosmo;
    }
    
    public void operarioSegundui(String dui){
        persona=empleadomoEJB.personaSegunDui(dui);
        empleadomo=empleadomoEJB.operarioSegunDui(dui);
    }

    public PersonaFacadeLocal getPersonaEJB() {
        return personaEJB;
    }

    public void setPersonaEJB(PersonaFacadeLocal personaEJB) {
        this.personaEJB = personaEJB;
    }

    public boolean isDesactivar() {
        return desactivar;
    }

    public void setDesactivar(boolean desactivar) {
        this.desactivar = desactivar;
    }

    public List<Descuentos> getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(List<Descuentos> descuentos) {
        this.descuentos = descuentos;
    }

    public boolean isDesactivarboton() {
        return desactivarboton;
    }

    public void setDesactivarboton(boolean desactivarboton) {
        this.desactivarboton = desactivarboton;
    }
    
    
    public void guardarDescuento(){
        //agregar
        if(descuentosEJB.find(descuento.getId())==null){
            this.descuento.setEmpleadomo(empleadomo);
            this.descuento.setMontopend(descuento.getMonto());
            this.descuento.setDescfijo(descuento.getDescquincenal());
            descuentosEJB.create(descuento);
            //actualizar tabla
            this.descuentos=descuentosEJB.descuentosaAplicar();
            
            FacesContext.getCurrentInstance().addMessage(
            null, new FacesMessage("Descuento ingresado con exito"));
 
            FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash().setKeepMessages(true);
        }else{//modificar
                    this.descuento.setEmpleadomo(empleadomo);
                    this.descuento.setMontopend(descuento.getMonto());
                    this.descuento.setDescfijo(descuento.getDescquincenal());
                    descuentosEJB.edit(descuento);
                    
                    FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage("Descuento modificado con exito"));
 
                    FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getFlash().setKeepMessages(true);
            }
        desactivarboton=true;
    }
    
    public void empSegunid(int idperson){
        persona=personaEJB.find(idperson);
        empleadomo=empleadomoEJB.find(persona.getId());
        desactivar=true;
    }
    
    
    public String nuevo(){
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        return view.getViewId() + "?faces-redirect=true&includeViewParams=true";
    }
    
     public void eliminar(){
          this.descuento=descuentosEJB.find(descuento.getId());
          descuentosEJB.remove(descuento);
          desactivarboton=true;
          //actualizar tabla
          this.descuentos=descuentosEJB.descuentosaAplicar();
          FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage("Descuento ELIMINADO con exito"));
 
                    FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getFlash().setKeepMessages(true);
    }
    
}
