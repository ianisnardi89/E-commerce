package com.informatorio.ejemplo.repository;

import java.util.List;

import com.informatorio.ejemplo.entity.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Producto getById(Long id);
    List<Producto> findByNombreContaining(String nombre);
    List<Producto> findByPublicadoTrue();
    List<Producto> findByPublicadoFalse();
    List<Producto> findByNombreStartingWith(String nombre);

}
