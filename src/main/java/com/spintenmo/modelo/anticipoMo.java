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
@Table(name="anticipomo")
public class anticipoMo implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_PERSONA")
    @SequenceGenerator(name="SEQ_ID_PERSONA",sequenceName="seq_idpersona", allocationSize=1)
    private int id;
    @JoinColumn(name="idoperacionesot")
    private operacionesOrdent operacionesordent;
    @Column(name="montoanticipo")
    private BigDecimal montoanticipo;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="fechaaplicacionplanilla")
    private Date fechaaplicacionplanilla;
    @Column(name="montopendiente")
    private BigDecimal montopendiente;

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

    public BigDecimal getMontoanticipo() {
        return montoanticipo;
    }

    public void setMontoanticipo(BigDecimal montoanticipo) {
        this.montoanticipo = montoanticipo;
    }

    public Date getFechaaplicacionplanilla() {
        return fechaaplicacionplanilla;
    }

    public void setFechaaplicacionplanilla(Date fechaaplicacionplanilla) {
        this.fechaaplicacionplanilla = fechaaplicacionplanilla;
    }

    public BigDecimal getMontopendiente() {
        return montopendiente;
    }

    public void setMontopendiente(BigDecimal montopendiente) {
        this.montopendiente = montopendiente;
    }
      
}