/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.spintenmo.ejb.operacionesOrdentFacadeLocal;
import com.spintenmo.ejb.ordenTrabajoFacadeLocal;
import com.spintenmo.modelo.Persona;
import com.spintenmo.modelo.empleadoMo;
import com.spintenmo.modelo.operacionesOrdent;
import com.spintenmo.modelo.ordenTrabajo;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.component.export.ExcelOptions;
import org.primefaces.component.export.PDFOptions;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class otPorasignarController implements Serializable {
    @EJB
    private operacionesOrdentFacadeLocal operacionesordentEJB;
    @EJB
    private ordenTrabajoFacadeLocal ordentrabajoEJB;
    //LISTAS
    List<operacionesOrdent> opporasignar;
    List<empleadoMo> empleadosmo;
    List<operacionesOrdent> filteredOpporasignar;
    
    //ENTIDADES
    private operacionesOrdent operacionesordent;
    private empleadoMo empleadomo;
    private Persona persona;
    private ordenTrabajo ordentrabajo;
    
    //PARA FORMATEAR EXPORTER
    private ExcelOptions excelOpt;
    private PDFOptions pdfOpt;
    
    private String valorestado;
    @PostConstruct
    public void init(){
        opporasignar=operacionesordentEJB.otPorasignar(operacionesordent);
        //operacionesordent= new operacionesOrdent();
        empleadomo= new empleadoMo();
        persona= new Persona();
        //ordentrabajo= new ordenTrabajo();
        customizationOptions();
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
        empleadosmo=operacionesordentEJB.operarioSegunop(operacionesordent);
    }
    //set adicional para cuando se requiera cambiar el estado
    public void setOperacionesordentEstado(operacionesOrdent operacionesordent) {
        this.operacionesordent = operacionesordent;
        this.setValorestado("Costo no asignado");
    }

    public empleadoMo getEmpleadomo() {
        return empleadomo;
    }

    public void setEmpleadomo(empleadoMo empleadomo) {
        this.empleadomo = empleadomo;
    }

    public List<operacionesOrdent> getOpporasignar() {
        return opporasignar;
    }

    public void setOpporasignar(List<operacionesOrdent> opporasignar) {
        this.opporasignar = opporasignar;
    }

    public List<empleadoMo> getEmpleadosmo() {
        return empleadosmo;
    }

    public void setEmpleadosmo(List<empleadoMo> empleadosmo) {
        this.empleadosmo = empleadosmo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<operacionesOrdent> getFilteredOpporasignar() {
        return filteredOpporasignar;
    }

    public void setFilteredOpporasignar(List<operacionesOrdent> filteredOpporasignar) {
        this.filteredOpporasignar = filteredOpporasignar;
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

    public String getValorestado() {
        return valorestado;
    }

    public void setValorestado(String valorestado) {
        this.valorestado = valorestado;
    }

    public ordenTrabajoFacadeLocal getOrdentrabajoEJB() {
        return ordentrabajoEJB;
    }

    public void setOrdentrabajoEJB(ordenTrabajoFacadeLocal ordentrabajoEJB) {
        this.ordentrabajoEJB = ordentrabajoEJB;
    }

    public ordenTrabajo getOrdentrabajo() {
        return ordentrabajo;
    }

    public void setOrdentrabajo(ordenTrabajo ordentrabajo) {
        this.ordentrabajo = ordentrabajo;
    }
    
    public void asignarOperario(){
        this.empleadomo.setPersona(persona);
        this.operacionesordent.setEmpleadomo(empleadomo);
        this.operacionesordent.setFechaasignado(new Date());
        this.operacionesordent.setMontopendiente(operacionesordent.getMontomo());
        //cambiar el estado de la operaciones de orden
        this.operacionesordent.setEstado("Operario Asignado");
        //guardar operacionesorden
        operacionesordentEJB.edit(operacionesordent);
    
        FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage("Se Actualizo Orden de Trabajo: " + operacionesordent.getOrdentrabajo().getCodigo()));
        
        init();
    }
    
        //validar que el costo negociado este entre maximo y minimo
   public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String toperacion=this.operacionesordent.getTipooperaciones();
        BigDecimal monto=((BigDecimal)value);
        if("enderezado".equals(toperacion)){
            BigDecimal mmax=(BigDecimal)this.operacionesordent.getMontomax();
            BigDecimal mmin=(BigDecimal)this.operacionesordent.getMontomin();
            if((monto.compareTo(mmax)>0) || (monto.compareTo(mmin)<0)){
                 throw new ValidatorException(new FacesMessage("Costo Neg. debe estar entre Valor maximo y minimo"));
            }
        }else{
            BigDecimal mmax=(BigDecimal)this.operacionesordent.getMontomaxp();
            BigDecimal mmin=(BigDecimal)this.operacionesordent.getMontominp();
             if((monto.compareTo(mmax)>0) ||(monto.compareTo(mmin)<0)){
                 throw new ValidatorException(new FacesMessage("Costo Neg. debe estar entre Valor maximo y minimo"));
            }
        }
        
    }
   //PARA AGREGAR LOGO, NO UTILIZADO
    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.LETTER.rotate());
        
        pdf.setMargins(1.5f,1.5f,1.5f,1.5f);
        pdf.setPageCount(1);
   
 
 
        //ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        //String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "demo" + File.separator + "images" + File.separator + "prime_logo.png";
         
        //pdf.add(Image.getInstance(logo));
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
        
   //Cambiar estado a Costo no asignado
    public void cambiarEstado(){
        ordentrabajoEJB.actualizarEstado(this.operacionesordent.getOrdentrabajo().getId());
        
        operacionesordent=operacionesordentEJB.find(this.operacionesordent.getId());
        operacionesordentEJB.remove(operacionesordent);
        
        
        init();
        
    }
        
}
