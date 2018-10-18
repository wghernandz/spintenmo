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
@Table(name="departamento")
public class Departamento implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_DEPARTAMENTO")
    @SequenceGenerator(name="SEQ_ID_DEPARTAMENTO",sequenceName="seq_iddepto", allocationSize=1)
    private int id;
    @Column(name="nombredepto")
    private String nombredepto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombredepto() {
        return nombredepto;
    }

    public void setNombredepto(String nombredepto) {
        this.nombredepto = nombredepto;
    }
    
}
