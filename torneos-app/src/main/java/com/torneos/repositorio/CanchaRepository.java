// com.torneos.repositorio.CanchaRepository.java
package com.torneos.repositorio;

import com.torneos.modelo.Cancha; // <-- REVISA ESTE IMPORT
import org.springframework.data.jpa.repository.JpaRepository;

public interface CanchaRepository extends JpaRepository<Cancha, Long> {
    // Déjalo vacío por ahora
}