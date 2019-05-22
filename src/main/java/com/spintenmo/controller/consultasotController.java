/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;

import com.spintenmo.ejb.operacionesOrdentFacade;
import com.spintenmo.ejb.operacionesOrdentFacadeLocal;
import com.spintenmo.ejb.ordenTrabajoFacadeLocal;
import com.spintenmo.modelo.Aux_detallepagoorden;
import com.spintenmo.modelo.operacionesOrdent;
import com.spintenmo.modelo.ordenTrabajo;
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
public class consultasotController implements Serializable {
    @EJB
    private operacionesOrdentFacadeLocal operacionesordentEJB;
    @EJB
    private ordenTrabajoFacadeLocal ordentrabajoEJB;
    private operacionesOrdent operacionesordent;
    private List<operacionesOrdent> operacionesordentLIST;
    private List<Aux_detallepagoorden> auxdetallepagoordenLIST;
    private List<operacionesOrdent> placasrepLIST;
    private List<ordenTrabajo> ordenestrabajo;
    
    private String placa;
    private String tipoop;
    private String estado;
    private boolean placarep;
    
    @PostConstruct
    public void init(){
        //operacionesordent=new operacionesOrdent();
        //operacionesordentLIST=null;
        //auxdetallepagoordenLIST=null;
        //placasrepLIST=null;
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

    public List<operacionesOrdent> getOperacionesordentLIST() {
        return operacionesordentLIST;
    }

    public void setOperacionesordentLIST(List<operacionesOrdent> operacionesordentLIST) {
        this.operacionesordentLIST = operacionesordentLIST;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public List<Aux_detallepagoorden> getAuxdetallepagoordenLIST() {
        return auxdetallepagoordenLIST;
    }

    public void setAuxdetallepagoordenLIST(List<Aux_detallepagoorden> auxdetallepagoordenLIST) {
        this.auxdetallepagoordenLIST = auxdetallepagoordenLIST;
    }
    
    public void buscarXplaca(){
        
        if(operacionesordentEJB.otpagadaXplaca(this.placa, this.tipoop, this.estado).size()>1 ){
            System.out.println("SIZE "+operacionesordentEJB.otpagadaXplaca(this.placa, this.tipoop, this.estado).size());
            placasrepLIST=operacionesordentEJB.otpagadaXplaca(this.placa, this.tipoop, this.estado);
            placarep=true;
            auxdetallepagoordenLIST=null;
            operacionesordent=new operacionesOrdent();
        } else{
                operacionesordent=operacionesordentEJB.otpagadaPlaca(this.placa, this.tipoop, this.estado);
                placasrepLIST=null;
                placarep=false;
                if (operacionesordent==null){
                auxdetallepagoordenLIST=null;
                
                    FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage("NO SE ENCONTRARON REGISTROS"));
 
                    FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash().setKeepMessages(true);
                }else{
                    System.out.println("VALOR OT "+operacionesordent.getId());
                    auxdetallepagoordenLIST=operacionesordentEJB.otPagadasdetalle(operacionesordent.getOrdentrabajo().getId(),this.tipoop);
                    //operacionesordentLIST=operacionesordentEJB.otpagadaXplaca(this.placa,this.tipoop,this.estado);
                    //operacionesordent=operacionesordentEJB.otpagadaXplaca(placa, tipoop, estado);  
                    }
        }
    }
    
        public void buscarXplaca(operacionesOrdent operacionesot){
                System.out.println("VALOR PASADO"+operacionesot.getId());
                this.operacionesordent=operacionesordentEJB.otpagadaIdorden(operacionesot.getOrdentrabajo().getId(),operacionesot.getTipooperaciones(), operacionesot.getEstado());
                if (this.operacionesordent==null){
                auxdetallepagoordenLIST=null;
                
                    FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage("NO SE ENCONTRARON REGISTROS"));
 
                    FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash().setKeepMessages(true);
                }else{
                    System.out.println("VALOR OT "+this.operacionesordent.getId());
                    auxdetallepagoordenLIST=operacionesordentEJB.otPagadasdetalle(this.operacionesordent.getOrdentrabajo().getId(),this.operacionesordent.getTipooperaciones());
                    //operacionesordentLIST=operacionesordentEJB.otpagadaXplaca(this.placa,this.tipoop,this.estado);
                    //operacionesordent=operacionesordentEJB.otpagadaXplaca(placa, tipoop, estado);  
                    }   
    }

    public String getTipoop() {
        return tipoop;
    }

    public void setTipoop(String tipoop) {
        this.tipoop = tipoop;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<operacionesOrdent> getPlacasrepLIST() {
        return placasrepLIST;
    }

    public void setPlacasrepLIST(List<operacionesOrdent> placasrepLIST) {
        this.placasrepLIST = placasrepLIST;
    }

    public boolean isPlacarep() {
        return placarep;
    }

    public void setPlacarep(boolean placarep) {
        this.placarep = placarep;
    }

    public ordenTrabajoFacadeLocal getOrdentrabajoEJB() {
        return ordentrabajoEJB;
    }

    public void setOrdentrabajoEJB(ordenTrabajoFacadeLocal ordentrabajoEJB) {
        this.ordentrabajoEJB = ordentrabajoEJB;
    }

    public List<ordenTrabajo> getOrdenestrabajo() {
        return ordenestrabajo;
    }

    public void setOrdenestrabajo(List<ordenTrabajo> ordenestrabajo) {
        this.ordenestrabajo = ordenestrabajo;
    }
    
    
    
}
