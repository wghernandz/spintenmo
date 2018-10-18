/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.controller;


import com.spintenmo.ejb.AbonosFacadeLocal;
import com.spintenmo.ejb.DescuentosFacade;
import com.spintenmo.ejb.DescuentosFacadeLocal;
import com.spintenmo.ejb.empleadoMoFacadeLocal;
import com.spintenmo.ejb.operacionesOrdentFacadeLocal;
import com.spintenmo.ejb.planillaMoFacadeLocal;
import com.spintenmo.modelo.Abonos;
import com.spintenmo.modelo.Descuentos;
import com.spintenmo.modelo.Persona;
import com.spintenmo.modelo.anticipoMo;
import com.spintenmo.modelo.empleadoMo;
import com.spintenmo.modelo.operacionesOrdent;
import com.spintenmo.modelo.planillaMo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FlowEvent;

@Named
@ViewScoped
public class calcularPlanillaController implements Serializable {
    @EJB
    private DescuentosFacadeLocal descuentosEJB;
    @EJB
    private empleadoMoFacadeLocal empleadomoEJB;
    @EJB
    private operacionesOrdentFacadeLocal operacionesordentEJB;
    @EJB
    private planillaMoFacadeLocal planillamoEJB;
    @EJB
    private AbonosFacadeLocal abonosEJB;
    
    //ENTIDADES
    private Descuentos descuento;
    private empleadoMo empleadomo;
    private planillaMo planillamo;
    private operacionesOrdent operacionesordent;
    private Persona persona;
    private Abonos abono;
    
    //LISTAS
    private List<Descuentos> descuentosaaplicar;
    private List<operacionesOrdent> otfinalizadas;
    private List<anticipoMo> otanticipos;
    private List<planillaMo> planillacalculada;
    
    private boolean skip;
    private boolean disabledgenerar;
    private boolean disabledpagar;
    private Date fechacalculo;
    private boolean cargadescuento;
    private boolean opplanilla;
            
    @PostConstruct
    public void init(){
        planillamo=new planillaMo();
        descuento=new Descuentos();
        empleadomo=new empleadoMo();
        abono= new Abonos();
        //validar si ya se obtuvieron los descuentos correctos.
        if(descuentosEJB.descVerificado()!=null){
            if (("SI".equals(descuentosEJB.descVerificado().getVerificada()))) {
            descuentosaaplicar=descuentosEJB.descuentosaAplicar();
            cargadescuento=true;
            }
        }
        otfinalizadas=operacionesordentEJB.otPreplanilla();
        otanticipos=operacionesordentEJB.anticipoPreplanilla();
        disabledgenerar=false;
        disabledpagar=false;
        opplanilla=false;
        //Verificar si existe una planilla generada previamente
        if(planillamoEJB.planillaSegunEstado("Generada").isEmpty()){
            disabledgenerar=false;
            opplanilla=true;
        }else{
            planillacalculada=planillamoEJB.planillaSegunEstado("Generada");
            disabledgenerar=true;
            opplanilla=false;
        }     
    }

    public DescuentosFacadeLocal getDescuentosEJB() {
        return descuentosEJB;
    }

    public void setDescuentosEJB(DescuentosFacadeLocal descuentosEJB) {
        this.descuentosEJB = descuentosEJB;
    }


    public void setDescuentosEJB(DescuentosFacade descuentosEJB) {
        this.descuentosEJB = descuentosEJB;
    }

    public empleadoMoFacadeLocal getEmpleadomoEJB() {
        return empleadomoEJB;
    }

    public void setEmpleadomoEJB(empleadoMoFacadeLocal empleadomoEJB) {
        this.empleadomoEJB = empleadomoEJB;
    }

    public Descuentos getDescuento() {
        return descuento;
    }

    public void setDescuento(Descuentos descuento) {
        this.descuento = descuento;
    }

    public empleadoMo getEmpleadomo() {
        return empleadomo;
    }

    public void setEmpleadomo(empleadoMo empleadomo) {
        this.empleadomo = empleadomo;
    }

