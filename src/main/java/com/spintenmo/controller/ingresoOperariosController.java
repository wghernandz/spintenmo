/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;

import com.spintemo.utilieria.desactivarControles;
import com.spintenmo.ejb.DepartamentoFacadeLocal;
import com.spintenmo.ejb.MunicipioFacadeLocal;
import com.spintenmo.ejb.PersonaFacadeLocal;
import com.spintenmo.ejb.empleadoMoFacadeLocal;
import com.spintenmo.ejb.operacionesOrdentFacadeLocal;
import com.spintenmo.ejb.planillaMoFacadeLocal;
import com.spintenmo.modelo.Departamento;
import com.spintenmo.modelo.Municipio;
import com.spintenmo.modelo.Persona;
import com.spintenmo.modelo.empleadoMo;
import com.spintenmo.modelo.operacionesOrdent;
import com.spintenmo.modelo.planillaMo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ingresoOperariosController implements Serializable {
    @EJB
    private PersonaFacadeLocal personaEJB;
    @EJB
    private empleadoMoFacadeLocal empleadomoEJB;
    @EJB
    private MunicipioFacadeLocal municipioEJB;
    @EJB
    private DepartamentoFacadeLocal departamentoEJB;
    @EJB
    private operacionesOrdentFacadeLocal operacionesordentEJB;
    @EJB
    private planillaMoFacadeLocal planillamoEJB;
    //ENTIDADES
    private empleadoMo empleadomo;
    private Persona persona;
    private Municipio municipio;
    private Departamento departamento;
    private operacionesOrdent operacionesordent;
    private planillaMo planillamo;
    //LISTAS
    private List<Municipio> municipios;
    private List<Departamento> departamentos;
    
    private boolean modificacion;
    private boolean desactivar;
    @PostConstruct
    public void init(){
        empleadomo= new empleadoMo();
        persona= new Persona();
        municipios= municipioEJB.findAll();
        departamentos=departamentoEJB.findAll();
        municipio= new Municipio();
        departamento= new Departamento();
        planillamo= new planillaMo();
    }

    public PersonaFacadeLocal getPersonaEJB() {
        return personaEJB;
    }

    public void setPersonaEJB(PersonaFacadeLocal personaEJB) {
        this.personaEJB = personaEJB;
    }

    public empleadoMoFacadeLocal getEmpleadomoEJB() {
        return empleadomoEJB;
    }

    public void setEmpleadomoEJB(empleadoMoFacadeLocal empleadomoEJB) {
        this.empleadomoEJB = empleadomoEJB;
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

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public MunicipioFacadeLocal getMunicipioEJB() {
        return municipioEJB;
    }

    public void setMunicipioEJB(MunicipioFacadeLocal municipioEJB) {
        this.municipioEJB = municipioEJB;
    }

    public DepartamentoFacadeLocal getDepartamentoEJB() {
        return departamentoEJB;
    }

    public void setDepartamentoEJB(DepartamentoFacadeLocal departamentoEJB) {
        this.departamentoEJB = departamentoEJB;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }
    
    public void cambiarMuni(){
        municipios=departamentoEJB.deptoXmunicipio(departamento);
    }

    public boolean isModificacion() {
        return modificacion;
    }

    public void setModificacion(boolean modificacion) {
        this.modificacion = modificacion;
    }

    public boolean isDesactivar() {
        return desactivar;
    }

    public void setDesactivar(boolean desactivar) {
        this.desactivar = desactivar;
    }

    public operacionesOrdentFacadeLocal getOperacionesordentEJB() {
        return operacionesordentEJB;
    }

    public void setOperacionesordentEJB(operacionesOrdentFacadeLocal operacionesordentEJB) {
        this.operacionesordentEJB = operacionesordentEJB;
    }

    public operacionesOrdent getOperacionesordent() {
        return operacionesordent;
    }

    public void setOperacionesordent(operacionesOrdent operacionesordent) {
        this.operacionesordent = operacionesordent;
    }

    public planillaMoFacadeLocal getPlanillamoEJB() {
        return planillamoEJB;
    }

    public void setPlanillamoEJB(planillaMoFacadeLocal planillamoEJB) {
        this.planillamoEJB = planillamoEJB;
    }

    public planillaMo getPlanillamo() {
        return planillamo;
    }

    public void setPlanillamo(planillaMo planillamo) {
        this.planillamo = planillamo;
    }
    
    public void guardarOperario(){
        //guardar
        if(modificacion==false){
        try{     
            this.persona.setMunicipio(municipio);
            this.municipio.setDepartamento(departamento);
            personaEJB.create(persona);
            this.empleadomo.setPersona(persona);
            empleadomoEJB.create(empleadomo);
            this.desactivar=true;
            FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage("Se Ingreso el empleado: " + persona.getPrimernombre()+" "+persona.getPrimerapellido()));
            }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage("SE PRODUJO UN ERROR"));
        }//modificar
        }else{
            try{
            this.persona.setMunicipio(municipio);
            this.municipio.setDepartamento(departamento);
            personaEJB.edit(persona);
            this.empleadomo.setPersona(persona);
            empleadomoEJB.edit(empleadomo);
            //verificar si existe una planilla generada, recalcular si se modifico salario
            if (planillamoEJB.planillaSegunEstado("Generada").isEmpty()==false) {
                if(planillamoEJB.planillaSegunEmpEstado(persona.getId(), "Generada")!=null){
                    this.planillamo=planillamoEJB.planillaSegunEmpEstado(persona.getId(), "Generada");
                    actualizarPlanilla(this.planillamo);
                }
            }
            
            this.desactivar=true;
                FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Se Modifico el empleado: " + persona.getPrimernombre()+" "+persona.getPrimerapellido()));
            
            }catch(Exception e){
                FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("SE PRODUJO UN ERROR"));
            }
        }
        
        //this.municipio.setDepartamento(departamento);
        
        //this.persona.setMunicipio(municipio);
        //  
    }
    
    public String nuevo(){
    
    UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        return view.getViewId() + "?faces-redirect=true&includeViewParams=true";
    }
    
    public String cancelar(){
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        return view.getViewId() + "?faces-redirect=true&includeViewParams=true";
    
    }
    
    public void verificarOperario(){
        
        if(empleadomoEJB.personaSegunDui(persona.getPdui())!=null){
            this.persona=empleadomoEJB.personaSegunDui(persona.getPdui());
            this.setPersona(persona);
            this.setMunicipio(persona.getMunicipio());
            this.setDepartamento(persona.getMunicipio().getDepartamento());
            this.empleadomo=empleadomoEJB.operarioSegunId(persona.getId());
            this.setEmpleadomo(empleadomo);
            this.modificacion=true;
            } else {
            this.modificacion=false;
        }
    }
    public void eliminarOperario(){
        long cantot=0;
                cantot=operacionesordentEJB.ordenesAsociadasEmp(this.persona.getId());
        if (cantot>0){
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("No SE PUEDE ELIMINAR " + persona.getPrimernombre()+" "+persona.getPrimerapellido()+", Tiene "+cantot+ " ordenes asociadas"));
        }else{
            this.persona=personaEJB.find(persona.getId());
            personaEJB.remove(persona);
            this.desactivar=true;
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("SE ELIMINO " + persona.getPrimernombre()+" "+persona.getPrimerapellido()));
        }
    
    }
    
     //recalcular planilla si se realiza un cambio a sueldo de empleado
     public void actualizarPlanilla(planillaMo planilla){
         planillamo=planilla;
         empleadomo=empleadomoEJB.operarioSegunId(planillamo.getEmpleadomo().getPersona().getId());
         BigDecimal ndiaslab=planillamo.getDiaslab();
         BigDecimal descuentos=planillamo.getSumadescuentos();
         BigDecimal montomototal=planillamo.getMontomototal();
         BigDecimal nsbasediario=(empleadomo.getSalariobase().divide(BigDecimal.valueOf(30.00)));
         BigDecimal nsalariobase=nsbasediario.multiply(ndiaslab);
         BigDecimal nsubtotalgravado=montomototal.subtract(nsalariobase);
         BigDecimal nrenta=nsubtotalgravado.multiply(BigDecimal.valueOf(0.10));
         BigDecimal nmontoapagar=nsubtotalgravado.subtract(nrenta).subtract(descuentos);
         
         this.planillamo.setSalariobase(nsalariobase);
         this.planillamo.setSubtotalgravado(nsubtotalgravado);
         this.planillamo.setRenta(nrenta);
         this.planillamo.setMontoapagar(nmontoapagar);
         
         planillamoEJB.edit(planillamo);
     }
}
