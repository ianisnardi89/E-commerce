package com.informatorio.ejemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.informatorio.ejemplo.entity.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    Carrito getById(Long id);
    
}
