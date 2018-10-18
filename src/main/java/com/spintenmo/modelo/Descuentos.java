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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="descuentos")
public class Descuentos implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_DESCUENTOS")
    @SequenceGenerator(name="SEQ_ID_DESCUENTOS",sequenceName="seq_iddescuento", allocationSize=1)
    private int id;
    @Column(name="concepto")
    private String concepto;
    @Column(name="monto")
    private BigDecimal monto;
    @Column(name="fingresodesc")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fingresodesc;
    @Column(name="montopend")
    private BigDecimal montopend;
    @ManyToOne
    @JoinColumn(name="idempleadomo")
    private empleadoMo empleadomo;
    @Column(name="descquincenal")
    private BigDecimal descquincenal;
    @Column(name="deley")
    private boolean deley;
    @Column(name="descfijo")
    private BigDecimal descfijo;
    @Column(name="verificada")
    private String verificada;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Date getFingresodesc() {
        return fingresodesc;
    }

    public void setFingresodesc(Date fingresodesc) {
        this.fingresodesc = fingresodesc;
    }

    public BigDecimal getMontopend() {
        return montopend;
    }

    public void setMontopend(BigDecimal montopend) {
        this.montopend = montopend;
    }

    public empleadoMo getEmpleadomo() {
        return empleadomo;
    }

    public void setEmpleadomo(empleadoMo empleadomo) {
        this.empleadomo = empleadomo;
    }

    public BigDecimal getDescquincenal() {
        return descquincenal;
    }

    public void setDescquincenal(BigDecimal descquincenal) {
        this.descquincenal = descquincenal;
    }

    public boolean isDeley() {
        return deley;
    }

    public void setDeley(boolean deley) {
        this.deley = deley;
    }

    public BigDecimal getDescfijo() {
        return descfijo;
    }

    public void setDescfijo(BigDecimal descfijo) {
        this.descfijo = descfijo;
    }

    public String getVerificada() {
        return verificada;
    }

    public void setVerificada(String verificada) {
        this.verificada = verificada;
    }
    
}
