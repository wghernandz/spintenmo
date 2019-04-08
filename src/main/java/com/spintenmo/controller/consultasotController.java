/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;

import com.spintenmo.ejb.operacionesOrdentFacade;
import com.spintenmo.ejb.operacionesOrdentFacadeLocal;
import com.spintenmo.modelo.operacionesOrdent;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class consultasotController implements Serializable {
    @EJB
    private operacionesOrdentFacadeLocal operacionesordentEJB;
    private operacionesOrdent operacionesordent;
    private List<operacionesOrdent> operacionesordentLIST;
    
    private String placa;
    private String tipoop;
    private String estado;
    
    @PostConstruct
    public void init(){
        operacionesordent=new operacionesOrdent();
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
    
    public void buscarXplaca(){
        operacionesordentLIST=operacionesordentEJB.otpagadaXplaca(this.placa,this.tipoop,this.estado);
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
    
}
