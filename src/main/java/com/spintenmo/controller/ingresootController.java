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
import com.spintenmo.ejb.colorVehiculoFacadeLocal;
import com.spintenmo.ejb.ordenTrabajoFacadeLocal;
import com.spintenmo.modelo.Anio;
import com.spintenmo.modelo.Marca;
import com.spintenmo.modelo.Modelo;
import com.spintenmo.modelo.aseguradoraCliente;
import com.spintenmo.modelo.colorVehiculo;
import com.spintenmo.modelo.ordenTrabajo;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import static org.primefaces.component.contextmenu.ContextMenu.PropertyKeys.event;

@Named
@ViewScoped
public class ingresootController implements Serializable {
    //EJBS
    @EJB
    private ordenTrabajoFacadeLocal ordenTrabajoEJB;
    @EJB
    private colorVehiculoFacadeLocal colorvehiculoEJB;
    @EJB
    private ModeloFacadeLocal modeloEJB;
    @EJB
    private MarcaFacadeLocal marcaEJB;
    @EJB
    private AnioFacadeLocal anioEJB;
    @EJB
    private aseguradoraClienteFacadeLocal aseguradoraClienteEJB;
    @EJB
    private ordenTrabajoFacadeLocal ordentrabajoEJB;
    //ENTIDADES
    private ordenTrabajo ordentrabajo;
    private Marca marca;
    private Modelo modelo;
    private Anio anio;
    private aseguradoraCliente aseguradoracliente;
    private colorVehiculo colorvehiculo;
    //VARIABLES
    private String fechaauingreso;
    private Date fautorizado;
    private String codigoot;
    private Date fecha;

    //LISTAS
    private List<aseguradoraCliente> clientes;
    private List<Marca> marcas;
    private List<Modelo> modelos;
    private List<Anio> anios;
    private List<colorVehiculo> colores;
    private List<ordenTrabajo> ultimasordenes;
    
    @PostConstruct
    public void init(){
        ordentrabajo=new ordenTrabajo();
        marca= new Marca();
        modelo= new Modelo();
        anio= new Anio();
        aseguradoracliente= new aseguradoraCliente();
        colorvehiculo=new colorVehiculo();
        
        //Inicializar fecha para fecha de ingreso
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        ordentrabajo.setFechaingreso(new Date());
        //fechaactual= formateador.format(new Date());
        //Inicializar entidades catalogo o listas
        clientes=aseguradoraClienteEJB.findAll();
        marcas=marcaEJB.findAll();
        modelos=modeloEJB.findAll();
        anios=anioEJB.findAll();
        colores=colorvehiculoEJB.findAll();
        ultimasordenes=ordenTrabajoEJB.ultimasIngOt("Costo no asignado");
    }

    public ordenTrabajoFacadeLocal getOrdenTrabajoEJB() {
        return ordenTrabajoEJB;
    }

    public void setOrdenTrabajoEJB(ordenTrabajoFacadeLocal ordenTrabajoEJB) {
        this.ordenTrabajoEJB = ordenTrabajoEJB;
    }

    public colorVehiculoFacadeLocal getColorvehiculoEJB() {
        return colorvehiculoEJB;
    }

    public void setColorvehiculoEJB(colorVehiculoFacadeLocal colorvehiculoEJB) {
        this.colorvehiculoEJB = colorvehiculoEJB;
    }

    public ModeloFacadeLocal getModeloEJB() {
        return modeloEJB;
    }

    public void setModeloEJB(ModeloFacadeLocal modeloEJB) {
        this.modeloEJB = modeloEJB;
    }

    public MarcaFacadeLocal getMarcaEJB() {
        return marcaEJB;
    }

    public void setMarcaEJB(MarcaFacadeLocal marcaEJB) {
        this.marcaEJB = marcaEJB;
    }

    public AnioFacadeLocal getAnioEJB() {
        return anioEJB;
    }

    public void setAnioEJB(AnioFacadeLocal anioEJB) {
        this.anioEJB = anioEJB;
    }

