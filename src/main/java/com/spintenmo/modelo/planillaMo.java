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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="planillamo")
public class planillaMo implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_PLANILLAMO")
    @SequenceGenerator(name="SEQ_ID_PLANILLAMO",sequenceName="seq_idplanillamo", allocationSize=1)
    private int id;
    @OneToOne
    @JoinColumn(name="idpersona")
    private empleadoMo empleadomo;
    @Column(name="finicio")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date finicio;
    @Column(name="ffin")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date ffin;
    @Column(name="fpagado")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fpagado;
    @Column(name="diaslab")
    private BigDecimal diaslab;
    @Column(name="montomototal")
    private BigDecimal montomototal;
    @Column(name="renta")
    private BigDecimal renta;
    @Column(name="montoapagar")
    private BigDecimal montoapagar;
    @Column(name="sumadescuentos")
    private BigDecimal sumadescuentos;
    @Column(name="salariobase")
    private BigDecimal salariobase;
    @Column(name="subtotalgravado")
    private BigDecimal subtotalgravado;
    @Column(name="estado")
    private String estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public empleadoMo getEmpleadomo() {
        return empleadomo;
    }

    public void setEmpleadomo(empleadoMo empleadomo) {
        this.empleadomo = empleadomo;
    }

    public Date getFinicio() {
        return finicio;
    }

    public void setFinicio(Date finicio) {
        this.finicio = finicio;
    }

    public Date getFfin() {
        return ffin;
    }

    public void setFfin(Date ffin) {
        this.ffin = ffin;
    }

    public Date getFpagado() {
        return fpagado;
    }

    public void setFpagado(Date fpagado) {
        this.fpagado = fpagado;
    }

    public BigDecimal getDiaslab() {
        return diaslab;
    }

    public void setDiaslab(BigDecimal diaslab) {
        this.diaslab = diaslab;
    }

    public BigDecimal getMontomototal() {
        return montomototal;
    }

    public void setMontomototal(BigDecimal montomototal) {
        this.montomototal = montomototal;
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

    public BigDecimal getSumadescuentos() {
        return sumadescuentos;
    }

    public void setSumadescuentos(BigDecimal sumadescuentos) {
        this.sumadescuentos = sumadescuentos;
    }

    public BigDecimal getSalariobase() {
        return salariobase;
    }

    public void setSalariobase(BigDecimal salariobase) {
        this.salariobase = salariobase;
    }

    public BigDecimal getSubtotalgravado() {
        return subtotalgravado;
    }

    public void setSubtotalgravado(BigDecimal subtotalgravado) {
        this.subtotalgravado = subtotalgravado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
