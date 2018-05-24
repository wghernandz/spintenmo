/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="aseguradoracliente")
public class aseguradoraCliente implements Serializable{
 @Id
 @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_ASEGURADORACLIENTE")
 @SequenceGenerator(name="SEQ_ID_ASEGURADORACLIENTE",sequenceName="seq_idaseguradoracliente", allocationSize=1)
 private int id;
 @Column(name="nombrecliente")
 private String nombrecliente;
 @Column(name="telefono1")
 private String telefono1;
 @Column(name="telefono2")
 private String telefono2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrecliente() {
        return nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.nombrecliente = nombrecliente;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }
 
 
         
    
}
