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
@Table(name="operacionesordent")
public class operacionesOrdent implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_OPERACIONESORDENT")
    @SequenceGenerator(name="SEQ_ID_OPERACIONESORDENT",sequenceName="seq_idoperacionesordent", allocationSize=1)
    private int id;
    @JoinColumn(name="idordentrabajo")
    private ordenTrabajo ordentrabajo;
    @JoinColumn(name="idpersona")
    private empleadoMo empleadomo;
    @Column(name="rutaenderezado")
    private String rutaenderezado;
    @Column(name="rutapintura")
    private String rutapintura;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="fechasubido")
    private Date fechasubido;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="fechavalorizado")
    private Date fechavalorizado;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="fechaasignado")
    private Date fechaasignado;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="fechaterminado")
    private Date fechaterminado;
    @Column(name="codigo")
    private String codigo;
    @Column(name="estado")
    private String estado;
    @Column(name="montomo")
    private BigDecimal montomo;
    @Column(name="montomax")
    private BigDecimal montomax;
    @Column(name="montomin")
    private BigDecimal montomin;
    @Column(name="tipooperaciones")
    private String tipooperaciones;
    @Column(name="rutarecibo")
    private String rutarecibo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ordenTrabajo getOrdentrabajo() {
        return ordentrabajo;
    }

    public void setOrdentrabajo(ordenTrabajo ordentrabajo) {
        this.ordentrabajo = ordentrabajo;
    }

    public empleadoMo getEmpleadomo() {
        return empleadomo;
    }

    public void setEmpleadomo(empleadoMo empleadomo) {
        this.empleadomo = empleadomo;
    }

    public String getRutaenderezado() {
        return rutaenderezado;
    }

    public void setRutaenderezado(String rutaenderezado) {
        this.rutaenderezado = rutaenderezado;
    }

    public String getRutapintura() {
        return rutapintura;
    }

    public void setRutapintura(String rutapintura) {
        this.rutapintura = rutapintura;
    }

    public Date getFechasubido() {
        return fechasubido;
    }

    public void setFechasubido(Date fechasubido) {
        this.fechasubido = fechasubido;
    }

    public Date getFechavalorizado() {
        return fechavalorizado;
    }

    public void setFechavalorizado(Date fechavalorizado) {
        this.fechavalorizado = fechavalorizado;
    }

    public Date getFechaasignado() {
        return fechaasignado;
    }

    public void setFechaasignado(Date fechaasignado) {
        this.fechaasignado = fechaasignado;
    }

    public Date getFechaterminado() {
        return fechaterminado;
    }

    public void setFechaterminado(Date fechaterminado) {
        this.fechaterminado = fechaterminado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getMontomo() {
        return montomo;
    }

    public void setMontomo(BigDecimal montomo) {
        this.montomo = montomo;
    }

    public BigDecimal getMontomax() {
        return montomax;
    }

    public void setMontomax(BigDecimal montomax) {
        this.montomax = montomax;
    }

    public BigDecimal getMontomin() {
        return montomin;
    }

    public void setMontomin(BigDecimal montomin) {
        this.montomin = montomin;
    }

    public String getTipooperaciones() {
        return tipooperaciones;
    }

    public void setTipooperaciones(String tipooperaciones) {
        this.tipooperaciones = tipooperaciones;
    }

    public String getRutarecibo() {
        return rutarecibo;
    }

    public void setRutarecibo(String rutarecibo) {
        this.rutarecibo = rutarecibo;
    }
    
}
