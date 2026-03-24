package com.torneos.repositorio;

import com.torneos.modelo.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PartidoRepository extends JpaRepository<Partido, Long> {

    List<Partido> findByTorneo_Id(Long idTorneo);
    boolean existsByCancha_Id(Long idCancha);
    List<Partido> findByResultado(String resultado);
    List<Partido> findByResultadoNot(String resultado);
}