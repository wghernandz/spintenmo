/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;

import com.spintenmo.ejb.colorVehiculoFacadeLocal;
import com.spintenmo.modelo.colorVehiculo;
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
public class coloresController implements Serializable {
    @EJB
    private colorVehiculoFacadeLocal colorEJB;
    
    private colorVehiculo color;
    
    private List<colorVehiculo> colores;
    private List<colorVehiculo> filteredColores;
    
    @PostConstruct
    public void init(){
        color= new colorVehiculo();
        colores= colorEJB.findAll();
    }

    public colorVehiculoFacadeLocal getColorEJB() {
        return colorEJB;
    }

    public void setColorEJB(colorVehiculoFacadeLocal colorEJB) {
        this.colorEJB = colorEJB;
    }

    public colorVehiculo getColor() {
        return color;
    }

    public void setColor(colorVehiculo color) {
        this.color = color;
    }

    public List<colorVehiculo> getColores() {
        return colores;
    }

    public void setColores(List<colorVehiculo> colores) {
        this.colores = colores;
    }

    public List<colorVehiculo> getFilteredColores() {
        return filteredColores;
    }

    public void setFilteredColores(List<colorVehiculo> filteredColores) {
        this.filteredColores = filteredColores;
    }
 
    public void guardarColor(){
    
         colorEJB.create(this.color);
     
        FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage("Se Guardo el Color "+this.color.getColor()));
        
        init();
    }
    
}
