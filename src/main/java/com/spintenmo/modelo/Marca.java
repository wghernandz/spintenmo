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
@Table(name="marca")
public class Marca implements Serializable{
   @Id
   @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_MARCA")
   @SequenceGenerator(name="SEQ_ID_MARCA",sequenceName="seq_idmarca", allocationSize=1)
   private int id;
   @Column(name="nombremarca")
   private String nombremarca;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombremarca() {
        return nombremarca;
    }

    public void setNombremarca(String nombremarca) {
        this.nombremarca = nombremarca;
    }
   
}
