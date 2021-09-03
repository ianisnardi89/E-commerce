package com.informatorio.ejemplo.controller;

import java.util.List;

import com.informatorio.ejemplo.entity.Orden;
import com.informatorio.ejemplo.entity.Usuario;
import com.informatorio.ejemplo.repository.CarritoRepository;
import com.informatorio.ejemplo.repository.OrdenRepository;
import com.informatorio.ejemplo.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdenController {
    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "api/orden")
    public List<Orden> getOrdenes(){
        return ordenRepository.findAll();
    }

    @GetMapping(value = "api/orden/{id_orden}")
    public Orden getOrden(@PathVariable("id_orden")Long id_orden){
        return ordenRepository.getById(id_orden);
    }

    @PostMapping(value = "api/orden/{id_carrito}")
    public Orden createOrden(@PathVariable("id_carrito")Long id_carrito, @RequestBody Orden orden){
        tratarCreacionOrden(orden, id_carrito);
        return null;
    }

    @PutMapping(value = "api/usuario/{id_usuario/orden/{id_carrito}/close")
    public Orden cancelarOrden(@PathVariable("id_carrito")Long id_carrito, @PathVariable("id_usuario")Long id_usuario){
        Orden orden = ordenRepository.getById(id_carrito);
        tratarCancelarOrden(orden, id_usuario);
        return null;
    }

    @DeleteMapping(value = "api/orden/{id_orden}")
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
