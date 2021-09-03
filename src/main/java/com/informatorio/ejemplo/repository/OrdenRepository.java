package com.informatorio.ejemplo.repository;

import java.util.List;

import com.informatorio.ejemplo.entity.Orden;
import com.informatorio.ejemplo.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
    Orden getById(Long id);
    List<Orden> findByUsuario(Usuario usuario);
    
}
