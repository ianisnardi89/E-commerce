package com.informatorio.ejemplo.controller;

import java.util.Date;
import java.util.List;

import com.informatorio.ejemplo.entity.Usuario;
import com.informatorio.ejemplo.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class UsuarioController {
    
    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping(value = "api/usuario")
    public Usuario crearUsuario (@RequestBody Usuario usuario){return usuarioRepository.save(usuario);}

    @GetMapping(value = "api/usuario")
    public List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }

    @DeleteMapping(value = "api/usuario/{usuario_id}")
    public void deleteUsuario(@PathVariable("usuario_id") Long id){
        Usuario usuario = usuarioRepository.getById(id);
        usuarioRepository.delete(usuario);
    }

    @PutMapping(value = "api/usuario/{usuario_id}")
    public Usuario modifUsuario(@PathVariable("usuario_id") Long id, @RequestBody Usuario usuario){
        Usuario user = usuarioRepository.getById(id);
        return modificarDatos(user, usuario);
    }

    @PutMapping(value = "api/usuario/{usuario_id}/credencial")
    public Usuario modifUsuarioCredenciales(@PathVariable("usuario_id") Long id, @RequestBody Usuario usuario){
        Usuario user = usuarioRepository.getById(id);
        return modificarCredenciales(user, usuario);
    }

    @GetMapping(value = "api/usuario/direccion")
    public List<Usuario> buscarUsuariosDeCiudad(@RequestParam String ciudad){
        return usuarioRepository.getByDireccionCiudad(ciudad);
    }

    @GetMapping(value = "api/usuario/direccion/resistencia")
    public List<Usuario> buscarUsuariosDeResistencia(){
        return usuarioRepository.getByDireccionCiudad("Resistencia");
    }
    
    @GetMapping(value = "api/usuario/buscar/fechaDesde")
    public List<Usuario> buscarUsuariosPorFechaAltaDesde(@RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy")Date fecha){
        return usuarioRepository.getByFechaDeCreacionAfter(fecha);
    }

    @GetMapping(value = "api/usuario/buscar/fechaDeCreacion")
    public List<Usuario> buscarUsuariosPorFecha(@RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy")Date fecha){
        return usuarioRepository.getByFechaDeCreacion(fecha);
    }






}
