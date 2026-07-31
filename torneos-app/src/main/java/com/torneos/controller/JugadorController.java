package com.torneos.controller;

import com.torneos.dto.EquipoDTO;
import com.torneos.dto.JugadorDTO;
import com.torneos.dto.PartidoDTO;
import com.torneos.dto.TarjetaDTO;
import com.torneos.servicio.EquipoService;
import com.torneos.servicio.JugadorService;
import com.torneos.servicio.PartidoService;
import com.torneos.servicio.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/equipos/{idEquipo}/jugadores")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private PartidoService partidoService;

    @Autowired
    private TarjetaService tarjetaService;

    @GetMapping
    public String gestionarJugadores(@PathVariable Long idEquipo, Model model) {
        
        EquipoDTO equipo = equipoService.buscarEquipoPorId(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        
        List<JugadorDTO> jugadores = jugadorService.listarJugadoresPorEquipo(idEquipo);
        List<PartidoDTO> partidosEquipo = partidoService.listarPartidosPorEquipo(idEquipo);

        model.addAttribute("equipo", equipo);
        model.addAttribute("jugadores", jugadores);
        model.addAttribute("partidosEquipo", partidosEquipo);
        model.addAttribute("tienePartidos", !partidosEquipo.isEmpty());
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

    @PostMapping("/editar/{idJugador}")
    public String editarJugador(@PathVariable Long idEquipo,
                                @PathVariable Long idJugador,
                                @ModelAttribute JugadorDTO jugadorDTO,
                                RedirectAttributes attributes) {
        jugadorService.actualizarJugador(idJugador, jugadorDTO);
        attributes.addFlashAttribute("mensajeExito", "Jugador actualizado con éxito.");
        return "redirect:/equipos/" + idEquipo + "/jugadores";
    }

    @PostMapping("/tarjetas/registrar")
    public String registrarTarjetaDesdeJugador(@PathVariable Long idEquipo,
                                               @ModelAttribute TarjetaDTO tarjetaDTO,
                                               RedirectAttributes attributes) {
        tarjetaService.registrarTarjeta(tarjetaDTO);

        attributes.addFlashAttribute("mensajeExito", "Tarjeta registrada para " + tarjetaDTO.getIdJugador() + ".");
        return "redirect:/equipos/" + idEquipo + "/jugadores";
    }

    @GetMapping("/eliminar/{idJugador}")
    public String eliminarJugador(@PathVariable Long idEquipo,
                                  @PathVariable Long idJugador) {
        
        jugadorService.eliminarJugador(idJugador);
        
        return "redirect:/equipos/" + idEquipo + "/jugadores";
    }

}