package com.informatorio.ejemplo.entity;

import java.beans.Transient;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.informatorio.ejemplo.service.CarritoService;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", })
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean estado = true;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaDeCreacion = CarritoService.creacion();

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Detalle> detalle = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Origen origen;


    public Long getId(){return id;}
    public Boolean getEstado(){return estado;}
    public Long getUsuarioId(){return usuario.getId();}
    @JsonIgnore
    public Usuario getUsuario(){return usuario;}
    public Date getFechaDeCreacion(){return fechaDeCreacion;}
    public List<Detalle> getDetalle(){return detalle;}
    public Origen getOrigen(){return origen;}
    @Transient
    public Double getCostoTotal(){
        Double total = 0.0;
        for (Detalle d: this.getDetalle()){
            total = d.getSubTotal() + total;
        }
        return total;
    }

    public void addDetalle(Detalle detalle){this.getDetalle().add(detalle);}
    public void removeDetalle(Detalle detalle){
        this.getDetalle().remove(detalle);
    }
    public void setEstado(Boolean estado){this.estado = estado;}
    public void setUsuario(Usuario usuario){this.usuario = usuario;}
    public void setOrigen(Origen origen){this.origen = origen;}






}
