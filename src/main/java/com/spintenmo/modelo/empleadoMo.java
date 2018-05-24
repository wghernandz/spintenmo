/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="empleadomo")
public class empleadoMo implements Serializable{
    @JoinColumn(name="idpersona")
    private Persona persona;
    @Column(name="cargo")
    private String cargo;
    @Column(name="salariobase")
    private BigDecimal salariobase;

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalariobase() {
        return salariobase;
    }

    public void setSalariobase(BigDecimal salariobase) {
        this.salariobase = salariobase;
    }
    
    
}
