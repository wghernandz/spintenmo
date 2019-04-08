/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;

import com.spintenmo.ejb.anticipoMoFacadeLocal;
import com.spintenmo.ejb.operacionesOrdentFacadeLocal;
import com.spintenmo.ejb.ordenTrabajoFacadeLocal;
import com.spintenmo.modelo.anticipoMo;
import com.spintenmo.modelo.operacionesOrdent;
import com.spintenmo.modelo.ordenTrabajo;
import com.spintenmo.modelo.planillaMo;
import com.spintenmo.modelo.usuarioRole;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.ValueExpression;
import javax.el.ValueReference;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.component.api.UIColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.ExcelOptions;
import org.primefaces.component.export.PDFOptions;
import org.primefaces.el.ValueExpressionAnalyzer;

@Named
@ViewScoped
public class otapagarPlanilla implements Serializable{
    //EJB
    @EJB
    private operacionesOrdentFacadeLocal operacionesordentEJB;
    @EJB
    private ordenTrabajoFacadeLocal ordentrabajoEJB;
    @EJB
    private anticipoMoFacadeLocal anticipomoEJB;
    //ENTIDADES
    private operacionesOrdent operacionesordent;
    private ordenTrabajo ordentrabajo;
    private anticipoMo anticipomo;
    private usuarioRole role; 
    private BigDecimal sumatoriaanticipos;
    private BigDecimal montop;
    //LISTAS
    private List<operacionesOrdent> otpreplanilla;
    private List<anticipoMo> anticipopreplanilla;
    private List<operacionesOrdent> filteredOtpreplanilla;
    private List<anticipoMo> filteredAnticipopreplanilla;
    private List<anticipoMo> pagosanteriores;
    //variables
    private String valorestado;
    private BigDecimal acumulatorAux= new BigDecimal(0);
    private BigDecimal acumulatorAuxAnt= new BigDecimal(0);
    
    //PARA FORMATEAR EXPORTER
    private ExcelOptions excelOpt;
    private PDFOptions pdfOpt;
    
    @PostConstruct
    public void init(){
        otpreplanilla=operacionesordentEJB.otPreplanilla();
        anticipopreplanilla=operacionesordentEJB.anticipoPreplanilla();
        operacionesordent=new operacionesOrdent();
        ordentrabajo= new ordenTrabajo();
        anticipomo= new anticipoMo();
        valorestado=null;
        
        customizationOptions();
    }

    public operacionesOrdentFacadeLocal getOperacionesordentEJB() {
        return operacionesordentEJB;
    }

    public void setOperacionesordentEJB(operacionesOrdentFacadeLocal operacionesordentEJB) {
        this.operacionesordentEJB = operacionesordentEJB;
    }

    public ordenTrabajoFacadeLocal getOrdentrabajoEJB() {
        return ordentrabajoEJB;
    }

    public void setOrdentrabajoEJB(ordenTrabajoFacadeLocal ordentrabajoEJB) {
        this.ordentrabajoEJB = ordentrabajoEJB;
    }

    public anticipoMoFacadeLocal getAnticipomoEJB() {
        return anticipomoEJB;
    }

    public void setAnticipomoEJB(anticipoMoFacadeLocal anticipomoEJB) {
        this.anticipomoEJB = anticipomoEJB;
    }

    public operacionesOrdent getOperacionesordent() {
        return operacionesordent;
    }

    public void setOperacionesordent(operacionesOrdent operacionesordent) {
        this.operacionesordent = operacionesordent;
        
        //verificar que estado debe tomar el select de acuerdo a si la orden es complemento o no
        int resultado=operacionesordent.getMontomo().compareTo(operacionesordent.getMontopendiente());
        if(resultado==1){
            this.setValorestado("Complemento");            
        }else{
            this.setValorestado("Operario Asignado");
        }        
    }

    public ordenTrabajo getOrdentrabajo() {
        return ordentrabajo;
    }

    public void setOrdentrabajo(ordenTrabajo ordentrabajo) {
        this.ordentrabajo = ordentrabajo;
    }

    public anticipoMo getAnticipomo() {
        return anticipomo;
    }

    public void setAnticipomo(anticipoMo anticipomo) {
        this.anticipomo = anticipomo;
    }

    public List<operacionesOrdent> getOtpreplanilla() {
        return otpreplanilla;
    }

    public void setOtpreplanilla(List<operacionesOrdent> otpreplanilla) {
        this.otpreplanilla = otpreplanilla;
    }

    public List<anticipoMo> getAnticipopreplanilla() {
        return anticipopreplanilla;
    }

    public void setAnticipopreplanilla(List<anticipoMo> anticipopreplanilla) {
        this.anticipopreplanilla = anticipopreplanilla;
    }

    public List<operacionesOrdent> getFilteredOtpreplanilla() {
        return filteredOtpreplanilla;
    }

    public void setFilteredOtpreplanilla(List<operacionesOrdent> filteredOtpreplanilla) {
        this.filteredOtpreplanilla = filteredOtpreplanilla;
    }

    public List<anticipoMo> getFilteredAnticipopreplanilla() {
        return filteredAnticipopreplanilla;
    }

    public void setFilteredAnticipopreplanilla(List<anticipoMo> filteredAnticipopreplanilla) {
        this.filteredAnticipopreplanilla = filteredAnticipopreplanilla;
    }

    public String getValorestado() {
        return valorestado;
    }

    public void setValorestado(String valorestado) {
        this.valorestado = valorestado;
    }

    public BigDecimal getAcumulatorAux() {
        return acumulatorAux;
    }

    public void setAcumulatorAux(BigDecimal acumulatorAux) {
        this.acumulatorAux = acumulatorAux;
    }

