/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;

import com.spintenmo.ejb.MarcaFacadeLocal;
import com.spintenmo.ejb.ModeloFacadeLocal;
import com.spintenmo.ejb.operacionesOrdentFacadeLocal;
import com.spintenmo.ejb.ordenTrabajoFacadeLocal;
import com.spintenmo.modelo.Marca;
import com.spintenmo.modelo.Modelo;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class vehiculosController implements Serializable{
    @EJB
    private MarcaFacadeLocal marcaEJB;
    @EJB
    private ModeloFacadeLocal modeloEJB;
    @EJB
    private operacionesOrdentFacadeLocal operacionesordentEJB;
    
    private Marca marca;
    private Modelo modelo;
    
    private List<Marca> marcas;
    private List<Modelo> modelos;
    private List<Modelo> filteredModelos;
    
    @PostConstruct
    public void init(){
        marcas=marcaEJB.findAll();
        modelos=modeloEJB.findAll();
        marca=new Marca();
        modelo= new Modelo();
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
        this.marca=modelo.getMarca();
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

    public List<Modelo> getFilteredModelos() {
        return filteredModelos;
    }

    public void setFilteredModelos(List<Modelo> filteredModelos) {
        this.filteredModelos = filteredModelos;
    }

    public operacionesOrdentFacadeLocal getOperacionesordentEJB() {
        return operacionesordentEJB;
    }

    public void setOperacionesordentEJB(operacionesOrdentFacadeLocal operacionesordentEJB) {
        this.operacionesordentEJB = operacionesordentEJB;
    }
    
    public void guardarMarca(){
        marcaEJB.create(this.marca);
         
        FacesContext.getCurrentInstance().addMessage(null,
         new FacesMessage("Se Guardo la marca "+this.marca.getNombremarca())); 
        
        init();
    }
    
    public void guardarModelo(){
        if(this.marca==null){
            FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage("Selecciona la marca"));
        
        }else{
        this.modelo.setMarca(this.marca);
        
        modeloEJB.create(this.modelo);
     
        FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage("Se Guardo el Modelo "+this.modelo.getNombremodelo()));
        
        init();
        }
    }
    
    public void modModelo(){
        marca=marcaEJB.find(this.marca.getId());
        this.modelo.setMarca(marca);
        if(operacionesordentEJB.optSegunModelo(this.modelo.getId(), "Pagada").isEmpty()==true){
            modeloEJB.edit(this.modelo);
            init();
        FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage("Modelo modificado "));
        } else
        {
            FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage("No se puede modificar modelo, ya posee registros cancelados. "));
        }
        
        
    }
    
    public void cancelar(){
        init();
    }
}
