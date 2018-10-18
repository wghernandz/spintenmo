/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;

import com.spintenmo.ejb.operacionesOrdentFacadeLocal;
import com.spintenmo.modelo.operacionesOrdent;
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
public class rptgerenteController implements Serializable {
    @EJB
    private operacionesOrdentFacadeLocal operacionesordentEJB;
    
    private operacionesOrdent operacionesordent;
    
    private List<operacionesOrdent> operacionesordentfecha;
    
    @PostConstruct
    public void init(){
        operacionesordent= new operacionesOrdent();
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

    public List<operacionesOrdent> getOperacionesordentfecha() {
        return operacionesordentfecha;
    }

    public void setOperacionesordentfecha(List<operacionesOrdent> operacionesordentfecha) {
        this.operacionesordentfecha = operacionesordentfecha;
    }

    public void generarPorfecha(ActionEvent actionEvent,Date fechainicial, Date fechafinal) throws JRException, IOException{
      
        operacionesordentfecha=operacionesordentEJB.otXfecha(fechainicial, fechafinal);
        
        File jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/rpt_opordentcostosg.jasper"));
        //JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),new HashMap(),new JRBeanCollectionDataSource(elementosComprobantepago,false));
        byte[] bytes=JasperRunManager.runReportToPdf(jasper.getPath(),null,new JRBeanCollectionDataSource(operacionesordentfecha,false));
        
        
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
