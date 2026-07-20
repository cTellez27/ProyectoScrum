package com.torneos.repositorio;

import com.torneos.modelo.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
    
    // Busca todas las tarjetas de un partido específico
    List<Tarjeta> findByPartido_Id(Long idPartido);
}