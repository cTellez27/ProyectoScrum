package com.torneos.controller;

import com.torneos.dto.CanchaDTO;
import com.torneos.dto.EquipoDTO;
import com.torneos.dto.PartidoDTO;
import com.torneos.dto.TorneoDTO;
import com.torneos.servicio.CanchaService;
import com.torneos.servicio.PartidoService;
import com.torneos.servicio.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/partidos")
public class PartidoController {

    @Autowired
    private PartidoService partidoService;
    @Autowired
    private TorneoService torneoService;
    @Autowired
    private CanchaService canchaService;


    private boolean esEspectador() {
        org.springframework.security.core.Authentication auth = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth instanceof org.springframework.security.authentication.AnonymousAuthenticationToken) {
            return true;
        }
        return auth.getAuthorities().stream().noneMatch(a -> 
            a.getAuthority().equals("ORGANIZADOR") || a.getAuthority().equals("ADMIN") || a.getAuthority().equals("ADMINISTRADOR"));
    }

    @GetMapping
    public String gestionarPartidosGeneral(Model model) {
        List<TorneoDTO> torneos = torneoService.listarTorneos();
        if (!torneos.isEmpty()) {
            return "redirect:/partidos/torneo/" + torneos.get(0).getId();
        }
        return "redirect:/torneos";
    }

    @GetMapping("/pendientes")
    public String listarPartidosPendientes(Model model) {
        model.addAttribute("partidosPendientes", partidoService.listarPartidosPendientes());
        
        if (esEspectador()) {
        	return "espectador/partido/lista-pendientes";
        }
        return "partido/lista-pendientes";
    }

    @GetMapping("/torneo/{idTorneo}")
    public String gestionarPartidos(@PathVariable Long idTorneo, Model model) {
        
        cargarDatosParaLaVista(idTorneo, model);
        model.addAttribute("partidosDelTorneo", partidoService.listarPartidosPorTorneo(idTorneo));
        model.addAttribute("nuevoPartido", new PartidoDTO());
        
        
        if (esEspectador()) {
        	return "espectador/partido/lista-programados";
        }
        
        return "partido/gestion-partidos";
    }


    @PostMapping("/torneo/{idTorneo}/guardar")
    public String guardarPartido(@PathVariable Long idTorneo, 
                                 @ModelAttribute("nuevoPartido") PartidoDTO partidoDTO) {
        
        partidoService.guardarPartido(idTorneo, partidoDTO);
        return "redirect:/partidos/torneo/" + idTorneo;
    }

    @PostMapping("/torneo/{idTorneo}/registrar-resultado")
    public String registrarResultado(@PathVariable Long idTorneo,
                                     @RequestParam Long idPartido,
                                     @RequestParam String golesLocal,
                                     @RequestParam String golesVisitante,
                                     RedirectAttributes redirectAttributes) {
                                         
        String resultado = golesLocal + "-" + golesVisitante;
        partidoService.registrarResultado(idPartido, resultado);
        
        redirectAttributes.addFlashAttribute("mensaje", "Resultado (" + resultado + ") registrado con éxito.");
        return "redirect:/partidos/torneo/" + idTorneo;
    }
    

    @GetMapping("/torneo/{idTorneo}/eliminar/{idPartido}")
    public String eliminarPartido(@PathVariable Long idTorneo,
                                  @PathVariable Long idPartido,
                                  RedirectAttributes redirectAttributes) {
                                      
        partidoService.eliminarPartido(idPartido);
        redirectAttributes.addFlashAttribute("mensaje", "Partido eliminado");
        return "redirect:/partidos/torneo/" + idTorneo;
    }
    

    private void cargarDatosParaLaVista(Long idTorneo, Model model) {
        TorneoDTO torneo = torneoService.buscarPorId(idTorneo)
                .orElseThrow(() -> new RuntimeException("Torneo no encontrado"));
        
        Set<EquipoDTO> equiposInscritos = torneoService.obtenerEquiposInscritos(idTorneo);
        List<CanchaDTO> canchasDisponibles = canchaService.listarCanchas();

        model.addAttribute("torneo", torneo);
        model.addAttribute("equiposInscritos", equiposInscritos);
        model.addAttribute("canchasDisponibles", canchasDisponibles);
    }
}