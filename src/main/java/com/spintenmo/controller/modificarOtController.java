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
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class modificarOtController implements Serializable {
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
    
      //ENTIDADES
    private ordenTrabajo ordentrabajo;
    private Marca marca;
    private Modelo modelo;
    private Anio anio;
    private aseguradoraCliente aseguradoracliente;
    private colorVehiculo colorvehiculo;
    //VARIABLES
    private String codigoot;
    private boolean disabledcodigo;
    private int tipoorden;
    //LISTAS
    private List<aseguradoraCliente> clientes;
    private List<Marca> marcas;
    private List<Modelo> modelos;
    private List<Anio> anios;
    private List<colorVehiculo> colores;
    
    @PostConstruct
    public void init(){
        ordentrabajo=new ordenTrabajo();
        marca= new Marca();
        modelo=new Modelo();
        anio= new Anio();
        aseguradoracliente=new aseguradoraCliente();
        colorvehiculo=new colorVehiculo();
        clientes=aseguradoraClienteEJB.findAll();
        marcas=marcaEJB.findAll();
        modelos=modeloEJB.findAll();
        anios=anioEJB.findAll();
        colores=colorvehiculoEJB.findAll();
        disabledcodigo=false;
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

    public String getCodigoot() {
        return codigoot;
    }

    public void setCodigoot(String codigoot) {
        this.codigoot = codigoot;
    }

    public boolean isDisabledcodigo() {
        return disabledcodigo;
    }

    public void setDisabledcodigo(boolean disabledcodigo) {
        this.disabledcodigo = disabledcodigo;
    }

    public int getTipoorden() {
        return tipoorden;
    }

    public void setTipoorden(int tipoorden) {
        this.tipoorden = tipoorden;
    }

    //metodo para mostrar ot a modificar
    public void getOtseguncodigo(AjaxBehaviorEvent e){
        String codigo=this.ordentrabajo.getCodigo();
        
        if(ordenTrabajoEJB.obtenerOt(codigo)==null){
            FacesContext.getCurrentInstance().addMessage("test", new javax.faces.application.FacesMessage("Successful"));
           
        }else{
            this.ordentrabajo=ordenTrabajoEJB.obtenerOt(codigo);
            //Establecer valores guardados en Select y mostrarlos en vista
            if(ordentrabajo.getModelot()!=null){
            this.marca.setId(ordentrabajo.getModelot().getMarca().getId());
            this.modelo.setId(ordentrabajo.getModelot().getId());
            }
            if(ordentrabajo.getAniot()!=null){
               this.anio.setId(ordentrabajo.getAniot().getId());
            }
            this.aseguradoracliente.setId(ordentrabajo.getAseguradoracliente().getId());
            if(ordentrabajo.getColorvehiculo()!=null){
                this.colorvehiculo.setId(ordentrabajo.getColorvehiculo().getId());
            }
            System.out.println("VALOR CODIGO "+codigo.substring(0,4));
            if(codigo.substring(0,4).equals("COMP")){
                this.tipoorden=1;
            }else if(codigo.substring(0,4).equals("PART")){
                this.tipoorden=2;
            }else{
                this.tipoorden=0;
            }
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("principal:tipoord");
           // this.disabledcodigo=true;      
        }
    }
    
    //ACTUALIZAR CODIGO SI SE CAMBIA FECHA AUTORIZADO O PLACA O TIPO DE ORDEN
    public void actualizarCodigoOrden(){
        if(ordentrabajo.getFechaautorizado()!=null){
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            codigoot=df.format(ordentrabajo.getFechaautorizado());
        }else {codigoot=""; }
        
        if(tipoorden==0){ 
            codigoot=ordentrabajo.getPlaca()+"-"+codigoot;
            ordentrabajo.setCodigo(codigoot);
        }else if(tipoorden==1){
            codigoot="COMP-"+ordentrabajo.getPlaca()+"-"+codigoot;
            ordentrabajo.setCodigo(codigoot);
        }else{
            codigoot="PART-"+ordentrabajo.getPlaca()+"-"+codigoot;
            ordentrabajo.setCodigo(codigoot);
            }   
    }
    
    //Metodo para actualizar datos de orden
  public String actualizarOrden(){
    try{
        
    if(ordenTrabajoEJB.obtenerOt(this.ordentrabajo.getCodigo(),"Costos asignados")==null){
        System.out.println("VALOR"+ordenTrabajoEJB.obtenerOt(this.ordentrabajo.getCodigo(),"Costos asignados"));
    if(this.anio.getId()==0){
        this.ordentrabajo.setAniot(null);
    }else{
         this.ordentrabajo.setAniot(anio);
    }
    if(modelo!=null){
        this.ordentrabajo.setModelot(modelo);
    }
    this.ordentrabajo.setAseguradoracliente(aseguradoracliente);
    if(this.colorvehiculo.getId()==0){
        this.ordentrabajo.setColorvehiculo(null);
    }else{
        this.ordentrabajo.setColorvehiculo(colorvehiculo);
    }
    ordenTrabajoEJB.edit(ordentrabajo); 
    
        FacesContext.getCurrentInstance().addMessage(
        null, new FacesMessage("Orden actualizada con Exito "+ordentrabajo.getCodigo()));
 
        FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);
 
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        return view.getViewId() + "?faces-redirect=true&includeViewParams=true";
    }else{
        FacesContext.getCurrentInstance().addMessage(
        null, new FacesMessage("Orden ya tiene costos asignados NO SE PUEDE MODIFICAR"));
 
        FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);
 
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        return view.getViewId() + "?faces-redirect=true&includeViewParams=true";
    
    }
    
    }catch(Exception e){
         FacesContext.getCurrentInstance().addMessage(
        null, new FacesMessage("Ocurrio un Error "+e.getMessage()));
 
        FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);
 
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        return view.getViewId() + "?faces-redirect=true&includeViewParams=true";
    }  
        
   }
    //Cambiar modelos cuando haya un cambio en la marca
     public void cambiarModelos(){
        modelos=marcaEJB.modelosXmarca(marca);
    }
     
    //cancelar actualizacion
    public String cancelarActualizacion(){
     FacesContext.getCurrentInstance().addMessage(
        null, new FacesMessage("Se Cancelo Actualizacion"));
 
        FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);    
         
     UIViewRoot view=FacesContext.getCurrentInstance().getViewRoot();
        return view.getViewId()+"?faces-redirect=true&includeViewParams=true";
     }
     
}
