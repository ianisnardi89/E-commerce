package com.informatorio.ejemplo.service;

import com.informatorio.ejemplo.entity.Producto;
import com.informatorio.ejemplo.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    
    @Autowired
    private static ProductoRepository productoRepository;

    public static Producto modificarDatosProducto(Producto prod, Producto producto){
        prod.setNombre(producto.getNombre());
        producto.setContenido(producto.getContenido());
        producto.setPrecioUnitario(producto.getPrecioUnitario());
        producto.setPublicado(producto.getPublicado());
        producto.setDescripcion(producto.getDescripcion());
        return productoRepository.save(prod);
    }










}
