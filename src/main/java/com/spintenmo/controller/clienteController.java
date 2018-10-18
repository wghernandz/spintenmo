/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;

import com.spintenmo.ejb.aseguradoraClienteFacadeLocal;
import com.spintenmo.modelo.aseguradoraCliente;
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
public class clienteController implements Serializable{
    @EJB
    private aseguradoraClienteFacadeLocal clienteEJB;
    
    private List<aseguradoraCliente> aseguradoraclienteLIST;
    
    private aseguradoraCliente aseguradoracliente;
    private boolean desactivar;
    
    @PostConstruct
    public void init(){
        aseguradoraclienteLIST = clienteEJB.findAll();
        aseguradoracliente = new aseguradoraCliente();
    }

    public aseguradoraClienteFacadeLocal getClienteEJB() {
        return clienteEJB;
    }

    public void setClienteEJB(aseguradoraClienteFacadeLocal clienteEJB) {
        this.clienteEJB = clienteEJB;
    }

    public List<aseguradoraCliente> getAseguradoraclienteLIST() {
        return aseguradoraclienteLIST;
    }

    public void setAseguradoraclienteLIST(List<aseguradoraCliente> aseguradoraclienteLIST) {
        this.aseguradoraclienteLIST = aseguradoraclienteLIST;
    }

    public aseguradoraCliente getAseguradoracliente() {
        return aseguradoracliente;
    }

    public void setAseguradoracliente(aseguradoraCliente aseguradoracliente) {
        this.aseguradoracliente = aseguradoracliente;
    }

    public boolean isDesactivar() {
        return desactivar;
    }

    public void setDesactivar(boolean desactivar) {
        this.desactivar = desactivar;
    }
    
    public void guardarCliente(){
        if("".equals(this.aseguradoracliente.getTelefono1())){
            this.aseguradoracliente.setTelefono1(null);
        }
        if("".equals(this.aseguradoracliente.getTelefono2())){
            this.aseguradoracliente.setTelefono2(null);
        }
        
        clienteEJB.create(aseguradoracliente);
        
        FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage("Se Guardo el cliente "+this.aseguradoracliente.getNombrecliente()));
        
        init();
        this.desactivar=true;
    }
    
    public String nuevo(){
       this.desactivar=true;
       init();
       UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
       return view.getViewId() + "?faces-redirect=true";
    }
    
}
