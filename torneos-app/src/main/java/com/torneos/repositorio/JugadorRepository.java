package com.torneos.repositorio;

import com.torneos.modelo.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    /**
     * ¡Método importante!
     * Spring Data JPA creará automáticamente la consulta para buscar
     * todos los jugadores que pertenezcan a un 'idEquipo' específico.
     */
    List<Jugador> findByEquipo_Id(Long idEquipo);
}