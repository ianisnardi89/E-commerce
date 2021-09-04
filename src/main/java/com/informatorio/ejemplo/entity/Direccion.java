package com.informatorio.ejemplo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Direccion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = true)
    private String numero;

    @Column(nullable = false, updatable = true)
    private String calle;

    @Column(nullable = false, updatable = true)
    private String barrio;

    @Column(nullable = false, updatable = true)
    private String ciudad;

    @Column(nullable = false, updatable = true)
    private String provincia;

    @Column(nullable = false, updatable = true)
    private String pais;

    @OneToOne(mappedBy = "direccion")
    private Usuario usuario;

    public Long getId() {return id;}

    
    public String getNumero() {return numero;}
    public void setNumero(String numero) {this.numero = numero;}


    public String getCalle() {return calle;}
    public void setCalle(String calle) {this.calle = calle;}


    public String getBarrio() {return barrio;}
    public void setBarrio(String barrio) {this.barrio = barrio;}


    public String getCiudad() {return ciudad;}
    public void setCiudad(String ciudad) {this.ciudad = ciudad;}


    public String getProvincia() {return provincia;}
    public void setProvincia(String provincia) {this.provincia = provincia;}


    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}


    public String getPais() {return pais;}
    public void setPais(String pais) {this.pais = pais;}

    






}
