/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;

import com.spintenmo.ejb.operacionesOrdentFacadeLocal;
import com.spintenmo.modelo.Persona;
import com.spintenmo.modelo.empleadoMo;
import com.spintenmo.modelo.operacionesOrdent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.component.export.ExcelOptions;
import org.primefaces.component.export.PDFOptions;

@Named
@ViewScoped
public class costosmoAsignadoController implements Serializable {
    @EJB
    private operacionesOrdentFacadeLocal operacionesordentEJB;
    
    //Listas
    private List<operacionesOrdent> listaotcosteadas;
   // private List<empleadoMo> empleadosmo;
    //Entidades
    private operacionesOrdent operacionesordent;
    private empleadoMo empleadomo;
    private Persona persona;
    
        //PARA FORMATEAR EXPORTER
    private ExcelOptions excelOpt;
    private PDFOptions pdfOpt;
    
    @PostConstruct
    public void init(){
        operacionesordent= new operacionesOrdent();
        listaotcosteadas= operacionesordentEJB.otPorasignar(operacionesordent);
        empleadomo= new empleadoMo();
        persona= new Persona();
    
        customizationOptions();
    }

    public operacionesOrdentFacadeLocal getOperacionesordentEJB() {
        return operacionesordentEJB;
    }

    public void setOperacionesordentEJB(operacionesOrdentFacadeLocal operacionesordentEJB) {
        this.operacionesordentEJB = operacionesordentEJB;
    }

    public List<operacionesOrdent> getListaotcosteadas() {
        return listaotcosteadas;
    }

    public void setListaotcosteadas(List<operacionesOrdent> listaotcosteadas) {
        this.listaotcosteadas = listaotcosteadas;
    }

    public operacionesOrdent getOperacionesordent() {
        return operacionesordent;
    }

    public void setOperacionesordent(operacionesOrdent operacionesordent) {
        this.operacionesordent = operacionesordent;
    }

    public empleadoMo getEmpleadomo() {
        return empleadomo;
    }

    public void setEmpleadomo(empleadoMo empleadomo) {
        this.empleadomo = empleadomo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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
    
    //Modificar valores minimos y maximos
    public void modificarMonto(){
        operacionesOrdent operacionesordent2=operacionesordentEJB.find(operacionesordent.getId());
        if ("Costo asignado".equals(operacionesordent2.getEstado())){
            operacionesordentEJB.edit(operacionesordent);
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("SE MODIFICO LA ORDEN"+operacionesordent.getOrdentrabajo().getCodigo()));
            init();
        }else{
            FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage("LA ORDEN " + operacionesordent.getOrdentrabajo().getCodigo()+"YA TIENE COSTO NEGOCIADO"));
        }
    }
    
    
     //validar monto minimo contra monto maximo ENDEREZADO
   public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Object otherValue = component.getAttributes().get("montomin");
        
        if(otherValue!=null){
        BigDecimal monto=((BigDecimal)value);
        BigDecimal montopendiente=(BigDecimal)otherValue;
        if (monto.compareTo(montopendiente) < 0) {
            throw new ValidatorException(new FacesMessage("Anticipo No puede ser mayor a "+montopendiente));
        }
    }
   }
   
       //validar monto minimo contra monto maximo PINTURA
   public void validatepintura(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Object otherValue = component.getAttributes().get("montominp");
        
        if(otherValue!=null){
        BigDecimal monto=((BigDecimal)value);
        BigDecimal montopendiente=(BigDecimal)otherValue;
        if (monto.compareTo(montopendiente) < 0) {
            throw new ValidatorException(new FacesMessage("Anticipo No puede ser mayor a "+montopendiente));
        }
    }
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
   
        
}
