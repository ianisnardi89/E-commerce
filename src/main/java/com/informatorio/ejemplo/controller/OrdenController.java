package com.informatorio.ejemplo.controller;

import java.util.List;

import com.informatorio.ejemplo.entity.Carrito;
import com.informatorio.ejemplo.entity.Detalle;
import com.informatorio.ejemplo.entity.Orden;
import com.informatorio.ejemplo.entity.Usuario;
import com.informatorio.ejemplo.repository.CarritoRepository;
import com.informatorio.ejemplo.repository.OrdenRepository;
import com.informatorio.ejemplo.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.informatorio.ejemplo.service.OrdenService.*;
import static com.informatorio.ejemplo.service.LineaService.*;
import static com.informatorio.ejemplo.entity.Estado.*;
import static com.informatorio.ejemplo.entity.Rol.*;

@RestController
public class OrdenController {

    @Autowired
    private OrdenRepository ordenRepository;


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarritoRepository carritoRepository;


    @GetMapping(value = "api/orden")
    public List<Orden> getOrdenes(){
        return ordenRepository.findAll();
    }

    @GetMapping(value = "api/orden/{id_orden}")
    public Object getOrden(@PathVariable("id_orden")Long id_orden){
        
        return ordenRepository.findById(id_orden);
        
    }

    @PostMapping(value = "api/orden/{id_carrito}")
    @ResponseStatus(HttpStatus.CREATED)
    public Orden createOrden(@PathVariable("id_carrito")Long id_carrito, @RequestBody Orden orden){
        Carrito carrito = carritoRepository.getById(id_carrito);

        if(carrito.getEstado() && (carrito.getDetalle().size() >= 1)){
            orden.setCarritoId(id_carrito);
            orden.setEstado(Confirmada);
            orden.setUsuario(carrito.getUsuario());
            orden.setObservacion(orden.getObservacion());
            orden.setNumero(generarNumeroDeFactura(carrito));

            List<Detalle> detallesDelCarrito = carrito.getDetalle();
            for(Detalle d : detallesDelCarrito){
                orden.addLinea(crearLinea(d,orden));
                ordenRepository.save(orden);
            }
            carrito.setEstado(false);
            carritoRepository.save(carrito);
            
        }
        return ordenRepository.save(orden);
    }

    @PutMapping(value = "api/usuario/{id_usuario}/orden/{id_orden}/close")
    public Orden cancelarOrden(@PathVariable("id_orden")Long id_orden, @PathVariable("id_usuario")Long id_usuario){
        Orden orden = ordenRepository.getById(id_orden);
        Usuario user = usuarioRepository.getById(id_usuario);

        if((user.getRol() == Vendedor) && (orden.getEstado() == Confirmada)){
            orden.setEstado(Cancelada);
        }

        return ordenRepository.save(orden);
    }

    @DeleteMapping(value = "api/orden/{id_orden}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarOrden(@PathVariable("id_orden")Long id_orden){
        Orden orden = ordenRepository.getById(id_orden);
        ordenRepository.delete(orden);
    }

    @GetMapping(value = "api/usuario/{id_usuario}/orden")
    public List<Orden> getOrdenesDeUsusario(@PathVariable("id_usuario")Long id_usuario){
        Usuario user = usuarioRepository.getById(id_usuario);
        return ordenRepository.findByUsuario(user);
    }


}
