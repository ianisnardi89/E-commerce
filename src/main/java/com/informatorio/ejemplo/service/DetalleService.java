package com.informatorio.ejemplo.service;

import java.util.List;

import com.informatorio.ejemplo.entity.Carrito;
import com.informatorio.ejemplo.entity.Detalle;
import com.informatorio.ejemplo.entity.Producto;
import com.informatorio.ejemplo.repository.CarritoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleService {
    
    @Autowired
    private static CarritoRepository carritoRepository;

    public static Detalle generarDetalle(Producto producto, Carrito carrito){
        Detalle detalle = new Detalle();

        detalle.setProducto(producto);
        detalle.setCarrito(carrito);
        return detalle;
    
    
    
    }


    public static Detalle existenciaDeProductosEnCarrito(Carrito carrito, Producto producto, Detalle detalle){
        List<Detalle> detallesDelCarrito = carrito.getDetalle();

        for(Detalle d: detallesDelCarrito){
            if(d.getProducto().equals(producto)){
                return d;
            }
        }
        carrito.addDetalle(detalle);
        carritoRepository.save(carrito);
        return detalle;

    }





}
