/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="planillamo")
public class planillaMo implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_ORDENTRABAJO")
    @SequenceGenerator(name="SEQ_ID_ORDENTRABAJO",sequenceName="seq_idordentrabajo", allocationSize=1)
    private int id;
    @JoinColumn(name="idoperacionesot")
    private operacionesOrdent operacionesordent;
    @JoinColumn(name="idpersona")
    private empleadoMo empleadomo;
    @Column(name="fechaplanilla")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaplanilla;
    @Column(name="montogravado")
    private BigDecimal montogravado;
    @Column(name="renta")
    private BigDecimal renta;
    @Column(name="montopagar")
    private BigDecimal montoapagar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getFechaplanilla() {
        return fechaplanilla;
    }

    public void setFechaplanilla(Date fechaplanilla) {
        this.fechaplanilla = fechaplanilla;
    }

    public BigDecimal getMontogravado() {
        return montogravado;
    }

    public void setMontogravado(BigDecimal montogravado) {
        this.montogravado = montogravado;
    }

    public BigDecimal getRenta() {
        return renta;
    }

    public void setRenta(BigDecimal renta) {
        this.renta = renta;
    }

    public BigDecimal getMontoapagar() {
        return montoapagar;
    }

    public void setMontoapagar(BigDecimal montoapagar) {
        this.montoapagar = montoapagar;
    }
      
    
}
