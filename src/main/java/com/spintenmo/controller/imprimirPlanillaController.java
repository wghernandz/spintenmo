/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;

import com.spintenmo.ejb.planillaMoFacadeLocal;
import com.spintenmo.modelo.Descuentos;
import com.spintenmo.modelo.operacionesOrdent;
import com.spintenmo.modelo.planillaMo;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Named
@RequestScoped
public class imprimirPlanillaController implements Serializable {
   @EJB
   private planillaMoFacadeLocal planillamoEJB;
   
   //entidades
   private planillaMo planillamo;
   
   //Listas
   private List<planillaMo> planillasgeneradas;
   private List<planillaMo> planillaspagadas;
   private List<List> consolidadoplanillagen;
   private List<List> consolidadoplanillapag;
   private List<planillaMo> planillagenerada;
   private List<planillaMo> planillapagada;
   private List<Descuentos> descuentosemp;
   private List<planillaMo> planillaspagadasdetalle;
   
   @PostConstruct
   public void init(){
       consolidadoplanillagen= planillamoEJB.consolidadoPlanilla("Generada");
       consolidadoplanillapag= planillamoEJB.consolidadoPlanilla("Pagada");
       planillamo= new planillaMo();
       planillaspagadas=planillamoEJB.planillaSegunEstado("Pagada");
       planillasgeneradas=planillamoEJB.planillaSegunEstado("Generada");
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

    public List<planillaMo> getPlanillasgeneradas() {
        return planillasgeneradas;
    }

    public void setPlanillasgeneradas(List<planillaMo> planillasgeneradas) {
        this.planillasgeneradas = planillasgeneradas;
    }

    public List<planillaMo> getPlanillaspagadas() {
        return planillaspagadas;
    }

    public void setPlanillaspagadas(List<planillaMo> planillaspagadas) {
        this.planillaspagadas = planillaspagadas;
    }

    public List getConsolidadoplanillagen() {
        return consolidadoplanillagen;
    }

    public void setConsolidadoplanillagen(List consolidadoplanillagen) {
        this.consolidadoplanillagen = consolidadoplanillagen;
    }

    public List getConsolidadoplanillapag() {
        return consolidadoplanillapag;
    }

    public void setConsolidadoplanillapag(List consolidadoplanillapag) {
        this.consolidadoplanillapag = consolidadoplanillapag;
    }

    public List<planillaMo> getPlanillaspagadasdetalle() {
        return planillaspagadasdetalle;
    }

    public void setPlanillaspagadasdetalle(List<planillaMo> planillaspagadasdetalle) {
        this.planillaspagadasdetalle = planillaspagadasdetalle;
    }
    
    
     public void verPlanillagenerada(ActionEvent actionEvent,Date fechapago) throws JRException, IOException{
        //master   
        planillagenerada=planillamoEJB.imprimirPlanillagen(fechapago);
        
        //hijo
        descuentosemp=planillamoEJB.imprimirDescuentos();
        //para table(hijo)
        //JRBeanCollectionDataSource descuentosdatos = new JRBeanCollectionDataSource(descuentosemp);
        
        File jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/planilla_all.jasper"));
        //JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),new HashMap(),new JRBeanCollectionDataSource(elementosComprobantepago,false));
        String ruta=FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/jc.png");
        System.out.println("VALOR DE RUTA"+ruta);
        HashMap params = new HashMap();
        params.put("descuentos",descuentosemp);
        params.put("imgdir",ruta);//ruta imagen
        
        
        byte[] bytes=JasperRunManager.runReportToPdf(jasper.getPath(),params,new JRBeanCollectionDataSource(planillagenerada,false));
        
        
        HttpServletResponse response=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        
        try (ServletOutputStream stream = response.getOutputStream()) {
            stream.write(bytes,0,bytes.length);
            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();   
    }
    
        public void verPlanillaPagada(ActionEvent actionEvent,Date fechapago) throws JRException, IOException{
        //master   
        planillagenerada=planillamoEJB.imprimirPlanillapag(fechapago);
        
        //hijo
        descuentosemp=planillamoEJB.imprimirDescuentos();
        //para table(hijo)
        //JRBeanCollectionDataSource descuentosdatos = new JRBeanCollectionDataSource(descuentosemp);
        
        File jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/planilla_all.jasper"));
        //JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),new HashMap(),new JRBeanCollectionDataSource(elementosComprobantepago,false));
        String ruta=FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/jc.png");
        System.out.println("VALOR DE RUTA"+ruta);
        HashMap params = new HashMap();
        params.put("descuentos",descuentosemp);
        params.put("imgdir",ruta);//ruta imagen
        
      
        byte[] bytes=JasperRunManager.runReportToPdf(jasper.getPath(),params,new JRBeanCollectionDataSource(planillagenerada,false));
        
        
        HttpServletResponse response=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        
        try (ServletOutputStream stream = response.getOutputStream()) {
            stream.write(bytes,0,bytes.length);
            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();   
    }
        
        public String verDetalle(ActionEvent actionEvent,Date fechapago) throws JRException, IOException{        
        planillaspagadasdetalle=planillamoEJB.imprimirPlanillapag(fechapago);
        return "/contabilidad/verdetalle.xhtml";
    
    } 
        
    public String verDetallegen(ActionEvent actionEvent,Date fechapago) throws JRException, IOException{        
        planillaspagadasdetalle=planillamoEJB.imprimirPlanillagen(fechapago);
        return "/contabilidad/verdetalle.xhtml";
    
    } 
        
}
