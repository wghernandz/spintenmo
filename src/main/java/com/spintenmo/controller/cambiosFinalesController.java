/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;

import com.spintenmo.ejb.AnioFacadeLocal;
import com.spintenmo.ejb.MarcaFacadeLocal;
import com.spintenmo.ejb.ModeloFacadeLocal;
import com.spintenmo.ejb.aseguradoraClienteFacadeLocal;
import com.spintenmo.ejb.operacionesOrdentFacadeLocal;
import com.spintenmo.ejb.ordenTrabajoFacadeLocal;
import com.spintenmo.modelo.Anio;
import com.spintenmo.modelo.Marca;
import com.spintenmo.modelo.Modelo;
import com.spintenmo.modelo.Persona;
import com.spintenmo.modelo.aseguradoraCliente;
import com.spintenmo.modelo.colorVehiculo;
import com.spintenmo.modelo.empleadoMo;
import com.spintenmo.modelo.operacionesOrdent;
import com.spintenmo.modelo.ordenTrabajo;
import com.spintenmo.modelo.usuarioRole;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author willian
 */
@Named
@ViewScoped
public class cambiosFinalesController implements Serializable {
    @EJB
    private ordenTrabajoFacadeLocal ordentrabajoEJB;
    @EJB
    private operacionesOrdentFacadeLocal operacionesordentEJB;
    @EJB
    private aseguradoraClienteFacadeLocal aseguradoraclienteEJB;
    @EJB
    private MarcaFacadeLocal marcaEJB;
    @EJB
    private ModeloFacadeLocal modeloEJB;
    @EJB
    private AnioFacadeLocal anioEJB;
    
    private ordenTrabajo ordentrabajo;
    private operacionesOrdent operacionesordent;
    private aseguradoraCliente aseguradoracliente;
    private Marca marca;
    private Modelo modelo;
    private usuarioRole usuariorole;
    private String codigoot;
    private String estadoprevio;
    private String trabajotaller;
    private Persona persona;
    private empleadoMo empleadomo;
    
    List<ordenTrabajo> ordenes;
    List<ordenTrabajo> filteredordenes;
    List<operacionesOrdent> operacionesot;
    List<operacionesOrdent> filteredoperacionesot;
    List<Marca> marcas;
    List<Modelo> modelos;
    List<colorVehiculo> colorvehiculos;
    List<Anio> anios;
    
    private final static String estadoCostoAsignado = "Costo Asignado";
    private final static String estadoOperarioAsignado = "Operario Asignado";
    private final static String estadoComplemento = "Complemento";
    private final static String estadoFinalizado = "Finalizado";
    private final static String estadoReasignada = "Reasignada";
    private final static String estadoNoAplica = "No Aplica";
    private final static String estadoAnticipo = "Anticipo";
    
    List<String> opcEstadoCostoAsignado;

    @PostConstruct
    public void init(){
        operacionesot = operacionesordentEJB.otEstadosvarios();
        marca = new Marca();
        modelo = new Modelo();
        aseguradoracliente = new aseguradoraCliente();
        operacionesordent = new operacionesOrdent();
        
        opcEstadoCostoAsignado = new ArrayList(
                Arrays.asList("Costo Asignado","Finalizado","Anticipo","No Aplica"));
    }

    public ordenTrabajoFacadeLocal getOrdentrabajoEJB() {
        return ordentrabajoEJB;
    }

    public operacionesOrdentFacadeLocal getOperacionesordentEJB() {
        return operacionesordentEJB;
    }

    public aseguradoraClienteFacadeLocal getAseguradoraclienteEJB() {
        return aseguradoraclienteEJB;
    }

    public MarcaFacadeLocal getMarcaEJB() {
        return marcaEJB;
    }

    public ModeloFacadeLocal getModeloEJB() {
        return modeloEJB;
    }

