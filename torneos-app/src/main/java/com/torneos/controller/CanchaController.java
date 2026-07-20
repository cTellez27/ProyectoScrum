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

import com.torneos.dto.CanchaDTO;
import com.torneos.servicio.CanchaService;

@Controller
@RequestMapping("/canchas")
public class CanchaController {

    @Autowired
    private CanchaService canchaService;

    @GetMapping
    public String listarCanchas(Model model) {
        model.addAttribute("canchas", canchaService.listarCanchas());
        
        return "cancha/lista-canchas";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("cancha", new CanchaDTO());
        // Busca en: templates/cancha/form-cancha.html
        return "cancha/form-cancha";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<CanchaDTO> canchaOpt = canchaService.buscarPorId(id);
        if (canchaOpt.isPresent()) {
            model.addAttribute("cancha", canchaOpt.get());
            
            return "cancha/form-cancha";
        } else {
            return "redirect:/canchas";
        }
    }

    @PostMapping("/guardar")
    public String guardarCancha(@ModelAttribute CanchaDTO canchaDTO) {
        canchaService.guardarCancha(canchaDTO);
        return "redirect:/canchas";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarCancha(@PathVariable Long id, RedirectAttributes attributes) {
        try {

            canchaService.eliminarCancha(id);

            attributes.addFlashAttribute("mensajeExito", "Cancha eliminada correctamente.");
            
        } catch (DataIntegrityViolationException e) {

            attributes.addFlashAttribute("mensajeError", e.getMessage());
        }
        
        return "redirect:/canchas";
    }
}