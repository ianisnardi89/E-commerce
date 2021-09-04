package com.informatorio.ejemplo.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.informatorio.ejemplo.entity.Carrito;
import com.informatorio.ejemplo.entity.Detalle;
import com.informatorio.ejemplo.entity.Producto;
import com.informatorio.ejemplo.entity.Usuario;
import com.informatorio.ejemplo.repository.CarritoRepository;
import com.informatorio.ejemplo.repository.DetalleRepository;
import com.informatorio.ejemplo.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.informatorio.ejemplo.service.DetalleService.*;

@Service
public class CarritoService {
    


    @Autowired
    private static CarritoRepository carritoRepository;
    
        
    @Autowired
    private static DetalleRepository detalleRepository;

    @Autowired
    private static ProductoRepository productoRepository;

    public static Date creacion(){
        return Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Boolean nuevo_carrito(Usuario user){
        List<Carrito> carritosDelUser = user.getCarritos();
        if(carritosDelUser.size() >= 1){
            Carrito ult = carritosDelUser.get(carritosDelUser.size() - 1);
            ult.setEstado(false);
        }
        return true;
    }

    public static List<Detalle> evaluarCerrarCarrito(Carrito carrito){
        if(carrito.getDetalle().size() >= 1){
            carrito.setEstado(false);
            carritoRepository.save(carrito);
            return carrito.getDetalle();
        }
        return null;
    }

    public static void hacerCarritoComprado(Carrito carrito){
        carrito.setEstado(false);
        carritoRepository.save(carrito);
    }

    public static Detalle evaluarAnadirProducto(Carrito carrito, Long id_producto){
        if(carrito.getEstado()){
            Producto producto = productoRepository.getById(id_producto);
            Detalle detalle = generarDetalle(producto, carrito);
            existenciaDeProductosEnCarrito(carrito, producto, detalle);
            return detalle;
        }
        return null;
    }

    public static Detalle evaluarRetirarProducto(Carrito carrito, Long id_producto){
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
    
    public static Detalle evaluarIncrementarProducto(Carrito carrito, Long id_producto){
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

    public static Detalle intentarSacarProducto(Carrito carrito, Long id_producto){
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






}
