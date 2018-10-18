/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;

import com.spintenmo.ejb.UsuarioFacadeLocal;
import com.spintenmo.modelo.Roleus;
import com.spintenmo.modelo.Usuario;
import com.spintenmo.modelo.usuarioRole;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class indexController implements Serializable {
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    private Usuario usuario;
    
    @PostConstruct
    public void init(){
        usuario=new Usuario();
    }

    public UsuarioFacadeLocal getUsuarioEJB() {
        return usuarioEJB;
    }

    public void setUsuarioEJB(UsuarioFacadeLocal usuarioEJB) {
        this.usuarioEJB = usuarioEJB;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    //logueo de usuario  
    public String Login(){
        Usuario us;
        usuarioRole role;
        String redireccion=null;
        
        try{
            us=usuarioEJB.Login(usuario);
            if(us!=null){
                role=usuarioEJB.usuarioRole(us);
         
                switch(role.getRoleus().getId()){
                    case 1:
                        redireccion="/operativo/principal.xhtml";
                        break;
                    case 2:
                        redireccion="/operativo/principal.xhtml";
                        break;
                    case 3:
                        redireccion="/gerente/establecimientoCostosmo.xhtml";
                        break;
                    case 4:
                        redireccion="/jefetaller/listaOrdenesporasignar.xhtml";
                        break;
                    case 5:
                        redireccion="/contabilidad/ingresooperarios.xhtml";
                        break;
                    default:
                        redireccion="/operativo/principal.xhtml";
                        break;
                }
                //ABRIR VARIABLE DE SESION
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioactivo",us);
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("mirol", role);
            }else{
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Credenciales Incorrectas :("));
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error"));
        }
        return redireccion;
    }  
}
