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
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="aux_detallepagoorden")
public class Aux_detallepagoorden implements Serializable {
    @Id
    private int id;
    @Column(name="nombreoperario")
    private String nombreoperario;
    @Column(name="estado")
    private String estado;
    @Column(name="monto")
    private BigDecimal monto;
    @Column(name="montopendiente")
    private BigDecimal montopendiente;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="fechaaplicacion")
    private Date fechaaplicacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreoperario() {
        return nombreoperario;
    }

    public void setNombreoperario(String nombreoperario) {
        this.nombreoperario = nombreoperario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getMontopendiente() {
        return montopendiente;
    }

    public void setMontopendiente(BigDecimal montopendiente) {
        this.montopendiente = montopendiente;
    }

    public Date getFechaaplicacion() {
        return fechaaplicacion;
    }

    public void setFechaaplicacion(Date fechaaplicacion) {
        this.fechaaplicacion = fechaaplicacion;
    }
}
