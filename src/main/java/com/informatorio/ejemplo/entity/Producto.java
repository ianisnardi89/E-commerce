package com.informatorio.ejemplo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.informatorio.ejemplo.service.UsuarioService;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", })
public class Producto {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    @NotBlank(message = "Debe inrgesar un nombre")
    @Size(min = 2, message = "El nombre debe tener 2 caracteres como minimo")
    private String nombre;

    @Column(nullable = false, unique = false)
    @NotBlank(message = "Debe inrgesar una descripcion") 
    @Size(min = 5, message = "El nombre debe tener 2 caracteres como minimo")
    private String descripcion;

    @Column(nullable = false)
    private Double precioUnitario;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaDeCreacion = UsuarioService.creacion();

    @Column(nullable = false)
    private Boolean publicado;

    @Column(nullable = false)
    private String contenido;

    //ID
    public Long getId(){return id;}
    
    //NOMBRE
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    //DESCRIPCION
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    //PRECIO UNITARIO
    public Double getPrecioUnitario() {return precioUnitario;}
    public void setPrecioUnitario(Double precioUnitario) {this.precioUnitario = precioUnitario;}

    //FECHA DE CREACION
    public Date getFechaDeCreacion() {return fechaDeCreacion;}

    //PUBLICADO
    public Boolean getPublicado() {return publicado;}
    public void setPublicado(Boolean publicado) {this.publicado = publicado;}

    //CONTENIDO
    public String getContenido() {return contenido;}
    public void setContenido(String contenido) {this.contenido = contenido;}

    


























}
