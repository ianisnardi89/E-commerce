package com.informatorio.ejemplo.repository;

import com.informatorio.ejemplo.entity.Linea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaRepository extends JpaRepository<Linea, Long> {
    Linea getById(Long id);
}
