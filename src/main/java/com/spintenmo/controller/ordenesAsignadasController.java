/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;

import com.spintenmo.ejb.PersonaFacadeLocal;
import com.spintenmo.ejb.anticipoMoFacadeLocal;
import com.spintenmo.ejb.empleadoMoFacadeLocal;
import com.spintenmo.ejb.operacionesOrdentFacadeLocal;
import com.spintenmo.ejb.ordenTrabajoFacadeLocal;
import com.spintenmo.modelo.Persona;
import com.spintenmo.modelo.anticipoMo;
import com.spintenmo.modelo.empleadoMo;
import com.spintenmo.modelo.operacionesOrdent;
import com.spintenmo.modelo.ordenTrabajo;
import com.spintenmo.modelo.usuarioRole;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.component.export.ExcelOptions;
import org.primefaces.component.export.PDFOptions;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class ordenesAsignadasController implements Serializable{
    @EJB
    private operacionesOrdentFacadeLocal operacionesordentEJB;
    @EJB
    private anticipoMoFacadeLocal anticipomoEJB;
    @EJB
    private PersonaFacadeLocal personaEJB;
    @EJB
    private ordenTrabajoFacadeLocal ordentrabajoEJB;
    @EJB
    private empleadoMoFacadeLocal empleadomoEJB;
    //LISTAS
    private List<operacionesOrdent> operacionesordentList;
    private List<operacionesOrdent> elementosComprobantepago;
    private List<operacionesOrdent> filteredOtasig;
    private List<Persona> personacargo;
    private List<anticipoMo> pagosanteriores;
    //ENTIDADES
    private operacionesOrdent operacionesordent;
    private anticipoMo anticipomo;
    private Persona persona;
    private empleadoMo empleadomo;
    private ordenTrabajo ordentrabajo;
    private usuarioRole role;
    private empleadoMo nuevoempleadomo;
    
    //variables
    private boolean check;
    private BigDecimal sumatoriaanticipos;
    private BigDecimal montop;
    //PARA FORMATEAR EXPORTER
    private ExcelOptions excelOpt;
    private PDFOptions pdfOpt;
    @PostConstruct
    public void init(){
        ordentrabajo=new ordenTrabajo();
        persona=new Persona();
        empleadomo= new empleadoMo();
        operacionesordentList=operacionesordentEJB.otAsignadas();
        operacionesordent=new operacionesOrdent();
        anticipomo=new anticipoMo();
        
        customizationOptions();
    }

    public operacionesOrdentFacadeLocal getOperacionesordentEJB() {
        return operacionesordentEJB;
    }

    public void setOperacionesordentEJB(operacionesOrdentFacadeLocal operacionesordentEJB) {
        this.operacionesordentEJB = operacionesordentEJB;
    }

    public List<operacionesOrdent> getOperacionesordentList() {
        return operacionesordentList;
    }

    public void setOperacionesordentList(List<operacionesOrdent> operacionesordentList) {
            //this.operacionesordentList=operacionesordentEJB.otAsignadas(operacionesordent);
            this.operacionesordentList=operacionesordentList;
            
    }

    public operacionesOrdent getOperacionesordent() {
        return operacionesordent;
    }

    public void setOperacionesordent(operacionesOrdent operacionesordent) {
        this.operacionesordent = operacionesordent;
        //VERIFICAR SI ESTE OBJETO ESTA RELACIONADO CON ANTICIPOMO Y SETEAR VALOR DE ANTICIPO
        if(anticipomoEJB.sumAnticipoOrden(operacionesordent.getId())!=null && !"Complemento".equals(operacionesordent.getEstado())){
        BigDecimal sumaanticipo=anticipomoEJB.sumAnticipoOrden(operacionesordent.getId());
        this.anticipomo.setMontoanticipo(sumaanticipo);
        }else{
        this.anticipomo.setMontoanticipo(null);
        }
    }

    public anticipoMo getAnticipomo() {
        return anticipomo;
    }

    public void setAnticipomo(anticipoMo anticipomo) {
        this.anticipomo = anticipomo;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public List<operacionesOrdent> getElementosComprobantepago() {
        return elementosComprobantepago;
    }

    public void setElementosComprobantepago(List<operacionesOrdent> elementosComprobantepago) {
        this.elementosComprobantepago = elementosComprobantepago;
    }   

    public anticipoMoFacadeLocal getAnticipomoEJB() {
        return anticipomoEJB;
    }

    public void setAnticipomoEJB(anticipoMoFacadeLocal anticipomoEJB) {
        this.anticipomoEJB = anticipomoEJB;
    }

    public List<operacionesOrdent> getFilteredOtasig() {
        return filteredOtasig;
    }

    public void setFilteredOtasig(List<operacionesOrdent> filteredOtasig) {
        this.filteredOtasig = filteredOtasig;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getPersonacargo() {
        return personacargo;
    }

    public void setPersonacargo(List<Persona> personacargo) {
        this.personacargo = personacargo;
    }

    public PersonaFacadeLocal getPersonaEJB() {
        return personaEJB;
    }

    public void setPersonaEJB(PersonaFacadeLocal personaEJB) {
        this.personaEJB = personaEJB;
    }

    public empleadoMo getEmpleadomo() {
        return empleadomo;
    }

    public void setEmpleadomo(empleadoMo empleadomo) {
        this.empleadomo = empleadomo;
    }

    public ordenTrabajo getOrdentrabajo() {
        return ordentrabajo;
    }

    public void setOrdentrabajo(ordenTrabajo ordentrabajo) {
        this.ordentrabajo = ordentrabajo;
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

    public ordenTrabajoFacadeLocal getOrdentrabajoEJB() {
        return ordentrabajoEJB;
    }

    public void setOrdentrabajoEJB(ordenTrabajoFacadeLocal ordentrabajoEJB) {
        this.ordentrabajoEJB = ordentrabajoEJB;
    }

    public List<anticipoMo> getPagosanteriores() {
        return pagosanteriores;
    }

    public void setPagosanteriores(List<anticipoMo> pagosanteriores) {
        this.pagosanteriores = pagosanteriores;
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

    public empleadoMoFacadeLocal getEmpleadomoEJB() {
        return empleadomoEJB;
    }

    public void setEmpleadomoEJB(empleadoMoFacadeLocal empleadomoEJB) {
        this.empleadomoEJB = empleadomoEJB;
    }

    public empleadoMo getNuevoempleadomo() {
        return nuevoempleadomo;
    }

    public void setNuevoempleadomo(empleadoMo nuevoempleadomo) {
        this.nuevoempleadomo = nuevoempleadomo;
    }
    
    public void actualizarOtAsignadas(){
        operacionesordentList=operacionesordentEJB.otAsignadas();
        this.setOperacionesordentList(operacionesordentList);
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "ordenesAsignadas.xhtml");
       
    }
    
    public void exportarPDF(ActionEvent actionEvent,operacionesOrdent opot) throws JRException, IOException{
        
        elementosComprobantepago=operacionesordentEJB.anexopagoOrden(opot);
        //operacionesOrdent elemento=elementosComprobantepago.get(0); 
        //System.out.println("REPORT HERE"+elemento.getCodigo());
        //Map<String,Object> parametros=new HashMap<String,Object>();
        //parametros.put("idoperacionesordent",331);
        
        File jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/comprobantePago.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),new HashMap(),new JRBeanCollectionDataSource(elementosComprobantepago,false));
    
        HttpServletResponse response=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition","attachment; filename=comprobantePago.pdf");
        ServletOutputStream stream=response.getOutputStream();
        
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
       
        
        //stream.flush();
        //stream.close();
        
        FacesContext.getCurrentInstance().responseComplete();
        
    }
    
     public void verPDF(ActionEvent actionEvent,operacionesOrdent opot) throws JRException, IOException{
           
        elementosComprobantepago=operacionesordentEJB.anexopagoOrden(opot);
        File jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/comprobantePago.jasper"));
        //JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),new HashMap(),new JRBeanCollectionDataSource(elementosComprobantepago,false));
        byte[] bytes=JasperRunManager.runReportToPdf(jasper.getPath(),null,new JRBeanCollectionDataSource(elementosComprobantepago,false));
        
        
        HttpServletResponse response=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        
        try (ServletOutputStream stream = response.getOutputStream()) {
            stream.write(bytes,0,bytes.length);
            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();
        
    }
    
    
    public void operacionesPlanilla(){
        //ACTUALIZAR ESTADO Y FECHA DE TERMINACION SI SE SELECCIONA TERMINAR OPERACIONES
        if (check==true){
            this.operacionesordent.setFechaterminado(new Date());
            this.operacionesordent.setEstado("Finalizado");
            this.operacionesordent.setMontoplanilla(operacionesordent.getMontopendiente());
            operacionesordentEJB.edit(operacionesordent);
            }else{ //SI SE REGISTRA UN ANTICIPO ACTULIZAR ESTADO DE OPERACION A CON ANTICIPO Y GUARDAR ANTICIPO
           
             //GUARDAR ANTICIPO
            this.anticipomo.setOperacionesordent(operacionesordent);
            this.anticipomo.setIdordentrabajo(operacionesordent.getOrdentrabajo().getId());
            anticipomoEJB.create(anticipomo);
          
            //ACTUALIZAR ESTADO DE ORDEN
            this.operacionesordent.setEstado("Anticipo");
            this.operacionesordent.setFechaanticipo(new Date());
            this.operacionesordent.setMontoplanilla(anticipomo.getMontoanticipo());
            operacionesordentEJB.edit(operacionesordent);    
            }
        init();
    }
    
   //validar monto pendiente contra monto a anticipar 
   public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Object otherValue = component.getAttributes().get("montopendiente");
        
        if(otherValue!=null){
        
        BigDecimal monto=((BigDecimal)value);
        BigDecimal montopendiente=(BigDecimal)otherValue;
        if (monto.compareTo(montopendiente) < 0) {
            throw new ValidatorException(new FacesMessage("Anticipo No puede ser mayor a "+montopendiente));
        }
    }
   }
   
   public void listarOperario(operacionesOrdent opt){
       String tipooperacion=opt.getTipooperaciones();
       if ("pintura".equals(tipooperacion)){
            this.setPersonacargo(personaEJB.personaCargo("Pintor de vehiculos"));
            this.setPersona(opt.getEmpleadomo().getPersona());
       }else{
           this.setPersonacargo(personaEJB.personaCargo("Enderezador de vehiculos"));
           this.setPersona(opt.getEmpleadomo().getPersona());
       }
       this.setOperacionesordent(opt);
   }
   
   public String cambiarOperario(){
       this.empleadomo.setPersona(persona);
       this.operacionesordent.setEmpleadomo(empleadomo);
      
       //this.operacionesordent.setOrdentrabajo(ordentrabajo);
       operacionesordentEJB.edit(operacionesordent);
       init();
        FacesContext.getCurrentInstance().addMessage(
        null, new FacesMessage("SE CAMBIO OPERARIO"));
 
        FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);
 
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        return view.getViewId() + "?faces-redirect=true&includeViewParams=true";
   }
   
   public void cambiarMontoneg(){
       this.operacionesordent.setMontomo(operacionesordent.getMontomo());
       this.operacionesordent.setMontopendiente(operacionesordent.getMontomo());
       operacionesordentEJB.edit(operacionesordent);
       init();
   }
   
     //validar que el costo negociado este entre maximo y minimo, no utilizado por el momento
   public void validateot(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String toperacion=operacionesordent.getTipooperaciones();
        BigDecimal monto=((BigDecimal)value);

        if("enderezado".equals(toperacion)){
            BigDecimal mmax=(BigDecimal)operacionesordent.getMontomax();
            BigDecimal mmin=(BigDecimal)operacionesordent.getMontomin();
            if((monto.compareTo(mmax)>0) || (monto.compareTo(mmin)<0)){
                
                 throw new ValidatorException(new FacesMessage("Costo Neg. debe estar entre Valor maximo y minimo"));
            }
        }else{
            BigDecimal mmax=(BigDecimal)operacionesordent.getMontomaxp();
            BigDecimal mmin=(BigDecimal)operacionesordent.getMontominp();
             if((monto.compareTo(mmax)>0) ||(monto.compareTo(mmin)<0)){
                 throw new ValidatorException(new FacesMessage("Costo Neg. debe estar entre Valor maximo y minimo"));
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
         
    //Metodo para actualizar a estado finalizado todas las ordenes que posean estado finalizado o anticipo para calculo de planilla; 
    public String finalizarTodo(){
        
          operacionesordentEJB.finalizarTodo();
          
           FacesContext.getCurrentInstance().addMessage(
        null, new FacesMessage("Ordenes Actualizadas"));
 
        FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);
 
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        return view.getViewId() + "?faces-redirect=true&includeViewParams=true";
    }
    
    public int obtenerRole(){
        FacesContext context = FacesContext.getCurrentInstance();
        this.role= (usuarioRole) context.getExternalContext().getSessionMap().get("mirol");
        return this.role.getId();
    }
    
    public void historialant(operacionesOrdent opot){
        this.setOperacionesordent(operacionesordentEJB.find(opot.getId()));
        pagosanteriores=anticipomoEJB.anticipoPorOrden(opot);
        System.out.println("valor pagosanteriores"+pagosanteriores.size());
        //operario a quien esta asignada la orden
        this.persona=this.operacionesordent.getEmpleadomo().getPersona();
        //listar operarios
        listarOperario(this.getOperacionesordent());
        //sumar anticipos
        this.setSumatoriaanticipos(new BigDecimal(0));
        for(int indice = 0;indice<pagosanteriores.size();indice++)
        {
            this.sumatoriaanticipos=this.sumatoriaanticipos.add(pagosanteriores.get(indice).getMontoanticipo());
        }
            this.montop=this.getOperacionesordent().getMontomo().subtract(this.sumatoriaanticipos).setScale(2, RoundingMode.HALF_UP);
        //pagosanteriores=operacionesordentEJB.otpagadaXplaca(placa, tipoop, "Pagada");
    }
    
      public String reasignarOperario(){
         
        //empleadoMo nuevoemp=new empleadoMo();
         operacionesOrdent nuevoop = new operacionesOrdent();
         nuevoop.setCodigo(this.operacionesordent.getCodigo());
         //nuevoop.setEmpleadomo(nuevoempleadomo);
         nuevoop.setEstado(this.operacionesordent.getEstado());
         nuevoop.setFechaanticipo(this.operacionesordent.getFechaanticipo());
         nuevoop.setFechaasignado(this.operacionesordent.getFechaasignado());
         nuevoop.setFechapago(this.operacionesordent.getFechapago());
         nuevoop.setFechasubido(this.operacionesordent.getFechasubido());
         nuevoop.setFechaterminado(this.operacionesordent.getFechaterminado());
         nuevoop.setFechavalorizado(this.operacionesordent.getFechavalorizado());
         nuevoop.setMontomax(this.operacionesordent.getMontomax());
         nuevoop.setMontomaxp(this.operacionesordent.getMontomaxp());
         nuevoop.setMontomin(this.operacionesordent.getMontomin());
         nuevoop.setMontominp(this.operacionesordent.getMontominp());
         nuevoop.setMontomo(this.operacionesordent.getMontomo());
         nuevoop.setMontopendiente(this.operacionesordent.getMontopendiente());
         nuevoop.setMontoplanilla(this.operacionesordent.getMontoplanilla());
         nuevoop.setOrdentrabajo(this.operacionesordent.getOrdentrabajo());
         nuevoop.setRutaenderezado(this.operacionesordent.getRutaenderezado());
         nuevoop.setRutapintura(this.operacionesordent.getRutapintura());
         nuevoop.setRutarecibo(this.operacionesordent.getRutarecibo());
         nuevoop.setTipooperaciones(this.operacionesordent.getTipooperaciones());
         operacionesordentEJB.create(nuevoop);
         
         this.operacionesordent.setEstado("Reasignada");
         operacionesordentEJB.edit(this.operacionesordent);
         
         this.nuevoempleadomo=empleadomoEJB.find(persona.getId());
         this.operacionesordent=nuevoop;
         this.operacionesordent.setEmpleadomo(this.nuevoempleadomo);
         operacionesordentEJB.edit(this.operacionesordent);
         
          
           FacesContext.getCurrentInstance().addMessage(
        null, new FacesMessage("SE REASIGNO OPERARIO"));
 
        FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);
 
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        return view.getViewId() + "?faces-redirect=true&includeViewParams=true";
     }
     
     public void setOrdenAcopiar(operacionesOrdent ot){
         
         listarOperario(ot);
     
     }
      
     public String crearOtraAsignacion(){
     
           //empleadoMo nuevoemp=new empleadoMo();
         operacionesOrdent nuevoop = new operacionesOrdent();
         nuevoop.setCodigo(this.operacionesordent.getCodigo());
         nuevoop.setEstado(this.operacionesordent.getEstado());
         nuevoop.setFechaanticipo(this.operacionesordent.getFechaanticipo());
         nuevoop.setFechaasignado(this.operacionesordent.getFechaasignado());
         nuevoop.setFechapago(this.operacionesordent.getFechapago());
         nuevoop.setFechasubido(this.operacionesordent.getFechasubido());
         nuevoop.setFechaterminado(this.operacionesordent.getFechaterminado());
         nuevoop.setFechavalorizado(this.operacionesordent.getFechavalorizado());
         if("enderezado".equals(this.operacionesordent.getTipooperaciones())){
            nuevoop.setMontomin(this.operacionesordent.getMontomo());
            nuevoop.setMontomax(this.operacionesordent.getMontomo());
         }else{
            nuevoop.setMontominp(null);
            nuevoop.setMontomaxp(null);
         }
         if("pintura".equals(this.operacionesordent.getTipooperaciones())){
            nuevoop.setMontominp(this.operacionesordent.getMontomo());
            nuevoop.setMontomaxp(this.operacionesordent.getMontomo());
         }else{
            nuevoop.setMontomin(this.operacionesordent.getMontomo());
            nuevoop.setMontomax(this.operacionesordent.getMontomo());
         }
         
         nuevoop.setMontomo(this.operacionesordent.getMontomo());
         nuevoop.setMontopendiente(this.operacionesordent.getMontomo());
         nuevoop.setMontoplanilla(this.operacionesordent.getMontoplanilla());
         nuevoop.setOrdentrabajo(this.operacionesordent.getOrdentrabajo());
         nuevoop.setRutaenderezado(this.operacionesordent.getRutaenderezado());
         nuevoop.setRutapintura(this.operacionesordent.getRutapintura());
         nuevoop.setRutarecibo(this.operacionesordent.getRutarecibo());
         nuevoop.setTipooperaciones(this.operacionesordent.getTipooperaciones());
         operacionesordentEJB.create(nuevoop);
         
         this.nuevoempleadomo=empleadomoEJB.find(persona.getId());
         this.operacionesordent=nuevoop;
         this.operacionesordent.setEmpleadomo(this.nuevoempleadomo);
         operacionesordentEJB.edit(this.operacionesordent);
         
          FacesContext.getCurrentInstance().addMessage(
        null, new FacesMessage("SE CREO OTRA ASIGNACION"));
 
        FacesContext.getCurrentInstance()
            .getExternalContext()
            .getFlash().setKeepMessages(true);
 
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        return view.getViewId() + "?faces-redirect=true&includeViewParams=true";
     }
     
}
