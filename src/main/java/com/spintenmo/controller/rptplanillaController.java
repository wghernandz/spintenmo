/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;

import com.spintenmo.ejb.planillaMoFacadeLocal;
import com.spintenmo.modelo.planillaMo;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Named
@ViewScoped
public class rptplanillaController implements Serializable {
    @EJB
    private planillaMoFacadeLocal planillamoEJB;
    
    private planillaMo planillamo;
    
    private List<planillaMo> planillas;
    
    @PostConstruct
    public void init(){
        planillamo=new planillaMo();
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

    public List<planillaMo> getPlanillas() {
        return planillas;
    }

    public void setPlanillas(List<planillaMo> planillas) {
        this.planillas = planillas;
    }
  
     public void retencionesPorfecha(ActionEvent actionEvent,Date fechainicial, Date fechafinal) throws JRException, IOException{
      
        planillas=planillamoEJB.planillaFecha(fechainicial, fechafinal);
        
        File jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/planillaretencion.jasper"));
        //JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),new HashMap(),new JRBeanCollectionDataSource(elementosComprobantepago,false));
        byte[] bytes=JasperRunManager.runReportToPdf(jasper.getPath(),null,new JRBeanCollectionDataSource(planillas,false));
        
        HttpServletResponse response=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        
        try (ServletOutputStream stream = response.getOutputStream()) {
            stream.write(bytes,0,bytes.length);
            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();
    }
    
}
