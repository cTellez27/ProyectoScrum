package com.torneos.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.torneos.dto.EquipoDTO;
import com.torneos.servicio.EquipoService;

@Controller
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public String listarTodosLosEquipos(Model model) {
        model.addAttribute("equipos", equipoService.listarEquipos());

        return "equipo/lista-equipos"; 
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("equipo", new EquipoDTO());

        return "equipo/form-equipo"; 
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        
        Optional<EquipoDTO> equipoDTO = equipoService.buscarEquipoPorId(id);
        
        if (equipoDTO.isPresent()) {
            model.addAttribute("equipo", equipoDTO.get());

            return "equipo/form-equipo"; 
        } else {
            return "redirect:/equipos";
        }
    }

    @PostMapping("/guardar")
    public String guardarEquipo(@ModelAttribute EquipoDTO equipoDTO) {
        equipoService.guardarEquipo(equipoDTO);
        return "redirect:/equipos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEquipo(@PathVariable Long id, RedirectAttributes attributes) {
        try {

            equipoService.eliminarEquipo(id);
            attributes.addFlashAttribute("mensajeExito", "Equipo eliminado permanentemente del sistema.");
            
        } catch (DataIntegrityViolationException e) {
           
            attributes.addFlashAttribute("mensajeError", "¡No se puede eliminar! El equipo está en uso (inscrito en un torneo, tiene jugadores o partidos).");
            
        } catch (RuntimeException e) {
            
            attributes.addFlashAttribute("mensajeError", e.getMessage());
        }
        
        
        return "redirect:/equipos";
    }
}