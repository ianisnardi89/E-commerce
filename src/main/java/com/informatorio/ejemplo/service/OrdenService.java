package com.informatorio.ejemplo.service;

import java.util.List;
import java.util.Random;

import com.informatorio.ejemplo.entity.Carrito;
import com.informatorio.ejemplo.entity.Detalle;
import com.informatorio.ejemplo.entity.Estado;
import com.informatorio.ejemplo.entity.Orden;
import com.informatorio.ejemplo.entity.Rol;
import com.informatorio.ejemplo.entity.Usuario;
import com.informatorio.ejemplo.repository.CarritoRepository;
import com.informatorio.ejemplo.repository.OrdenRepository;
import com.informatorio.ejemplo.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenService {
    
    @Autowired
    private static UsuarioRepository usuarioRepository;

    @Autowired
    private static OrdenRepository ordenRepository;

    @Autowired
    private static CarritoRepository carritoRepository;

    public static Long generarNumeroDeFactura(Carrito carrito){
        Random random = new Random();
        Long numero = carrito.getUsuario().getId()*carrito.getId()*random.nextInt();
        return numero;
    }

    public static void cargarLineas(Carrito carrito, Orden orden){
        List<Detalle> detallesCarrito = carrito.getDetalle();

        for(Detalle d : detallesCarrito){
            orden.addLinea(LineaService.crearLinea(d, orden));
            ordenRepository.save(orden);
        }
    }

    public static Orden tratarCreacionOrden(Orden orden, Long id_carrito){
        Carrito carrito = carritoRepository.getById(id_carrito);
        if(carrito.getEstado() && (carrito.getDetalle().size()>=1)){
            orden.setCarritoId(id_carrito);
            orden.setUsuario(carrito.getUsuario());
            orden.setObservacion(orden.getObservacion());
            orden.setNumero(generarNumeroDeFactura(carrito));
            cargarLineas(carrito, orden);
            CarritoService.hacerCarritoComprado(carrito);
            return ordenRepository.save(orden);
        }
        return null;
    }

    public static Orden tratarCancelarOrden(Orden orden, Long id_usuario){
        Usuario usuario = usuarioRepository.getById(id_usuario);

        if((usuario.getRol()==Rol.Vendedor) && (orden.getEstado()==Estado.Confirmada)){
            orden.setEstado(Estado.Cancelada);
            return ordenRepository.save(orden);
        }
        return null;
    }


    public static Orden generarOrden(Orden orden, Long id_carrito){
        Carrito carrito = carritoRepository.getById(id_carrito);
        if(carrito.getEstado() && (carrito.getDetalle().size()>=1)){
            orden.setCarritoId(id_carrito);
            orden.setUsuario(carrito.getUsuario());
            orden.setObservacion(orden.getObservacion());
            Random random = new Random();
            Long numero = carrito.getUsuario().getId()*carrito.getId()*random.nextInt();
            orden.setNumero(numero);
            List<Detalle> detallesCarrito = carrito.getDetalle();
            for(Detalle d : detallesCarrito){
                orden.addLinea(LineaService.crearLinea(d,orden));
                ordenRepository.save(orden);
            }
            CarritoService.hacerCarritoComprado(carrito);
            return ordenRepository.save(orden);
        }
        return null;
    }




}
