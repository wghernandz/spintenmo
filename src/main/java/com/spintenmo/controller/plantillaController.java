/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;

import com.spintenmo.ejb.UsuarioFacadeLocal;
import com.spintenmo.modelo.Usuario;
import com.spintenmo.modelo.usuarioRole;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import static org.primefaces.component.focus.Focus.PropertyKeys.context;

@Named
@SessionScoped
public class plantillaController implements Serializable{
    
    private Usuario us;
    private usuarioRole role;

    public Usuario getUs() {
        return us;
    }

    public void setUs(Usuario us) {
        this.us = us;
    }

    public usuarioRole getRole() {
        return role;
    }

    public void setRole(usuarioRole role) {
        this.role = role;
    }
    
    public void verificarSession(){
    try{   
    FacesContext context = FacesContext.getCurrentInstance();
    us=(Usuario) context.getExternalContext().getSessionMap().get("usuarioactivo");
        if(us==null){
            context.getExternalContext().redirect("../index.xhtml");
        }
    }catch(Exception e){
        
    }   
      
    }
    
    public void cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    public int obtenerRole(){
        FacesContext context = FacesContext.getCurrentInstance();
        this.role= (usuarioRole) context.getExternalContext().getSessionMap().get("mirol");
        return this.role.getId();
    }
}
    
