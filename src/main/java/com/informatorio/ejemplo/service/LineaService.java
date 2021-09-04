package com.informatorio.ejemplo.service;

import com.informatorio.ejemplo.entity.Detalle;
import com.informatorio.ejemplo.entity.Linea;
import com.informatorio.ejemplo.entity.Orden;

import org.springframework.stereotype.Service;

@Service
public class LineaService {
    
    public static Linea crearLinea(Detalle detalle, Orden orden){
        Linea linea = new Linea();
        linea.setProductoItd(detalle.getProducto().getId());
        linea.setCantidad(detalle.getCantidad());
        linea.setPrecio(detalle.getProducto().getPrecioUnitario());
        linea.setOrden(orden);
        return linea;
    }

}
