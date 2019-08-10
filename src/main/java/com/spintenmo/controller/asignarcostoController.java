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
import com.spintenmo.ejb.operacionesOrdentFacadeLocal;
import com.spintenmo.ejb.ordenTrabajoFacadeLocal;
import com.spintenmo.modelo.Anio;
import com.spintenmo.modelo.Marca;
import com.spintenmo.modelo.Modelo;
import com.spintenmo.modelo.aseguradoraCliente;
import com.spintenmo.modelo.colorVehiculo;
import com.spintenmo.modelo.operacionesOrdent;
import com.spintenmo.modelo.ordenTrabajo;
import com.spintenmo.modelo.usuarioRole;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

@Named
@SessionScoped
public class asignarcostoController implements Serializable {
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
    private colorVehiculoFacadeLocal colorEJB;
    @EJB
    private AnioFacadeLocal anioEJB;
    //@Inject
    //private asignarcostoController costocontroller; 
    //ENTIDADES
    private ordenTrabajo ordentrabajo;
    private operacionesOrdent operacionesordent;
    private operacionesOrdent operacionesordentpintura;
    private aseguradoraCliente aseguradoracliente;
    private Marca marca;
    private Modelo modelo;
    private usuarioRole role;
    private String codigoot;
    private String estadoprevio;
    private String trabajotaller;

    
    //LISTAS
     List<ordenTrabajo> ordenesnoasignadas;
     List<ordenTrabajo> filteredOrdenes;
     List<aseguradoraCliente> clientes;
     List<operacionesOrdent> ottodas;
     List<operacionesOrdent> filteredottodas;
     List<Marca> marcas;
     List<Modelo> modelos;
     List<colorVehiculo> colores;
     List<Anio> anios;
     
    //variables
    private BigDecimal maxpintura;
    private BigDecimal minpintura;
    DataTable theDataTable = new DataTable();
    private Map<String, Serializable> filterValues = new HashMap<>();
 
    @PostConstruct
    public void init(){
        ordenesnoasignadas=ordentrabajoEJB.otIngresadas();
        //filteredOrdenes=ordentrabajoEJB.otIngresadas();
        //operacionesordent=new operacionesOrdent();
        //ordentrabajo=new ordenTrabajo();
        //aseguradoracliente=new aseguradoraCliente();
        clientes=aseguradoraclienteEJB.findAll();
        marcas=marcaEJB.findAll();
        modelos=modeloEJB.findAll();
        colores=colorEJB.findAll();
        anios=anioEJB.findAll();
        marca=new Marca();
        modelo=new Modelo();
        ordentrabajo=new ordenTrabajo();
        ottodas=operacionesordentEJB.otEstadosvarios();
        aseguradoracliente= new aseguradoraCliente();
        //trabajotaller="MECANICA";
    }