    public aseguradoraClienteFacadeLocal getAseguradoraClienteEJB() {
        return aseguradoraClienteEJB;
    }

    public void setAseguradoraClienteEJB(aseguradoraClienteFacadeLocal aseguradoraClienteEJB) {
        this.aseguradoraClienteEJB = aseguradoraClienteEJB;
    }

    public ordenTrabajo getOrdentrabajo() {
        return ordentrabajo;
    }

    public void setOrdentrabajo(ordenTrabajo ordentrabajo) {
        this.ordentrabajo = ordentrabajo;
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

    public Anio getAnio() {
        return anio;
    }

    public void setAnio(Anio anio) {
        this.anio = anio;
    }

    public aseguradoraCliente getAseguradoracliente() {
        return aseguradoracliente;
    }

    public void setAseguradoracliente(aseguradoraCliente aseguradoracliente) {
        this.aseguradoracliente = aseguradoracliente;
    }

    public colorVehiculo getColorvehiculo() {
        return colorvehiculo;
    }

    public void setColorvehiculo(colorVehiculo colorvehiculo) {
        this.colorvehiculo = colorvehiculo;
    }

    public String getFechaauingreso() {
        return fechaauingreso;
    }

    public void setFechaauingreso(String fechaauingreso) {
        this.fechaauingreso = fechaauingreso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    } 

    public List<aseguradoraCliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<aseguradoraCliente> clientes) {
        this.clientes = clientes;
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

    public List<Anio> getAnios() {
        return anios;
    }

    public void setAnios(List<Anio> anios) {
        this.anios = anios;
    }

    public List<colorVehiculo> getColores() {
        return colores;
    }

    public void setColores(List<colorVehiculo> colores) {
        this.colores = colores;
    }

    public Date getFautorizado() {
        return fautorizado;
    }

    public void setFautorizado(Date fautorizado) {
        this.fautorizado = fautorizado;
    }

    public String getCodigoot() {
        return codigoot;
    }

    public void setCodigoot(String codigoot) {
        this.codigoot = codigoot;
    }

    public ordenTrabajoFacadeLocal getOrdentrabajoEJB() {
        return ordentrabajoEJB;
    }

    public void setOrdentrabajoEJB(ordenTrabajoFacadeLocal ordentrabajoEJB) {
        this.ordentrabajoEJB = ordentrabajoEJB;
    }

    public List<ordenTrabajo> getUltimasordenes() {
        return ultimasordenes;
    }

    public void setUltimasordenes(List<ordenTrabajo> ultimasordenes) {
        this.ultimasordenes = ultimasordenes;
    }
    
    public void asignarCodigoOt(){ 
   
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        codigoot=df.format(ordentrabajo.getFechaautorizado());
        codigoot=ordentrabajo.getPlaca()+"-"+codigoot;
        ordentrabajo.setCodigo(codigoot); 
    }
    
    public String registrarOrden(){
       try{
            this.ordentrabajo.setAseguradoracliente(aseguradoracliente);
            this.ordentrabajo.setColorvehiculo(colorvehiculo);
            this.ordentrabajo.setAniot(anio);
            this.ordentrabajo.setModelot(modelo);
            this.ordentrabajo.setEsreclamo("No");
            this.ordentrabajo.setEstado("Costo no asignado");
            ordenTrabajoEJB.create(ordentrabajo);     
            
        FacesContext.getCurrentInstance().addMessage(
        null, new FacesMessage("Orden ingresada con exito"));
 
        FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);
 
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        return view.getViewId() + "?faces-redirect=true&includeViewParams=true";
       }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(
            null, new FacesMessage("Ocurrio un Error"+e.getMessage()));
 
        FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);
 
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
                return view.getViewId() + "?faces-redirect=true&includeViewParams=true";
       
       }
    } 
    public String cancelar(){
        UIViewRoot view=FacesContext.getCurrentInstance().getViewRoot();
        return view.getViewId()+"?faces-redirect=true&includeViewParams=true";
    }
    
    public void cambiarModelos(){
        modelos=marcaEJB.modelosXmarca(marca);
        
    }
    
}
