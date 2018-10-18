/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;

import com.spintenmo.ejb.ordenTrabajoFacadeLocal;
import com.spintenmo.modelo.operacionesOrdent;
import com.spintenmo.modelo.ordenTrabajo;
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
public class reportesotController implements Serializable {
    @EJB
    private ordenTrabajoFacadeLocal ordentrabajoEJB;
    
    private List<ordenTrabajo> ordenesfecha;
    
    
    
    @PostConstruct
    public void init(){
    
    
    }

    public ordenTrabajoFacadeLocal getOrdentrabajoEJB() {
        return ordentrabajoEJB;
    }

    public void setOrdentrabajoEJB(ordenTrabajoFacadeLocal ordentrabajoEJB) {
        this.ordentrabajoEJB = ordentrabajoEJB;
    }

    public List<ordenTrabajo> getOrdenesfecha() {
        return ordenesfecha;
    }

    public void setOrdenesfecha(List<ordenTrabajo> ordenesfecha) {
        this.ordenesfecha = ordenesfecha;
    }
    
    public void generarPorfecha(ActionEvent actionEvent,Date fechainicial, Date fechafinal) throws JRException, IOException{

        ordenesfecha=ordentrabajoEJB.otXfecha(fechainicial, fechafinal);
        File jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/rpt_otfecha.jasper"));
        //JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),new HashMap(),new JRBeanCollectionDataSource(elementosComprobantepago,false));
        byte[] bytes=JasperRunManager.runReportToPdf(jasper.getPath(),null,new JRBeanCollectionDataSource(ordenesfecha,false));
        
        
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
