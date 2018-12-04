/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="ordentrabajo")
public class ordenTrabajo implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_ORDENTRABAJO")
    @SequenceGenerator(name="SEQ_ID_ORDENTRABAJO",sequenceName="seq_idordentrabajo", allocationSize=1)
    private int id;
    @OneToOne
    @JoinColumn(name="idcliente")
    private aseguradoraCliente aseguradoracliente;
    @OneToOne
    @JoinColumn(name="idmodelo")
    private Modelo modelot;
    @OneToOne
    @JoinColumn(name="idanio")
    private Anio aniot;
    @Column(name="placa")
    private String placa;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="fechaautorizado")
    private Date fechaautorizado;
    @Column(name="esreclamo")
    private String esreclamo;
    @OneToOne
    @JoinColumn(name="idcolor")
    private colorVehiculo colorvehiculo; 
    @Column(name="codigo")
    private String codigo;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="fechaingreso")
    private Date fechaingreso;
    @Column(name="estado")
    private String estado;
    @Column(name="tipoorden")
    private int tipoorden;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public aseguradoraCliente getAseguradoracliente() {
        return aseguradoracliente;
    }

    public void setAseguradoracliente(aseguradoraCliente aseguradoracliente) {
        this.aseguradoracliente = aseguradoracliente;
    }

    public Modelo getModelot() {
        return modelot;
    }

    public void setModelot(Modelo modelot) {
        this.modelot = modelot;
    }

    public Anio getAniot() {
        return aniot;
    }

    public void setAniot(Anio aniot) {
        this.aniot = aniot;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getFechaautorizado() {
        return fechaautorizado;
    }

    public void setFechaautorizado(Date fechaautorizado) {
        this.fechaautorizado = fechaautorizado;
    }

    public String getEsreclamo() {
        return esreclamo;
    }

    public void setEsreclamo(String esreclamo) {
        this.esreclamo = esreclamo;
    }

    public colorVehiculo getColorvehiculo() {
        return colorvehiculo;
    }

    public void setColorvehiculo(colorVehiculo colorvehiculo) {
        this.colorvehiculo = colorvehiculo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    } 

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getTipoorden() {
        return tipoorden;
    }

    public void setTipoorden(int tipoorden) {
        this.tipoorden = tipoorden;
    }
    
}
