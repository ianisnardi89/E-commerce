package com.informatorio.ejemplo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Linea {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private Long producto_id;

    @Column(nullable = false, updatable = false)
    private Integer cantidad;

    @Column(nullable = false, updatable = false)
    private Double precio;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Orden orden;




    public Long getId(){return id;}
    public Long getProductoId(){return producto_id;}
    public Integer getCantidad(){return cantidad;}
    public Double getPrecio(){return precio;}
    @Transient
    public Double getSubTotal(){return precio*cantidad;}

    public void setProductoItd(Long producto_id){this.producto_id = producto_id;}
    public void setCantidad(Integer cantidad){this.cantidad = cantidad;}
    public void setPrecio(Double precio) {this.precio = precio;}
    public void setOrden(Orden orden){this.orden = orden;}
}
