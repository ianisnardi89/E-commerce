package com.informatorio.ejemplo.controller;

import java.util.List;


import com.informatorio.ejemplo.entity.Carrito;
import com.informatorio.ejemplo.entity.Detalle;
import com.informatorio.ejemplo.entity.Producto;
import com.informatorio.ejemplo.repository.CarritoRepository;
import com.informatorio.ejemplo.repository.DetalleRepository;
import com.informatorio.ejemplo.repository.ProductoRepository;
import com.informatorio.ejemplo.repository.UsuarioRepository;
import static com.informatorio.ejemplo.service.CarritoService.nuevo_carrito;

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

import com.informatorio.ejemplo.entity.Usuario;



@RestController
public class CarritoController {
    
    
    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleRepository detalleRepository;


    @PostMapping(value = "api/usuario/{id_usuario}/carrito")
    @ResponseStatus(HttpStatus.CREATED)
    public Carrito crearCarrito(@PathVariable("id_usuario")Long id_usuario, @RequestBody Carrito carrito){
        Usuario user = usuarioRepository.getById(id_usuario);
        carrito.setUsuario(user);
        nuevo_carrito(user);
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
        if(carrito.getDetalle().size() >= 1){
            carrito.setEstado(false);
            carritoRepository.save(carrito);
            return carrito.getDetalle();
        }
        return null;
    }

    @DeleteMapping(value = "api/carrito/{id_carrito}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCarrito(@PathVariable("id_carrito")Long id_carrito){
        carritoRepository.deleteById(id_carrito);
    }

    @PutMapping(value = "api/carrito/{id_carrito}/producto/{id_producto}/remove")
    public Detalle removeProducto(@PathVariable("id_carrito")Long id_carrito, @PathVariable("id_producto")Long id_producto){
        Carrito carrito = carritoRepository.getById(id_carrito);
        if(carrito.getEstado()){
            Producto producto = productoRepository.getById(id_producto);
            List<Detalle> detallesDelCarrito = carrito.getDetalle();
            for(Detalle d : detallesDelCarrito){
                if(d.getProducto().getId().equals(producto.getId())){
                    carrito.removeDetalle(d);
                    detalleRepository.delete(d);
                    return d;
                }
            }
        }
        return null;
    }


    @PutMapping(value = "api/carrito/{id_carrito}/producto/{id_producto}/add")
    public Detalle addProducto(@PathVariable("id_carrito")Long id_carrito, @PathVariable("id_producto")Long id_producto){
        Carrito carrito = carritoRepository.getById(id_carrito);
        if(carrito.getEstado()){
            Producto producto = productoRepository.getById(id_producto);
            Detalle detalle = new Detalle();
            if(producto.getPublicado()){
                detalle.setProducto(producto);
                detalle.setCarrito(carrito);
                
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
        return null;
    }

    @PutMapping(value = "api/carrito/{id_carrito}/producto/{id_producto}/suma")
    public Detalle incrementarProducto(@PathVariable("id_carrito")Long id_carrito, @PathVariable("id_producto")Long id_producto){
        Carrito carrito = carritoRepository.getById(id_carrito);
        if(carrito.getEstado()){
            Producto producto = productoRepository.getById(id_producto);
            List<Detalle> detallesDelCarrito = carrito.getDetalle();
            for(Detalle d : detallesDelCarrito){
                if(d.getProducto().getId().equals(producto.getId())){
                    d.incCantidad();
                    return detalleRepository.save(d);
                }
            }
        }
        return null;
    }


    @PutMapping(value = "api/carrito/{id_carrito}/producto/{id_producto}/baja")
    public Detalle deleteProducto(@PathVariable("id_carrito")Long id_carrito, @PathVariable("id_producto")Long id_producto){
        Carrito carrito = carritoRepository.getById(id_carrito);
        if(carrito.getEstado()){
            Producto producto = productoRepository.getById(id_producto);
            List<Detalle> detallesDelCarrito = carrito.getDetalle();
            for(Detalle d : detallesDelCarrito){
                if(d.getProducto().getId().equals(producto.getId())){
                    if(d.getCantidad() == 1){
                        carrito.removeDetalle(d);
                        detalleRepository.save(d);
                        break;
                    }else{
                        d.decCantidad();
                        return detalleRepository.save(d);
                    }
                }
            }
        }
        return null;
    }






























}
