/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ordentrabajo")
public class ordenTrabajo implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_ORDENTRABAJO")
    @SequenceGenerator(name="SEQ_ID_ORDENTRABAJO",sequenceName="seq_idordentrabajo", allocationSize=1)
    private int id;
    @JoinColumn(name="idcliente")
    private aseguradoraCliente aseguradoracliente;
    @JoinColumn(name="idmodelo")
    private Modelo modelot;
    @JoinColumn(name="idanio")
    private Anio aniot;
    @Column(name="marca")
    private String marca;
    @Column(name="modelo")
    private String modelo;
    @Column(name="placa")
    private String placa;
    @Column(name="anio")
    private String anio;
    @Column(name="color")
    private String color;
    @Column(name="aseguradora")
    private String aseguradora;
    @Column(name="fechaautorizado")
    private Date fechaautorizado;
    @Column(name="esreclamo")
    private String esreclamo;
    @JoinColumn(name="idcolor")
    private colorVehiculo colorvehiculo; 

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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAseguradora() {
        return aseguradora;
    }

    public void setAseguradora(String aseguradora) {
        this.aseguradora = aseguradora;
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
    
    
}
