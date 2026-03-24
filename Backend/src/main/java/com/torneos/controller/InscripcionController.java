package com.torneos.controller;

import com.torneos.dto.EquipoDTO;
import com.torneos.dto.TorneoDTO;
import com.torneos.servicio.EquipoService;
import com.torneos.servicio.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/torneos/{idTorneo}/equipos") 
public class InscripcionController {

    @Autowired
    private TorneoService torneoService;
    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public String gestionarEquiposDelTorneo(@PathVariable Long idTorneo, Model model) {
        
        TorneoDTO torneo = torneoService.buscarPorId(idTorneo)
                .orElseThrow(() -> new RuntimeException("Torneo no encontrado con ID: " + idTorneo));
        
        Set<EquipoDTO> equiposInscritos = torneoService.obtenerEquiposInscritos(idTorneo);
        
        model.addAttribute("equiposNoInscritos", equipoService.obtenerEquiposNoInscritos(idTorneo));
        model.addAttribute("torneoNombre", torneo.getNombre());
        model.addAttribute("torneoId", idTorneo);
        model.addAttribute("equipos", equiposInscritos); 

        return "inscripcion/gestion-equipos";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCreacion(@PathVariable Long idTorneo, Model model) {
        String nombreTorneo = torneoService.buscarPorId(idTorneo)
            .orElseThrow(() -> new RuntimeException("Torneo no encontrado")).getNombre();
            
        model.addAttribute("equipo", new EquipoDTO());
        model.addAttribute("torneoId", idTorneo);
        model.addAttribute("torneoNombre", nombreTorneo);
        
        return "equipo/form-equipo"; 
    }
    
    @PostMapping("/guardar-y-asociar")
    public String guardarYAsociarEquipo(@PathVariable Long idTorneo, 
                                        @ModelAttribute EquipoDTO equipoDTO,
                                        RedirectAttributes redirectAttributes) {

        EquipoDTO nuevoEquipo = equipoService.guardarEquipo(equipoDTO);
        
        torneoService.inscribirEquipoEnTorneo(idTorneo, nuevoEquipo.getId());
        
        redirectAttributes.addFlashAttribute("mensaje", "Equipo '" + nuevoEquipo.getNombre() + "' creado e inscrito exitosamente.");
        return "redirect:/torneos/" + idTorneo + "/equipos";
    }

    @PostMapping("/inscribir")
    public String inscribirEquipo(@PathVariable Long idTorneo, 
                                  @RequestParam Long equipoId,
                                  RedirectAttributes redirectAttributes) {
        
        torneoService.inscribirEquipoEnTorneo(idTorneo, equipoId);
        
        redirectAttributes.addFlashAttribute("mensaje", "Equipo inscrito exitosamente.");
        return "redirect:/torneos/" + idTorneo + "/equipos";
    }

    @GetMapping("/desinscribir/{equipoId}")
    public String desinscribirEquipo(@PathVariable Long idTorneo, 
                                     @PathVariable Long equipoId,
                                     RedirectAttributes redirectAttributes) {
        
        torneoService.desinscribirEquipoDeTorneo(idTorneo, equipoId);
        
        redirectAttributes.addFlashAttribute("mensaje", "Equipo desinscrito correctamente.");
        return "redirect:/torneos/" + idTorneo + "/equipos";
    }

    @GetMapping("/editar/{equipoId}")
    public String mostrarFormularioEditar(@PathVariable Long idTorneo, @PathVariable Long equipoId, Model model) {
        Optional<EquipoDTO> equipoDTO = equipoService.buscarEquipoPorId(equipoId);
        
        if (equipoDTO.isPresent()) {
            model.addAttribute("equipo", equipoDTO.get());
            model.addAttribute("torneoId", idTorneo);
            model.addAttribute("torneoNombre", torneoService.buscarPorId(idTorneo).get().getNombre());
            return "equipo/form-equipo"; 
        } else {
            return "redirect:/torneos/" + idTorneo + "/equipos";
        }
    }
}