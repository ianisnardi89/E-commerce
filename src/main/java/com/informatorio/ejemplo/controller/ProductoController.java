package com.informatorio.ejemplo.controller;

import java.util.List;

import com.informatorio.ejemplo.entity.Producto;
import com.informatorio.ejemplo.repository.ProductoRepository;
import com.informatorio.ejemplo.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ProductoController {
    

    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping(value = "api/producto")
    public Producto crearProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    @GetMapping(value = "api/producto")
    public List<Producto> getProductos(){
        return productoRepository.findAll();
    }

    @GetMapping(value = "api/producto/{id_producto}")
    public Producto getProducto(@PathVariable("id_producto")Long id_producto){
        return productoRepository.getById(id_producto);
    }

    @PutMapping(value = "api/producto/{id_producto}")
    public Producto modifProducto(@PathVariable("id_producto")Long id_producto, @RequestBody Producto producto_modif){
        Producto producto = productoRepository.getById(id_producto);
        return ProductoService.modificarDatosProducto(producto, producto_modif);
    }

    @DeleteMapping(value = "api/producto/{id_producto}")
    public void deleteProducto(@PathVariable("id_producto")Long id){
        Producto producto = productoRepository.getById(id);
        productoRepository.delete(producto);
    }

    @GetMapping(value = "api/producto/buscarContiene")
    public List<Producto> buscarProductoPorCaracteresEnNombre(@RequestParam String nombre){
        return productoRepository.findByNombreContaining(nombre);
    }

    @GetMapping(value = "api/producto/publicado")
    public List<Producto> buscarProductosPublicados(){
        return productoRepository.findByPublicadoTrue();
    }

    @GetMapping(value = "api/producto/noPublicado")
    public List<Producto> buscarProductosNoPublicados(){
        return productoRepository.findByPublicadoFalse();
    }














}
