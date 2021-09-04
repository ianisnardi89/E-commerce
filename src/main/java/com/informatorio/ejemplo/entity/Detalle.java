package com.informatorio.ejemplo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Detalle {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="producto", referencedColumnName = "id")
    private Producto producto;  

    @Column(nullable = false, updatable = true)
    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Carrito carrito;

    public Long getId() {return id;}
    public Producto getProducto(){return producto;}
    public Integer getCantidad(){return cantidad;}
    @Transient
    public Double getSubTotal(){return producto.getPrecioUnitario()*cantidad;}


    public void setProducto(Producto producto){this.producto = producto;}
    public void setCarrito(Carrito carrito){this.carrito = carrito;}
    public void incCantidad(){this.cantidad += 1;}
    public void decCantidad(){this.cantidad -= 1;}

    public Detalle(){this.cantidad =1;}

}
