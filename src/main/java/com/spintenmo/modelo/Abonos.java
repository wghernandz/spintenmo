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
@Table(name="abonos")
public class Abonos implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_ABONOS")
    @SequenceGenerator(name="SEQ_ID_ABONOS",sequenceName="seq_idabono", allocationSize=1)
    private int id;
    @Column(name="montoabono")
    private BigDecimal montoabono;
    @Column(name="fechaabono")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaabono;
    @ManyToOne
    @JoinColumn(name="iddescuento")
    private Descuentos descuentos;
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getMontoabono() {
        return montoabono;
    }

    public void setMontoabono(BigDecimal montoabono) {
        this.montoabono = montoabono;
    }

    public Date getFechaabono() {
        return fechaabono;
    }

    public void setFechaabono(Date fechaabono) {
        this.fechaabono = fechaabono;
    }

    public Descuentos getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(Descuentos descuentos) {
        this.descuentos = descuentos;
    }
}
