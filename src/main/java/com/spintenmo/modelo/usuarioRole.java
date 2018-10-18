/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenmo.modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="usuariorole")
public class usuarioRole implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_USUARIOROLE")
    @SequenceGenerator(name="SEQ_ID_USUARIOROLE",sequenceName="seq_idusuariorole", allocationSize=1)
    private int id;
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="idrole")
    private Roleus roleus;
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="idusuario")
    private Usuario usuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Roleus getRoleus() {
        return roleus;
    }

    public void setRoleus(Roleus roleus) {
        this.roleus = roleus;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
