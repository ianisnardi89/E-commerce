package com.informatorio.ejemplo.controller;

import java.util.List;


import com.informatorio.ejemplo.entity.Carrito;
import com.informatorio.ejemplo.entity.Detalle;
import com.informatorio.ejemplo.repository.CarritoRepository;
import com.informatorio.ejemplo.repository.UsuarioRepository;
import com.informatorio.ejemplo.service.CarritoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.informatorio.ejemplo.entity.Usuario;

@RestController
public class CarritoController {
    
    
    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired

    @PostMapping(value = "api/usuario/{id_usuario}/carrito")
    public Carrito crearCarrito(@PathVariable("id_usuario")Long id_usuario, @RequestBody Carrito carrito){
        Usuario user = usuarioRepository.getById(id_usuario);
        carrito.setUsuario(user);
        CarritoService.nuevo_carrito(user);
        return carritoRepository.save(carrito);
    }

    @GetMapping("api/carrito")
    public List<Carrito> getCarritos(){
        return carritoRepository.findAll();
    }

    @GetMapping(value = "api/carrito/{id_carrito}")
    public Carrito getCarrito(@PathVariable("id_carrito")Long id_carrito){
        return carritoRepository.getById(id_carrito);
    }

    @PutMapping(value = "api/carrito/{id_carrito}/estado")
    public List<Detalle> cerrarCarrito(@PathVariable("id_carrito")Long id_carrito){
        Carrito carrito = carritoRepository.getById(id_carrito);
        CarritoService.evaluarCerrarCarrito(carrito);
        return null;
    }

    @DeleteMapping(value = "api/carrito/{id_carrito}")
    public void deleteCarrito(@PathVariable("id_carrito")Long id_carrito){
        carritoRepository.deleteById(id_carrito);
    }

    @PutMapping(value = "api/carrito/{id_carrito}/producto/{id_producto}/remove")
    public Detalle removeProducto(@PathVariable("id_carrito")Long id_carrito, @PathVariable("id_producto")Long id_producto){
        Carrito carrito = carritoRepository.getById(id_carrito);
        CarritoService.evaluarRetirarProducto(carrito, id_producto);
        return null;
    }


    @PutMapping(value = "api/carrito/{id_carrito}/producto/{id_producto}/add")
    public Detalle addProducto(@PathVariable("id_carrito")Long id_carrito, @PathVariable("id_producto")Long id_producto){
        Carrito carrito = carritoRepository.getById(id_carrito);
        CarritoService.evaluarAnadirProducto(carrito, id_producto);
        return null;
    }

    @PutMapping(value = "api/carrito/{id_carrito}/producto/{id_producto/suma")
    public Detalle incrementarProducto(@PathVariable("id_carrito")Long id_carrito, @PathVariable("id_producto")Long id_producto){
        Carrito carrito = carritoRepository.getById(id_carrito);
        CarritoService.evaluarIncrementarProducto(carrito, id_producto);
        return null;
    }

    @PutMapping(value = "api/carrito/{id_carrito}/producto/{id_producto}/baja")
    public Detalle deleteProducto(@PathVariable("id_carrito")Long id_carrito, @PathVariable("id_producto")Long id_producto){
        Carrito carrito = carritoRepository.getById(id_carrito);
        CarritoService.intentarSacarProducto(carrito, id_producto);
        return null;
    }






























}
