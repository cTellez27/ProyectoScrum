package com.torneos.controller;

import com.torneos.dto.JugadorDTO;
import com.torneos.dto.PartidoDTO;
import com.torneos.dto.TarjetaDTO;
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
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;
    @Autowired
    private PartidoService partidoService;
    @Autowired
    private JugadorService jugadorService;

    @GetMapping("/partidos/{idPartido}/tarjetas")
    public String gestionarTarjetas(@PathVariable Long idPartido, Model model) {
        
        PartidoDTO partido = partidoService.buscarPorId(idPartido)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));

        List<JugadorDTO> jugadoresLocal = jugadorService.listarJugadoresPorEquipo(partido.getIdEquipoLocal());
        List<JugadorDTO> jugadoresVisitante = jugadorService.listarJugadoresPorEquipo(partido.getIdEquipoVisitante());
        List<TarjetaDTO> tarjetasRegistradas = tarjetaService.listarTarjetasPorPartido(idPartido);

        model.addAttribute("partido", partido);
        model.addAttribute("jugadoresLocal", jugadoresLocal);
        model.addAttribute("jugadoresVisitante", jugadoresVisitante);
        model.addAttribute("tarjetasRegistradas", tarjetasRegistradas);
        
        model.addAttribute("nuevaTarjeta", new TarjetaDTO());

        return "partido/gestion-tarjetas";
    }

    @PostMapping("/partidos/{idPartido}/tarjetas/registrar")
    public String registrarTarjeta(@PathVariable Long idPartido,
                                   @ModelAttribute TarjetaDTO nuevaTarjeta,
                                   RedirectAttributes attributes) {
        
        nuevaTarjeta.setIdPartido(idPartido);
        tarjetaService.registrarTarjeta(nuevaTarjeta);
        
        attributes.addFlashAttribute("mensajeExito", "Tarjeta " + nuevaTarjeta.getTipoTarjeta() + " registrada.");
        return "redirect:/partidos/" + idPartido + "/tarjetas";
    }
    
    @GetMapping("/partidos/tarjetas/eliminar/{idTarjeta}")
    public String eliminarTarjeta(@PathVariable Long idTarjeta, RedirectAttributes attributes) {
        
        Long idPartido = tarjetaService.buscarPorId(idTarjeta)
                .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"))
                .getIdPartido();
                
        tarjetaService.eliminarTarjeta(idTarjeta);
        
        attributes.addFlashAttribute("mensajeError", "Registro de tarjeta eliminado.");
        return "redirect:/partidos/" + idPartido + "/tarjetas";
    }
}