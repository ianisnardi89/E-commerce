package com.informatorio.ejemplo.entity;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.informatorio.ejemplo.service.CarritoService;


@Entity
public class Orden {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @Column(unique = true, nullable = false)
    private Long numero;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Linea> linea = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private Long carrito_id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Estado estado;
    
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaDeCreacion = CarritoService.creacion();

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Origen origen;

    @Column(nullable = false, length = 200)
    private String observacion;


    public void setUsuario(Usuario usuario){this.usuario = usuario;}
    public void addLinea(Linea linea){this.linea.add(linea);}
    public void setCarritoId(Long carrito_id){this.carrito_id = carrito_id;}
    public void setDescripcion(String observacion){this.observacion = observacion;}
    public void setObservacion(String observacion){this.observacion = observacion;}
    public void setNumero(Long numero){this.numero = numero;}
    public void setEstado(Estado estado){this.estado = estado;}
    public void setOrigen(Origen origen){this.origen = origen;}

    public String getObservacion(){return observacion;}
    public Long getId(){return id;}
    public Long getNumero(){return numero;}
    public Long getUsuarioId(){return usuario.getId();}
    public Estado getEstado(){return estado;}
    @JsonIgnore
    public Usuario getUsuario(){return usuario;}
    public List<Linea> getLinea(){return linea;}
    public Long getCarritoId(){return carrito_id;}
    public Origen getOrigen(){return origen;}
    public Date getFechaDeCreacion(){return fechaDeCreacion;}
    @Transient
    public Double getCostoTotal(){
        Double total = 0.0;
        for (Linea l : this.getLinea()){
            total = l.getSubTotal() + total;
        }
        return total;
    }










}