    public List<Descuentos> getDescuentosaaplicar() {
        return descuentosaaplicar;
    }

    public void setDescuentosaaplicar(List<Descuentos> descuentosaaplicar) {
        this.descuentosaaplicar = descuentosaaplicar;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public operacionesOrdentFacadeLocal getOperacionesordentEJB() {
        return operacionesordentEJB;
    }

    public void setOperacionesordentEJB(operacionesOrdentFacadeLocal operacionesordentEJB) {
        this.operacionesordentEJB = operacionesordentEJB;
    } 

    public List<operacionesOrdent> getOtfinalizadas() {
        return otfinalizadas;
    }

    public void setOtfinalizadas(List<operacionesOrdent> otfinalizadas) {
        this.otfinalizadas = otfinalizadas;
    }

    public List<anticipoMo> getOtanticipos() {
        return otanticipos;
    }

    public void setOtanticipos(List<anticipoMo> otanticipos) {
        this.otanticipos = otanticipos;
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
    
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }

    public operacionesOrdent getOperacionesordent() {
        return operacionesordent;
    }

    public void setOperacionesordent(operacionesOrdent operacionesordent) {
        this.operacionesordent = operacionesordent;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<planillaMo> getPlanillacalculada() {
        return planillacalculada;
    }

    public void setPlanillacalculada(List<planillaMo> planillacalculada) {
        this.planillacalculada = planillacalculada;
    }

    public Date getFechacalculo() {
        return fechacalculo;
    }

    public void setFechacalculo(Date fechacalculo) {
        this.fechacalculo = fechacalculo;
    }

    public boolean isDisabledgenerar() {
        return disabledgenerar;
    }

    public void setDisabledgenerar(boolean disabledgenerar) {
        this.disabledgenerar = disabledgenerar;
    }

    public boolean isDisabledpagar() {
        return disabledpagar;
    }

    public void setDisabledpagar(boolean disabledpagar) {
        this.disabledpagar = disabledpagar;
    }

    public AbonosFacadeLocal getAbonosEJB() {
        return abonosEJB;
    }

    public void setAbonosEJB(AbonosFacadeLocal abonosEJB) {
        this.abonosEJB = abonosEJB;
    }

    public Abonos getAbono() {
        return abono;
    }

    public void setAbono(Abonos abono) {
        this.abono = abono;
    }

    public boolean isCargadescuento() {
        return cargadescuento;
    }

    public void setCargadescuento(boolean cargadescuento) {
        this.cargadescuento = cargadescuento;
    }

    public boolean isOpplanilla() {
        return opplanilla;
    }

    public void setOpplanilla(boolean opplanilla) {
        this.opplanilla = opplanilla;
    }
    
    
     public void generarPlanilla() {
         String fechaini;
         String fechafin;
        //Calcular la fecha de inicio
        Date fechainicio;
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.getFechacalculo());
        
         SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        if(cal.get(Calendar.DAY_OF_MONTH)>15){
           cal.set(Calendar.DAY_OF_MONTH,16);
           fechainicio=cal.getTime();
           
           fechaini=formateador.format(fechainicio);
        }else{
           cal.set(Calendar.DAY_OF_MONTH,1);
           fechainicio=cal.getTime();
           fechaini=formateador.format(fechainicio);
        }
        fechafin=formateador.format(this.getFechacalculo());
        
        //Calcular planilla en BD. 
        this.planillacalculada=planillamoEJB.calculoPlanilla(fechaini, fechafin);
        if (planillacalculada.isEmpty()==true){
             FacesMessage msg = new FacesMessage("No se encontraron ordenes para calculo", "Planilla NO Generada:");
             FacesContext.getCurrentInstance().addMessage(null, msg);
        }else{
            this.disabledgenerar=true;
            this.opplanilla=false;
            FacesMessage msg = new FacesMessage("Successful", "Planilla generada:" + fechafin );
            FacesContext.getCurrentInstance().addMessage(null, msg); 
        }   
    }
     //recalcular planilla si re realizan cambios a los dias laborados
     public void actualizarPlanilla(){
         //En la planilla generada solo se podra cambiar los dias laborados
         //obtener los datos del empleado para utilizar el salario base
         empleadomo=empleadomoEJB.operarioSegunId(planillamo.getEmpleadomo().getPersona().getId());
         BigDecimal ndiaslab=planillamo.getDiaslab();
         BigDecimal descuentos=planillamo.getSumadescuentos();
         BigDecimal montomototal=planillamo.getMontomototal();
         BigDecimal nsbasediario=(empleadomo.getSalariobase().divide(BigDecimal.valueOf(30.00)));
         BigDecimal nsalariobase=nsbasediario.multiply(ndiaslab);
         BigDecimal nsubtotalgravado=montomototal.subtract(nsalariobase);
         BigDecimal nrenta=nsubtotalgravado.multiply(BigDecimal.valueOf(0.10));
         BigDecimal nmontoapagar=nsubtotalgravado.subtract(nrenta).subtract(descuentos);
         this.planillamo.setSalariobase(nsalariobase);
         this.planillamo.setSubtotalgravado(nsubtotalgravado);
         this.planillamo.setRenta(nrenta);
         this.planillamo.setMontoapagar(nmontoapagar);
         planillamoEJB.edit(planillamo);
     }
     
     //Modificar descuentos
     public String modDescuentoquinc(){
         descuentosEJB.edit(descuento);
         //verificar si ya hay una planilla ya generada y hacer el cambio respectivo
         if(planillamoEJB.planillaSegunEstado("Generada").isEmpty()==false){
             this.setPlanillamo(descuentosEJB.planillaPoroperario(descuento.getEmpleadomo().getPersona().getId()));
             BigDecimal nsumdescuentos=descuentosEJB.sumDescuentooperario(descuento.getEmpleadomo().getPersona().getId());
             this.planillamo.setSumadescuentos(nsumdescuentos);
             planillamoEJB.edit(planillamo);
             //actualizar calculos
             this.actualizarPlanilla();  
         }
         UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
         return view.getViewId() + "?faces-redirect=true";
     }   
     //operaciones a  realizar al efectuar pago de planilla 
     public String pagarPlanilla(){
         //Registrar abono de Descuento
         SimpleDateFormat formateo=new SimpleDateFormat("dd-MM-yyyy");
         String fechapago= formateo.format(new Date());
         
         this.planillacalculada=planillamoEJB.pagarPlanilla(fechapago);
         if(planillacalculada.isEmpty()==true){
              FacesMessage msg = new FacesMessage("ERROR");
              FacesContext.getCurrentInstance().addMessage(null, msg); 
              
              UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
              return view.getViewId() + "?faces-redirect=true";
         }else{
         //actualizar tabla descuentos
         descuentosEJB.despuespagodesc();
         init();
         this.opplanilla=true;
         FacesMessage msg = new FacesMessage("Se registro planilla como Pagada");
         FacesContext.getCurrentInstance().addMessage(null, msg);
         
         UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
         return view.getViewId() + "?faces-redirect=true";
         }       
     }   
     //Cargar descuentos actualizados
     public void cargaDescuentos(){
         this.descuentosaaplicar=descuentosEJB.descuentosUpdate();  
         this.cargadescuento=true;
     }
     
     public String elimPlanilla(){
         try{
              planillamoEJB.eliminarPsegunestado("Generada");
              this.planillacalculada=null;
              
              FacesMessage msg = new FacesMessage("Planilla Generada fue Eliminada");
              FacesContext.getCurrentInstance().addMessage(null, msg);
              
               init();
               UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
               return view.getViewId() + "?faces-redirect=true";
         }catch(Exception e){
              FacesMessage msg = new FacesMessage("ERROR"+e.getMessage());
              FacesContext.getCurrentInstance().addMessage(null, msg);
              
              UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
              return view.getViewId() + "?faces-redirect=true";
              
         }
         
     }
     
     
     
}
