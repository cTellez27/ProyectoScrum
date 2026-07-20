package com.torneos.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.torneos.dto.TorneoDTO; // Necesitamos el repo para guardar el estado
import com.torneos.servicio.TorneoService;

@Controller
@RequestMapping("/torneos")
public class TorneoController {

    @Autowired
    private TorneoService torneoService;

    @GetMapping
    public String listarTorneos(Model model) {
        model.addAttribute("torneos", torneoService.listarTorneos());
        return "torneo/lista-torneos"; 
    }

    @GetMapping("/{idTorneo}")
    public String verDashboardTorneo(@PathVariable Long idTorneo, Model model) {
        Optional<TorneoDTO> torneoOpt = torneoService.buscarPorId(idTorneo);
        
        if (torneoOpt.isPresent()) {
            model.addAttribute("torneo", torneoOpt.get());
            return "torneo/dashboard-torneo"; 
        } else {
            return "redirect:/torneos"; 
        }
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("torneo", new TorneoDTO());
        return "torneo/form-torneo"; 
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<TorneoDTO> torneoOpt = torneoService.buscarPorId(id);
        if (torneoOpt.isPresent()) {
            model.addAttribute("torneo", torneoOpt.get());
            return "torneo/form-torneo";
        } else {
            return "redirect:/torneos";
        }
    }

    @PostMapping("/guardar")
    public String guardarTorneo(@ModelAttribute TorneoDTO torneoDTO) {
        torneoService.guardarTorneo(torneoDTO);
        return "redirect:/torneos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarTorneo(@PathVariable Long id) {
        torneoService.eliminarTorneo(id);
        return "redirect:/torneos";

    }
}