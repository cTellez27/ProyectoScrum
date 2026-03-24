package com.torneos.controller;

import com.torneos.dto.EquipoDTO;
import com.torneos.dto.JugadorDTO;
import com.torneos.servicio.EquipoService;
import com.torneos.servicio.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/equipos/{idEquipo}/jugadores")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;

    @Autowired
    private EquipoService equipoService;


    @GetMapping
    public String gestionarJugadores(@PathVariable Long idEquipo, Model model) {
        
        EquipoDTO equipo = equipoService.buscarEquipoPorId(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        
        List<JugadorDTO> jugadores = jugadorService.listarJugadoresPorEquipo(idEquipo);

        model.addAttribute("equipo", equipo);
        model.addAttribute("jugadores", jugadores);
        model.addAttribute("nuevoJugador", new JugadorDTO());
        model.addAttribute("idEquipo", idEquipo);

        return "jugador/gestion-jugadores";
    }

    @PostMapping("/guardar")
    public String guardarJugador(@PathVariable Long idEquipo, 
                                 @ModelAttribute("nuevoJugador") JugadorDTO jugadorDTO) {
        
        jugadorService.guardarJugador(jugadorDTO, idEquipo);
        
        return "redirect:/equipos/" + idEquipo + "/jugadores";
    }

    @GetMapping("/eliminar/{idJugador}")
    public String eliminarJugador(@PathVariable Long idEquipo,
                                  @PathVariable Long idJugador) {
        
        jugadorService.eliminarJugador(idJugador);
        
        return "redirect:/equipos/" + idEquipo + "/jugadores";
    }

}