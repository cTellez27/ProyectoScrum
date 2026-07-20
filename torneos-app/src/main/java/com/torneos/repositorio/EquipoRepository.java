package com.torneos.repositorio;

import com.torneos.modelo.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository (Opcional, JpaRepository ya lo incluye)
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    
    // Con esto ya tienes:
    // .save(equipo) -> Guarda o actualiza
    // .findById(id) -> Busca uno
    // .findAll() -> Busca todos
    // .deleteById(id) -> Borra
}