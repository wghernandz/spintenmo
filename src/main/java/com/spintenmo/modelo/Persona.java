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
@Table(name="persona")
public class Persona implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_PERSONA")
    @SequenceGenerator(name="SEQ_ID_PERSONA",sequenceName="seq_idpersona", allocationSize=1)
    private int id;
    @OneToOne
    @JoinColumn(name="idmunicipio")
    private Municipio municipio;
    @Column(name="primernombre")
    private String primernombre;
    @Column(name="segundonombre")
    private String segundonombre;
    @Column(name="primerapellido")
    private String primerapellido;
    @Column(name="segundoapellido")
    private String segundoapellido;
    @Column(name="ptelefono1")
    private String ptelefono1;
    @Column(name="ptelefono2")
    private String ptelefono2;
    @Column(name="pnit")
    private String pnit;
    @Column(name="pdui")
    private String pdui;
    @Column(name="direccion")
    private String direccion;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="fechaingreso")
    private Date fechaingreso;
    @Column(name="email")
    private String email;
    @Column(name="estado")
    private int estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getPrimernombre() {
        return primernombre;
    }

    public void setPrimernombre(String primernombre) {
        this.primernombre = primernombre;
    }

    public String getSegundonombre() {
        return segundonombre;
    }

    public void setSegundonombre(String segundonombre) {
        this.segundonombre = segundonombre;
    }

    public String getPrimerapellido() {
        return primerapellido;
    }

    public void setPrimerapellido(String primerapellido) {
        this.primerapellido = primerapellido;
    }

    public String getSegundoapellido() {
        return segundoapellido;
    }

    public void setSegundoapellido(String segundoapellido) {
        this.segundoapellido = segundoapellido;
    }

    public String getPtelefono1() {
        return ptelefono1;
    }

    public void setPtelefono1(String ptelefono1) {
        this.ptelefono1 = ptelefono1;
    }

    public String getPtelefono2() {
        return ptelefono2;
    }

    public void setPtelefono2(String ptelefono2) {
        this.ptelefono2 = ptelefono2;
    }

    public String getPnit() {
        return pnit;
    }

    public void setPnit(String pnit) {
        this.pnit = pnit;
    }

    public String getPdui() {
        return pdui;
    }

    public void setPdui(String pdui) {
        this.pdui = pdui;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
}
