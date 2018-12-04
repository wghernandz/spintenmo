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
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.component.datatable.DataTable;

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
    
    //LISTAS
     List<ordenTrabajo> ordenesnoasignadas;
     List<ordenTrabajo> filteredOrdenes;
     List<aseguradoraCliente> clientes;
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
        operacionesordent=new operacionesOrdent();
        //ordentrabajo=new ordenTrabajo();
        clientes=aseguradoraclienteEJB.findAll();
        marcas=marcaEJB.findAll();
        modelos=modeloEJB.findAll();
        colores=colorEJB.findAll();
        anios=anioEJB.findAll();
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
        //System.out.println("FILTRAMOS LA TABLA");
        DataTable table = (DataTable) event.getSource();
        
        List<ordenTrabajo> obj = table.getFilteredValue();
        
        //this.ordenesnoasignadas = obj;
        this.setOrdenesnoasignadas(obj);
        if (obj != null) {
            System.out.println("filtered = " + obj.size());
            System.out.println("probando"+obj.get(0).getCodigo());
            System.out.println("probando"+obj.get(2).getCodigo());
            System.out.println("probando"+obj.get(3).getCodigo());
        } else {
            System.out.println("No records found");
        }
        Map<String, Object> filters = table.getFilters();
        ArrayList valores= new ArrayList(filters.values());
        
        
        System.out.println("VALOR FILTRADO"+valores.get(0).toString());
         System.out.println("VALOR FILTRADO"+table.getColumns().get(0).getColumnKey());
        System.out.println("VALOR FILTRADO"+table.getColumns().get(0).getField());
         
        Iterator it = filters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
        System.out.println(e.getKey() + " " + e.getValue());
           
        System.out.println("ordenTrabajo filtrado"+e.getValue());  
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
   
}
