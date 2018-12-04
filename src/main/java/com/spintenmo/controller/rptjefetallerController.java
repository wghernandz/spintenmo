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
/**
 *
 * @author willian
 */
@Named
@ViewScoped
public class rptjefetallerController implements Serializable{
    @EJB
    private operacionesOrdentFacadeLocal operacionesordentEJB;
    
    private operacionesOrdent operacionesordent;
    
    private List<operacionesOrdent> operacionesordentfinalizadas;
    

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

    public List<operacionesOrdent> getOperacionesordentfinalizadas() {
        return operacionesordentfinalizadas;
    }

    public void setOperacionesordentfinalizadas(List<operacionesOrdent> operacionesordentfinalizadas) {
        this.operacionesordentfinalizadas = operacionesordentfinalizadas;
    }
    
    
    public void generarPorEstado(ActionEvent actionEvent,String estado) throws JRException, IOException{
        File jasper=null;
         
        operacionesordentfinalizadas=operacionesordentEJB.otEstado(estado);
        System.out.println("longitud "+operacionesordentfinalizadas.size());
        if(estado.equals("Preplanilla")){
            jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/ordenesPreplanilla.jasper"));
        }else{
             jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/ordenesSegunEstado.jasper"));
        }
         //JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),new HashMap(),new JRBeanCollectionDataSource(elementosComprobantepago,false));
         byte[] bytes=JasperRunManager.runReportToPdf(jasper.getPath(),null,new JRBeanCollectionDataSource(operacionesordentfinalizadas,false));
        
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