    public AnioFacadeLocal getAnioEJB() {
        return anioEJB;
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

    public aseguradoraCliente getAseguradoracliente() {
        return aseguradoracliente;
    }

    public void setAseguradoracliente(aseguradoraCliente aseguradoracliente) {
        this.aseguradoracliente = aseguradoracliente;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public usuarioRole getUsuariorole() {
        return usuariorole;
    }

    public void setUsuariorole(usuarioRole usuariorole) {
        this.usuariorole = usuariorole;
    }

    public String getCodigoot() {
        return codigoot;
    }

    public void setCodigoot(String codigoot) {
        this.codigoot = codigoot;
    }

    public String getEstadoprevio() {
        return estadoprevio;
    }

    public void setEstadoprevio(String estadoprevio) {
        this.estadoprevio = estadoprevio;
    }

    public String getTrabajotaller() {
        return trabajotaller;
    }

    public void setTrabajotaller(String trabajotaller) {
        this.trabajotaller = trabajotaller;
    }

    public List<ordenTrabajo> getOrdenes() {
        return ordenes;
    }

    public List<ordenTrabajo> getFilteredordenes() {
        return filteredordenes;
    }

    public List<operacionesOrdent> getOperacionesot() {
        return operacionesot;
    }

    public List<operacionesOrdent> getFilteredoperacionesot() {
        return filteredoperacionesot;
    }

    public List<Marca> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<Marca> marcas) {
        this.marcas = marcas;
    }

    public List<Modelo> getModelos() {
        return modelos;
    }

    public void setModelos(List<Modelo> modelos) {
        this.modelos = modelos;
    }

    public List<colorVehiculo> getColorvehiculos() {
        return colorvehiculos;
    }

    public void setColorvehiculos(List<colorVehiculo> colorvehiculos) {
        this.colorvehiculos = colorvehiculos;
    }

    public List<Anio> getAnios() {
        return anios;
    }

    public void setAnios(List<Anio> anios) {
        this.anios = anios;
    }

    public List<String> getOpcEstadoCostoAsignado() {
        return opcEstadoCostoAsignado;
    }

    public void setOpcEstadoCostoAsignado(List<String> opcEstadoCostoAsignado) {
        this.opcEstadoCostoAsignado = opcEstadoCostoAsignado;
    }
       
    public void cambiarEstado(ValueChangeEvent e){
        System.out.println(e);
        System.out.println("estadoActual "+e.getOldValue()+"estadoNuevo "+e.getNewValue());
        
        /*switch(estadoActual){
            case estadoCostoAsignado:
                
                if (estadoNuevo.equals(estadoFinalizado)){
                   // FacesContext.getCurrentInstance().addMessage(null,
                   // new FacesMessage("Asigne primero el operario " + operacionesordent.getOrdentrabajo().getCodigo()));
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('dlg3').show();");
                }
                if (estadoNuevo.equals(estadoAnticipo)){
                
                }
                if (estadoNuevo.equals(estadoNoAplica)){
                
                }
                
                //puedo cambiar a costo no asignado
                //a finalizado o anticipo
                //
                break;
            case estadoOperarioAsignado:
                //crear otra asignacion
                //cambiar a no aplica
                //cambiar a finalizado o anticipo
                break;
                
            case estadoComplemento:
                //reasignar
                //cambiar a finalizado o anticipo
                break;
            case estadoFinalizado:
                
                //cambiar a operario asignado
                break;
            case estadoReasignada:
                
                //ver como quedaria es un caso especial
                break;
            case estadoNoAplica:
                
                //
                break;
            case estadoAnticipo:
                
                //verificar pasar a estados anteriores
                break;
        }*/
    }
    
    public void totalPorEmpleado(){
        
    }
    
       public void asignarOperario(){
       // if (this.noaplica==false){
        this.empleadomo.setPersona(persona);
        this.operacionesordent.setEmpleadomo(empleadomo);
        this.operacionesordent.setFechaasignado(new Date());
        this.operacionesordent.setMontopendiente(operacionesordent.getMontomo());
        //cambiar el estado de la operaciones de orden
        this.operacionesordent.setEstado("Operario Asignado");
        //guardar operacionesorden
        operacionesordentEJB.edit(operacionesordent);
    
        FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage("Se Actualizo Orden de Trabajo: " + operacionesordent.getOrdentrabajo().getCodigo()));
        
        init();
        //}
        /*else{
            this.operacionesordent.setFechaasignado(new Date());
            this.operacionesordent.setMontopendiente(operacionesordent.getMontomo());
            this.operacionesordent.setEstado("No Aplica");
            operacionesordentEJB.edit(operacionesordent);
    
            FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage("Se Actualizo Orden de Trabajo: " + operacionesordent.getOrdentrabajo().getCodigo()));
        
            init();
        }*/
    }
    
}