    public BigDecimal getAcumulatorAuxAnt() {
        return acumulatorAuxAnt;
    }

    public void setAcumulatorAuxAnt(BigDecimal acumulatorAuxAnt) {
        this.acumulatorAuxAnt = acumulatorAuxAnt;
    }

    public ExcelOptions getExcelOpt() {
        return excelOpt;
    }

    public void setExcelOpt(ExcelOptions excelOpt) {
        this.excelOpt = excelOpt;
    }

    public PDFOptions getPdfOpt() {
        return pdfOpt;
    }

    public void setPdfOpt(PDFOptions pdfOpt) {
        this.pdfOpt = pdfOpt;
    }

    public usuarioRole getRole() {
        return role;
    }

    public void setRole(usuarioRole role) {
        this.role = role;
    }

    public BigDecimal getSumatoriaanticipos() {
        return sumatoriaanticipos;
    }

    public void setSumatoriaanticipos(BigDecimal sumatoriaanticipos) {
        this.sumatoriaanticipos = sumatoriaanticipos;
    }

    public BigDecimal getMontop() {
        return montop;
    }

    public void setMontop(BigDecimal montop) {
        this.montop = montop;
    }

    public List<anticipoMo> getPagosanteriores() {
        return pagosanteriores;
    }

    public void setPagosanteriores(List<anticipoMo> pagosanteriores) {
        this.pagosanteriores = pagosanteriores;
    }
    
    //Modificar Anticipo antes de la aplicacion correspondiente
    public void modificarAnticipomo(){
        //actualizar anticipo
        this.anticipomo.setOperacionesordent(anticipomo.getOperacionesordent());
        anticipomoEJB.edit(anticipomo);
        //actualizar monto planilla
        this.operacionesordent=anticipomo.getOperacionesordent();
        this.operacionesordent.setMontoplanilla(anticipomo.getMontoanticipo());
        operacionesordentEJB.edit(operacionesordent);
        
    }
    
    //Cambiar estado Finalizado a Operario asignado o complemento
    public void cambiarEstado(){
        //setear fechaterminado y montoplanilla a null
        this.operacionesordent.setMontoplanilla(null);
        this.operacionesordent.setFechaterminado(null);
        operacionesordentEJB.edit(operacionesordent);
        init();
    }
    
    public void establecerOperacion(anticipoMo anticipomo){
        //establecer entidades cuando se cambie de estado anticipo a estado anterior
        this.setOperacionesordent(anticipomo.getOperacionesordent());
        this.setAnticipomo(anticipomo);
    
    }
    //Cambiar estado Anticipo a Operario asignado o complemento
    public void cambiarEstadoAnticipo(){
        //eliminar anticipo registrado
        anticipomoEJB.remove(anticipomo);
        //actualizar operacionesordent
        this.operacionesordent=anticipomo.getOperacionesordent();
        this.operacionesordent.setFechaanticipo(null);
        this.operacionesordent.setMontoplanilla(null);  
        operacionesordentEJB.edit(operacionesordent);
        init();
    }  
   //obtener montos pendientes acumulados planilla
    public BigDecimal getAcumulatedValue() {
        BigDecimal aux = this.acumulatorAux ;
        this.acumulatorAux = new BigDecimal(0);
        return aux;
    }
    //acumular montos pendientes
    public void acumulateValue(operacionesOrdent ot)
    { 
        this.acumulatorAux=this.acumulatorAux.add(ot.getMontopendiente());
    }
    //obtener anticipos acumulados
    public BigDecimal getAcumulatedValueAnt(){
        BigDecimal aux2=this.acumulatorAuxAnt;
        this.acumulatorAuxAnt=new BigDecimal(0);
        return aux2;
    }
    //acumular montos anticipos
    public void acumulateValueAnt(anticipoMo anticipo){
        this.acumulatorAuxAnt=this.acumulatorAuxAnt.add(anticipo.getMontoanticipo());
    }
    
    //FORMATO PARA LOS ARCHIVOS PDF A EXPORTAR
        public void customizationOptions() {
        excelOpt = new ExcelOptions();
        excelOpt.setFacetBgColor("#F88017");
        excelOpt.setFacetFontSize("10");
        excelOpt.setFacetFontColor("#0000ff");
        excelOpt.setFacetFontStyle("BOLD");
        excelOpt.setCellFontColor("#00ff00");
        excelOpt.setCellFontSize("8");
         
        pdfOpt = new PDFOptions();
        pdfOpt.setFacetBgColor("#F88017");
        pdfOpt.setFacetFontStyle("BOLD");
        pdfOpt.setCellFontSize("8");
        pdfOpt.setFacetFontSize("10");
    }
    
      public int obtenerRole(){
        FacesContext context = FacesContext.getCurrentInstance();
        this.role= (usuarioRole) context.getExternalContext().getSessionMap().get("mirol");
        return this.role.getId();
    }
    
        public void historialant(operacionesOrdent idopt){
        this.setOperacionesordent(operacionesordentEJB.find(idopt));
        pagosanteriores=anticipomoEJB.anticipoPorOrden(idopt);
        //sumar anticipos
        this.setSumatoriaanticipos(new BigDecimal(0));
        for(int indice = 0;indice<pagosanteriores.size();indice++)
        {
            this.sumatoriaanticipos=this.sumatoriaanticipos.add(pagosanteriores.get(indice).getMontoanticipo());
        }
            this.montop=this.getOperacionesordent().getMontomo().subtract(this.sumatoriaanticipos).setScale(2, RoundingMode.HALF_UP);
        //pagosanteriores=operacionesordentEJB.otpagadaXplaca(placa, tipoop, "Pagada");
    }
   
}
