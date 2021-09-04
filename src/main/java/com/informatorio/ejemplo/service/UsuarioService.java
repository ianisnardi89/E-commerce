package com.informatorio.ejemplo.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.informatorio.ejemplo.entity.Usuario;
import com.informatorio.ejemplo.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    private static UsuarioRepository usuarioRepository;

    public static Date creacion(){
        return Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Usuario modificarCredenciales(Usuario user, Usuario usuario){
        user.setPass(usuario.pass());
        user.setEmail(usuario.getEmail());
        return usuarioRepository.save(user);
    }

    public static Usuario modificarDatos(Usuario user, Usuario usuario){
        user.setNombre(usuario.getNombre());
        user.setApellido(usuario.getApellido());
        user.setDireccion(usuario.getDireccion());
        return usuarioRepository.save(user);
    }






}
