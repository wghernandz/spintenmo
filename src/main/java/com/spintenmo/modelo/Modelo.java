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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="modelo")
public class Modelo implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_MODELO")
    @SequenceGenerator(name="SEQ_ID_MODELO",sequenceName="seq_idmodelo", allocationSize=1)
    private int Id;
    @ManyToOne
    @JoinColumn(name="idmarca")
    private Marca marca;
    @Column(name="nombremodelo")
    private String nombremodelo;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getNombremodelo() {
        return nombremodelo;
    }

    public void setNombremodelo(String nombremodelo) {
        this.nombremodelo = nombremodelo;
    }
    
}
