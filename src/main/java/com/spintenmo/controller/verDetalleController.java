/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;

import com.lowagie.text.pdf.AcroFields.Item;
import com.spintenmo.ejb.planillaMoFacadeLocal;
import com.spintenmo.modelo.anticipoMo;
import com.spintenmo.modelo.planillaMo;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import net.sf.jasperreports.engine.JRException;

@Named
@ViewScoped
public class verDetalleController implements Serializable{
    @EJB
    private planillaMoFacadeLocal planillamoEJB;
    @Inject
    private imprimirPlanillaController imprimirplanilla;
    
    private planillaMo planillamo;
    
    //Listas
    private List<planillaMo> planillaspagadas;
    private List<anticipoMo> anticipospagados;
    
    private BigDecimal suma;
    
    @PostConstruct
    public void init(){
        planillamo= new planillaMo();
        this.planillaspagadas=imprimirplanilla.getPlanillaspagadasdetalle();
         
        this.suma=this.sumatoriaMontopagado();
    }

    public planillaMoFacadeLocal getPlanillamoEJB() {
        return planillamoEJB;
    }

    public void setPlanillamoEJB(planillaMoFacadeLocal planillamoEJB) {
        this.planillamoEJB = planillamoEJB;
    }

    public planillaMo getPlanillamo() {
        return planillamo;
    }

    public void setPlanillamo(planillaMo planillamo) {
        this.planillamo = planillamo;
    }

    public List<planillaMo> getPlanillaspagadas() {
        return planillaspagadas;
    }

    public void setPlanillaspagadas(List<planillaMo> planillaspagadas) {
        this.planillaspagadas = planillaspagadas;
    }

    public List<anticipoMo> getAnticipospagados() {
        return anticipospagados;
    }

    public void setAnticipospagados(List<anticipoMo> anticipospagados) {
        this.anticipospagados = anticipospagados;
    }

    public BigDecimal getSuma() {
        return suma;
    }

    public void setSuma(BigDecimal suma) {
        this.suma = suma;
    }

    public imprimirPlanillaController getImprimirplanilla() {
        return imprimirplanilla;
    }

    public void setImprimirplanilla(imprimirPlanillaController imprimirplanilla) {
        this.imprimirplanilla = imprimirplanilla;
    }
    
    public BigDecimal sumatoriaMontopagado(){
      suma= new BigDecimal(0);
     
    Iterator<planillaMo> it = planillaspagadas.iterator();
        while(it.hasNext()){
        planillaMo item=it.next();
       suma=suma.add((BigDecimal)item.getMontoapagar());
    }
     return suma;
    }

}