package com.informatorio.ejemplo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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


@Entity
public class Orden {
    


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Linea> linea = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private Long carrito_id;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaDeCreacion = carritoService.creacion();

    @Column(nullable = false, length = 200)
    private String observacion;


    public void setUsuario(Usuario usuario){this.usuario = usuario;}
    public void addLinea(Linea linea){this.linea.add(linea);}
    public void setCarritoId(Long carrito_id){this.carrito_id = carrito_id;}
    public void setDescripcion(String observacion){this.observacion = observacion;}
    

    public String getObservacion(){return observacion;}
    public Long getId(){return id;}
    public Long getUsuarioId(){return usuario.getId();}
    @JsonIgnore
    public Usuario getUsuario(){return usuario;}
    public List<Linea> getLinea(){return linea;}
    public Long getCarritoId(){return carrito_id;}
    public Date getFechaDeCreacion(){return fechaDeCreacion;}
    @Transient
    public Double getCostoTotal(){
        Double total = 0.0;
        for (Linea l : this.getLinea()){
            total = l.getSubTotal() + total;
        }
        return total
    }










}
