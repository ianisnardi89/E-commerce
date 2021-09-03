package com.informatorio.ejemplo.repository;

import com.informatorio.ejemplo.entity.Detalle;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleRepository extends JpaRepository<Detalle, Long> {
    Detalle getById(Long id);
    
}