    public void refrescarTabla(){
        ordenesnoasignadas=ordentrabajoEJB.otIngresadas();
        ottodas=operacionesordentEJB.otEstadosvarios();
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

    public List<ordenTrabajo> getOrdenesnoasignadas() {
        return ordenesnoasignadas;
    }

    public void setOrdenesnoasignadas(List<ordenTrabajo> ordenesnoasignadas) {
        this.ordenesnoasignadas = ordenesnoasignadas;
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

    public operacionesOrdent getOperacionesordentpintura() {
        return operacionesordentpintura;
    }

    public void setOperacionesordentpintura(operacionesOrdent operacionesordentpintura) {
        this.operacionesordentpintura = operacionesordentpintura;
    }

    public BigDecimal getMaxpintura() {
        return maxpintura;
    }

    public void setMaxpintura(BigDecimal maxpintura) {
        this.maxpintura = maxpintura;
    }

    public BigDecimal getMinpintura() {
        return minpintura;
    }

    public void setMinpintura(BigDecimal minpintura) {
        this.minpintura = minpintura;
    }     

    public List<ordenTrabajo> getFilteredOrdenes() {
        return filteredOrdenes;
    }

    public void setFilteredOrdenes(List<ordenTrabajo> filteredOrdenes) {
        this.filteredOrdenes = filteredOrdenes;      
    }

    public DataTable getTheDataTable() {
        return theDataTable;
    }

    public void setTheDataTable(DataTable theDataTable) {
        this.theDataTable = theDataTable;
    }

    public Map<String, Serializable> getFilterValues() {
        return filterValues;
    }

    public void setFilterValues(Map<String, Serializable> filterValues) {
        this.filterValues = filterValues;
    }

    public usuarioRole getRole() {
        return role;
    }

    public void setRole(usuarioRole role) {
        this.role = role;
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

    public List<operacionesOrdent> getOttodas() {
        return ottodas;
    }

    public void setOttodas(List<operacionesOrdent> ottodas) {
        this.ottodas = ottodas;
    }

    public List<operacionesOrdent> getFilteredottodas() {
        return filteredottodas;
    }

    public void setFilteredottodas(List<operacionesOrdent> filteredottodas) {
        this.filteredottodas = filteredottodas;
    }

    public String getEstadoprevio() {
        return estadoprevio;
    }

    public void setEstadoprevio(String estadoprevio) {
        this.estadoprevio = estadoprevio;
    }
    
    public String registrarMaxminpintura(){
        //guardar valores para pintura en variables auxiliares
       // maxpintura=this.operacionesordent.getMontomaxp();
       // minpintura=this.operacionesordent.getMontominp();
        
        //guardar datos iniciales operaciones enderezado
        this.operacionesordent.setOrdentrabajo(ordentrabajo);
        this.operacionesordent.setEmpleadomo(null);
        this.operacionesordent.setMontomax(null);
        this.operacionesordent.setMontomin(null);
        operacionesordentEJB.create(operacionesordent);
        //guardar datos iniciales operaciones pintura
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        return view.getViewId() + "?faces-redirect=true";
    }
    
    public void obtenerMinMax(){
        operacionesordent=operacionesordentEJB.valoresMinMax(this.ordentrabajo);
    }
    
    public void obtenerOtseleccionada(ordenTrabajo orden){
            this.ordentrabajo=orden;
    }  

    //pendiente //no utilizado actualmente
   public List<ordenTrabajo> onFilter(AjaxBehaviorEvent event) {
        DataTable table = (DataTable) event.getSource();
        
        List<ordenTrabajo> obj = table.getFilteredValue();
        
        //this.ordenesnoasignadas = obj;
        this.setOrdenesnoasignadas(obj);
        if (obj != null) {
            System.out.println("filtered = " + obj.size());
           
        } else {
            System.out.println("No records found");
        }
        Map<String, Object> filters = table.getFilters();
        ArrayList valores= new ArrayList(filters.values());
       
         
        Iterator it = filters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
        System.out.println(e.getKey() + " " + e.getValue());
           
        
        }
        return obj;
    }

    public aseguradoraClienteFacadeLocal getAseguradoraclienteEJB() {
        return aseguradoraclienteEJB;
    }

    public void setAseguradoraclienteEJB(aseguradoraClienteFacadeLocal aseguradoraclienteEJB) {
        this.aseguradoraclienteEJB = aseguradoraclienteEJB;
    }

    public List<aseguradoraCliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<aseguradoraCliente> clientes) {
        this.clientes = clientes;
    }

    public MarcaFacadeLocal getMarcaEJB() {
        return marcaEJB;
    }

    public void setMarcaEJB(MarcaFacadeLocal marcaEJB) {
        this.marcaEJB = marcaEJB;
    }

    public ModeloFacadeLocal getModeloEJB() {
        return modeloEJB;
    }

    public void setModeloEJB(ModeloFacadeLocal modeloEJB) {
        this.modeloEJB = modeloEJB;
    }

    public colorVehiculoFacadeLocal getColorEJB() {
        return colorEJB;
    }

    public void setColorEJB(colorVehiculoFacadeLocal colorEJB) {
        this.colorEJB = colorEJB;
    }

    public AnioFacadeLocal getAnioEJB() {
        return anioEJB;
    }

    public void setAnioEJB(AnioFacadeLocal anioEJB) {
        this.anioEJB = anioEJB;
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

    public List<colorVehiculo> getColores() {
        return colores;
    }

    public void setColores(List<colorVehiculo> colores) {
        this.colores = colores;
    }

    public List<Anio> getAnios() {
        return anios;
    }

    public void setAnios(List<Anio> anios) {
        this.anios = anios;
    }

    public String getCodigoot() {
        return codigoot;
    }

    public void setCodigoot(String codigoot) {
        this.codigoot = codigoot;
    }

    public int obtenerRole(){
        FacesContext context = FacesContext.getCurrentInstance();
        this.role= (usuarioRole) context.getExternalContext().getSessionMap().get("mirol");
        return this.role.getId();
    }
     
    public void cambiarModelos(){
         modelos=marcaEJB.modelosXmarca(marca);  
    }

    public String getTrabajotaller() {
        return trabajotaller;
    }

    public void setTrabajotaller(String trabajotaller) {
        this.trabajotaller = trabajotaller;
    }
    
     public void asignarCodigoOt(){ 
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        codigoot=df.format(new Date());
        
        codigoot=this.trabajotaller+"-"+ordentrabajo.getPlaca()+"-"+codigoot;
        
        ordentrabajo.setCodigo(codigoot);
    }
     
    public void ingresarOtmecanica(){
         
         this.aseguradoracliente=aseguradoraclienteEJB.clienteSegunNombre(trabajotaller);
        
         this.ordentrabajo.setAseguradoracliente(this.aseguradoracliente);
         this.ordentrabajo.setEsreclamo("No");
         if(this.getModelo()!=null){
             this.ordentrabajo.setModelot(this.modelo);
         }else{
             this.ordentrabajo.setModelot(null);
         }
         this.ordentrabajo.setCodigo(codigoot);

         this.ordentrabajo.setEstado("Costos asignados");
         this.ordentrabajo.setFechaautorizado(new Date());
         this.ordentrabajo.setFechaingreso(new Date());
 
         this.ordentrabajo.setTipoorden(0);
         ordentrabajoEJB.create(this.ordentrabajo);
              
         operacionesOrdent nuevoop = new operacionesOrdent();
         nuevoop.setEstado("Costo asignado");
         nuevoop.setFechavalorizado(new Date());
         if("enderezado".equals(this.operacionesordent.getTipooperaciones())){
            nuevoop.setMontomin(this.operacionesordent.getMontomo());
            nuevoop.setMontomax(this.operacionesordent.getMontomo());
         }else{
            nuevoop.setMontominp(null);
            nuevoop.setMontomaxp(null);
         }
         if("pintura".equals(this.operacionesordent.getTipooperaciones())){
            nuevoop.setMontominp(this.operacionesordent.getMontomo());
            nuevoop.setMontomaxp(this.operacionesordent.getMontomo());
         }else{
            nuevoop.setMontomin(this.operacionesordent.getMontomo());
            nuevoop.setMontomax(this.operacionesordent.getMontomo());
         }
         
         //nuevoop.setMontomo(this.operacionesordent.getMontomo());
         //nuevoop.setMontopendiente(this.operacionesordent.getMontomo());
         nuevoop.setOrdentrabajo(this.ordentrabajo);
         nuevoop.setTipooperaciones(this.operacionesordent.getTipooperaciones());
         operacionesordentEJB.create(nuevoop);
         
         
          FacesContext.getCurrentInstance().addMessage(
        null, new FacesMessage("SE AGREGO TRABAJO DE MECANICA"));
 
        FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);
        
        this.ordentrabajo=new ordenTrabajo();
        this.operacionesordent=new operacionesOrdent();
        
    }
    
    public String cancelar(){
        this.ordentrabajo=new ordenTrabajo();
        this.operacionesordent=new operacionesOrdent();
        
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        return view.getViewId() + "?faces-redirect=true";
    }
    
       public void cambiarMontoneg(){
        
            operacionesordent.setMontomo(this.operacionesordent.getMontomo());
            operacionesordent.setMontopendiente(this.operacionesordent.getMontomo());
            operacionesordent.setMontoplanilla(this.operacionesordent.getMontomo());
            operacionesordent.setEstado(this.operacionesordent.getEstado());
            operacionesordentEJB.edit(operacionesordent);
            init();        
   }
       
       public void crearOperacionot(){       
           operacionesordent=new operacionesOrdent();
           this.aseguradoracliente=new aseguradoraCliente();
           marca=new Marca();
           modelo=new Modelo();       
    }
       
       public void cambiarMontomax(){
           operacionesordent.setMontomax(this.operacionesordent.getMontomax());
           operacionesordent.setMontomin(this.operacionesordent.getMontomax());
           operacionesordent.setMontomaxp(this.operacionesordent.getMontomaxp());
           operacionesordent.setMontominp(this.operacionesordent.getMontomaxp());
           operacionesordent.setEstado(this.operacionesordent.getEstado());
           operacionesordentEJB.edit(operacionesordent);
           init();
       }
}
