package com.informatorio.ejemplo.repository;

import java.util.Date;
import java.util.List;

import com.informatorio.ejemplo.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario getById(Long id);
    List<Usuario> getByDireccionCiudad(String ciudad);
    List<Usuario> getByFechaDeCreacionAfter(Date fecha);
    List<Usuario> getByFechaDeCreacion(Date fecha);
    
}
